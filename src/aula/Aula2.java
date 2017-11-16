package aula;
import java.io.InputStream;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;


public class Aula2 {

	public static void main(String args[]){
		
		Model M_grupo1 = ModelFactory.createDefaultModel();
		Model M_grupo2 = ModelFactory.createDefaultModel();
		
		String a = "RDFGrupo1.xml";
		InputStream in = FileManager.get().open(a);

		M_grupo1.read(in, null);

		String b = "RDFGrupo2.xml";
		InputStream in2 = FileManager.get().open(b);

		M_grupo2.read(in2, null);
				
		Model result = M_grupo1.union(M_grupo2);
		
		result.write(System.out);
		
		
	}
	
	
}
