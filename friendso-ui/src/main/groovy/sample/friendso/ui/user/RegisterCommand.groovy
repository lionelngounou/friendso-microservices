package sample.friendso.ui.user


//import grails.plugin.springsecurity.SpringSecurityUtils
import grails.plugin.springsecurity.authentication.dao.NullSaltSource

/** @author lionel stephane ngounou
 */
@groovy.transform.ToString(includeNames=true, includes="email, firstname, lastname, password, password2")
class RegisterCommand implements grails.validation.Validateable {
	
	String email
	String firstname, lastname
	String password, password2

	def saltSource
	def springSecurityService

	String getEncodedPassword(){
		if(!password) return null
		String salt = (saltSource instanceof NullSaltSource)? null : email
		springSecurityService.encodePassword password, salt
	}

	def getUserMap(){
		[email:email, firstname:firstname, lastname:lastname, password:encodedPassword]
	}

	//grails 3 - nullable for all properties
	static boolean defaultNullable(){ true }

	static constraints = {		
		email email: true, shared: "notNullOrBlank", size: 1..255
		firstname shared: "notNullOrBlank", size: 2..100
		lastname shared: "notNullOrBlank", size: 2..100
		password shared: "notNullOrBlank", size: 4..100, validator: passwordValidator
		password2 validator: password2Validator
	}

	static final passwordValidator = { String password, command ->
		if (password==command.email) {
			return 'registerCommand.password.matchEmail'
		}
	}

	static final password2Validator = { value, command ->
		if (command.password != command.password2) {
			return 'registerCommand.password2.passwordMismatch'
		}
	}
}