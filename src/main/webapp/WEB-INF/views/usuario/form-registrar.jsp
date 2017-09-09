<%@taglib tagdir="/WEB-INF/tags" prefix="customTags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<customTags:siteTemplate>
	<div class="container-fluid" style="padding-left: 0px">
		<div class="row">
			<div class="col-md-12">
				<div class="container">
					<div class="row" style="margin-top: 80px;">
						<div class="col-md-8 col-md-offset-2">

							<%@include file="../includes/flash-messages.jsp"%>

							<div class="panel panel-default">
								<div class="panel-heading">Registrar</div>

								<div class="panel-body">
									<form:form cssClass="form-horizontal" action="${pageContext.request.contextPath}/realizar-registro" method="post" modelAttribute="usuario">

										<div class="form-group">
											<label for="nome" class="col-md-4 control-label">Nome</label>
											<div class="col-md-6">
												<form:input path="nome" cssClass="form-control" type="text" required="required"/>
											</div>
										</div>

										<div class="form-group">
											<label for="email" class="col-md-4 control-label">E-Mail</label>
											<div class="col-md-6">
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
											<label for="confirmar-senha" class="col-md-4 control-label">Confirmar Senha</label>
											<div class="col-md-6">
												<input id="confirmar-senha" type="password" class="form-control" name="confirmar-senha" required>
											</div>
										</div>

										<div class="form-group">
											<div class="col-md-1 col-md-offset-4">
												<button type="submit" class="btn btn-primary">
													Enviar
												</button>
											</div>
											<div class="col-md-1" style="margin-left: 10px">
												<a class="btn btn-link" href="<c:url value="/" />">
													Voltar
												</a>
											</div>
										</div>
									</form:form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
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