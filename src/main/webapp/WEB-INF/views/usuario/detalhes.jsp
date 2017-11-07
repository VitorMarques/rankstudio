<%@taglib tagdir="/WEB-INF/tags" prefix="customTags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<customTags:painelTemplate title="Dados do Usu&aacute;rio" pagina="usuario" tamanhoColunas="s12">

	<c:if test="${usuario!=null}">

		<c:url var="urlFormularioUsuario" value="/usuario/${usuario.id}/edita" />

		<ul>
			<li><strong>Nome: </strong>${usuario.nome}</li>
			<li><strong>E-mail: </strong>${usuario.email}</li>
			<li><strong>Endereco: </strong>${usuario.endereco}</li>
			<li><strong>Bairro: </strong>${usuario.bairro}</li>
			<li><strong>Cidade: </strong>${usuario.cidade}</li>
			<li><strong>Perfil: </strong>${usuario.perfil.descricao}</li>
		</ul>

		<p>Deseja Alterar suas informa&ccedil;&otilde;es? Clique ao lado &raquo;
		<a href="${urlFormularioUsuario}" class="btn btn-floating btn-medium deep-purple tooltipped" data-position="right" data-tooltip="Editar Dados Usuario" data-delay="50">
			<i class="material-icons large">edit</i>
		</a>

	</c:if>

</customTags:painelTemplate>