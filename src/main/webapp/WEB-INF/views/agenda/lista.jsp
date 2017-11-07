<%@taglib tagdir="/WEB-INF/tags" prefix="customTags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<customTags:painelTemplate title="Gerenciar Agendas" pagina="estudio" tamanhoColunas="s12">

	<c:if test="${estudio!=null}">

		<h4 class="deep-purple-text caption-uppercase" style="margin-top: 25px; padding-left: 12px">${estudio.nome}</h4>

		<c:if test="${empty agendas}">
			<p>N&atilde;o existem agendas cadastradas.</p>
		</c:if>

		<c:forEach var="agenda" items="${agendas}">
			<div class="col s4 m4 l4" style="margin-bottom: 10px !important;">
				<div class="card-panel hoverable">

					<ul>
						<h4 class="deep-purple-text caption-uppercase">${agenda.sala}</h4>
						<li><strong>Data: </strong>${agenda.data}</li>
						<li><strong>Hor&aacute;rio: </strong>${agenda.horario}</li>
					</ul>

					<div id="actions">

						<c:url var="urlEditarAgenda" value="/estudio/${estudio.id}/agenda/${agenda.id}"/>

						<ul>
							<li class="inline">
								<a href="${urlEditarAgenda}" class="btn btn-floating btn-medium orange lighten-1 tooltipped hoverable" data-position="top" data-tooltip="Editar Informa&ccedil;&otilde;es" data-delay="50">
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

	<c:url var="urlFormularioAgenda" value="/estudio/${estudio.id}/agenda/novo"/>

	<span>Clique no bot&atilde;o ao lado para cadastrar uma nova agenda.
		<a href="${urlFormularioAgenda}" class="btn btn-floating btn-medium deep-purple tooltipped" data-position="right" data-tooltip="Cadastrar Agenda" data-delay="50">
			<i class="material-icons large">add</i>
		</a>
	</span>

</customTags:painelTemplate>