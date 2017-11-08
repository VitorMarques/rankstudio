<%@taglib tagdir="/WEB-INF/tags" prefix="customTags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<customTags:painelTemplate title="Meus Agendamentos" pagina="agendamento" tamanhoColunas="s12">

	<div id="agendamentos" class="col s12 m12 l12" style="margin-top: 40px">
		<c:if test="${! empty agendamentos}">
			<ul id="lista-agendamentos">
				<c:forEach var="agendamento" items="${agendamentos}" varStatus="loop">
					<div class="col s6 m6 l6">
						<div class="card hoverable">
							<li style="padding: 20px">
								<h5>Est&uacute;dio:  <strong>${estudios[loop.index].nome} </strong></h5>
								<h6>Sala: ${agendamento.salaAgendamento}</h6>
								<h6>Data: ${agendamento.dataAgendamento}</h6>
								<h6>Hor&aacute;rio: ${agendamento.horarioAgendamento}</h6>
								<h6>Horas: ${agendamento.periodoAgendamento}</h6>
								<h6>Valor: R$ ${agendamento.valorAgendamento}</h6>
							</li>
						</div>
					</div>
				</c:forEach>
			</ul>
		</c:if>
		<c:if test="${empty agendamentos}">
			<h5>Voc&ecirc; ainda n&atilde;o realizou nenhum agendamento.</h5>
		</c:if>
	</div>

</customTags:painelTemplate>