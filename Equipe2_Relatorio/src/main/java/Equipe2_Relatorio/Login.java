package Equipe2_Relatorio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public Login()
	{
		super("Autenticação");
	}

	public void IniciarLogin(){		
		JButton botaoLogin;
		JPanel janelaLogin;
		final JTextField campoUsuario;
		final JTextField campoSenha;
		JLabel etiquetaUsuario;
		JLabel etiquetaSenha;
		botaoLogin = new JButton("Login");
	    janelaLogin = new JPanel();
	    campoUsuario = new JTextField(15);
	    campoSenha = new JPasswordField(8);
	    etiquetaUsuario = new JLabel("ID do Usuário");
	    etiquetaSenha = new JLabel("Senha");
	    setSize(300,200);
	    setLocation(500,280);
	    janelaLogin.setLayout(null);
	    campoUsuario.setBounds(100,30,150,20);
	    campoSenha.setBounds(100,65,150,20);
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
	        public void actionPerformed(ActionEvent e){
	        	UsuarioOperador usuario = new UsuarioOperador();	        	
	            List<UsuarioOperador> listaUsuarios;
				try {
					listaUsuarios = usuario.ObterUsuarios();
		            Boolean usuarioExistente = false;
		            Boolean senhaCorreta = false;
		            UsuarioOperador usuarioLogado = new UsuarioOperador();
		            Criptografia criptografia = new Criptografia();		            
		            String senhaCriptografada = criptografia.criptografarSenha(campoSenha.getText());
		            for (UsuarioOperador usuarioOperador : listaUsuarios) {
						if (usuarioOperador.getId().equals(campoUsuario.getText())){
							usuarioExistente = true;
							if (usuarioOperador.getSenha().equals(senhaCriptografada)) {
								senhaCorreta = true;
								usuarioLogado = usuarioOperador;
								break;
							}
						}
					}		            
		            if (usuarioExistente && senhaCorreta) {
		            	setVisible(false);
						DiretorioRelatorio janelaSelecaoDiretorio = new DiretorioRelatorio();
						janelaSelecaoDiretorio.SelecionarDiretorio(usuarioLogado);
		            }
		            else if (!usuarioExistente)
		            {
		            	JOptionPane.showMessageDialog(null,"Usuário inexistente");
		            }
		            else
		            {
		            	JOptionPane.showMessageDialog(null,"Senha incorreta");
		            }
				} catch (SQLException | ClassNotFoundException | NoSuchAlgorithmException | UnsupportedEncodingException excecao) {
			        System.out.println("Falha na conexão ao banco de dados");
					excecao.printStackTrace();
				}
	        }
	    });
	}	
}