package Equipe2_Relatorio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class ConexaoAcessoDados {
	
	public Connection ObterConexao() throws SQLException, ClassNotFoundException {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conexao = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "abc1234");
    	if (conexao == null) {
    		JOptionPane.showMessageDialog(null,"Falha na conexão ao banco de dados");
    		throw new SQLException();
    	}
    	
	    return conexao;
	}	
}
