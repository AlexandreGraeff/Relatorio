package Equipe2_RelatorioANS;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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

public class ReportAns {

	private UsuarioOperador usuarioOperador;
	private ReportAnsDA reportAnsDa;

	public ReportAns(UsuarioOperador usuarioLogado) {
		super();
		this.usuarioOperador = usuarioLogado;
		this.reportAnsDa = new ReportAnsDA();
	}

	public ReportAnsDA getReportAnsDa() {
		return reportAnsDa;
	}

	public void setReportAnsDa(ReportAnsDA reportAnsDa) {
		this.reportAnsDa = reportAnsDa;
	}

	public void createReportByDA(String filePath, String ProgramName) throws DocumentException {
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
			/*
			FederativeUnitEnum federativeUnit[] = FederativeUnitEnum.values();
			for ( int i = 0; i <= FederativeUnitEnum.values().length - 1; i++ ){
			
				if (i == 0) document.add(new Paragraph("-----------------------------------------------------------------------------"));

				paragraph1 = getStateParagraph(federativeUnit, i);
				
				document.add(paragraph1);
				
				paragraph1 = getQtyProgramParagraph(federativeUnit, i);

				document.add(paragraph1);

				paragraph1 = getQtyClientParagraph(federativeUnit, i);				
				
				document.add(paragraph1);
				
				
				document.add(new Paragraph("-----------------------------------------------------------------------------"));
			}
*/
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private Paragraph getQtyClientParagraph( FederativeUnitEnum[] federativeUnit, int i) {
		
		Paragraph paragraph1 = new Paragraph();
		
		Anchor qtyCltField = new Anchor("Quantidade de beneficiários: ",FontFactory.getFont(FontFactory.TIMES, 14, Font.BOLD, new CMYKColor(255, 255, 255, 255)));
		Anchor qtyCltValue = new Anchor(new Integer( this.getReportAnsDa().getQtyProgramsClients(federativeUnit[i]) ).toString(),FontFactory.getFont(FontFactory.TIMES, 14, Font.NORMAL, new CMYKColor(255, 255, 255, 255)));
						
		paragraph1.add( qtyCltField );
		paragraph1.add( qtyCltValue );
		return paragraph1;
	}

	private Paragraph getQtyProgramParagraph( FederativeUnitEnum[] federativeUnit, int i) {
		Anchor qtyPrgField = new Anchor("Quantidade de programas em andamento: ",FontFactory.getFont(FontFactory.TIMES, 14, Font.BOLD, new CMYKColor(255, 255, 255, 255)));
		Anchor qtyPrgValue = new Anchor(new Integer( this.getReportAnsDa().getQtyPrograms(federativeUnit[i]) ).toString(),FontFactory.getFont(FontFactory.TIMES, 14, Font.NORMAL, new CMYKColor(255, 255, 255, 255)));
		
		Paragraph paragraph1 = new Paragraph();						
		paragraph1.add( qtyPrgField );
		paragraph1.add( qtyPrgValue );
		return paragraph1;
	}

	private Paragraph getStateParagraph(FederativeUnitEnum[] federativeUnit, int i) {
		
		Anchor stateField = new Anchor("Estado: ",FontFactory.getFont(FontFactory.TIMES, 14, Font.BOLD, new CMYKColor(255, 255, 255, 255)));
		Anchor stateValue = new Anchor(federativeUnit[i].name(),FontFactory.getFont(FontFactory.TIMES, 14, Font.NORMAL, new CMYKColor(255, 255, 255, 255)));

		Paragraph paragraph = new Paragraph();
		paragraph.add( stateField );
		paragraph.add( stateValue );
		return paragraph;
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
	
	private Paragraph getQtyByProgram(String ProgramName)
	{
		Anchor qtyPrgField = new Anchor("Quantidade de participantes: ",FontFactory.getFont(FontFactory.TIMES, 14, Font.BOLD, new CMYKColor(255, 255, 255, 255)));
		Anchor qtyPrgValue = new Anchor(new Integer( this.getReportAnsDa().getQtyByProgram(ProgramName) ).toString(),FontFactory.getFont(FontFactory.TIMES, 14, Font.NORMAL, new CMYKColor(255, 255, 255, 255)));
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