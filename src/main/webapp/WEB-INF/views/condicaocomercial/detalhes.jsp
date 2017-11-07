<%@taglib tagdir="/WEB-INF/tags" prefix="customTags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<customTags:painelTemplate title="Condi&ccedil;&otilde;es Comerciais" pagina="estudio" tamanhoColunas="s12">

	<c:if test="${estudio!=null}">

		<h4 class="deep-purple-text caption-uppercase" style="margin-top: 25px; padding-left: 12px">${estudio.nome}</h4>

		<c:if test="${empty condicoesComerciais}">
			<p>N&atilde;o existem Condicoes Comerciais cadastradas.</p>
		</c:if>

		<c:forEach var="condicao" items="${condicoesComerciais}">
			<div class="col s4 m4 l4" style="margin-bottom: 10px !important;">
				<div class="card-panel hoverable">

					<ul>
						<h4 class="deep-purple-text caption-uppercase">${condicao.tipoCondicao}</h4>
						<li><strong>Pre&ccedil;o: </strong>${condicao.preco}</li>
						<li><strong>Tipo Pagamento: </strong>${condicao.tipoPagamento}</li>
					</ul>

					<div id="actions">

						<c:url var="urlEditaCondicaoComercial" value="/estudio/${estudioId}/condicao-comercial/${condicao.id}"/>

						<ul>
							<li class="inline">
								<a href="${urlEditaCondicaoComercial}" class="btn btn-floating btn-medium orange lighten-1 tooltipped hoverable" data-position="top" data-tooltip="Editar Informa&ccedil;&otilde;es" data-delay="50">
									<i class="material-icons large">edit</i>
								</a>
							</li>
						</ul>
					</div>

				</div>
			</div>
		</c:forEach>

	</c:if>

	<div class="clearfix"></div>
	<br>

	<c:url var="urlFormularioCondicaoComercial" value="/estudio/${estudio.id}/condicao-comercial/novo"/>

	<span>Clique no bot&atilde;o ao lado para cadastrar uma nova Condicao Comercial.
		<a href="${urlFormularioCondicaoComercial}" class="btn btn-floating btn-medium deep-purple tooltipped" data-position="right" data-tooltip="Cadastrar Condicao Comercial" data-delay="50">
			<i class="material-icons large">add</i>
		</a>
	</span>


</customTags:painelTemplate>