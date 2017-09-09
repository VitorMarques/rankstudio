$(document).ready(function () {
    $('div.alert').delay(3000).slideUp(500);
});

$(document).ready(function () {
    $('#dataNascimento').datepicker(
       {
           dateFormat: 'dd-mm-yy',
           changeYear: true,
           changeMonth: true,
           yearRange: '1920:2017',
       }
    );
});

$(document).ready(function(){
    $('#cep').mask('99999-999');
});

function confirmarExclusao(nomeCliente, urlExclusao) {

    if(confirm("Deseja realmente excluir o cliente " +nomeCliente+ " ?")) {
        window.location = urlExclusao;
    }
}