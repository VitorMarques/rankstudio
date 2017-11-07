/**
 * 
 */
package br.com.tcc.rankstudio.exception;

/**
 * @author Vitor
 *
 */
public class CredencialUsuarioInvalidaException extends RuntimeException {

	private static final long serialVersionUID = -7364977447956380496L;
	
	public CredencialUsuarioInvalidaException(String mensagem) {
		super(mensagem);
	}

}
