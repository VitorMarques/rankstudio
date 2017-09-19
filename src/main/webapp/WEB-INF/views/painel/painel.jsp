<%@taglib tagdir="/WEB-INF/tags" prefix="customTags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<customTags:painelTemplate title="" pagina="Painel de Controle">

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

						<c:url var="urlEditarEstudio" value="/estudio/${estudio.id}/edita"/>
						<c:url var="urlListaEquipamentos" value="/estudio/${estudio.id}/equipamento/info"/>

						<ul>
							<li class="inline">
								<a href="#" class="btn btn-floating btn-medium blue tooltipped hoverable" data-position="top" data-tooltip="Ver Detalhes" data-delay="50">
									<i class="material-icons large">search</i>
								</a>
							</li>
								<%--						<li class="inline">
                                                            <a href="${urlEditarEstudio}" class="btn btn-floating btn-medium orange lighten-1 tooltipped hoverable" data-position="top" data-tooltip="Editar Informa&ccedil;&otilde;es" data-delay="50">
                                                                <i class="material-icons large">edit</i>
                                                            </a>
                                                        </li>
                                                        <li class="inline">
                                                            <a href="${urlListaEquipamentos}" class="btn btn-floating btn-medium green tooltipped hoverable" data-position="top" data-tooltip="Gerenciar Equipamentos" data-delay="50">
                                                                <i class="material-icons large">build</i>
                                                            </a>
                                                        </li>
                                                        <li class="inline">
                                                            <a href="#" class="btn btn-floating btn-medium red darken-1 tooltipped hoverable" data-position="top" data-tooltip="Incluir Condi&ccedil;&otilde;es Comerciais" data-delay="50">
                                                                <i class="material-icons large">description</i>
                                                            </a>
                                                        </li>
                                                        <li class="inline">
                                                            <a href="#" class="btn btn-floating btn-medium pink lighten-2 tooltipped hoverable" data-position="top" data-tooltip="Cadastrar Agenda" data-delay="50">
                                                                <i class="material-icons large">event</i>
                                                            </a>
                                                        </li>--%>
						</ul>
					</div>

				</div>
			</div>
		</c:forEach>
	</c:if>
</customTags:painelTemplate>