<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>
<div class="container">
    <g:each var="category" in="${categories}">
        <div class="col-md-3">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <h3 class="panel-title">${category.name} <small class="pull-right badge"><g:link action="create" controller="advertisement" params="['category':"${category.id}"]">Create Advertisement</g:link> </small></h3>
                </div>
                <div class="panel-body">
                    <ul class="list-unstyled row">
                        <g:each in="${category.subCategories}">
                            <li class="col-sm-6"><a href="${createLink(action:'listAdvertisements', base:"${request.requestURL[0..-2]}", controller:'area', id: it.id)}">${it.name}</a></li>
                        </g:each>
                    </ul>
                </div>
            </div>
        </div>
    </g:each>
</div>

</body>
</html>