package aula;
import java.io.InputStream;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;


public class Aula2 {

	public static void main(String args[]){
		
		Model futebol1 = ModelFactory.createDefaultModel();
		Model futebol2 = ModelFactory.createDefaultModel();
		
		String a = "RDF01.xml";
		InputStream in = FileManager.get().open(a);

		futebol1.read(in, null);

		String b = "RDF02.xml";
		InputStream in2 = FileManager.get().open(b);

		futebol2.read(in2, null);
				
		Model result = futebol1.union(futebol2);
		
		result.write(System.out);
		
		
	}
	
	
}
