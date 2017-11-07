<%@taglib tagdir="/WEB-INF/tags" prefix="customTags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<customTags:painelTemplate title="Detalhes do Estudio" pagina="estudio" tamanhoColunas="s12">

	<spring:eval expression="@environment.getProperty('amazons3context')" var="amazons3context" />

	<c:set var="fotos" value="${estudio.fotosEstudio}" />

	<div id="fotos-estudio" class="col s12 m12 l12">
		<c:forEach var="foto" items="${fotos}">
			<div class="card hoverable col s3 m3 l3" style="max-height: 300px">
					<img src="${amazons3context}${foto.nomeArquivo}" class="materialboxed">
			</div>
		</c:forEach>
	</div>

	<div class="clearfix"></div>
	<br>
	<br>

	<div id="dados-estudio" class="col s12 m12 l12 card hoverable  grey lighten-5">
		<div class="col s4 m4 l4 ">
			<ul>
				<h4 class="deep-purple-text caption-uppercase">Informa&ccedil;&otilde;es</h4>
				<li><strong>Nome: </strong>${estudio.nome}</li>
				<li><strong>CNPJ: </strong>${estudio.cnpj}</li>
				<li><strong>Endereco: </strong>${estudio.endereco}</li>
				<li><strong>Bairro: </strong>${estudio.bairro}</li>
				<li><strong>Cidade: </strong>${estudio.cidade}</li>
				<li><strong>Rank: </strong>${estudio.rank==null?0:estudio.rank}</li>
				<li><strong>Descri&ccedil;&atilde;o: </strong>${estudio.descricao}</li>
			</ul>
		</div>
		<div class="col s4 m4 l4">
			<h4 class="deep-purple-text caption-uppercase">Condi&ccedil;&atilde;o Comercial</h4>
			<c:forEach var="condicao" items="${condicoesComercias}">
			<div class="col s2 m2 l2" style="margin-bottom: 10px !important;">
				<div class="card-panel hoverable">
					<ul>
						<h5>Tipo: ${condicao.tipo}</h5>
						<li><strong>Pre&ccedil;o: </strong>${condicao.preco}</li>
						<li><strong>Tipo Pagamento: </strong>${condicao.tipoPagamento}</li>
					</ul>
				</div>
			</div>
			</c:forEach>
		</div>
		<div class="col s4 m4 l4">
			<h4 class="deep-purple-text caption-uppercase">Agendas</h4>
			<c:forEach var="agenda" items="${agendas}">
				<div class="col s2 m2 l2" style="margin-bottom: 10px !important;">
					<div class="card-panel hoverable">
						<ul>
							<h5>${agenda.sala}</h5>
							<li><strong>Data Inicio: </strong>${agenda.dataInicio}</li>
							<li><strong>Data Fim: </strong>${agenda.dataFim}</li>
						</ul>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>

	<div class="divider"></div>

	<div id="equipamentos" class="col s12 m12 l12" style="margin-top: 40px">
		<blockquote>Equipamentos</blockquote>
		<c:forEach var="equipamento" items="${equipamentos}">
			<div class="col s2 m2 l2" style="margin-bottom: 10px !important;">
				<div class="card hoverable">
					<div class="card-image">
						<img src="${amazons3context}${equipamento.fotoEquipamento.nomeArquivo}" class="materialboxed">
					</div>
					<div class="card-content">
						<ul>
							<h4 class="deep-purple-text caption-uppercase">${equipamento.nome}</h4>
							<li><strong>Tipo Equipamento: </strong>${equipamento.tipoEquipamento}</li>
								<%--<li><strong>N&cir; de S&eacute;rie: </strong>${equipamento.numeroSerie}</li>--%>
							<li><strong>Data de Aquisi&ccedil;&atilde;o: </strong>${equipamento.dataAquisicao}</li>
						</ul>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>

	<div class="clearfix"></div>

	<c:set var="avaliacoes" value="${estudio.avaliacoes}"/>

	<div id="avaliacoes" class="col s12 m12 l12" style="margin-top: 40px">
		<blockquote>Avalia&ccedil;&otilde;es </blockquote>
		<c:if test="${! empty avaliacoes}">
			<ul id="lista-avaliacoes">
				<c:forEach var="avaliacao" items="${avaliacoes}">
					<div class="card hoverable">
						<li style="padding: 20px"><strong>${avaliacao.nomeUsuario} </strong>
							<small>nota: <span class="deep-orange-text">${avaliacao.nota}</span></small> -> ${avaliacao.comentario}</li>
					</div>
				</c:forEach>
			</ul>
		</c:if>
	</div>

	<%-- Action Buttons--%>
	<div class="fixed-action-btn horizontal">
		<a class="btn btn-large white black-text">
			<i class="material-icons amber-text accent-2">star</i>
			<span>Rank do Studio: <span class="red-text"><strong>${estudio.rank}</strong></span><span style="font-size: 20px"> / 5.0</span></span>
		</a>
	</div>


</customTags:painelTemplate>