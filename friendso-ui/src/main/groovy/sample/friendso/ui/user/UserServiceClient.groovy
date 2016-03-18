package sample.friendso.ui.user

import grails.plugins.rest.client.*

class UserServiceClient {

	private RestBuilder restBuilder = new RestBuilder()
	private static final String BASE_URL = "http://localhost:8089/api/v1/users"
	public static final String CONTENT_TYPE = "application/vnd.org.jfrog.artifactory.security.Group+json"

	def findByEmail(String email){
		println "user client findByEmail::: $email"
	    restBuilder.get(BASE_URL + "?email=$email")
	}

	def findByAliasName(String aliasName){
		println "user client findByAliasName ::: $aliasName"
	    restBuilder.get(BASE_URL + "/$aliasName")
	}

	def create(user){
		println "~~~~~~~~~~ create this user $user for me. with $BASE_URL - thanks"
		def resp = restBuilder.post(BASE_URL, {
			json(user)
		})		
	}

	def update(user){
		
	}

	def delete(user){
		
	}


}