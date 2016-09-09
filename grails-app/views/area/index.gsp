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
                    <h3 class="panel-title">${category.name}</h3>
                </div>
                <div class="panel-body">
                    <ul class="list-group">
                        <g:each in="${category.subCategories}">
                            <li class="list-group-item">${it.name}</li>
                        </g:each>
                    </ul>
                </div>
            </div>
        </div>
    </g:each>
</div>

</body>
</html>