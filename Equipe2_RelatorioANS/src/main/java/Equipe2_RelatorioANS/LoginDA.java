package Equipe2_RelatorioANS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class LoginDA 
{
	private Connection connection;
	private List<UsuarioOperador> UserList;
	public LoginDA()
	{
		try 
		{
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } 
		catch (ClassNotFoundException excecao) {
            System.out.println("Não encontrou o driver de conexão ao banco");
            excecao.printStackTrace();
            return;
        }
        connection = null;
        try 
        {
            connection = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:xe", "system", "abc1234");
        } 
        catch (SQLException excecao) 
        {
            System.out.println("Falha na conexão");
            excecao.printStackTrace();
            return;
        }
        if (connection != null) 
        {
            System.out.println("Sucesso na conexão");
        }
        else 
        {
            System.out.println("Falha na conexão");
            return;
        }
	}
	
	public List<UsuarioOperador> getUser()
	{
		List<UsuarioOperador> UserList = new ArrayList<>();
        String sql = "SELECT ID, NOME, SENHA FROM USUARIO_OPERADOR";	
        try 
        {
            try(PreparedStatement stm = connection.prepareStatement(sql);
                ResultSet retornoConsulta = stm.executeQuery()){
	            while(retornoConsulta.next()){
	            	UsuarioOperador usuarioOperador = new UsuarioOperador();
	            	usuarioOperador.setId(retornoConsulta.getString(1));
	            	usuarioOperador.setNome(retornoConsulta.getString(2));
	            	usuarioOperador.setSenha(retornoConsulta.getString(3));
	            	UserList.add(usuarioOperador);
	                }    
	            }
        } 
        catch (SQLException excecao) 
        {
            System.out.println("Falha na conexão");
            excecao.printStackTrace();
            return null;	
        }
        return UserList;
	}
}
