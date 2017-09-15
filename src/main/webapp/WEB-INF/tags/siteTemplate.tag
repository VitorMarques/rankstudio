<%@include file="/WEB-INF/views/includes/header.jsp" %>

<body class="cyan" <%--background="<c:url value="/resources/images/login-bg.jpg"/>"--%>>

<div id="app">
    <div id="login-page" class="row">
        <div class="col s12 z-depth-4 card-panel">

                <jsp:doBody />

        </div>
    </div>
</div>

<%@include file="/WEB-INF/views/includes/footer.jsp" %>