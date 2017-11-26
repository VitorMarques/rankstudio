<%@taglib tagdir="/WEB-INF/tags" prefix="customTags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<customTags:indexTemplate title="" pagina="Painel de Controle" tamanhoColunas="s12">

	<spring:eval expression="@environment.getProperty('amazons3context')" var="amazons3context" />

	<c:if test="${empty estudios}">
		<h5 class="text-field deep-purple-text center-align" style="text-transform: uppercase">Ainda n&atilde;o existem Est&uacute;dios cadastrados no sistema!</h5>
	</c:if>

	<c:if test="${!empty estudios}">

		<c:forEach var="estudio" items="${estudios}">

			<c:url var="urlDetalhesEstudio" value="../../site/estudio/${estudio.id}/detalhes"/>

			<div class="col s3 m3 l3" style="margin-bottom: 10px !important; cursor: pointer" onclick="window.location.href='${urlDetalhesEstudio}'">
				<div class="card hoverable">

					<div class="card-image" style="height: 300px;">
						<img src="${amazons3context}${estudio.fotosEstudio[0].nomeArquivo}" style="max-height: 300px">
					</div>

					<div class="card-content">
						<ul style="white-space: nowrap;">
							<h4 class="deep-purple-text caption-uppercase">${estudio.nome}
								<span class="red-text" style="padding-left: 60px"><small>rank: </small>${estudio.rank}</span>
								<span class="black-text" style="font-size: 20px"> / 5.0</span>
							</h4>
							<li><strong>CNPJ: </strong>${estudio.cnpj}</li>
							<li><strong>Endereco: </strong>${estudio.endereco}</li>
							<li><strong>Bairro: </strong>${estudio.bairro}</li>
							<li><strong>Cidade: </strong>${estudio.cidade}</li>
							<%--<li><strong>Rank: </strong>${estudio.rank==null?0:estudio.rank}</li>--%>
						</ul>
					</div>

					<div class="card-action">
						<div id="actions">

							<ul>
								<li class="inline">
									<a href="${urlDetalhesEstudio}"
									   class="waves-effect waves-light btn btn-medium blue tooltipped hoverable"
									   data-position="top"
									   data-tooltip="Ver Detalhes"
									   data-delay="50"
									   style="width: 100%">
										<%--<i class="material-icons large">search</i>--%> Detalhes
									</a>
								</li>

							</ul>
						</div>
					</div>

				</div>
			</div>
		</c:forEach>
	</c:if>
</customTags:indexTemplate>