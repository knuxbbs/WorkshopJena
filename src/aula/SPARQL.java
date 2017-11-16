package aula;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


public class SPARQL {
    public static void main(String args[]) {
		// Open the bloggers RDF graph from the filesystem
		Model futebol = ModelFactory.createDefaultModel();

		String ns="http://disciplinas.dcc.ufba.br/MATC93/Marginais#";

		// Recursos		
		Resource corinthians = futebol.createResource(ns + "corinthians");
		Resource flamengo = futebol.createResource(ns + "flamengo");
		Resource tecnico = futebol.createResource(ns + "tecnico");
		
		//Propriedades
		Property jogaContra = futebol.createProperty(ns, "jogaContra");
		Property temTorcidaMaior = futebol.createProperty(ns, "temTorcidaMaior");
		Property possuiTitulo = futebol.createProperty(ns, "possuiTitulo");
		Property treina = futebol.createProperty(ns, "treina");
		Property tem_nome = futebol.createProperty(ns, "tem_nome");
					
		// Literal		
		futebol.addLiteral(flamengo, possuiTitulo, false);
		
		
		// Statements (Tripla)
		Statement exemplo = futebol.createStatement(corinthians, jogaContra, flamengo);
		Statement exemplo2 = futebol.createStatement(tecnico, treina, corinthians);
		
		futebol.add(exemplo);
		futebol.add(exemplo2);
		
		// Create a new query
		String queryString =    "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                                        "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                                        "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                                        "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                                        "PREFIX g: <http://disciplinas.dcc.ufba.br/MATC93/Marginais#>\n" +
                                        "SELECT ?subject ?object " +
                                        "WHERE { ?subject rdf:type ?object }";
                                        
		//String queryString ="SELECT ?x ?y ?z WHERE { ?x  <http://disciplinas.dcc.ufba.br/MATC93/Marginais#jogaContra> <http://disciplinas.dcc.ufba.br/MATC93/Marginais#flamengo> .}";
		

		
		
		Query query = QueryFactory.create(queryString);

		// Execute the query and obtain results
		QueryExecution qe = QueryExecutionFactory.create(query, futebol);
		//futebol.write(System.out);
		ResultSet results = qe.execSelect();
		
		// Output query results
		ResultSetFormatter.out(System.out, results, query);

		// Important - free up resources used running the query
		qe.close();

	}
}
