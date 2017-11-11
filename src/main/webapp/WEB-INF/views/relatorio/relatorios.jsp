<%--suppress ELValidationInJSP --%>
<%@taglib tagdir="/WEB-INF/tags" prefix="customTags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<customTags:painelTemplate title="Relatorio de ${nomeRelatorio}" pagina="Relatorios" tamanhoColunas="s12">

	<div class="col s12 m12 l12">
		<input type="hidden" name="estudioId" id="estudioId" value="${estudio.id}">
		<h5>Selecione um per&iacute;odo para gera&ccedil;&atilde;o do Relat&oacute;rio:</h5>
		<div class="col m3 s3 l3">
			<label for="dataIni">Selecione a Data Inicial</label>
			<input type="date" name="dataIni" id="dataIni">
		</div>
		<div class="col m3 s3 l3">
			<label for="dataFim">Selecione a Data Final</label>
			<input type="date" name="dataFim" id="dataFim">
		</div>
		<div class="col m3 s3 l3">
			<label for="estudio">Selecione o Est&uacute;dio</label>
			<select name="estudio" id="estudio">
				<option value="">-- SELECIONE --</option>
				<c:forEach var="estudio" items="${estudios}">
					<option value="${estudio.id}">${estudio.nome}</option>
				</c:forEach>
			</select>
		</div>
		<div class="col s3 m3 l3">
			<button class="waves-effect waves-light btn btn-large"
					type="button"
					name="button"
					onclick="geraRelatorio('${pageContext.request.contextPath}', '${nomeRelatorio}', '${tipoRelatorio}')"> Gerar Relatorio </button>
		</div>
	</div>
	<div class="col s12 m12 l12" id="resultadoRelatorioLista" style="display:none">
		<h5>Relatorio de Estudios Cadastrados</h5>
		<table class="table table-hover table-condensed">
            <thead>
                <tr>
                    <th>Nome</th>
					<th>Endereco</th>
					<th>Bairro</th>
					<th>Cidade</th>
                </tr>
            </thead>
            <tbody id="tabelaRelatorioLista">

            </tbody>
        </table>
	</div>

	<div class="col s12 m12 l12" id="resultadoRelatorioGrafico" style="display:none">
		<canvas id="myChart" height="400"></canvas>
	</div>

</customTags:painelTemplate>