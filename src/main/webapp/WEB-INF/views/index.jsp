<%@taglib tagdir="/WEB-INF/tags" prefix="customTags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<customTags:siteTemplate>
    <div class="container">
        <div class="row" style="margin-top:80px">
            <div class="col-md-8 col-md-offset-2">
                <%@include file="includes/flash-messages.jsp"%>
                <div class="panel panel-default">
                    <div class="panel-heading">Login</div>

                    <div class="panel-body">

                        <form:form cssClass="form-horizontal" action="${pageContext.request.contextPath}/login" method="post" modelAttribute="usuario">

                            <div class="form-group">
                                <label for="email" class="col-md-4 control-label">E-Mail</label>
                                <div class="col-md-6">
                                    <%--<input id="email" type="email" class="form-control" name="email" value="" required autofocus>--%>
                                    <form:input path="email" cssClass="form-control" type="email" required="required"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="senha" class="col-md-4 control-label">Senha</label>
                                <div class="col-md-6">
                                    <input id="senha" type="password" class="form-control" name="senha" required>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-8 col-md-offset-4">
                                    <div class="col-md-2" style="padding:0">
                                        <button type="submit" class="btn btn-primary">
                                            Entrar
                                        </button>
                                    </div>

                                    <div class="col-md-7 pull-left">
                                        N&atilde;o possui cadastro?
                                        <a class="btn btn-link" href="<c:url value="/registrar" />">
                                            Registre-se!
                                        </a>
                                    </div>

                                </div>
                            </div>

                        </form:form>

                    </div>
                </div>
            </div>
        </div>
    </div>
</customTags:siteTemplate>