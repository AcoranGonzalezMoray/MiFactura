package BoxPDF;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.TextPosition;
 
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
 
public class GetLinesFromPDF extends PDFTextStripper {
    
    public GetLinesFromPDF() throws IOException {
		super();
		
	}

	static List<String> lines = new ArrayList<String>();

    public static void CARGAR() throws IOException {
        PDDocument document = null;
        String fileName = System.getProperty("java.io.tmpdir")+"factura.pdf";
        try {
            document =  PDDocument.load(new File(fileName) );
            PDFTextStripper stripper = new GetLinesFromPDF();
            stripper.setSortByPosition( true );
            stripper.setStartPage( 0 );
            stripper.setEndPage( document.getNumberOfPages() );
 
            Writer dummy = new OutputStreamWriter(new ByteArrayOutputStream());
            stripper.writeText(document, dummy);

        
        }finally {
            if( document != null )  document.close();
            
        }
    }
    
    @Override
    protected void writeString(String str, List<TextPosition> textPositions) throws IOException {lines.add(str);}

	public static List<String> getLines() {return lines;}

	public static void setLines(List<String> lines) {GetLinesFromPDF.lines = lines;}

	public static void setLinesEmpty() {lines.clear();}
    
    
}