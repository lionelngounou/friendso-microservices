<!doctype html>
<html>
    <head>
        <meta name="layout" content="main"/>
        <title>login</title>
    </head>
    <body>
        <div class="row"> 
            <div class="col-md-offset-3 col-md-6"> 
                <g:form name="loginForm" url="${postUrl}" useToken="true" autocomplete="off">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <h2>Login</h2>
                            <g:if test="${params.error=='1'}">
                                <div class="alert alert-danger">
                                    Incorrect email and/or password
                                </div>
                            </g:if>
                            <fieldset class="row">
                                <div class="col-md-12 form-group">
                                    <label>Email</label>
                                    <g:field type="email" name="email" class="form-control email" max="254" required="" />
                                </div>
                            </fieldset>
                            <fieldset class="row">
                                <div class="col-md-12 form-group">
                                    <label>Password</label>
                                    <g:passwordField name="password" class="form-control" max="100" required="" />
                                </div>
                            </fieldset>
                            <button type="submit" class="btn btn-primary btn-block">Login
                                <span class="glyphicon glyphicon-menu-right pull-right" aria-hidden="true"></span></button>
                            <a class="btn btn-link btn-block" href="${createLink(uri:'/register')}">Not registered?</a>
                        </div>
                    </div>
                </g:form>
            </div> 
        </div> 
    </body>
</html>
