package BoxPDF;
import org.apache.pdfbox.io.MemoryUsageSetting;
import org.apache.pdfbox.multipdf.PDFMergerUtility;
import Modelo.Documentos;
import java.io.File;
import java.io.IOException;

public class GFG {

    public void Fusionar(Documentos D) throws IOException
    {
    	PDFMergerUtility obj = new PDFMergerUtility();
    	for(int i=0;i<D.getDocumentos().size();i++) {
    		obj.addSource(new File(D.getDocumentos().get(i).toString()));
    	}
        obj.setDestinationFileName(System.getProperty("java.io.tmpdir")+"factura.pdf"); 
        obj.mergeDocuments(MemoryUsageSetting.setupTempFileOnly());
    }
}