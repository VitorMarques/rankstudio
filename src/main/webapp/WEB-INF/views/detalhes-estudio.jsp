<%@taglib tagdir="/WEB-INF/tags" prefix="customTags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<customTags:indexTemplate title="Detalhes do Estudio" pagina="estudio" tamanhoColunas="s12">

	<spring:eval expression="@environment.getProperty('amazons3context')" var="amazons3context" />

	<c:set var="fotos" value="${estudio.fotosEstudio}" />
	<input type="hidden" id="estudioId" value="${estudio.id}">

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
		<div class="col s6 m6 l6 ">
			<ul>
				<h4 class="deep-purple-text caption-uppercase">Informa&ccedil;&otilde;es</h4>
				<li><strong>Nome: </strong>${estudio.nome}</li>
				<li><strong>CNPJ: </strong>${estudio.cnpj}</li>
				<li><strong>Endereco: </strong>${estudio.endereco}</li>
				<li><strong>Bairro: </strong>${estudio.bairro}</li>
				<li><strong>Cidade: </strong>${estudio.cidade}</li>
				<li><strong>Rank: </strong>${estudio.rank==null?0:estudio.rank}</li>
			</ul>
		</div>
		<div class="col s6 m6 l6">
			<h6>${estudio.descricao}</h6>
		</div>
	</div>

	<div class="divider"></div>

	<div id="condicoes" class="col s12 m12 l12" style="margin-top: 40px">
		<blockquote>Condi&ccedil;&otilde;es Comerciais</blockquote>

		<c:forEach var="condicao" items="${condicoesComerciais}">
			<div class="col s2 m2 l2" style="margin-bottom: 10px !important;">
				<div class="card hoverable" style="padding: 20px">
					<ul>
						<h5>${condicao.tipoCondicao}</h5>
						<li><strong>Pre&ccedil;o: </strong>${condicao.preco}</li>
						<li><strong>Tipo Pagamento: </strong>${condicao.tipoPagamento}</li>
					</ul>
				</div>
			</div>
		</c:forEach>
	</div>

	<div class="divider"></div>

	<div id="agendas" class="col s12 m12 l12" style="margin-top: 40px">
		<blockquote>Agendas Dispon&iacute;veis</blockquote>
		<c:choose>
			<c:when test="${empty agendas}">
				<h5>Este Est&uacute;dio n&atilde;o possui agendas dispon&iacute;veis no momento.</h5>
			</c:when>
			<c:otherwise>
				<c:if test="${authUser!=null && authUser.perfil.id==1}">
					<div class="col s12">
						<a class="btn blue modal-trigger" href="#modalAgendamento">
							Agendar Agora
						</a>
					</div>
				</c:if>
				<c:forEach var="agenda" items="${agendas}">
					<div class="col s2 m2 l2" style="margin-bottom: 10px !important;">
						<div class="card hoverable" style="padding: 20px">
							<ul>
								<h5>${agenda.sala}</h5>
								<li><strong>Data: </strong>${agenda.data}</li>
								<li><strong>Horario: </strong>${agenda.horario}</li>
							</ul>
						</div>
					</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>
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
        <c:if test="${authUser!=null && authUser.perfil.id==1}">
            <div class="col s12">
                <a class="btn blue modal-trigger" href="#modalAvaliacao">
                    Avaliar Agora
                </a>
            </div>
        </c:if>
		<c:if test="${! empty avaliacoes}">
			<ul id="lista-avaliacoes" style="margin-top: calc(4vw);">
				<c:forEach var="avaliacao" items="${avaliacoes}">
					<div class="card hoverable">
						<li style="padding: 20px"><strong>${avaliacao.nomeUsuario} </strong>
							<small>nota: <span class="deep-orange-text">${avaliacao.nota}</span></small> -> ${avaliacao.comentario}</li>
					</div>
				</c:forEach>
			</ul>
		</c:if>
	</div>

	<div class="fixed-action-btn horizontal">
		<a class="btn btn-large white black-text">
			<i class="material-icons amber-text accent-2">star</i>
			<span>Rank do Studio: <span class="red-text"><strong>${estudio.rank}</strong></span><span style="font-size: 20px"> / 5.0</span></span>
		</a>
	</div>

	<%--Agendamento Modal--%>
	<div id="modalAgendamento" class="modal">
		<div class="modal-content">
			<h4>Realizar Agendamento: </h4>
			<div class="input-field" style="margin-top: 50px;margin-bottom: 50px;">
				<select name="tipoAgendamento" id="tipoAgendamento" class="input-field" onchange="javascript:calculaValorAgendamento();">
					<option value="">-- Selecione --</option>
					<c:forEach var="condicao" items="${condicoesComerciais}">
						<option value="${condicao.preco}">${condicao.tipoCondicao} - R$ ${condicao.preco}/Hora</option>
					</c:forEach>
				</select>
				<label for="tipoAgendamento">Selecione um Tipo de Agendamento:</label>
			</div>
			<div class="input-field" style="margin-top: 50px;margin-bottom: 50px;">
				<select name="salaAgendamento" id="salaAgendamento" class="input-field" onchange="montaSubCombosAgenda('${pageContext.request.contextPath}', this.value)">
					<option value="">-- Selecione --</option>
					<c:forEach var="agenda" items="${agendas}">
						<option value="${agenda.sala}">${agenda.sala}</option>
					</c:forEach>
				</select>
				<label for="salaAgendamento">Selecione uma sala:</label>
			</div>
			<div class="input-field" style="margin-top: 50px;margin-bottom: 50px;">
				<select name="dataAgendamento" id="dataAgendamento" class="input-field">
					<option value="">-- Selecione --</option>
<%--					<c:forEach var="agenda" items="${agendas}">
						<option value="${agenda.data}">${agenda.data}</option>
					</c:forEach>--%>
				</select>
				<label for="dataAgendamento">Selecione uma data:</label>
			</div>
			<div class="input-field" style="margin-top: 50px;margin-bottom: 50px;">
				<select name="horarioAgendamento" id="horarioAgendamento" class="input-field">
					<option value="">-- Selecione --</option>
<%--					<c:forEach var="agenda" items="${agendas}">
						<option value="${agenda.horario}">${agenda.horario}</option>
					</c:forEach>--%>
				</select>
				<label for="horarioAgendamento">Selecione um hor&aacute;rio:</label>
			</div>
			<div class="input-field" style="margin-top: 50px;margin-bottom: 50px;">
				<select name="periodoAgendamento" id="periodoAgendamento" class="input-field" onchange="javascript:calculaValorAgendamento();">
					<option value="">-- Selecione --</option>
					<option value="1">1 Hora</option>
					<option value="2">2 Horas</option>
					<option value="3">3 Horas</option>
					<option value="4">4 Horas</option>
					<option value="5">5 Horas</option>
				</select>
				<label for="periodoAgendamento">Durante quantas horas gostaria de utilizar o Est&uacute;dio?</label>
			</div>
			<div class="input-field" style="margin-top: 50px;margin-bottom: 50px;">
				<h5 id="valor-total"></h5>
			</div>
			<input type="hidden" id="valorAgendamento" value="">
		</div>
		<div class="modal-footer">
			<a href="#!" class="modal-action modal-close waves-effect waves-green btn-flat" onclick="javascript:realizarAgendamento('${pageContext.request.contextPath}');">Agendar</a>
			<a href="#!" class="modal-action modal-close waves-effect waves-red btn-flat">Cancelar</a>
		</div>
	</div>


	<%--Avaliacao Modal--%>
	<div id="modalAvaliacao" class="modal">
		<div class="modal-content">
			<h4>Avaliar Est&uacute;dio ${estudio.nome}</h4>
			<div class="input-field" style="margin-top: 50px;margin-bottom: 50px;">
				<select name="nota" id="nota" class="input-field">
					<option value="">-- Selecione --</option>
					<option value="1">1 Estrela</option>
					<option value="2">2 Estrelas</option>
					<option value="3">3 Estrelas</option>
					<option value="4">4 Estrelas</option>
					<option value="5">5 Estrelas</option>
				</select>
				<label for="nota">Selecione uma nota:</label>
			</div>
			<div class="input-field">
				<textarea id="comentario" name="comentario" class="materialize-textarea"></textarea>
				<label for="comentario">Deixe seu Coment&aacute;rio:</label>
			</div>
		</div>
		<div class="modal-footer">
			<a href="#!" class="modal-action waves-effect waves-green btn-flat" onclick="javascript:enviaAvaliacao('${pageContext.request.contextPath}');">Enviar Avalia&ccedil;&atilde;o</a>
			<a href="#!" class="modal-action modal-close waves-effect waves-red btn-flat">Cancelar</a>
		</div>
	</div>

</customTags:indexTemplate>
