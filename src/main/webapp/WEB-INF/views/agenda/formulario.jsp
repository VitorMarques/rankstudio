<%@taglib tagdir="/WEB-INF/tags" prefix="customTags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<customTags:painelTemplate title="Formul&aacute;rio de cadastro de Agendas" pagina="estudio" tamanhoColunas="s6 offset-s3">

    <c:url var="adicionaAgendaUrl" value="/estudio/${estudio.id}/agenda/"/>

    <form:form cssClass="cadastro-form" action="${adicionaAgendaUrl}" method="post" modelAttribute="agenda">

        <form:input path="id" type="hidden" value="${agenda.id}"/>
        <form:input path="estudio.id" type="hidden" value="${estudio.id}"/>

        <div class="row margin">
            <div class="input-field col s12">
                <i class="material-icons prefix small">room</i>
                <form:select path="sala" required="required">
                    <form:option value="Sala A">Sala A</form:option>
                    <form:option value="Sala B">Sala B</form:option>
                    <form:option value="Sala C">Sala C</form:option>
                    <form:option value="Sala D">Sala D</form:option>
                    <form:option value="Sala E">Sala E</form:option>
                </form:select>
                <label for="sala" class="center-align">Sala</label>
            </div>
        </div>

        <div class="row margin">
            <div class="input-field col s12">
                <i class="material-icons prefix small">date_range</i>
                <form:input path="data" required="required"/>
                <label for="data" class="center-align">Data</label>
            </div>
        </div>

        <div class="row margin">
            <div class="input-field col s12">
                <i class="material-icons prefix small">date_range</i>
                <form:input path="horario" required="required"/>
                <label for="horario" class="center-align">Hor&aacute;rio</label>
            </div>
        </div>

        <c:if test="${edita}">

            <div class="row margin">
                <p>Agenda disponivel? </p>
                <input name="disponivel" type="radio" id="sim" />
                <label for="sim">Sim</label>

                <input name="disponivel" type="radio" id="nao" />
                <label for="nao">Nao</label>
            </div>

            <div class="row">
                <div class="input-field col s12">
                    <button type="submit" class="btn deep-purple waves-effect waves-light col s12">Atualizar Dados da Agenda</button>
                </div>
            </div>
        </c:if>
        <c:if test="${!edita}">
            <div class="row">
                <div class="input-field col s12">
                    <button type="submit" class="btn deep-purple waves-effect waves-light col s12">Cadastrar Agenda</button>
                </div>
            </div>
        </c:if>

    </form:form>

</customTags:painelTemplate>