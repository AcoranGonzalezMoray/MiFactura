package Lectura;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class Salida {
	private String contenidoSalida;
	private int cont =0;
	public void CrearSalida(List<String> salida) throws IOException {
        try (PDDocument document = new PDDocument()) {
        	int Paginas = Math.round((salida.size()/16)+1);
            for(int j=0;j<Paginas;j++) {
            	PDPage page = new PDPage(PDRectangle.A6);
                document.addPage(page);
                //contenidoSalida = "";
                PDPageContentStream contentStream = new PDPageContentStream(document, page);
                //Begin the Content stream   
                contentStream.beginText();   
                  
                //Setting the font to the Content stream    
                contentStream.setFont(PDType1Font.TIMES_BOLD, 9);  

                //Setting the leading  
                contentStream.setLeading(14.5f);  
                
                //Setting the position for the line   
                contentStream.newLineAtOffset( 20, page.getMediaBox().getHeight() - 52);
                
                int contInterno=0;
                for(int i=cont;i<salida.size();i++) {
                	contentStream.showText(salida.get(i).replace("\n", "").replace("\r", "").replace("�", ""));
                	contentStream.newLine();  
                	if((i+1)%2==0) contentStream.newLine();	
                	if(contInterno==15)break;
                	contInterno++;
                }
                
                //Ending the content stream  
                contentStream.endText();  
              
                //System.out.println("Multiple Text Content is added in the PDF Document.");  
                // Image
                //PDImageXObject image = PDImageXObject.createFromByteArray(document, Salida.class.getResourceAsStream("/java.png").readAllBytes(), "Java Logo");
                //contentStream.drawImage(image, 20, 20, image.getWidth() / 3, image.getHeight() / 3);
                cont+=(contInterno+1);
                contentStream.close();
            }

            document.save(System.getProperty("java.io.tmpdir")+"factura.pdf");
            cont=0;
            File path = new File (System.getProperty("java.io.tmpdir")+"factura.pdf");
            Desktop.getDesktop().open(path);
           
                
           
        }
	}
}
