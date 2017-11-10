<div class="navbar-fixed">
    <nav class="cyan">
        <div class="nav-wrapper">
            <div class="col s12">
                <a href="<c:url value="/"/>" class="brand-logo tooltipped" data-position="right" data-tooltip="RankStudio Portal">
                    <img src="<c:url value="/resources/images/rankstudio-logo.png"/>" alt="rankstudio-logo">
                </a>
                <ul class="right hide-on-med-and-down">
                    <li>
                        <a href="<c:url value="/"/>" class="waves-effect waves-block waves-light tooltipped"
                           data-position="bottom" data-tooltip="Voltar para o Site">
                            <i class="material-icons">public</i>
                        </a>
                    </li>

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
        <c:url var="urlPaginaAvaliacoes" value="/usuario/${authUser.id}/avaliacoes"/>
        <c:url var="urlPaginaAgendamentos" value="/usuario/${authUser.id}/agendamentos"/>

        <nav id="horizontal-nav" class="white hide-on-med-and-down">
            <div class="nav-wrapper">
                <ul id="nav-mobile" class="left hide-on-med-and-down">

                    <li id="link-usuario">
                        <a href="${urlPaginaUsuario}" class="cyan-text">
                            <i class="material-icons">account_circle</i>
                            <span>Meus Dados</span>
                        </a>
                    </li>

                    <c:if test="${authUser.perfil.id==1}"> <%--Cliente--%>
                        <li id="link-avaliacao">
                            <a href="${urlPaginaAvaliacoes}" class="cyan-text">
                                <i class="material-icons">insert_comment</i>
                                <span>Minhas Avalia&ccedil;&otilde;es</span>
                            </a>
                        </li>
                        <li id="link-agendamento">
                            <a href="${urlPaginaAgendamentos}" class="cyan-text">
                                <i class="material-icons">access_time</i>
                                <span>Meus Agendamentos</span>
                            </a>
                        </li>
                    </c:if>

                    <c:if test="${authUser.perfil.id==2}"> <%--Proprietario--%>
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
                        <li>
                            <a href="<c:url value="/relatorios/movimentacoes"/>" class="cyan-text">
                                <i class="material-icons">assignment</i>
                                <span>Relat&oacute;rio Movimenta&ccedil;&otilde;es</span>
                            </a>
                        </li>
                        <li>
                            <a href="<c:url value="/relatorios/clientes"/>" class="cyan-text">
                                <i class="material-icons">assignment</i>
                                <span>Relat&oacute;rio Visitas Clientes</span>
                            </a>
                        </li>
                        <li>
                            <a href="<c:url value="/relatorios/historiconotas"/>" class="cyan-text">
                                <i class="material-icons">assignment</i>
                                <span>Relat&oacute;rio Hist&oacute;rico Notas</span>
                            </a>
                        </li>
                    </c:if>

                    <c:if test="${authUser.perfil.id==3}"> <%--Admin--%>
                        <li>
                            <a href="<c:url value="/relatorios/estudios"/>" class="cyan-text">
                                <i class="material-icons">assignment</i>
                                <span>Est&uacute;dios Cadastrados</span>
                            </a>
                        </li>
                        <li>
                            <a href="<c:url value="/relatorios/ranks"/>" class="cyan-text">
                                <i class="material-icons">assignment</i>
                                <span>Relat&oacute;rio TOP 10 Estudios</span>
                            </a>
                        </li>
                    </c:if>

                </ul>
            </div>
        </nav>

    </nav>
</div>
