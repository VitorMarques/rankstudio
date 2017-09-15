<%@taglib tagdir="/WEB-INF/tags" prefix="customTags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<customTags:siteTemplate>
	<form:form cssClass="cadastro-form" action="${pageContext.request.contextPath}/realizar-registro" method="post" modelAttribute="usuario">
		<div class="row">
			<div class="input-field col s12 center">
				<img src="<c:url value="/resources/images/rankstudio-logo.png"/>" alt="" class="circle responsive-img valign profile-image-login">
				<h2 class="center login-form-text">Preencha os dados abaixo para se cadastrar!</h2>
			</div>
		</div>
		<div class="row margin">
			<div class="input-field col s12">
				<i class="material-icons prefix small">person_outline</i>
				<form:input path="nome" required="required"/>
				<label for="nome" class="center-align">Nome</label>
			</div>
		</div>
		<div class="row margin">
			<div class="input-field col s12">
				<i class="material-icons prefix small">email</i>
				<form:input path="email" type="email" required="required"/>
				<label for="email" class="center-align">E-Mail</label>
			</div>
		</div>
		<div class="row margin">
			<div class="input-field col s12">
				<i class="material-icons prefix small">lock_open</i>
				<input id="senha" name="senha" type="password" required>
				<label for="senha">Senha</label>
			</div>
		</div>
		<div class="row margin">
			<div class="input-field col s12">
				<i class="material-icons prefix small">lock_outline</i>
				<input id="confirmar-senha" name="confirmar-senha" type="password" required>
				<label for="confirmar-senha">Confirmar</label>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12">
				<button type="submit" class="btn deep-purple waves-effect waves-light col s12">Registrar</button>
			</div>
		</div>
		<div class="row">
			<div class="input-field col s12 m12 l12">
				<p class="margin medium-small">J&aacute; possui cadastro? <a href="<c:url value="/" />">&nbsp;Entrar</a></p>
			</div>
		</div>
	</form:form>
	<script>
        var senha = document.getElementById("senha");
		var confirmarSenha = document.getElementById("confirmar-senha");

        function validatePassword() {
            if(senha.value != confirmarSenha.value) {
                confirmarSenha.setCustomValidity("As senhas devem ser iguais.");
            } else {
                confirmarSenha.setCustomValidity('');
            }
        }

        senha.onchange = validatePassword;
        confirmarSenha.onkeyup = validatePassword;
	</script>
</customTags:siteTemplate>