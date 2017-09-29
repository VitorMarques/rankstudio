<%@taglib tagdir="/WEB-INF/tags" prefix="customTags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<customTags:painelTemplate title="Formul&aacute;rio de cadastro de Empresa" pagina="empresa" tamanhoColunas="s6 offset-s3">

    <c:url var="adicionaEmpresaUrl" value="/empresa/"/>

    <form:form cssClass="cadastro-form" action="${adicionaEmpresaUrl}" method="post" modelAttribute="empresa">

        <form:input path="id" type="hidden" value="${empresa.id}"/>
        <form:input path="representante.id" type="hidden" value="${authUser.id}"/>

        <div class="row margin">
            <div class="input-field col s12">
                <i class="material-icons prefix small">person_outline</i>
                <form:input path="nome" required="required"/>
                <label for="nome" class="center-align">Nome Fantasia ou Raz&atilde;o Social</label>
            </div>
        </div>

        <div class="row margin">
            <div class="input-field col s12">
                <i class="material-icons prefix small">assignment_ind</i>
                <form:input path="cnpj" required="required"/>
                <label for="cnpj" class="center-align">CNPJ</label>
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

        <c:if test="${edita}">
            <div class="row">
                <div class="input-field col s12">
                    <button type="submit" class="btn deep-purple waves-effect waves-light col s12">Atualizar Dados da Empresa</button>
                </div>
            </div>
        </c:if>
        <c:if test="${!edita}">
            <div class="row">
                <div class="input-field col s12">
                    <button type="submit" class="btn deep-purple waves-effect waves-light col s12">Cadastrar Empresa</button>
                </div>
            </div>
        </c:if>

    </form:form>

</customTags:painelTemplate>