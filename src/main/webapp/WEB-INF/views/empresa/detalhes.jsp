<%@taglib tagdir="/WEB-INF/tags" prefix="customTags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<customTags:painelTemplate title="Informa&ccedil;&otilde;es da Empresa" pagina="empresa">

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

		<c:url var="urlEditaEmpresa" value="/empresa/${empresa.id}/edita"/>

		<ul>
			<li><strong>Nome Representante: </strong>${empresa.representante.nome}</li>
			<li><strong>Nome Empresa: </strong>${empresa.nome}</li>
			<li><strong>CNPJ: </strong>${empresa.cnpj}</li>
			<li><strong>Endereco: </strong>${empresa.endereco}</li>
			<li><strong>Bairro: </strong>${empresa.bairro}</li>
			<li><strong>Cidade: </strong>${empresa.cidade}</li>
		</ul>

		<p>Deseja alterar as informa&ccedil;&otilde;es da Empresa? Clique ao lado &raquo;
		<a href="${urlEditaEmpresa}" class="btn btn-floating btn-medium deep-purple tooltipped" data-position="right" data-tooltip="Editar Dados Empresa" data-delay="50">
			<i class="material-icons large">edit</i>
		</a>


	</c:if>

</customTags:painelTemplate>