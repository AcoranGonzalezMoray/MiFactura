package Lectura;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Procesamiento{
	
	private List<String> proceso = new ArrayList<String>();
    public String add="";
    public String clave;
	public List<String> Facturas(List<String> lineas, List<String> claves) {
	    	int numeroFacturas=0;
	    	float precioFacturas=0;
	    	
	    	proceso.clear();
	    	for(int i=0;i<lineas.size();i++) {
	
	     	   String temp = Contenido(lineas.get(i).trim(),claves);
	     	   if(temp !="" && temp !=null ) {
	     		   temp = temp.replaceAll(",", ".");
	     		   proceso.add("PRECIO Individual Factura con Palabra clave: ");
	     		   proceso.add("Clave="+clave+" : Valor: "+temp);
	     		   numeroFacturas++;
	     		   precioFacturas+=Float.valueOf(temp);
	     	   };
	    		
	    		add=lineas.get(i).replaceAll("\\s+","");
	    		
	    	}
	    	proceso.add("De las "+numeroFacturas+" facturas hay un total de "+precioFacturas+"�");
	    	return proceso;
    }
	
	public String Contenido(String linea,List<String>claves) {
		try {
			for(int x=0;x<claves.size();x++) {
				clave = claves.get(x);
				String result = linea.replaceAll("\\s+","");
		    	Pattern patron = Pattern.compile("^["+claves.get(x)+"]+\\d+(\\.|\\,)\\d+");
		    	Matcher matcher = patron.matcher(result);
		    	boolean  matchFound_part1 = matcher.find();
		    	//Palabra clave antes del valor
		    	if(matchFound_part1 ) {
			    	String clean = matcher.group(0).replaceAll(claves.get(x), "");
			    	this.add="";
			    	return clean;	
		    	}
		    	//Palabra clave detras del valor
	    		if(claves.get(x).replaceAll("\\s+","").compareTo(add.replaceAll("\\s+",""))==0) {
	    			Pattern patronn = Pattern.compile("\\d+(\\.|\\,)\\d+");
	    			Matcher matcherr = patronn.matcher(linea);
	    			boolean matchFound = matcherr.find();
		    		return matcherr.group(0);
	    		}
			}

		}catch (Exception e) {

            // Print out the error stack
            e.printStackTrace();

        }
    	
    	return "";
    }
}