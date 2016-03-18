<div class="panel panel-default">
    <div class="panel-heading">
        <p><h3><b>${user?.firstname} ${user?.lastname}</b></h3></p>
        <p><a href="javascript:;">#${user?.aliasName}</a></p>
    </div>
    <div class="panel-body">
        <p><h5><span class="glyphicon glyphicon-calendar" aria-hidden="true"></span> Since ${formatDate(date: Date.parse('yyyy-MM-dd', user?.dateCreated), format:'MMMM yyyy')}</h5></p>
        <p><h5><span class="glyphicon glyphicon-link" aria-hidden="true"></span> <a href="#">+ Connect</a></h5></p>
    </div>
</div>