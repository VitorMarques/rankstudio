<%@taglib tagdir="/WEB-INF/tags" prefix="customTags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<customTags:painelTemplate title="Formul&aacute;rio de cadastro de Equipamentos" pagina="estudio" tamanhoColunas="s6 offset-s3">

    <c:url var="adicionaEquipamentoUrl" value="/estudio/${estudio.id}/equipamento/"/>

    <form:form cssClass="cadastro-form" action="${adicionaEquipamentoUrl}" method="post" modelAttribute="equipamento">

        <form:input path="id" type="hidden" value="${equipamento.id}"/>
        <form:input path="estudio.id" type="hidden" value="${estudio.id}"/>

        <div class="row margin">
            <div class="input-field col s12">
                <i class="material-icons prefix small">person_outline</i>
                <form:input path="nome" required="required"/>
                <label for="nome" class="center-align">Nome</label>
            </div>
        </div>

        <div class="row margin">
            <div class="input-field col s12">
                <i class="material-icons prefix small">build</i>
                <%--<form:input path="tipoEquipamento" required="required"/>--%>
                <form:select path="tipoEquipamento" required="required">
                    <form:option value="" label="--- Selecionar ---"/>
                    <c:forEach items="${tiposEquipamento}" var="tipoEquipamento">
                        <form:option value="${tipoEquipamento.descricao}" label="${tipoEquipamento.descricao}"/>
                    </c:forEach>
                </form:select>
                <label for="tipoEquipamento" class="center-align">Tipo de Equipamento</label>
            </div>
        </div>

<%--        <div class="row margin">
            <div class="input-field col s12">
                <i class="material-icons prefix small">looks_one</i>
                <form:input path="numeroSerie" required="required" max="7"/>
                <label for="numeroSerie" class="center-align">N&uacute;mero de Serie</label>
            </div>
        </div>--%>

        <div class="row margin">
            <div class="input-field col s12">
                <i class="material-icons prefix small">date_range</i>
                <form:input path="dataAquisicao" required="required"/>
                <label for="dataAquisicao" class="center-align">Data de Aquisi&ccedil;&atilde;o</label>
            </div>
        </div>

        <c:if test="${edita}">
            <div class="row">
                <div class="input-field col s12">
                    <button type="submit" class="btn deep-purple waves-effect waves-light col s12">Atualizar Dados do Equipamento</button>
                </div>
            </div>
        </c:if>
        <c:if test="${!edita}">
            <div class="row">
                <div class="input-field col s12">
                    <button type="submit" class="btn deep-purple waves-effect waves-light col s12">Cadastrar Equipamento</button>
                </div>
            </div>
        </c:if>

    </form:form>


</customTags:painelTemplate>