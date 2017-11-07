<%@taglib tagdir="/WEB-INF/tags" prefix="customTags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<customTags:painelTemplate title="Atualizar informa&ccedil;&atilde;oes do Usu&aacute;rio" pagina="usuario" tamanhoColunas="s6 offset-s3">

    <c:url var="editaUsuarioUrl" value="/usuario/atualiza"/>

    <form:form cssClass="cadastro-form" action="${editaUsuarioUrl}" method="post" modelAttribute="usuario">

        <form:input path="id" type="hidden" />
        <form:input path="perfil.id" type="hidden" />

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

<%--        <div class="row margin" style="margin-top: 15px !important; margin-bottom: 5px !important;">
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
        </div>--%>

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
                <button type="submit" class="btn deep-purple waves-effect waves-light col s12">Atualizar Dados do Usu&aacute;rio</button>
            </div>
        </div>

    </form:form>

</customTags:painelTemplate>