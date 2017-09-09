<%@include file="/WEB-INF/views/includes/header.jsp" %>

<div id="app">

    <%@include file="/WEB-INF/views/includes/navigation.jsp" %>

    <div class="container-fluid" style="padding-left: 0px">
        <div class="row">
            <div class="col-md-12">

                <jsp:doBody />

            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/views/includes/footer.jsp" %>