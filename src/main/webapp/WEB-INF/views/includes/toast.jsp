<c:if test="${mensagem!=null}">
    <script>
        Materialize.toast('${mensagem}', 3000);
    </script>
</c:if>
