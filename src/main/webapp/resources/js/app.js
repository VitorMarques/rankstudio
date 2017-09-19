$(document).ready(function () {
    $('div.alert').delay(3000).slideUp(500);
});

$(document).ready(function() {
    $('#cep').mask('99999-999');
    $('#cnpj').mask('99.999.999/9999');
    $('#cpf').mask('999.999.999-99');
    $('#telefone').mask('(99)9999-9999?9');
    $('#dataAquisicao').mask('99/99/9999');
    $('#numeroSerie').mask('9999999');
});

function definePaginaAtiva(nomeDaPagina) {
    if(nomeDaPagina=='usuario') {
        $('#link-usuario').addClass('item-menu-ativo');
    } else if(nomeDaPagina=='empresa') {
        $('#link-empresa').addClass('item-menu-ativo');
    } else if(nomeDaPagina=='estudio') {
        $('#link-estudio').addClass('item-menu-ativo');
    }
}

function confirmarExclusao(nomeCliente, urlExclusao) {

    if(confirm("Deseja realmente excluir o cliente " +nomeCliente+ " ?")) {
        window.location = urlExclusao;
    }
}