<%@taglib tagdir="/WEB-INF/tags" prefix="customTags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<customTags:painelTemplate title="" pagina="Painel de Controle" tamanhoColunas="s12">

	<c:if test="${empty estudios}">
		<h5 class="text-field deep-purple-text center-align" style="text-transform: uppercase">Ainda n&atilde;o existem Est&uacute;dios cadastrados no sistema!</h5>
	</c:if>

	<c:if test="${!empty estudios}">
		<h5 class="text-field deep-purple-text center-align" style="text-transform: uppercase">Est&uacute;dios cadastrados recentemente</h5>
		<div class="divider"></div>
		<br>
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

						<c:url var="urlDetalhesEstudio" value="/estudio/${estudio.id}/detalhes"/>

						<ul>
							<li class="inline">
								<a href="${urlDetalhesEstudio}" class="btn btn-floating btn-medium blue tooltipped hoverable" data-position="top" data-tooltip="Ver Detalhes" data-delay="50">
									<i class="material-icons large">search</i>
								</a>
							</li>

						</ul>
					</div>

				</div>
			</div>
		</c:forEach>
	</c:if>
</customTags:painelTemplate>