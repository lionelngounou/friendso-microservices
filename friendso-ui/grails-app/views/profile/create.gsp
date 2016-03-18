<!doctype html>
<g:set var="cmd" value="${cmd}" />
<html>
    <head>
        <meta name="layout" content="main"/>
        <title>register</title>
    </head>
    <body>
        <div class="row"> 
            <div class="col-md-9"> 
                <g:form controller="profile" action="save" useToken="true" >
                    <h1>Register</h1>
                    <g:hasErrors bean="${cmd}">
                        <div class="alert alert-danger">
                            <ul><g:eachError bean="${cmd}"><li>${message(error:it)}</li></g:eachError></ul>
                        </div>
                    </g:hasErrors>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <h3>Contact details</h3>
                            <fieldset class="row">
                                <div class="col-md-6 form-group ${hasErrors(bean:cmd, field:'firstname', 'has-error')}">
                                    <label>First name</label>
                                    <g:textField name="firstname" class="form-control" max="100" required="" value="${cmd?.firstname}" />
                                </div>
                                <div class="col-md-6 form-group ${hasErrors(bean:cmd, field:'lastname', 'has-error')}">
                                    <label>Last name</label>
                                    <g:textField name="lastname" class="form-control" max="100" required="" value="${cmd?.lastname}" />
                                </div>
                            </fieldset>
                        </div>
                    </div>
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <h3>Login details</h3>
                            <fieldset class="row">
                                <div class="col-md-12 form-group ${hasErrors(bean:cmd, field:'email', 'has-error')}">
                                    <label>Email</label>
                                    <g:field type="email" name="email" class="form-control email" max="254" required="" value="${cmd?.email}"/>
                                </div>
                            </fieldset>
                            <fieldset class="row">
                                <div class="col-md-6 form-group ${hasErrors(bean:cmd, field:'password', 'has-error')}">
                                    <label>Password</label>
                                    <g:passwordField name="password" class="form-control" max="100" required="" value="passy1" />
                                </div>
                                <div class="col-md-6 form-group ${hasErrors(bean:cmd, field:'password', 'has-errors')}">
                                    <label class="control-label">Confirm password</label>
                                    <g:passwordField name="password2" class="form-control" max="100" required="" value="passy1"/>
                                </div>
                            </fieldset>
                        </div>
                    </div>
                    <fieldset >
                        <button type="submit" class="btn btn-primary btn-block">Register</button>
                        <a class="btn btn-link btn-block clearfix" href="${createLink(uri:'/login')}">Already registered?</a>
                    </fieldset>
                </g:form>
            </div> 
        </div> 
    </body>
</html>
