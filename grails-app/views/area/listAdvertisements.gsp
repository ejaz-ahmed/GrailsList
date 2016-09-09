<html>
<head>
    <meta name="layout" content="main"/>
</head>
<body>
    <g:if test="${advertisements}">
        <g:each in="${advertisements}" var="listing">
            ${listing.title}
        </g:each>
    </g:if>
    <g:else>
        Nothing right now
    </g:else>
</body>
</html>
