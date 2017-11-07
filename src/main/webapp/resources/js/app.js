$(document).ready(function () {
    $('div.alert').delay(3000).slideUp(500);
});

$(document).ready(function() {
    $('#cep').mask('99999-999');
    $('#cnpj').mask('99.999.999/9999');
    $('#cpf').mask('999.999.999-99');
    $('#telefone').mask('(99)9999-9999?9');
    $('#dataAquisicao').mask('99/99/9999');
    $('#dataInicio').mask('99/99/9999');
    $('#dataFim').mask('99/99/9999');
    $('#numeroSerie').mask('9999999');
    $('#data').mask('99/99/9999');
    $('#horario').mask('99:99');
});

function definePaginaAtiva(nomeDaPagina) {
    if(nomeDaPagina=='usuario') {
        $('#link-usuario').addClass('item-menu-ativo');
    } else if(nomeDaPagina=='empresa') {
        $('#link-empresa').addClass('item-menu-ativo');
    } else if(nomeDaPagina=='estudio') {
        $('#link-estudio').addClass('item-menu-ativo');
    } else if(nomeDaPagina=='avaliacao') {
        $('#link-avaliacao').addClass('item-menu-ativo');
    } else if(nomeDaPagina=='agendamento') {
        $('#link-agendamento').addClass('item-menu-ativo');
    }
}

function confirmarExclusao(nomeCliente, urlExclusao) {

    if(confirm("Deseja realmente excluir o cliente " +nomeCliente+ " ?")) {
        window.location = urlExclusao;
    }
}

function enviaAvaliacao(pageContext) {
    var nota = $('#nota').val();
    var comentario = $('#comentario').val();
    var estudioId = $('#estudioId').val();
    var data = {nota:nota, comentario:comentario};
    var url = pageContext + "/estudio/" + estudioId + "/avaliacao";

    if((nota==undefined || nota=='' || nota==null) || (comentario==undefined || comentario=='' || comentario==null)) {
        alert('Favor preencher as informacoes necessarias!');
    } else {
        $.ajax({
            type: 'POST',
            url: url,
            data: data,
            success: function (data) {Materialize.toast(data.msg, 4000);setTimeout(locationReload(), 4000)},
            error: function (data) {
                Materialize.toast(data.error, 4000);
            }
        });
    }
}

function realizarAgendamento(pageContext) {
    //pegar todos os dados da tela
    //pegar id do usuario logado
    //validar se todos os campos estao preenchidos
    //converter dados para JSON
    //criar URL da requisicao
    //fazer     chamada POST via ajax
}

function calculaValorAgendamento() {
    valorTotal = ($('#tipoAgendamento').val() * $('#periodoAgendamento').val());
    $('#valorAgendamento').val(valorTotal);
    $('#valor-total').text('Valor Total = R$ ' + valorTotal);
}

function locationReload() {
    window.location.reload();
}