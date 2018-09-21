package Equipe2_Relatorio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ConsultaBancoDados {
	
	public Connection ObterConexao() throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conexao = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "abc1234");
    	if (conexao == null) {
    		throw new SQLException();
    	}
    	
	    return conexao;
	}
	
	public List<UsuarioOperador> ObterUsuarios() throws SQLException, ClassNotFoundException{
    	Connection novaConexao = ObterConexao();
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
	
	public List<String> ObterProgramas() throws SQLException, ClassNotFoundException{
    	Connection novaConexao = ObterConexao();
        String sql = "SELECT ID, NOME_DO_PROGRAMA, QTD_PARTICIPANTES FROM PROGRAMAS";	
        try(PreparedStatement stm = novaConexao.prepareStatement(sql);
            ResultSet retornoConsultaProgramas = stm.executeQuery()){
            List<String> listaProgramas = new ArrayList<>();
            while(retornoConsultaProgramas.next()){
            	listaProgramas.add(retornoConsultaProgramas.getString(2));
            }
            return listaProgramas;
        }
	}
	
	public int ObterParticipantesProgramas(String nomePrograma) throws SQLException, ClassNotFoundException{
    	Connection novaConexao = ObterConexao();
        String sql = "SELECT NOME_DO_PROGRAMA, QTD_PARTICIPANTES FROM PROGRAMAS";	
        try(PreparedStatement stm = novaConexao.prepareStatement(sql);
            ResultSet retornoConsultaParticipantes = stm.executeQuery()){
    		int quantidadeParticipantes = 0;
            while(retornoConsultaParticipantes.next()){
            	if(retornoConsultaParticipantes.getString(1).equals(nomePrograma))
            	{
            		quantidadeParticipantes = retornoConsultaParticipantes.getInt(2);
            	}
            }
            return quantidadeParticipantes;
        }
	}
}
