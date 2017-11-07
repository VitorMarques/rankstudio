<%@taglib tagdir="/WEB-INF/tags" prefix="customTags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<customTags:painelTemplate title="Formul&aacute;rio de cadastro de Est&uacute;dios" pagina="estudio" tamanhoColunas="s6 offset-s3">

    <c:url var="adicionaEstudioUrl" value="/estudio/"/>

    <form:form cssClass="cadastro-form" action="${adicionaEstudioUrl}" method="post" modelAttribute="estudio" enctype="multipart/form-data">

        <form:input path="id" type="hidden" value="${estudio.id}"/>
        <form:input path="empresa.id" type="hidden" value="${empresa.id}"/>

        <div class="row margin">
            <div class="input-field col s12">
                <i class="material-icons prefix small">person_outline</i>
                <form:input path="nome" required="required"/>
                <label for="nome" class="center-align">Nome</label>
            </div>
        </div>

        <div class="row margin">
            <div class="input-field col s12">
                <i class="material-icons prefix small">insert_comment</i>
                <form:textarea path="descricao" class="materialize-textarea"/>
                <label for="descricao" class="center-align">Descri&ccedil;&atilde;o</label>
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
                <label for="endereco" class="center-align">Endere&ccedil&ccedil;o</label>

                <form:input path="bairro" required="required" placeholder="Bairro"/>
                <form:input path="cidade" required="required" placeholder="Cidade"/>
            </div>
        </div>

        <div class="row margin">
            <div class="input-field col s12 file-field">
                <div class="btn">
                    <span><i class="material-icons small">add_a_photo</i></span>
                    <input type="file" name="files" multiple value="${estudio.fotosEstudio}" />
                </div>
                <div class="file-path-wrapper">
                    <input class="file-path validate" type="text" placeholder="Selecione um ou mais arquivos. No m&aacute;ximo 4!">
                </div>
            </div>
        </div>

        <c:if test="${edita}">
            <div class="row">
                <div class="input-field col s12">
                    <button type="submit" class="btn deep-purple waves-effect waves-light col s12">Atualizar Dados do Est&uacute;dio</button>
                </div>
            </div>
        </c:if>
        <c:if test="${!edita}">
            <div class="row">
                <div class="input-field col s12">
                    <button type="submit" class="btn deep-purple waves-effect waves-light col s12">Cadastrar Est&uacute;dio</button>
                </div>
            </div>
        </c:if>

    </form:form>

</customTags:painelTemplate>