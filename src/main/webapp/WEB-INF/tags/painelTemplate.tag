<%@include file="/WEB-INF/views/includes/header.jsp" %>
<%@attribute name="title" required="true" %>
<%@attribute name="exibirBusca" required="true" %>

<div id="app">

    <%@include file="/WEB-INF/views/includes/navigation.jsp" %>

    <div class="container-fluid" style="padding-left: 0px">
        <div class="row">
            <div class="col-md-12">

                <%@include file="/WEB-INF/views/app/menu.jsp"%>

                <div class="col-md-9 col-md-offset-2" style="margin-top: 100px;">

                    <%@include file="/WEB-INF/views/includes/flash-messages.jsp"%>

                    <div class="panel panel-default">
                        <div class="panel-heading">

                            <strong>${title}</strong>

                            <c:if test="${exibirBusca}">
                                <%@include file="/WEB-INF/views/includes/busca.jsp"%>
                            </c:if>

                        </div>

                        <div class="panel-body">
                            <jsp:doBody />
                        </div>

                    </div>
                </div>

            </div>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/views/includes/footer.jsp" %>