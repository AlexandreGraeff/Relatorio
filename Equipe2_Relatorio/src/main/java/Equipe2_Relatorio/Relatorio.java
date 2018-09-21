package Equipe2_Relatorio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.Calendar;
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

	public void criarRelatorio(String filePath, String ProgramName) throws DocumentException, SQLException, ClassNotFoundException {
		this.programaSaude = new ProgramaSaude();
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);		
		try {
			PdfWriter.getInstance(document,	new FileOutputStream( filePath + "\\ReportAns" + Calendar.getInstance().get(Calendar.YEAR) + ".pdf"));
			document.open();
			Paragraph paragraph1 = getTitleParagraph(Calendar.getInstance());
			document.add(paragraph1);
			document.add(new Paragraph("-----------------------------------------------------------------------------"));
			paragraph1 = getProgramNameParagraph(ProgramName);
			document.add(paragraph1);
			paragraph1 = getQtyByProgram(ProgramName);
			document.add(paragraph1);
			document.add(new Paragraph("-----------------------------------------------------------------------------"));
			Anchor responField = new Anchor("Responsável: ",FontFactory.getFont(FontFactory.TIMES, 14, Font.BOLD, new CMYKColor(255, 255, 255, 255)));
			Anchor responValue = new Anchor( this.usuarioOperador.getNome(), FontFactory.getFont(FontFactory.TIMES, 14, Font.NORMAL, new CMYKColor(255, 255, 255, 255)));
			paragraph1 = new Paragraph();
			paragraph1.add( responField );
			paragraph1.add( responValue );
			paragraph1.setSpacingBefore(50);
			document.add(paragraph1);
			document.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	
	private Paragraph getProgramNameParagraph(String ProgramName)
	{
		Anchor anchorTarget = new Anchor("Relatório do programa " + ProgramName);
		Paragraph paragraph = new Paragraph();
		paragraph.setSpacingBefore(50);
		paragraph.setSpacingAfter(50);
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);
		paragraph.add(anchorTarget);
		return paragraph;
	}
	
	private Paragraph getQtyByProgram(String ProgramName) throws SQLException, ClassNotFoundException
	{
		Anchor qtyPrgField = new Anchor("Quantidade de participantes: ",FontFactory.getFont(FontFactory.TIMES, 14, Font.BOLD, new CMYKColor(255, 255, 255, 255)));
		Anchor qtyPrgValue = new Anchor(new Integer(this.programaSaude.ObterParticipantesProgramas(ProgramName) ).toString(),FontFactory.getFont(FontFactory.TIMES, 14, Font.NORMAL, new CMYKColor(255, 255, 255, 255)));
		Paragraph paragraph1 = new Paragraph();						
		paragraph1.add( qtyPrgField );
		paragraph1.add( qtyPrgValue );
		return paragraph1;
	}	
	
	private Paragraph getTitleParagraph(Calendar calendar) {
		Anchor anchorTarget = new Anchor("Relatório ANS " + calendar.get(Calendar.YEAR));

		Paragraph paragraph = new Paragraph();
		paragraph.setSpacingBefore(50);
		paragraph.setSpacingAfter(50);
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);
		paragraph.add(anchorTarget);
		return paragraph;
	}
}