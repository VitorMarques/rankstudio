<div class="navbar-fixed">
    <nav class="cyan">
        <div class="nav-wrapper">
            <div class="col s12">
                <a href="<c:url value="/app"/>" class="brand-logo tooltipped" data-position="right" data-tooltip="RankStudio Portal">
                    <img src="<c:url value="/resources/images/rankstudio-logo.png"/>" alt="rankstudio-logo">
                </a>
                <ul class="right hide-on-med-and-down">
                    <li>
                        <a href="<c:url value="/loginPage"/>" class="waves-effect waves-block waves-light"
                            data-position="bottom" data-tooltip="Sair">
                            Entrar
                        </a>
                    </li>
                    <li>
                        <a href="<c:url value="/registrar"/>" class="waves-effect waves-block waves-light">
                            Registrar
                        </a>
                    </li>
                </ul>
            </div>
        </div>
        <nav class="purple lighten-1">
            <form action="<c:url value='/site/estudios/query'/>" method="GET">
                <input id="input-busca-header"
                       type="text"
                       name="busca"
                       class="header-search-input z-depth-2"
                       placeholder="Informe o nome do Est&uacute;dio ou do Bairro"
                       style="width: 60%;float: left;margin-top: 13px;margin-left: 200px;">
                <div class="col s2 m2 l2">
                    <button type="submit" class="btn cyan" style="margin-top: 15px;margin-left: 10px;">Pesquisar</button>
                </div>
            </form>
        </nav>

    </nav>
</div>