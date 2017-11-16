package aula;
import java.io.FileWriter;
import java.io.IOException;	
import java.io.InputStream;

import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;



public class Aula1 {

	public static void main(String args[]) throws IOException {
		
		Model futebol = ModelFactory.createDefaultModel();
		
		String ns = "http://disciplina.dcc.ufba.br/MATC93/Marginais#";
		
		// Recurso
				
		Resource time_c= futebol.createResource(ns + "time_c");
				
		Resource tecnico_c = futebol.createResource(ns + "tecnico_c");
		
		Resource jogador_c = futebol.createResource(ns + "jogador_c");
		
		Resource posicao_a = futebol.createResource(ns + "posicao_a");
		
		// Propriedades
		
		Property treina = futebol.createProperty(ns, "treina");
		Property tem_nome = futebol.createProperty(ns, "tem_nome");
		Property joga_como = futebol.createProperty(ns, "joga_como");
		Property joga_no = futebol.createProperty(ns, "joga_no");
					

		// Literal
		
		Literal titi =  futebol.createLiteral("Titi");
		Literal corinthians = futebol.createLiteral("Corinthians");
		Literal pato = futebol.createLiteral("Pato");
		Literal atacante = futebol.createLiteral("Atacante");
		
		
		// Statements (Tripla)
		
		
		Statement tecnico_time_c = futebol.createStatement(tecnico_c, treina, time_c);
		futebol.add(tecnico_time_c);
		
		
		
		Statement nome_time = futebol.createStatement(time_c, tem_nome, corinthians);
		futebol.add(nome_time);
		
		Statement nome_posicao_c = futebol.createStatement(posicao_a, tem_nome, atacante);
		futebol.add(nome_posicao_c);
		
		//futebol.write(System.out);
		
		//Escrever no arquivo
		
		String fileName = "RDF01.xml";
		FileWriter out = new FileWriter(fileName);
		futebol.write(out);
		
		//------------------------------------------------------------------------------------
		
		
		
	}

}
