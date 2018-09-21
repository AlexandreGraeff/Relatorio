package Equipe2_Relatorio;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import com.itextpdf.text.DocumentException;
import java.awt.event.ActionEvent;
import java.util.List;

public class DiretorioRelatorio extends JFrame {

	private static final long serialVersionUID = 1L;
	private String fullPath;
	
	public DiretorioRelatorio() {
		super("Gerar Relatórios ANS");
	}

	public void SelecionarDiretorio(final UsuarioOperador usuarioLogado) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				ProgramaSaude reportsDatabase = new ProgramaSaude();
				try {
					List<String> listaProgramas = reportsDatabase.ObterProgramas();
					iniciarJanelaSelecao(usuarioLogado, listaProgramas);
				} catch (SQLException | ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void iniciarJanelaSelecao(final UsuarioOperador usuarioLogado, List<String> listaProgramasSaude) 
	{
		JButton ChooseFolderButton = new JButton("Selecione um diretório");
		JButton GenerateReportButton = new JButton("Gerar relatório");
		final JComboBox<Object> ProgramsComboBox = new JComboBox<Object>((Object[])listaProgramasSaude.toArray());
		JLabel ChooseProgramLabel = new JLabel("Selecione um programa");
		JPanel GenerateReportWindow = new JPanel();
	    setSize(300,250);
	    setLocation(500,280);
	    GenerateReportWindow.setLayout (null);
	    ChooseProgramLabel.setBounds(85,80,200,20);
	    ProgramsComboBox.setBounds(20, 110, 250, 20);
	    ChooseFolderButton.setBounds(50,40,200,20);
	    GenerateReportButton.setBounds(75,150,150,20);
		GenerateReportWindow.add(ChooseFolderButton);
		GenerateReportWindow.add(ProgramsComboBox);		
		GenerateReportWindow.add(GenerateReportButton);
		GenerateReportWindow.add(ChooseProgramLabel);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    getContentPane().add(GenerateReportWindow);
	    setVisible(true);
	    ChooseFolderButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		JFileChooser openFile = new JFileChooser();
	    		openFile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    		openFile.showOpenDialog(null);		          
	            File file = openFile.getSelectedFile();
	            fullPath = file.getAbsolutePath();	            
	    	}	    		    	     	    
	    });
	    
	    GenerateReportButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e)
	    	{	
	    		if(fullPath != null)
	    		{
	    			String SelectedItem = ProgramsComboBox.getSelectedItem().toString();
	    	        try {
	    	    	    Relatorio reportAns = new Relatorio(usuarioLogado);
	    	        	reportAns.criarRelatorio(fullPath, SelectedItem);
	    			} catch (DocumentException | SQLException | ClassNotFoundException excecao) {
	    				excecao.printStackTrace();
	    			}	
	    		}
	    	}	    		    	     	    
	    });
	}
}
