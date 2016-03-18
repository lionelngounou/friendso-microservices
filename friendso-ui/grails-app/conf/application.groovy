
grails.gorm.default.constraints = {
	//println "delagating global constraints to ${delegate?.toString()} for $it"
    '*' (nullable: true, minSize:0)
    notNullOrBlank(nullable: false, blank: false)
}

grails.plugin.springsecurity.active = true //activate or deactivate spring security

grails.plugin.springsecurity.sch.strategyName = org.springframework.security.core.context.SecurityContextHolder.MODE_INHERITABLETHREADLOCAL
grails.plugin.springsecurity.useSessionFixationPrevention = true

grails.plugin.springsecurity.password.bcrypt.logrounds = 10

//grails.plugin.springsecurity.dao.reflectionSaltSourceProperty = 'email'  - not needed with bcrypt

grails.plugin.springsecurity.securityConfigType = "InterceptUrlMap"
def interceptUrlMap = [
   [pattern: '/',               access: ['permitAll']],
   [pattern: '/*',              access: ['permitAll']],
   [pattern: '/assets/**',      access: ['permitAll']],
   [pattern: '/**/js/**',       access: ['permitAll']],
   [pattern: '/**/css/**',      access: ['permitAll']],
   [pattern: '/**/images/**',   access: ['permitAll']],
   [pattern: '/**/favicon.ico', access: ['permitAll']],
   [pattern: '/login',          access: ['permitAll']],
   [pattern: '/login/**',       access: ['permitAll']],
   [pattern: '/signin',       	access: ['permitAll']],
   [pattern: '/signin/**',      access: ['permitAll']],
   [pattern: '/register',       access: ['permitAll']],
   [pattern: '/register/**',    access: ['permitAll']],
   [pattern: '/logout',         access: ['permitAll']],
   [pattern: '/logout/**',      access: ['permitAll']],
   [pattern: '/error',          access: ['permitAll']],
   [pattern: '/dbconsole/**',   access: ['isRole("ADMIN")']],
   [pattern: '/profile/create', access: ['permitAll']],
   [pattern: '/profile/save', access: ['permitAll']],
   [pattern: '/profile/show/**', access: ['permitAll']],
   [pattern: '/profile',     access: ['isFullyAuthenticated()']],
   [pattern: '/profile/edit/**',     access: ['isFullyAuthenticated()']],
   [pattern: '/profile/update/**',     access: ['isFullyAuthenticated()']]
]

if(Environment.current != Environment.PRODUCTION){
   interceptUrlMap << ([pattern: '/fixture/**',   access: ['permitAll']])
}

grails.plugin.springsecurity.interceptUrlMap = interceptUrlMap

//Use https
/*
grails.plugin.springsecurity.secureChannel.definition = [
   '**': 'REQUIRES_SECURE_CHANNEL'
]
*/

grails.plugin.springsecurity.logout.filterProcessesUrl = '/logout'

grails.plugin.springsecurity.auth.loginFormUrl = '/login'
grails.plugin.springsecurity.failureHandler.defaultFailureUrl = '/login?error=1'
//grails.plugin.springsecurity.auth.ajaxLoginFormUrl = '/login'

//grails.plugin.springsecurity.failureHandler.defaultFailureUrl = '/login?login_error=1'
//grails.plugin.springsecurity.failureHandler.ajaxAuthFailUrl = '/login?login_error=1'

grails.plugin.springsecurity.apf.filterProcessesUrl = '/signin'
grails.plugin.springsecurity.apf.usernameParameter = 'email'
grails.plugin.springsecurity.apf.passwordParameter = 'password'

grails.plugin.springsecurity.successHandler.defaultTargetUrl = '/profile'
grails.plugin.springsecurity.logout.afterLogoutUrl = '/'
grails.plugin.springsecurity.useSecurityEventListener = true

grails.plugin.springsecurity.onAbstractAuthenticationFailureEvent = { e, appCtx ->
   println "AbstractAuthentication failed for user $e.authentication.name: $e.exception.message"
}

grails.plugin.springsecurity.onAuthenticationSuccessEvent = { e, appCtx ->
   def userDetails = e.authentication?.principal
   //todo: set user last log in by client
   println "AuthenticationSuccess for user $userDetails"
}

//after full authentication
grails.plugin.springsecurity.onInteractiveAuthenticationSuccessEvent = { e, appCtx ->
   def userDetails = e.authentication?.principal
   println "InteractiveAuthenticationSuccess for user $userDetails"
}

