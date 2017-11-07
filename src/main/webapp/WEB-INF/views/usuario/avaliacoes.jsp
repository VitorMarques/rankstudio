<%@taglib tagdir="/WEB-INF/tags" prefix="customTags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<customTags:painelTemplate title="Minhas Avalia&ccedil;&otilde;es" pagina="avaliacao" tamanhoColunas="s12">

	<div id="avaliacoes" class="col s12 m12 l12" style="margin-top: 40px">
		<c:if test="${! empty avaliacoes}">
			<ul id="lista-avaliacoes">
				<c:forEach var="avaliacao" items="${avaliacoes}">
					<div class="col s6 m6 l6">
						<div class="card hoverable">
							<li style="padding: 20px">
								<h5>Est&uacute;dio avaliado: <strong>${avaliacao.estudio.nome} </strong></h5>
								<small>nota: <span class="deep-orange-text">${avaliacao.nota}</span></small> -> ${avaliacao.comentario}
							</li>
						</div>
					</div>
				</c:forEach>
			</ul>
		</c:if>
		<c:if test="${empty avaliacoes}">
			<h5>Voc&ecirc; ainda n&atilde;o avaliou nenhum est&uacute;dio.</h5>
		</c:if>
	</div>

</customTags:painelTemplate>