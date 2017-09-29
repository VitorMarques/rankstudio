<%@taglib tagdir="/WEB-INF/tags" prefix="customTags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<customTags:painelTemplate title="Condi&ccedil;&otilde;es Comerciais" pagina="estudio" tamanhoColunas="s12">

	<c:if test="${condicaoComercial==null}">

		<c:url var="urlFormularioCondicaoComercial" value="/estudio/${estudioId}/condicao-comercial/novo"/>

		<p>As condi&ccedil;&otilde;es comerciais deste estudio ainda nao foram cadastradas.</p>
		<span>Clique no bot&atilde;o ao lado para realizar o cadastro.
			<a href="${urlFormularioCondicaoComercial}" class="btn btn-floating btn-medium deep-purple tooltipped" data-position="right" data-tooltip="Cadastrar Condicoes Comerciais" data-delay="50">
				<i class="material-icons large">add</i>
			</a>
		</span>
	</c:if>

	<c:if test="${condicaoComercial!=null}">

		<c:url var="urlEditaCondicaoComercial" value="/estudio/${estudioId}/condicao-comercial/${condicaoComercial.id}"/>

		<ul>
			<li><strong>Pre&ccedil;o: </strong>${condicaoComercial.preco}</li>
			<li><strong>Tipo Pagamento: </strong>${condicaoComercial.tipoPagamento}</li>
		</ul>

		<p>Deseja alterar as condicoes comerciais? Clique ao lado &raquo;
		<a href="${urlEditaCondicaoComercial}" class="btn btn-floating btn-medium deep-purple tooltipped" data-position="right" data-tooltip="Editar Condicoes Comerciais" data-delay="50">
			<i class="material-icons large">edit</i>
		</a>

	</c:if>

</customTags:painelTemplate>