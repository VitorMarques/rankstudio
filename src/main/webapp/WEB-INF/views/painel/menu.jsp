<div class="nav-side-menu">
    <div class="brand"><i class="fa fa-dashboard fa-md"></i><strong style="color: #3097D1;">&nbsp;&nbsp;${authUser.nome}</strong></div>

    <div class="menu-list">

        <ul id="menu-content" class="menu-content collapse out">

            <li id="lnk-lista-usuarios" class="collapsed active">
                <a href="<c:url value="/app/clientes"/> "><i class="fa fa-users fa-lg"></i> Clientes </a>
            </li>

            <li>
                <a href="#" onclick="event.preventDefault(); document.getElementById('logout-form').submit();">
                    <i class="fa fa fa-sign-out fa-lg"></i> Sair
                </a>
            </li>

            <form id="logout-form" action="<c:url value="/logout" />" method="POST" style="display: none;"></form>

        </ul>
    </div>
</div>
