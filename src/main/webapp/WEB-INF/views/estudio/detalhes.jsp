<%@taglib tagdir="/WEB-INF/tags" prefix="customTags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<customTags:painelTemplate title="Detalhes do Estudio" pagina="estudio" tamanhoColunas="s12">

	<ul>
		<h4 class="deep-purple-text caption-uppercase">${estudio.nome}</h4>
		<li><strong>CNPJ: </strong>${estudio.cnpj}</li>
		<li><strong>Endereco: </strong>${estudio.endereco}</li>
		<li><strong>Bairro: </strong>${estudio.bairro}</li>
		<li><strong>Cidade: </strong>${estudio.cidade}</li>
		<li><strong>Rank: </strong>${estudio.rank==null?0:estudio.rank}</li>

		<h5>Condi&ccedil;&atilde;o Comercial</h5>

		<ul>
			<li><strong>Pre&ccedil;o: </strong>${condicaoComercial.preco}</li>
			<li><strong>Tipo Pagamento: </strong>${condicaoComercial.tipoPagamento}</li>
		</ul>

	</ul>

	<div class="divider"></div>
	<h5>Equipamentos</h5>

	<c:forEach var="equipamento" items="${equipamentos}">
		<div class="col s2 m2 l2" style="margin-bottom: 10px !important;">
			<div class="card-panel hoverable">

				<ul>
					<h4 class="deep-purple-text caption-uppercase">${equipamento.nome}</h4>
					<li><strong>Tipo Equipamento: </strong>${equipamento.tipoEquipamento}</li>
					<%--<li><strong>N&cir; de S&eacute;rie: </strong>${equipamento.numeroSerie}</li>--%>
					<li><strong>Data de Aquisi&ccedil;&atilde;o: </strong>${equipamento.dataAquisicao}</li>
				</ul>

			</div>
		</div>
	</c:forEach>

	<div class="clearfix"></div>
	<div class="divider"></div>
	<h5>Agendas</h5>

	<c:forEach var="agenda" items="${agendas}">
		<div class="col s2 m2 l2" style="margin-bottom: 10px !important;">
			<div class="card-panel hoverable">

				<ul>
					<h4 class="deep-purple-text caption-uppercase">${agenda.sala}</h4>
					<li><strong>Data Inicio: </strong>${agenda.dataInicio}</li>
					<li><strong>Data Fim: </strong>${agenda.dataFim}</li>
				</ul>
			</div>
		</div>
	</c:forEach>

</customTags:painelTemplate>