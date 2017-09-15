<%@taglib tagdir="/WEB-INF/tags" prefix="customTags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<customTags:siteTemplate>
    <form:form cssClass="login-form" action="${pageContext.request.contextPath}/login" method="post" modelAttribute="usuario">
        <div class="row">
            <div class="input-field col s12 center">
                <img src="<c:url value="/resources/images/rankstudio-logo.png"/>" alt="" class="circle responsive-img valign profile-image-login">
                <h2 class="center login-form-text">Seja Bem-Vindo a Plataforma RankStudio</h2>
            </div>
        </div>
        <div class="row margin">
            <div class="input-field col s12">
                <i class="material-icons prefix small">email</i>
                <form:input path="email" type="email" required="required"/>
                <label for="email" class="center-align">E-mail</label>
            </div>
        </div>
        <div class="row margin">
            <div class="input-field col s12">
                <i class="material-icons prefix small">lock_outline</i>
                <input id="senha" name="senha" type="password" required>
                <label for="senha">Senha</label>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12">
                <button type="submit" class="btn deep-purple waves-effect waves-light col s12">Login</button>
            </div>
        </div>
        <div class="row">
            <div class="input-field col s12 m12 l12">
                <p class="margin medium-small">N&atilde;o possui cadastro?<a href="<c:url value="/registrar" />">&nbsp;Registre-se!</a></p>
            </div>
        </div>
    </form:form>
</customTags:siteTemplate>