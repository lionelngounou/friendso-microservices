<div class="container">
    <div class="navbar-header">
      <a href="${createLink(uri:'/')}" class="navbar-brand">
        friend<b class="btn-info disabled">so</b>
      </a>
      <button class="navbar-toggle" type="button" data-toggle="collapse" data-target="#navbar-main">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
    </div>

    <div class="navbar-collapse collapse" id="navbar-main">
      <ul class="nav navbar-nav">
        <li>
          <a href="${createLink(uri:'/post')}"><g:message code="header.post" default="Post" /></a>
        </li>
        <li>
          <a href="${createLink(uri:'/help')}"><g:message code="header.help" default="Help" /></a>
        </li>
      </ul>

      <ul class="nav navbar-nav navbar-right">
          <li class="dropdown">
            <a class="dropdown-toggle main-nav" data-toggle="dropdown" 
            href="#" id="download" aria-expanded="false"><g:message code="header.greeting" args="${[donut]}" default="Hi Donut"/><span class="caret"></span></a>
            <ul class="dropdown-menu " aria-labelledby="download">
              <li><a href="${createLink(controller:'profile', action:'overview')}"><g:message code="header.my_profile" default="My profile" /></a></li>
              <li class="divider"></li>
              <li><a href="${createLink(controller:'post', action:'list')}"><g:message code="header.my_posts" default="My posts" /></a></li>
              <li class="divider"></li>
              <li><a href="${createLink(controller:'friends', action:'list')}"><g:message code="header.my_friends" default="My friends" /></a></li>
              <li class="divider"></li>
              <li><a href="${createLink(uri:'/logout')}"><g:message code="header.logout" default="Logout" /></a></li>
            </ul>
          </li>
          <li class=""><a class="main-nav" href="${createLink(uri:'/register')}" ><g:message code="header.register" default="Register" /></a></li>
          <li class=""><a href="${createLink(uri:'/login')}" ><g:message code="header.login" default="Login" /></a></li>
        <%/*
        <sec:ifLoggedIn>
          <li class="dropdown">
            <a class="dropdown-toggle main-nav" data-toggle="dropdown" 
            href="#" id="download" aria-expanded="false"><g:message code="header.greeting" args="${[sec.loggedInUserInfo(field:'firstname')]}" default="Hi ${sec.loggedInUserInfo(field:'firstname')}"/><span class="caret"></span></a>
            <ul class="dropdown-menu " aria-labelledby="download">
              <li><a href="${createLink(controller:'profile', action:'overview')}"><g:message code="header.my_profile" default="My profile" /></a></li>
              <li class="divider"></li>
              <li><a href="${createLink(controller:'post', action:'list')}"><g:message code="header.my_posts" default="My posts" /></a></li>
              <li class="divider"></li>
              <li><a href="${createLink(controller:'friends', action:'list')}"><g:message code="header.my_friends" default="My friends" /></a></li>
              <li class="divider"></li>
              <li><a href="${createLink(uri:'/logout')}"><g:message code="header.logout" default="Logout" /></a></li>
            </ul>
          </li>
        </sec:ifLoggedIn>
        <sec:ifNotLoggedIn>
          <li><a class="main-nav" href="${createLink(uri:'/register')}" ><g:message code="header.register" default="Register" /></a></li>
          <li><a href="${createLink(uri:'/login')}" ><g:message code="header.login" default="Login" /></a></li>
        </sec:ifNotLoggedIn>
        */%>
      </ul>
    </div>
  </div>
