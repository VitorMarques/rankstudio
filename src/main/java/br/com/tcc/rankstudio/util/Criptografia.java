package br.com.tcc.rankstudio.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

/**
 * TODO Comentar
 * @author Vitor Marques
 * @since 1.0
 */
public class Criptografia {

	/**
	 * Metodo responsavel por receber a senha do usuario em formato de String e realizar a criptografia da mesma
	 * 
	 * @param senha a senha que sera criptografada
	 * @return {@link String} a senha criptografada
	 * @throws UnsupportedEncodingException  encode nao suportado
	 * @throws NoSuchAlgorithmException  algoritmo inexistente
	 */
    public static String criptografarSenha( final String senha ) throws UnsupportedEncodingException, NoSuchAlgorithmException {
    	
        final byte[] texto = senha.getBytes( "UTF-8" );
        
        // Cria um objeto MessageDigest usando o algoritmo SHA
        final MessageDigest messageDigest = MessageDigest.getInstance( "SHA" ); // ("MD5");
        
        // Calcula o digest
        messageDigest.update( texto );
        
        // Transforma o digest em uma String legível
        return new Base64().encodeToString( messageDigest.digest() );
        
    }
	
}
