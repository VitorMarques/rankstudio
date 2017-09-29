<%@taglib tagdir="/WEB-INF/tags" prefix="customTags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<customTags:painelTemplate title="Formul&aacute;rio de cadastro de Condi&ccedil;&atilde;o Comercial" pagina="estudio" tamanhoColunas="s6 offset-s3">

    <c:url var="adicionaCondicaoComericalUrl" value="/estudio/${estudio.id}/condicao-comercial/"/>

    <form:form cssClass="cadastro-form" action="${adicionaCondicaoComericalUrl}" method="post" modelAttribute="condicaoComercial">

        <input type="hidden" name="estudioId" value="${estudio.id}">

        <div class="row margin">
            <div class="input-field col s12">
                <i class="material-icons prefix small">attach_money</i>
                <form:input path="preco" required="required"/>
                <label for="preco" class="center-align">Pre&ccedil;o</label>
            </div>
        </div>

        <div class="row margin">
            <div class="input-field col s12">
                <i class="material-icons prefix small">credit_card</i>
                <form:input path="tipoPagamento" required="required"/>
                <label for="tipoPagamento" class="center-align">Tipo Pagamento</label>
            </div>
        </div>

        <c:if test="${edita}">
            <div class="row">
                <div class="input-field col s12">
                    <button type="submit" class="btn deep-purple waves-effect waves-light col s12">Atualizar Condicoes Comerciais</button>
                </div>
            </div>
        </c:if>
        <c:if test="${!edita}">
            <div class="row">
                <div class="input-field col s12">
                    <button type="submit" class="btn deep-purple waves-effect waves-light col s12">Cadastrar Condicao Comercial</button>
                </div>
            </div>
        </c:if>

    </form:form>

</customTags:painelTemplate>