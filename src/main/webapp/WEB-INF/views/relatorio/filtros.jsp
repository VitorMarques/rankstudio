<%@taglib tagdir="/WEB-INF/tags" prefix="customTags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<customTags:painelTemplate title="" pagina="Relatorios" tamanhoColunas="s12">

	<div class="col s12 m12 l12">
		<h5>Selecione um per&iacute;odo para gera&ccedil;&atilde;o do Relat&oacute;rio:</h5>
		<input type="hidden" name="estudioId" id="estudioId" value="${estudio.id}">
		<input type="date" name="dataIni" id="dataIni">
		<label for="dataIni">Selecione a Data Inicial</label>
		<input type="date" name="dataFim" id="dataFim">
		<label for="dataFim">Selecione a Data Final</label>
		<button class="waves-effect waves-light btn"
			type="button"
			name="button"
			onclick="geraRelatorio('${pageContext.request.contextPath}', '${nomeRelatorio}', '${tipoRelatorio}')"> Gerar Relatorio </button>
	</div>
	<div class="col s12 m12 l12" id="resultadoRelatorioLista" style="display:none">
		<ul id="listaResultado">

		</ul>
	</div>
	<div class="col s12 m12 l12" id="resultadoRelatorioGrafico" style="display:none">
		<canvas id="canvas" width="300" height="300"></canvas>
	</div>

</customTags:painelTemplate>
