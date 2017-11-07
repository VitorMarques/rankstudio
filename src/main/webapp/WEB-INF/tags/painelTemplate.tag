<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="c"   uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!--Import Google Icon Font-->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

    <title>RankStudio</title>

    <link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/materialize.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/style.min.css"/>">
    <link rel="stylesheet" href="<c:url value="/resources/css/menu-navegacao.css"/>">

</head>
<%@attribute name="title" required="true" %>
<%@attribute name="pagina" required="true" %>
<%@attribute name="tamanhoColunas" required="true" %>

<body onload="definePaginaAtiva('${pagina}')">

<div id="app">

    <%@include file="/WEB-INF/views/includes/navigation.jsp" %>

    <div class="container white card-panel" style="margin-top: 104px">
        <div class="row">
            <div class="col ${tamanhoColunas}" style="padding: 25px">
                <blockquote>${title}</blockquote>
                <jsp:doBody />
            </div>
        </div>
    </div>

</div>

<script type="text/javascript" src="<c:url value="/resources/js/lib/jquery-2.1.4.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/lib/materialize.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/lib/jquery.maskedinput.min.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/app.js"/>"></script>
<script>
    $(document).ready(function() {
        $('select').material_select();
        $('.materialboxed').materialbox();
        $('#modalAvaliacao').modal({
            complete: function () {
                var nota = $('#nota').val();
                var comentario = $('#comentario').val();
                var estudioId = $('#estudioId').val();
                var data = {nota:nota, comentario:comentario};

                $.ajax({
                  type: 'POST',
                  url: 'avaliacao',
                  data: data,
                  success: function (data) {Materialize.toast(data.msg, 4000);setTimeout(locationReload(), 4000)},
                  error: function (data) {
                      Materialize.toast(data.error, 4000);
                  }
                });

            },
        });
        $('#modalAgendamento').modal({
            complete: realizarAgendamento(),
        });
    });
</script>

<%@include file="/WEB-INF/views/includes/toast.jsp"%>

<%@include file="/WEB-INF/views/includes/footer.jsp"%>

</body>
</html>
