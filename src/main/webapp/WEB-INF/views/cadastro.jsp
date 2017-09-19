<%@taglib tagdir="/WEB-INF/tags" prefix="customTags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<customTags:siteTemplate>

	<c:url var="adicionaClienteUrl" value="/realizar-registro"/>

	<form:form cssClass="cadastro-form" action="${adicionaClienteUrl}" method="post" modelAttribute="usuario">

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
				<i class="material-icons prefix small">location_on</i>
				<form:input path="endereco" required="required" placeholder="Ex.: Avenida Rio Branco, 156"/>
				<label for="endereco" class="center-align">Endere&ccedil;o</label>

				<form:input path="bairro" required="required" placeholder="Bairro"/>
				<form:input path="cidade" required="required" placeholder="Cidade"/>
			</div>
		</div>

		<div class="row margin" style="margin-top: 15px !important; margin-bottom: 5px !important;">
			<div class="col s12">
				<div class="col s6">
					<span>Como deseja acessar nosso sistema?</span>
				</div>
				<div class="col s6">
					<form:radiobutton path="perfil.id" class="with-gap" id="radio-cliente" value="1" checked="checked"/>
					<label for="radio-cliente">Cliente</label>
					<form:radiobutton path="perfil.id" class="with-gap" id="radio-proprietario" value="2" />
					<label for="radio-proprietario">Dono de Est&uacute;dio</label>
				</div>
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

	</form:form>

	<div class="row">
		<div class="input-field col s12 m12 l12">
			<p class="margin medium-small">J&aacute; possui cadastro? <a href="<c:url value="/" />">&nbsp;Entrar</a></p>
		</div>
	</div>

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