<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>
<div class="container">
    <g:if test="${flash.notavailable}">
        <div class="alert alert-error" style="display: block">${flash.notavailable}</div>
    </g:if>
    <g:each var="state" in="${states}">
        <div class="col-md-3">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <h3 class="panel-title">${state.name}</h3>
                </div>
                <div class="panel-body">
                    <ul class="list-group">
                        <g:each in="${state.cities}">
                            <li class="list-group-item"><a href="http://${it.urlSlug}.localhost:8080">${it.name}</a></li>
                        </g:each>
                    </ul>
                </div>
            </div>
        </div>
    </g:each>
</div>

</body>
</html>