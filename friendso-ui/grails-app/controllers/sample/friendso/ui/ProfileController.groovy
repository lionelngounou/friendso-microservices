package sample.friendso.ui

import grails.plugins.rest.client.*
import com.netflix.hystrix.*
import sample.friendso.ui.user.*
import org.springframework.http.HttpStatus

class ProfileController {

	private UserServiceClient userRestClient = new UserServiceClient()

	def secUserDetailsService

    def index() { }

    def show(String aliasName) {    
    	def resp = new UserReaderHystrixCommand("findByAliasName", {
	    	userRestClient.findByAliasName(aliasName)
	    }).execute() //hystrix fail silent if needed - getFallback
    	println "\n.......${resp?.json}.......\n\n"
	    if(!resp || resp.statusCode==HttpStatus.NOT_FOUND){ 
	    	flash.message = (!resp)? "profile service unavailable: please try shortly..." : "profile #$userAlias not found"
	    	flash.messageStatus = "warning"
	    	redirect uri:"/"
	    	return 
	    }
	    [user:resp.json]
    }

    def create(){
    	if(!isLoggedIn())
    		return [cmd: flash.command]
    	redirect uri:"/${authenticatedUser.aliasName}"
    }

    def save(RegisterCommand command){
		withForm {
			//check 1st validation errors
			if(command.hasErrors()) {
			   	flash.errors = command.errors
			   	flash.messageStatus = "danger"
				render view: "create", model: [cmd: command]
				return
			}

			def resp = null
			try {
				def user = command.userMap //bcrypt slow encoding - watch for timeout exception
				resp = new UserWriterHystrixCommand("create", {
			    	userRestClient.create user 
			    }).execute() //block until done
			}
			catch(e) { //hystrix fail fast - HystrixRuntimeException
				e.printStackTrace()
				println e.message
				flash.command = command
				flash.message = "Registration currenlty unavailable, please try again... \n ${e.message}"
			   	flash.messageStatus = "danger"
			   	flash.messageCol = 9
				redirect action:"create"
				return
			}			

			//check 2nd - service errors
			def data = resp.json
			println "@@@user created: \n\t >>> $data"
			if(resp.statusCode!=HttpStatus.CREATED && data?.errors){
				data.errors.each {
					if(it.type=="FIELD")
						command.errors.rejectValue(it.target, it.code, it.message)
					else
						command.errors.reject(it.code, it.message)
				}
				render view: "create", model: [cmd: command]
				return
			}
			
			//authenticate and show profile
			redirect uri:"/${data?.aliasName}"
		}.invalidToken {
			redirect uri:"/register"
		}
    }
}
