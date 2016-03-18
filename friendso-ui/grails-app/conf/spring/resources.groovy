// Place your Spring DSL code here

//import simpleappwithsecurity.SecurityConfiguration

beans = {
    //webSecurityConfiguration(SecurityConfiguration)
    userDetailsService(sample.friendso.ui.user.SecUserDetailsService)
}