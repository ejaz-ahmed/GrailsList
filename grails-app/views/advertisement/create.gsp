<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
    </head>
    <body>
        <div class="container">
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.advertisement}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.advertisement}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:form action="save">
                <div class="form-group">
                    <label for="subCategory">Subcategory:</label>
                    <g:select class="form-control" name="subCategory" from="${subcategories}" optionValue="name" optionKey="id"/>
                </div>
                <div class="form-group">
                    <label for="title">Title:</label>
                    <g:textField class="form-control" name="title"/>
                </div>
                <div class="form-group">
                    <label for="description">Description:</label>
                    <g:textArea class="form-control" name="description" rows="10"/>
                </div>
                <g:submitButton name="create" class="btn btn-success" value="Submit" />
            </g:form>
        </div>
    </body>
</html>
