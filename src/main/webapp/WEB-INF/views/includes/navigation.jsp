<div class="navbar-fixed">
    <nav class="cyan">
        <div class="nav-wrapper">
            <div class="col s12">
                <a href="<c:url value="/app"/>" class="brand-logo tooltipped" data-position="right" data-tooltip="RankStudio Portal">
                    <img src="<c:url value="/resources/images/rankstudio-logo.png"/>" alt="rankstudio-logo">
                </a>
                <div class="header-search-wrapper hide-on-med-and-down">
                    <i class="material-icons">search</i>
                    <input id="input-busca-header" type="text" name="busca" class="header-search-input z-depth-2" placeholder="Pesquisar Est&uacute;dios">
                </div>
                <ul class="right hide-on-med-and-down">
<%--                    <li>
                        <a href="#" class="waves-effect waves-block waves-light tooltipped"
                           data-position="bottom" data-tooltip="Meus Dados">
                            <i class="material-icons">account_circle</i>
                        </a>
                    </li>--%>
                    <li>
                        <a href="<c:url value="/logout"/>" class="waves-effect waves-block waves-light tooltipped"
                            data-position="bottom" data-tooltip="Sair">
                            <i class="material-icons">exit_to_app</i>
                        </a>
                    </li>
                </ul>
            </div>
        </div>

         <%--Menu exibido apenas para proprietarios de estudios--%>

        <c:url var="urlPaginaUsuario" value="/usuario/info"/>
        <c:url var="urlPaginaEmpresa" value="/empresa/info"/>
        <c:url var="urlPaginaEstudios" value="/estudio/info"/>
<%--            <c:url var="urlPaginaEquipamentos" value="/equipamentos/"/>
        <c:url var="urlPaginaCondicoesComerciais" value="/cond-comerciais/"/>
        <c:url var="urlPaginaAgenda" value="/agenda/"/>
        <c:url var="urlPaginaAvaliacoes" value="/avaliacoes/"/>--%>

        <nav id="horizontal-nav" class="white hide-on-med-and-down">
            <div class="nav-wrapper">
                <ul id="nav-mobile" class="left hide-on-med-and-down">

                    <li id="link-usuario">
                        <a href="${urlPaginaUsuario}" class="cyan-text">
                            <i class="material-icons">account_circle</i>
                            <span>Meus Dados</span>
                        </a>
                    </li>

                    <c:if test="${authUser.perfil.id==2}">

                    <li id="link-empresa">
                        <a href="${urlPaginaEmpresa}" class="cyan-text">
                            <i class="material-icons">business</i>
                            <span>Empresa</span>
                        </a>
                    </li>
                    <li id="link-estudio">
                        <a href="${urlPaginaEstudios}" class="cyan-text">
                            <i class="material-icons">album</i>
                            <span>Est&uacute;dios</span>
                        </a>
                    </li>
                    <%--<li>
                        <a href="${urlPaginaEquipamentos}" class="cyan-text">
                            <i class="material-icons">build</i>
                            <span>Equipamentos</span>
                        </a>
                    </li>
                    <li>
                        <a href="${urlPaginaCondicoesComerciais}" class="cyan-text">
                            <i class="material-icons">description</i>
                            <span>Condi&ccedil;&otilde;es Comerciais</span>
                        </a>
                    </li>
                    <li>
                        <a href="${urlPaginaAgenda}" class="cyan-text">
                            <i class="material-icons">event</i>
                            <span>Agenda</span>
                        </a>
                    </li>
                    <li>
                        <a href="${urlPaginaAvaliacoes}" class="cyan-text">
                            <i class="material-icons">comment</i>
                            <span>Avalia&ccedil;&otilde;es</span>
                        </a>
                    </li>--%>
                    </c:if>
                </ul>
            </div>
        </nav>

    </nav>
</div>