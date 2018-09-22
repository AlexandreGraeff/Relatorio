package Equipe2_Relatorio;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import com.itextpdf.text.DocumentException;
import java.awt.event.ActionEvent;
import java.util.List;

public class DiretorioRelatorio extends JFrame {

	private static final long serialVersionUID = 1L;
	private String diretorioSelecionado;
	
	public DiretorioRelatorio() {
		super("Gerar Relatórios ANS");
	}

	public void SelecionarDiretorio(final UsuarioOperador usuarioLogado) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				ProgramaSaude programasDeSaude = new ProgramaSaude();
				try {
					List<String> listaProgramas = programasDeSaude.ObterProgramas();
					iniciarJanelaSelecao(usuarioLogado, listaProgramas);
				} catch (SQLException | ClassNotFoundException e) {
					JOptionPane.showMessageDialog(null,"Falha na conexão ao banco de dados");
					e.printStackTrace();
				}
			}
		});
	}

	private void iniciarJanelaSelecao(final UsuarioOperador usuarioLogado, List<String> listaProgramasSaude) 
	{
		JButton botaoEscolherDiretorio = new JButton("Selecione um diretório");
		JButton botaoGerarRelatorio = new JButton("Gerar relatório");
		final JComboBox<Object> comboBoxProgramas = new JComboBox<Object>((Object[])listaProgramasSaude.toArray());
		JLabel labelSelecionarPrograma = new JLabel("Selecione um programa");
		JPanel janelaGerarRelatorio = new JPanel();
	    setSize(300,250);
	    setLocation(500,280);
	    janelaGerarRelatorio.setLayout (null);
	    labelSelecionarPrograma.setBounds(85,80,200,20);
	    comboBoxProgramas.setBounds(20, 110, 250, 20);
	    botaoEscolherDiretorio.setBounds(50,40,200,20);
	    botaoGerarRelatorio.setBounds(75,150,150,20);
	    janelaGerarRelatorio.add(botaoEscolherDiretorio);
	    janelaGerarRelatorio.add(comboBoxProgramas);		
		janelaGerarRelatorio.add(botaoGerarRelatorio);
		janelaGerarRelatorio.add(labelSelecionarPrograma);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    getContentPane().add(janelaGerarRelatorio);
	    setVisible(true);
	    botaoEscolherDiretorio.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		JFileChooser openFile = new JFileChooser();
	    		openFile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    		openFile.showOpenDialog(null);		          
	            File file = openFile.getSelectedFile();
	            diretorioSelecionado = file.getAbsolutePath();	            
	    	}	    		    	     	    
	    });
	    
	    botaoGerarRelatorio.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e)
	    	{	
	    		if(diretorioSelecionado != null)
	    		{
	    			String itemSelecionado = comboBoxProgramas.getSelectedItem().toString();
    	    	    Relatorio relatorio = new Relatorio(usuarioLogado);
    	        	try {
						relatorio.criarRelatorio(diretorioSelecionado, itemSelecionado);
					} catch (ClassNotFoundException | DocumentException | SQLException excecao) {
						JOptionPane.showMessageDialog(null,"Erro ao gerar relatório");
						excecao.printStackTrace();
					}
	    		}
	    		else
	    		{
	    			JOptionPane.showMessageDialog(null,"Selecione um diretório");
	    		}
	    	}	    		    	     	    
	    });
	}
}
