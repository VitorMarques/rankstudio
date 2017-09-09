package br.com.tcc.rankstudio.exception;

public class UsuarioExistenteException extends RuntimeException {

    public UsuarioExistenteException(String mensagem) {
        super(mensagem);
    }
}
