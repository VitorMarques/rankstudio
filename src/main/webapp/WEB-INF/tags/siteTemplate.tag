<%@include file="/WEB-INF/views/includes/header.jsp" %>

<body class="cyan" <%--background="<c:url value="/resources/images/login-bg.jpg"/>"--%>>

<div id="app">
    <div id="login-page" class="row">
        <div class="col s12 z-depth-4 card-panel">

                <jsp:doBody />

        </div>
    </div>
</div>

<script type="text/javascript" src="<c:url value="/resources/js/lib/jquery-2.1.4.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/lib/materialize.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/lib/jquery.maskedinput.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/app.js"/>"></script>

<%@include file="/WEB-INF/views/includes/toast.jsp"%>

</body>
</html>