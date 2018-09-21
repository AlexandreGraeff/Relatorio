package Equipe2_RelatorioANS;

import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import com.itextpdf.text.DocumentException;
import java.awt.event.ActionEvent;
import java.util.List;

public class BeginScreen  extends JFrame {

	private JFrame frame;
	private File file;
	private String fullPath;
	private ReportAns reportAns;
	private JButton ChooseFolderButton;
	private JButton GenerateReportButton;
	private JPanel GenerateReportWindow;
	private JComboBox ProgramsComboBox ;
	private JLabel ChooseProgramLabel;
	private ReportAnsDA reportsDatabase;
	private List<String> ProgramsList;
	private String SelectedItem;
	public BeginScreen(final UsuarioOperador usuarioLogado){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
			
				try 
				{	
					reportsDatabase = new ReportAnsDA();
					ProgramsList = reportsDatabase.getPrograms();
					//ProgramsList.add("TODOS OS PROGRAMAS");
					initialize(usuarioLogado);
					//frame.setVisible(false);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(UsuarioOperador usuarioLogado) 
	{
		ChooseFolderButton = new JButton("Selecione um diretório");
		GenerateReportButton = new JButton("Gerar relatório");
		ProgramsComboBox = new  JComboBox(ProgramsList.toArray());
		ChooseProgramLabel = new JLabel("Selecione um programa");
		GenerateReportWindow = new JPanel();
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
	    this.reportAns = new ReportAns(usuarioLogado);
	    ChooseFolderButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		JFileChooser openFile = new JFileChooser();
	    		openFile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    		openFile.showOpenDialog(null);		          
	            file = openFile.getSelectedFile();
	            fullPath = file.getAbsolutePath(); 
	            
	    	}	    		    	     	    
	    });
	    
	    GenerateReportButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e)
	    	{	
	    		if(fullPath != null)
	    		{
	    			SelectedItem = ProgramsComboBox.getSelectedItem().toString();
	    	        try {
	    				reportAns.createReportByDA( fullPath,SelectedItem );
	    			} catch (DocumentException e1) {
	    				// TODO Auto-generated catch block
	    				e1.printStackTrace();
	    			}	
	    		}
	    	}	    		    	     	    
	    });
	    
/*
		JFileChooser openFile = new JFileChooser();
		openFile.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        openFile.showOpenDialog(null);	
        
        file = openFile.getSelectedFile();
        fullPath = file.getAbsolutePath();                

        this.reportAns = new ReportAns(usuarioLogado);
        try {
			reportAns.createReportByDA( fullPath );
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	*/
	}
}
