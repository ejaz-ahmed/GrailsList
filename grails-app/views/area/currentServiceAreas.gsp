${flash.notavailable}
<g:each var="state" in="${states}">
    ${state.name}
    <g:each in="${state.cities}">
        ${it.name}
    </g:each>
</g:each>