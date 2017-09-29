<%@taglib tagdir="/WEB-INF/tags" prefix="customTags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<customTags:painelTemplate title="Informa&ccedil;&otilde;es dos Est&uacute;dios Cadastrados" pagina="estudio" tamanhoColunas="s12">

	<c:if test="${empresa==null}">
		<c:url var="urlFormularioEmpresa" value="/empresa/nova"/>

		<p>Parece que voc&ecirc; ainda n&atilde;o possui uma Empresa cadastrada.</p>
		<span>Clique no bot&atilde;o ao lado para realizar o cadastro.
			<a href="${urlFormularioEmpresa}" class="btn btn-floating btn-medium deep-purple tooltipped" data-position="right" data-tooltip="Cadastrar Empresa" data-delay="50">
				<i class="material-icons large">add</i>
			</a>
		</span>
	</c:if>

	<c:if test="${empresa!=null}">

		<c:forEach var="estudio" items="${estudios}">
			<div class="col s3 m3 l3" style="margin-bottom: 10px !important;">
				<div class="card-panel hoverable">

					<ul>
						<h4 class="deep-purple-text caption-uppercase">${estudio.nome}</h4>
						<li><strong>CNPJ: </strong>${estudio.cnpj}</li>
						<li><strong>Endereco: </strong>${estudio.endereco}</li>
						<li><strong>Bairro: </strong>${estudio.bairro}</li>
						<li><strong>Cidade: </strong>${estudio.cidade}</li>
						<li><strong>Rank: </strong>${estudio.rank==null?0:estudio.rank}</li>
					</ul>

					<div id="actions">

						<c:url var="urlEditarEstudio" value="/estudio/${estudio.id}/edita"/>
						<c:url var="urlDetalhesEstudio" value="/estudio/${estudio.id}/detalhes"/>
						<c:url var="urlListaEquipamentos" value="/estudio/${estudio.id}/equipamento/info"/>
						<c:url var="urlListaAgendas" value="/estudio/${estudio.id}/agenda/info"/>
						<c:url var="urlCondicaoComercial" value="/estudio/${estudio.id}/condicao-comercial/info"/>

						<ul>
							<li class="inline">
								<a href="${urlDetalhesEstudio}" class="btn btn-floating btn-medium blue tooltipped hoverable" data-position="top" data-tooltip="Ver Detalhes" data-delay="50">
									<i class="material-icons large">search</i>
								</a>
							</li>
							<li class="inline">
								<a href="${urlEditarEstudio}" class="btn btn-floating btn-medium orange lighten-1 tooltipped hoverable" data-position="top" data-tooltip="Editar Informa&ccedil;&otilde;es" data-delay="50">
									<i class="material-icons large">edit</i>
								</a>
							</li>
							<li class="inline">
								<a href="${urlListaEquipamentos}" class="btn btn-floating btn-medium green tooltipped hoverable" data-position="top" data-tooltip="Equipamentos" data-delay="50">
									<i class="material-icons large">build</i>
								</a>
							</li>
							<li class="inline">
								<a href="${urlCondicaoComercial}" class="btn btn-floating btn-medium red darken-1 tooltipped hoverable" data-position="top" data-tooltip="Condi&ccedil;&otilde;es Comerciais" data-delay="50">
									<i class="material-icons large">description</i>
								</a>
							</li>
							<li class="inline">
								<a href="${urlListaAgendas}" class="btn btn-floating btn-medium pink lighten-2 tooltipped hoverable" data-position="top" data-tooltip="Agenda" data-delay="50">
									<i class="material-icons large">event</i>
								</a>
							</li>
						</ul>
					</div>
				
				</div>
			</div>
		</c:forEach>

		<div class="clearfix"></div>
		<br>

		<c:url var="urlFormularioEstudio" value="/estudio/novo"/>

		<span>Clique no bot&atilde;o ao lado cadastrar um novo est&uacute;dio.
			<a href="${urlFormularioEstudio}" class="btn btn-floating btn-medium deep-purple tooltipped" data-position="right" data-tooltip="Cadastrar Est&uacute;dio" data-delay="50">
				<i class="material-icons large">add</i>
			</a>
		</span>

	</c:if>

</customTags:painelTemplate>