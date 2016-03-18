<!doctype html>
<html>
    <head>
        <meta name="layout" content="main"/>
        <title>profile</title>
    </head>
    <body>
        <div class="row"> 
            <div class="col-md-3"> 
                <g:render template="desc" />
            </div> 
            <div class="col-md-6"> 
                <g:render template="/post/recent" />
            </div> 
            <div class="col-md-3"> 
                <g:render template="/friend/recent" />
            </div> 
        </div> 
    </body>
</html>
