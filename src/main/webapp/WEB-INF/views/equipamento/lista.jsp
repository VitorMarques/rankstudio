<%@taglib tagdir="/WEB-INF/tags" prefix="customTags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<customTags:painelTemplate title="Gerenciar Equipamentos" pagina="estudio" tamanhoColunas="s12">

	<c:if test="${estudio!=null}">

		<h4 class="deep-purple-text caption-uppercase" style="margin-top: 25px; padding-left: 12px">${estudio.nome}</h4>

		<c:if test="${empty equipamentos}">
			<p>N&atilde;o existem equipamentos cadastrados.</p>
		</c:if>

		<c:forEach var="equipamento" items="${equipamentos}">
			<div class="col s3 m3 l3" style="margin-bottom: 10px !important;">
				<div class="card-panel hoverable">

					<ul>
						<h4 class="deep-purple-text caption-uppercase">${equipamento.nome}</h4>
						<li><strong>Tipo Equipamento: </strong>${equipamento.tipoEquipamento}</li>
						<%--<li><strong>N&cir; de S&eacute;rie: </strong>${equipamento.numeroSerie}</li>--%>
						<li><strong>Data de Aquisi&ccedil;&atilde;o: </strong>${equipamento.dataAquisicao}</li>
					</ul>

					<div id="actions">

						<c:url var="urlEditarEquipamento" value="/estudio/${estudio.id}/equipamento/${equipamento.id}"/>
						<c:url var="urlExcluirEquipamento" value="/estudio/${estudio.id}/equipamento/${equipamento.id}/excluir"/>

						<ul>
							<li class="inline">
								<a href="${urlEditarEquipamento}" class="btn btn-floating btn-medium orange lighten-1 tooltipped hoverable" data-position="top" data-tooltip="Editar Informa&ccedil;&otilde;es" data-delay="50">
									<i class="material-icons large">edit</i>
								</a>
							</li>
							<li class="inline">
								<a href="${urlExcluirEquipamento}" class="btn btn-floating btn-medium red lighten-1 tooltipped hoverable" data-position="top" data-tooltip="Excluir Equipamento" data-delay="50">
									<i class="material-icons large">delete</i>
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

	<c:url var="urlFormularioEquipamento" value="/estudio/${estudio.id}/equipamento/novo"/>

	<span>Clique no bot&atilde;o ao lado para cadastrar um equipamento.
		<a href="${urlFormularioEquipamento}" class="btn btn-floating btn-medium deep-purple tooltipped" data-position="right" data-tooltip="Cadastrar Equipamento" data-delay="50">
			<i class="material-icons large">add</i>
		</a>
	</span>

</customTags:painelTemplate>