package aula;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;


public class Aula3 {

	public static void main(String args[]) throws IOException {
		
		Model futebol = ModelFactory.createDefaultModel();
		
		String ns = "http://disciplina.dcc.ufba.br/MATC93/Marginais#";
		
		// Recurso
				
		Resource time_c= futebol.createResource(ns + "time_c");
		
		Resource time_f= futebol.createResource(ns + "time_f");
				
		Resource tecnico_c = futebol.createResource(ns + "tecnico_c");
		
		Resource tecnico_f = futebol.createResource(ns + "tecnico_f");
		
		Resource jogador_c = futebol.createResource(ns + "jogador_c");
		
		Resource jogador_f = futebol.createResource(ns + "jogador_f");
		
		Resource posicao_a = futebol.createResource(ns + "posicao_a");
		
		
		// Propriedades
		
		Property treina = futebol.createProperty(ns, "treina");
		Property tem_nome = futebol.createProperty(ns, "tem_nome");
		Property joga_como = futebol.createProperty(ns, "joga_como");
		Property joga_no = futebol.createProperty(ns, "joga_no");
		Property joga_contra = futebol.createProperty(ns, "joga_contra");
					

		// Literal
		
		Literal titi =  futebol.createLiteral("Titi");
		Literal jayme =  futebol.createLiteral("Jayme de Almeida");
		Literal corinthians = futebol.createLiteral("Corinthians");
		Literal flamengo = futebol.createLiteral("Flamengo");
		Literal pato = futebol.createLiteral("Pato");
		Literal hernane = futebol.createLiteral("Hernane");
		Literal atacante = futebol.createLiteral("Atacante");
		
		
		// Statements (Tripla)
		
		Statement partida = futebol.createStatement(time_c, joga_contra, time_f);
		futebol.add(partida);
		
		Statement tecnico_time_c = futebol.createStatement(tecnico_c, treina, time_c);
		futebol.add(tecnico_time_c);
		
		Statement time_jogador_c = futebol.createStatement(jogador_c, joga_no, time_c);
		futebol.add(time_jogador_c);
		
		Statement posicao_jogador = futebol.createStatement(jogador_c, joga_como, posicao_a);
		futebol.add(posicao_jogador);
		
		Statement nome_tecnico_c = futebol.createStatement(tecnico_c, tem_nome, titi);
		futebol.add(nome_tecnico_c);
		
		Statement nome_jogador_c = futebol.createStatement(jogador_c, tem_nome, pato);
		futebol.add(nome_jogador_c);
		
		Statement posicao_jogador_c = futebol.createStatement(jogador_c, joga_como, posicao_a);
		futebol.add(posicao_jogador_c);
		
		Statement nome_time = futebol.createStatement(time_c, tem_nome, corinthians);
		futebol.add(nome_time);
		
		Statement nome_posicao_c = futebol.createStatement(posicao_a, tem_nome, atacante);
		futebol.add(nome_posicao_c);
		//----------------------------------------------------------------------------------------
		Statement tecnico_time_f = futebol.createStatement(tecnico_f, treina, time_f);
		futebol.add(tecnico_time_f);
		
		Statement time_jogador_f = futebol.createStatement(jogador_f, joga_no, time_f);
		futebol.add(time_jogador_f);
		
		Statement posicao_jogador_f = futebol.createStatement(jogador_f, joga_como, posicao_a);
		futebol.add(posicao_jogador_f);
		
		Statement nome_tecnico_f = futebol.createStatement(tecnico_f, tem_nome, jayme);
		futebol.add(nome_tecnico_f);
		
		Statement nome_jogador_f = futebol.createStatement(jogador_f, tem_nome, hernane);
		futebol.add(nome_jogador_f);
				
		Statement nome_time_f = futebol.createStatement(time_f, tem_nome, flamengo);
		futebol.add(nome_time_f);
		
		Statement nome_posicao_f = futebol.createStatement(posicao_a, tem_nome, atacante);
		futebol.add(nome_posicao_f);
		
		//futebol.write(System.out);
		
		//Escrever no arquivo
		
		String fileName = "RDF02.xml";
		FileWriter out = new FileWriter(fileName);
		futebol.write(out);
		
		//------------------------------------------------------------------------------------
		
		
		
	}

}
