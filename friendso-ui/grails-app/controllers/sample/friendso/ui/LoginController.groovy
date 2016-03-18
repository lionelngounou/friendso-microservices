package sample.friendso.ui

import grails.plugins.rest.client.*
import com.netflix.hystrix.*
import sample.friendso.ui.user.*
import grails.plugin.springsecurity.SpringSecurityUtils

class LoginController {

	/**
	 * Show the login page.
	 */
	def auth() {
		def config = SpringSecurityUtils.securityConfig
		if (isLoggedIn()) {
			redirect uri: "/${authenticatedUser.aliasName}"
			return
		}
		String postUrl = "${request.contextPath}${config.apf.filterProcessesUrl}"
		[postUrl: postUrl]
	}
}