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
    //$('#dataFim').mask('99/99/9999');
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

    var tipoAgendamento = $( "#tipoAgendamento option:selected" ).text().split("-")[0].trim();
    var salaAgendamento = $('#salaAgendamento').val();
    var dataAgendamento = convertStringToCalendar($('#dataAgendamento').val());
    var horarioAgendamento = $('#horarioAgendamento').val();
    var periodoAgendamento = $('#periodoAgendamento').val();
    var valorAgendamento = $('#valorAgendamento').val();
    var estudioId = $('#estudioId').val();

    var data = {
      tipoAgendamento:tipoAgendamento,
      salaAgendamento:salaAgendamento,
      dataAgendamento:dataAgendamento,
      horarioAgendamento:horarioAgendamento,
      periodoAgendamento:periodoAgendamento,
      valorAgendamento:valorAgendamento,
      estudioId:estudioId
    };

    var url = pageContext + "/estudio/" + estudioId + "/agendamento";

    if(!validaDadosAgendamento(data)) {
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

function geraRelatorio(pageContext, nomeRelatorio, tipoRelatorio) {
    var data = getData();
    var url = getUrl(pageContext, nomeRelatorio)
    $.ajax({
        type: 'POST',
        url: url,
        dataType: 'json',
        data: data,
        success: function(data) {

            if(tipoRelatorio=='GRAFICO')
                geraRelatorioGrafico(data);
            else
                geraRelatorioLista(data);

        },
        error: function(data) {Materialize.toast(data.error, 4000);}
    });
}

function getData() {
    var estudioId = $('#estudio').val();
    var dataIni = $('#dataIni').val();
    var dataFim = $('#dataFim').val();

    var data = {estudioId:estudioId, dataIni:dataIni, dataFim:dataFim};

    if(validaFiltrosRelatorio(data))
        return data;
    else
        alert('Filtro invalido!');
}

function getUrl(pageContext, nomeRelatorio) {
    var resource = '/relatorios'
    return pageContext + resource + '/' + nomeRelatorio;
}

function validaFiltrosRelatorio(data) {
    if(/*(data.estudioId==''||data.estudioId==undefined)
        || */(data.dataIni==''||data.dataIni==undefined)
        || (data.dataFim==''||data.dataFim==undefined))
        return false;
    return true;
}

function geraRelatorioGrafico(data) {
    $('#resultadoRelatorioGrafico').css('display', 'block');

    var labels = [];
    var meses = [];
    var totais = [];
    var movimentacoes = [];
    var datasets = [];
    var backgroundColor = ['rgba(255, 99, 132, 0.2)', 'rgba(54, 162, 235, 0.2)', 'rgba(255, 206, 86, 0.2)', 'rgba(75, 192, 192, 0.2)', 'rgba(153, 102, 255, 0.2)', 'rgba(255, 159, 64, 0.2)'];
    var borderColor = ['rgba(255,99,132,1)', 'rgba(54, 162, 235, 1)', 'rgba(255, 206, 86, 1)', 'rgba(75, 192, 192, 1)', 'rgba(153, 102, 255, 1)', 'rgba(255, 159, 64, 1)'];

    $.each(data, function (index, value) {
        labels[index] = value.mes;
        totais[index] = value.total;
        movimentacoes[index] = value.tipoAgendamento;
    });

    //remove os meses duplicados
    $.each(labels, function(i, el){
        if($.inArray(el, meses) === -1) meses.push(el);
    });

    $.each(movimentacoes, function (index, value) {
        datasets[index] = {
            label: value,
            data: [totais[index]],
            backgroundColor: backgroundColor[index],
            borderColor: borderColor[index],
            borderWidth: 1
        }
    });

    var ctx = $("#myChart");
    var myChart = new Chart(ctx, {
        type: 'bar',
        data: {
            labels: meses,
            datasets: datasets
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero:true
                    }
                }]
            },
            responsive:true,
            maintainAspectRatio:false,
            legend: {position:'top'},
            title: {display:true, text:'Relatorio de Movimentacao dos Estudios'}
        }
    });
}

function geraRelatorioLista(data) {
    var container = $('#resultadoRelatorioLista');
    var tabela = $('#tabelaRelatorioLista');
    container.css('display', 'block');
    var html = '';
    $.each(data, function(index, value) {
        html += '<tr>'
        + '<td>'+value.nome+'</td>'
        + '<td>'+value.endereco+'</td>'
        + '<td>'+value.bairro+'</td>'
        + '<td>'+value.cidade+'</td></tr>' ;
    });
    tabela.html(html);
}

function validaDadosAgendamento(data) {
  if((data.tipoAgendamento==''||data.tipoAgendamento==undefined)
    || (data.salaAgendamento==''||data.salaAgendamento==undefined)
    || (data.dataAgendamento==''||data.dataAgendamento==undefined)
    || (data.horarioAgendamento==''||data.horarioAgendamento==undefined)
    || (data.tipoAgendamento==''||data.tipoAgendamento==undefined)
    || (data.periodoAgendamento==''||data.periodoAgendamento==undefined)
    || (data.valorAgendamento==''||data.valorAgendamento==undefined))
    return false;
  return true;
}

function convertStringToCalendar(data) {
    var arrayData = data.split("/");
    var dataCalendar = arrayData[2] + '-' + arrayData[1] + '-' + arrayData[0]; //formato yyyy-mm-dd
    return dataCalendar;
}

function calculaValorAgendamento() {
    valorTotal = ($('#tipoAgendamento').val() * $('#periodoAgendamento').val());
    $('#valorAgendamento').val(valorTotal);
    $('#valor-total').text('Valor Total = R$ ' + valorTotal);
}

function locationReload() {
    window.location.reload();
}
