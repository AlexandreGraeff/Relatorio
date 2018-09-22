package Equipe2_Relatorio;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Criptografia {
	
    public String criptografarSenha(String senha) throws NoSuchAlgorithmException, UnsupportedEncodingException {
    	MessageDigest algorithm = MessageDigest.getInstance("MD5");
    	return new String(algorithm.digest(senha.getBytes("UTF-8")));
    }
}
