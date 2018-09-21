package Equipe2_RelatorioANS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class Login extends JFrame {
	private JButton botaoLogin;
	private JPanel janelaLogin;
	private JTextField campoUsuario;
	private JTextField campoSenha;
	private JLabel etiquetaUsuario;
	private JLabel etiquetaSenha;
	private UsuarioOperador user_teste;
	public Login(){
		super("Autentica��o");
		botaoLogin = new JButton("Login");
	    janelaLogin = new JPanel();
	    campoUsuario = new JTextField(15);
	    campoSenha = new JPasswordField(8);
	    etiquetaUsuario = new JLabel("Usu�rio");
	    etiquetaSenha = new JLabel("Senha");
	    setSize(300,200);
	    setLocation(500,280);
	    janelaLogin.setLayout (null);
	    campoUsuario.setBounds(70,30,150,20);
	    campoSenha.setBounds(70,65,150,20);
	    botaoLogin.setBounds(110,100,80,20);
	    etiquetaUsuario.setBounds(20,28,80,20);
	    etiquetaSenha.setBounds(20,63,80,20);
	    janelaLogin.add(botaoLogin);
	    janelaLogin.add(campoUsuario);
	    janelaLogin.add(campoSenha);
	    janelaLogin.add(etiquetaUsuario);
	    janelaLogin.add(etiquetaSenha);
	    getContentPane().add(janelaLogin);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setVisible(true);
	    botaoLogin.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	//BeginScreen window = new BeginScreen(user_teste);
	        	
	        	try {
	                Class.forName("oracle.jdbc.driver.OracleDriver");
	            } catch (ClassNotFoundException excecao) {
	                System.out.println("N�o encontrou o driver de conex�o ao banco");
	                excecao.printStackTrace();
	                return;
	            }
	            Connection connection = null;
	            try {
	                connection = DriverManager.getConnection(
	                        "jdbc:oracle:thin:@localhost:1521:xe", "system", "abc1234");
	            } catch (SQLException excecao) {
	                System.out.println("Falha na conex�o");
	                excecao.printStackTrace();
	                return;
	
	            }
	
	            if (connection != null) {
	                System.out.println("Sucesso na conex�o");
	            } else {
	                System.out.println("Falha na conex�o");
	                return;
	            }
	            
	            List<UsuarioOperador> listaUsuarios = new ArrayList<>();
	            String sql = "SELECT ID, NOME, SENHA FROM USUARIO_OPERADOR";	
	
	            try {
		            try(PreparedStatement stm = connection.prepareStatement(sql);
		                ResultSet retornoConsulta = stm.executeQuery()){
			            while(retornoConsulta.next()){
			            	UsuarioOperador usuarioOperador = new UsuarioOperador();
			            	usuarioOperador.setId(retornoConsulta.getString(1));
			            	usuarioOperador.setNome(retornoConsulta.getString(2));
			            	usuarioOperador.setSenha(retornoConsulta.getString(3));
			            	listaUsuarios.add(usuarioOperador);
			                }    
			            }
		        } catch (SQLException excecao) {
		            System.out.println("Falha na conex�o");
		            excecao.printStackTrace();
		            return;	
		        }
	            
	            Boolean usuarioExistente = false;
	            Boolean senhaCorreta = false;
	            UsuarioOperador usuarioLogado = new UsuarioOperador();
	            for (UsuarioOperador usuarioOperador : listaUsuarios) {
					if (usuarioOperador.getNome().equals(campoUsuario.getText())){
						usuarioExistente = true;
						if (usuarioOperador.getSenha().equals(campoSenha.getText())) {
							senhaCorreta = true;
							usuarioLogado = usuarioOperador;
							break;
						}
					}
				}
	            
	            if (usuarioExistente && senhaCorreta) {
	            	setVisible(false);
					BeginScreen window = new BeginScreen(usuarioLogado);
	            }
	            else if (!usuarioExistente)
	            {
	            	JOptionPane.showMessageDialog(null,"Usu�rio inexistente");
	            }
	            else
	            {
	            	JOptionPane.showMessageDialog(null,"Senha incorreta");
	            }
	            
	        }
	    });
	}	
}