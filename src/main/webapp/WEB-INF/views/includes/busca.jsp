<div class="col-md-7 pull-right no-padding">
<%--    <div class="col-md-5 col-md-offset-5">
        <form id="pesquisa-usuarios-form" action="<c:url value="/app/clientes" />" method="post">
            <div class="input-group">
                <input name="textoPesquisa" type="text" class="form-control input-sm" placeholder="Pesquisar" aria-describedby="search-icon" required>
                <span class="input-group-addon" id="search-icon" style="cursor: pointer"
                      onclick="event.preventDefault(); document.getElementById('pesquisa-usuarios-form').submit();">
                    <i class="fa fa-search" aria-hidden="true"></i>
                </span>
            </div>
        </form>
    </div>--%>
    <div class="col-md-2 pull-right no-padding">
        <button class="btn btn-sm btn-primary pull-right" type="button"
                title="Adicionar Usu&aacute;rio"
                onclick="location.href='<c:url value="/app/clientes/novo"/>'">
            <i class="fa fa-plus" aria-hidden="true"></i> Adicionar Cliente
        </button>
    </div>
</div>