$(document).ready(function () {
    $('div.alert').delay(3000).slideUp(500);
    $('#dataAgendamento').on('contentChanged', function() {
        $(this).material_select();
    });
    $('#horarioAgendamento').on('contentChanged', function() {
        $(this).material_select();
    });

    $('.slider').slider({
        indicators: false,
        height: 450,
        interval: 4000
    });

});

$(document).ready(function () {
    var nomeRelatorio = $('#nomeRelatorio').val();
    if(nomeRelatorio==='movimentacoes'
        ||nomeRelatorio==='historiconotas')
        $('#selectEstudioRelatorio').css('display', 'block');
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
    if(nomeDaPagina==='usuario') {
        $('#link-usuario').addClass('item-menu-ativo');
    } else if(nomeDaPagina==='empresa') {
        $('#link-empresa').addClass('item-menu-ativo');
    } else if(nomeDaPagina==='estudio') {
        $('#link-estudio').addClass('item-menu-ativo');
    } else if(nomeDaPagina==='avaliacao') {
        $('#link-avaliacao').addClass('item-menu-ativo');
    } else if(nomeDaPagina==='agendamento') {
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

    if((nota===undefined || nota==='' || nota===null) || (comentario===undefined || comentario==='' || comentario===null)) {
        alert('Favor preencher as informacoes necessarias!');
    } else {
        $.ajax({
            type: 'POST',
            url: url,
            data: data,
            success: function (data) {$('#modalAvaliacao').modal('close');Materialize.toast(data.msg, 3500, '', function reload(){window.location.reload();})},
            error: function (data) {
                Materialize.toast(data.error, 4000);
            }
        });
    }
}

function realizarAgendamento(pageContext) {

    var tipoAgendamento = $("#tipoAgendamento").find("option:selected").text().split("-")[0].trim();
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
        success: function () {Materialize.toast('Seus agendamentos foram atualizados. Clique em seu painel de controle para mais informacoes!', 3500, '', function reload(){window.location.reload();})},
        error: function (data) {
            Materialize.toast(data.error, 4000);
        }
      });
    }
}

function limpaFormAgendamento() {
    $('#salaAgendamento').val(undefined);
    $('#dataAgendamento').val(undefined);
    $('#horarioAgendamento').val(undefined);
    $('#periodoAgendamento').val(undefined);
    $('#valorAgendamento').val(undefined);
    $('#estudioId').val(undefined);
}

function montaSubCombosAgenda(pageContext, agenda) {

    if(agenda==='')
        alert('Favor preencher todos os campos.');
    else {

        var estudioId = $('#estudioId').val();
        var url = pageContext + "/estudio/" + estudioId + "/agenda/" + agenda + "/lista";

        $.ajax({
            type: 'GET',
            url: url,
            success: function (data) {
                $('#dataAgendamento').empty();
                $('#horarioAgendamento').empty();
                $.each(data, function (i, e) {
                    $('#dataAgendamento').append($('<option>', {
                        value: e.data,
                        text : e.data
                    }));
                    $('#horarioAgendamento').append($('<option>', {
                        value: e.horario,
                        text : e.horario
                    }));
                });
                $('#dataAgendamento').trigger('contentChanged');
                $('#horarioAgendamento').trigger('contentChanged');
            },
            error: function (data) {
                Materialize.toast(data.error, 4000);
            }
        });

    }

}

function geraRelatorio(pageContext, nomeRelatorio, tipoRelatorio) {
    var dados = getData();
    var url = getUrl(pageContext, nomeRelatorio);
    $.ajax({
        type: 'POST',
        url: url,
        dataType: 'json',
        data: dados,
        success: function(data) {

            if(nomeRelatorio==='movimentacoes')
                geraRelatorioMovimentacoes(data);
            else if(nomeRelatorio==='clientes')
                geraRelatorioClientes(data);
            else if(nomeRelatorio==='historiconotas')
                geraRelatorioHistoricoNotas(data, pageContext, dados.estudioId);
            else if(nomeRelatorio==='ranks')
                geraRelatorioRanks(data);
            else
                geraRelatorioEstudios(data);

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
    var resource = '/relatorios';
    return pageContext + resource + '/' + nomeRelatorio;
}

function validaFiltrosRelatorio(data) {
    return !((data.dataIni === '' || data.dataIni === undefined)
        || (data.dataFim === '' || data.dataFim === undefined));
}

function getBackgroundColors(index) {
    var backgroundColorsArray = [
        'rgba(255, 99, 132, 0.2)',
        'rgba(54, 162, 235, 0.2)',
        'rgba(255, 206, 86, 0.2)',
        'rgba(75, 192, 192, 0.2)',
        'rgba(153, 102, 255, 0.2)',
        'rgba(255, 159, 64, 0.2)'
    ];
    return backgroundColorsArray[index];
}

function getBorderColor(index) {
    var borderColorsArray = [
        'rgba(255,99,132,1)',
        'rgba(54, 162, 235, 1)',
        'rgba(255, 206, 86, 1)',
        'rgba(75, 192, 192, 1)',
        'rgba(153, 102, 255, 1)',
        'rgba(255, 159, 64, 1)'
    ];
    return borderColorsArray[index];
}

function geraRelatorioMovimentacoes(data) {

    $('#chartContainerDiv').css('display', 'block');

    var dataPointEnsaio = [];
    var dataPointGravacao = [];
    var indiceArrayEnsaio = 0;
    var indiceArrayGravacao = 0;
    var lucroTotalEnsaio = 0;
    var lucroTotalGravacao = 0;

    $.each(data, function (k, v) {
       console.log(data);
       if(v.tipoAgendamento==='Ensaio') {
           dataPointEnsaio[indiceArrayEnsaio] = {
               x: new Date(v.ano,v.mes-1,01), y: v.total
           };
           lucroTotalEnsaio += v.lucro;
           indiceArrayEnsaio++;
       } else if(v.tipoAgendamento==='Gravacao') {
           dataPointGravacao[indiceArrayGravacao] = {
               x: new Date(v.ano,v.mes-1,01), y: v.total
           };
           lucroTotalGravacao += v.lucro;
           indiceArrayGravacao++;
       } else {
           alert('Tipo de movimentacao invalido');
       }
    });

    var chart = new CanvasJS.Chart("chartContainer", {
        animationEnabled: true,
        title:{
            text: "Relatorio de Movimentacao Mensal Por Estudio"
        },
        subtitles:[
            {
                text: "Lucro Ensaios: R$ " + lucroTotalEnsaio + " | Lucro Gravacoes: R$ " + lucroTotalGravacao
            }
        ],
        axisX: {
            valueFormatString: "MMM,YY"
        },
        axisY: {
            title: "Quantidade de Movimentacoes",
            includeZero: false
        },
        legend:{
            cursor: "pointer",
            fontSize: 16,
            itemclick: toggleDataSeries
        },
        toolTip:{
            shared: true
        },
        data: [
            {
                name: "Ensaio",
                type: "line",
                showInLegend: true,
                dataPoints: dataPointEnsaio
            },
            {
                name: "Gravacao",
                type: "line",
                showInLegend: true,
                dataPoints: dataPointGravacao
            }
            ]
    });
    chart.render();

    function toggleDataSeries(e){
        if (typeof(e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
            e.dataSeries.visible = false;
        }
        else{
            e.dataSeries.visible = true;
        }
        chart.render();
    }

}

function toggleDataSeries(e){
    if (typeof(e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
        e.dataSeries.visible = false;
    }
    else{
        e.dataSeries.visible = true;
    }
    chart.render();
}

function geraRelatorioClientes(data) {
    var container = $('#resultadoRelatorioLista');
    container.css('display', 'block');
    var html = '<h5>Relatorio Historico de Clientes</h5>' +
        '<table class="table table-hover table-condensed">\n' +
        '            <thead>\n' +
        '                <tr>\n' +
        '                    <th>Nome</th>\n' +
        '\t\t\t\t\t<th>Bairro</th>\n' +
        '\t\t\t\t\t<th>Servi&ccedil;o</th>\n' +
        '\t\t\t\t\t<th>Horario Agendamento</th>\n' +
        '\t\t\t\t\t<th>Data Agendamento</th>\n' +
        '\t\t\t\t\t<th>Estudio</th>\n' +
        '                </tr>\n' +
        '            </thead>\n' +
        '            <tbody id="tabelaRelatorioLista">';
    $.each(data, function(index, value) {
        html += '<tr>'
            + '<td>'+value.nome+'</td>'
            + '<td>'+value.bairro+'</td>'
            + '<td>'+value.tipoAgendamento+'</td>'
            + '<td>'+value.horarioAgendamento+'</td>'
            + '<td>'+value.dataAgendamento+'</td>'
            + '<td>'+value.nomeEstudio+'</td></tr>' ;
    });
    html += '</tbody></table>';
    container.html(html);
}

function geraRelatorioHistoricoNotas(data, pageContext, estudioId) {

    $('#resultadoRelatorioGrafico').css('display', 'block');

    var meses = [];
    var notas = [];

    $.each(data, function (index, value) {
        meses[index] = value.mes;
        notas[index] = value.notaMedia;
    });

    var dataset = {
        label: "Nota Media",
        data: notas,
        backgroundColor: getBackgroundColors(3),
        borderColor: getBorderColor(0),
        borderWidth: 3,
        fill: true
    };

    var ctx = $("#myChart");
    var myChart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: meses,
            datasets: [dataset]
        },
        options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero:true
                    }
                }]
            },
            tooltips: {
              mode: 'index',
              intersect: 'false'
            },
            hover: {
                mode: 'nearest',
                intersect: 'true'
            },
            responsive:true,
            maintainAspectRatio:false,
            legend: {position:'top'},
            title: {display:true, text:'Relatorio de Historico de Notas do Estudio'}
        }
    });

    geraListaAvaliacoesEstudio(estudioId, pageContext);

}

function geraListaAvaliacoesEstudio(estudioId, pageContext) {

    var container = $('#relatorioNotasLista');
    var url = pageContext + '/relatorios/estudio/'+estudioId+'/avaliacoes';

    $.ajax({
       method:'GET',
       url: url,
       success: function (data) {
           container.css('display', 'block');
           container.html(getHtmlListaAvaliacoes(data));
       },
       error: function (error, status) {
           alert(error);
       }
    });

}

function geraRelatorioRanks(data) {
    var container = $('#resultadoRelatorioLista');
    container.css('display', 'block');
    var html = '<h5>Relatorio dos Melhores Estudios</h5> ' +
        '<table class="table table-hover table-condensed">\n' +
        '            <thead>\n' +
        '                <tr>\n' +
        '                    <th>Nome</th>\n' +
        '\t\t\t\t\t<th>Nota</th>\n' +
        '                </tr>\n' +
        '            </thead><tbody id="tabelaRelatorioLista">';
    $.each(data, function(index, value) {
        html += '<tr>'
            + '<td>'+value.nome+'</td>'
            + '<td>'+value.nota+'</td></tr>' ;
    });
    html += '</tbody></table>';
    container.html(html);
}

function getHtmlListaAvaliacoes(data) {

    var html = '<h5>Avaliacoes: </h5>' +
        '<table class="table table-hover table-condensed">\n' +
        '            <thead>\n' +
        '                <tr>\n' +
        '                    <th>Usuario</th>\n' +
        '\t\t\t\t\t<th>Nota</th>\n' +
        '\t\t\t\t\t<th>Comentario</th>\n' +
        '                </tr>\n' +
        '            </thead><tbody id="tabelaRelatorioLista">';
    $.each(data, function(index, value) {
        html += '<tr>'
            + '<td>'+value.nomeUsuario+'</td>'
            + '<td>'+value.nota+'</td>'
            + '<td>'+value.comentario+'</td>' +
            '</tr>' ;
    });
    html += '</tbody></table>';

    return html;
}

function geraRelatorioEstudios(data) {
    var container = $('#resultadoRelatorioLista');
    container.css('display', 'block');
    var html = '<h5>Relatorio de Estudios Cadastrados</h5>' +
        '<table class="table table-hover table-condensed">\n' +
        '            <thead>\n' +
        '                <tr>\n' +
        '                    <th>Nome</th>\n' +
        '\t\t\t\t\t<th>Endereco</th>\n' +
        '\t\t\t\t\t<th>Bairro</th>\n' +
        '\t\t\t\t\t<th>Cidade</th>\n' +
        '                </tr>\n' +
        '            </thead>\n' +
        '            <tbody id="tabelaRelatorioLista">';
    $.each(data, function(index, value) {
        html += '<tr>'
        + '<td>'+value.nome+'</td>'
        + '<td>'+value.endereco+'</td>'
        + '<td>'+value.bairro+'</td>'
        + '<td>'+value.cidade+'</td></tr>' ;
    });
    html += '</tbody></table>';
    container.html(html);
}

function validaDadosAgendamento(data) {
  return !((data.tipoAgendamento === '' || data.tipoAgendamento === undefined)
      || (data.salaAgendamento === '' || data.salaAgendamento === undefined)
      || (data.dataAgendamento === '' || data.dataAgendamento === undefined)
      || (data.horarioAgendamento === '' || data.horarioAgendamento === undefined)
      || (data.tipoAgendamento === '' || data.tipoAgendamento === undefined)
      || (data.periodoAgendamento === '' || data.periodoAgendamento === undefined)
      || (data.valorAgendamento === '' || data.valorAgendamento === undefined));

}

function convertStringToCalendar(data) {
    var arrayData = data.split("/");
    return arrayData[2] + '-' + arrayData[1] + '-' + arrayData[0];
}

function calculaValorAgendamento() {
    var valorTotal = ($('#tipoAgendamento').val() * $('#periodoAgendamento').val());
    $('#valorAgendamento').val(valorTotal);
    $('#valor-total').text('Valor Total = R$ ' + valorTotal);
}

function locationReload() {
    window.location.reload();
}


/*Compara dois objetos do array de dados pela propriedade y que representa o valor total de vendas*/
function compare(a,b) {
    if (a.y < b.y)
        return -1;
    if (a.y > b.y)
        return 1;
    return 0;
}