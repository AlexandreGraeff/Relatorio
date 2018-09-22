package Equipe2_Relatorio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Calendar;

import javax.swing.JOptionPane;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfWriter;

public class Relatorio {

	private UsuarioOperador usuarioOperador;
	private ProgramaSaude programaSaude;

	public Relatorio(UsuarioOperador usuarioLogado) {
		this.usuarioOperador = usuarioLogado;
	}

	public void CriarRelatorio(String filePath, String nomeProgramaSaude) throws DocumentException, SQLException, ClassNotFoundException {
		this.programaSaude = new ProgramaSaude();
		Document documento = new Document(PageSize.A4, 50, 50, 50, 50);		
		try {			
			PdfWriter.getInstance(documento, new FileOutputStream( filePath + "\\Relatorio_ANS_" + LocalDateTime.now().toString().replace('-', '_').replace(':', '_') + ".pdf"));
			documento.open();
			Paragraph linha = GerarLinhaTitulo(Calendar.getInstance());
			documento.add(linha);
			linha = GerarLinhaNomePrograma(nomeProgramaSaude);
			documento.add(linha);
			linha = GerarLinhaParticipantes(nomeProgramaSaude);
			documento.add(linha);
			Anchor linhaResponsavelEtiqueta = new Anchor("Responsável: ",FontFactory.getFont(FontFactory.TIMES, 14, Font.BOLD, new CMYKColor(255, 255, 255, 255)));
			Anchor linhaResponsavelNome = new Anchor(this.usuarioOperador.getNome(), FontFactory.getFont(FontFactory.TIMES, 14, Font.NORMAL, new CMYKColor(255, 255, 255, 255)));
			linha = new Paragraph();
			linha.add(linhaResponsavelEtiqueta);
			linha.add(linhaResponsavelNome);
			documento.add(linha);
			documento.close();
        	JOptionPane.showMessageDialog(null,"Relatório gerado");
		} catch (FileNotFoundException | ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,"Erro ao gerar relatório");
			e.printStackTrace();
		}
	}
	
	private Paragraph GerarLinhaNomePrograma(String nomePrograma)
	{
		Anchor linhaProgramaEtiqueta = new Anchor("Nome do programa: ", FontFactory.getFont(FontFactory.TIMES, 14, Font.BOLD, new CMYKColor(255, 255, 255, 255)));
		Anchor linhaProgramaNome = new Anchor(nomePrograma, FontFactory.getFont(FontFactory.TIMES, 14, Font.NORMAL, new CMYKColor(255, 255, 255, 255)));
		Paragraph linha = new Paragraph();						
		linha.add(linhaProgramaEtiqueta);
		linha.add(linhaProgramaNome);
		return linha;		
	}
	
	private Paragraph GerarLinhaParticipantes(String nomePrograma) throws SQLException, ClassNotFoundException
	{
		Anchor linhaParticipantesEtiqueta = new Anchor("Quantidade de participantes: ", FontFactory.getFont(FontFactory.TIMES, 14, Font.BOLD, new CMYKColor(255, 255, 255, 255)));
		Anchor linhaParticipantesQuantidade = new Anchor(new Integer(this.programaSaude.ObterParticipantesProgramas(nomePrograma) ).toString(),FontFactory.getFont(FontFactory.TIMES, 14, Font.NORMAL, new CMYKColor(255, 255, 255, 255)));
		Paragraph linha = new Paragraph();						
		linha.add(linhaParticipantesEtiqueta);
		linha.add(linhaParticipantesQuantidade);
		return linha;
	}	
	
	private Paragraph GerarLinhaTitulo(Calendar calendar) {
		Anchor anchorTarget = new Anchor("Relatório ANS " + calendar.get(Calendar.YEAR));
		Paragraph paragraph = new Paragraph();
		paragraph.setSpacingBefore(50);
		paragraph.setSpacingAfter(50);
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);
		paragraph.add(anchorTarget);
		return paragraph;
	}
}