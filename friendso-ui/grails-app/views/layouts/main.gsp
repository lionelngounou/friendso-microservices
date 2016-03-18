<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="${session.selectedLanguage ?: 'en'}" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="${session.selectedLanguage ?: 'en'}" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="${session.selectedLanguage ?: 'en'}" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="${session.selectedLanguage ?: 'en'}" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="${session.selectedLanguage ?: 'en'}" class="no-js"><!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <title><g:layoutTitle default="Home"/></title>
        <!-- <meta name="viewport" content="width=device-width, initial-scale=1.0"> -->
        
        <!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"> -->
        <asset:stylesheet src="application.css"/>
        <link href="//maxcdn.bootstrapcdn.com/bootswatch/3.3.2/${params.t ?: 'united'}/bootstrap.min.css" rel="stylesheet">
        <asset:javascript src="application.header.js"/>
        <g:layoutHead/>
    </head>
    <body role="document" >
        <div id="bodyWrapper">
            <div id="bodyWrapperIn">
                <header class="navbar navbar-inverse navbar-fixed-top no-rad" role="navigation" >
                    <g:render template="/layouts/header" />
                </header>
                <section class="container">
                    <g:if test="${flash.message}">
                        <div id="msgRow" class="row"> 
                            <div class="col-md-${flash.messageCol ?: 12}"> 
                                <div class="alert alert-dismissible alert-${flash.messageStatus ?: 'default'}"> 
                                    <button type="button" class="close" data-dismiss="alert">x</button>
                                    ${flash.message}
                                </div> 
                            </div> 
                        </div> 
                    </g:if>
                    <g:layoutBody />
                </section>  
            </div>
        </div>
        <footer class="footer">
            <div class="container">
                <g:render template="/layouts/footer" />
            </div>
        </footer>

        <div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
        
        <!-- <script src="//code.jquery.com/jquery-1.11.2.min.js"></script>-->
        <!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>-->
        
        <asset:javascript src="application.footer.js"/>
        <asset:deferredScripts />
    </body>
</html>

