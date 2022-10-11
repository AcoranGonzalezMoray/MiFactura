package Modelo;
import java.util.ArrayList;
import java.util.List;

public class Documentos {
	private List documentos = new ArrayList(); 
	private List claves = new ArrayList(); 

	public Documentos() {
		
	}

	public List getDocumentos() {
		return documentos;
	}
	
	public void add(String a) {
		for(int i=0;i<documentos.size();i++) {
			if(a.compareTo(documentos.get(i).toString())==0) {
				return;
			}
		}
		documentos.add(a);
	}
	
	public void setDocumentos(List documentos) {
		this.documentos = documentos;
	}

	public List getClaves() {
		return claves;
	}

	public void setClaves(String clave) {
		for(int i=0;i<claves.size();i++) {
			if(clave.compareTo(claves.get(i).toString())==0) {
				return;
			}
		}
		claves.add(clave);
	}
	
}
