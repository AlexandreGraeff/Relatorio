package Equipe2_Relatorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsuarioOperadorAcessoDados {
	public List<UsuarioOperador> ObterUsuarios() throws SQLException, ClassNotFoundException{
		ConexaoAcessoDados conexao = new ConexaoAcessoDados();
    	Connection novaConexao = conexao.ObterConexao();
        String sql = "SELECT ID, NOME, SENHA FROM USUARIO_OPERADOR";	
        try(PreparedStatement stm = novaConexao.prepareStatement(sql);
            ResultSet retornoConsultaUsuarios = stm.executeQuery()){
    		List<UsuarioOperador> listaUsuarios = new ArrayList<>();
        	while(retornoConsultaUsuarios.next()){
            	UsuarioOperador usuarioOperador = new UsuarioOperador();
            	usuarioOperador.setId(retornoConsultaUsuarios.getString(1));
            	usuarioOperador.setNome(retornoConsultaUsuarios.getString(2));
            	usuarioOperador.setSenha(retornoConsultaUsuarios.getString(3));
            	listaUsuarios.add(usuarioOperador);
            }
        	return listaUsuarios;
        }
	}
}
