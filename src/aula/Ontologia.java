package aula;

import java.io.FileWriter;
import java.io.IOException;

import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.ontology.AllValuesFromRestriction;
import org.apache.jena.ontology.DatatypeProperty;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.IntersectionClass;
import org.apache.jena.ontology.MaxCardinalityRestriction;
import org.apache.jena.ontology.MinCardinalityRestriction;
import org.apache.jena.ontology.ObjectProperty;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.RDFList;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.vocabulary.XSD;

public class Ontologia {

	/**
	 * @param args
	 */
	public static void main(String args[]) {

		//criando um modelo de ontologia vazio
		OntModel ontModel = ModelFactory.createOntologyModel();
		String ns = "http://www.exemplo.com/onto1#";
		
		//criando as classes
		OntClass pessoa = ontModel.createClass(ns + "Pessoa");
		OntClass masculino = ontModel.createClass(ns + "Masculino");
		OntClass feminino = ontModel.createClass(ns + "Feminino");
		
		//defininindo subclasses
		pessoa.addSubClass(masculino);	
		pessoa.addSubClass(feminino);

		//disjunção
		masculino.addDisjointWith(feminino);
		feminino.addDisjointWith(masculino);

		//propriedade de tipo de dado + domínio + range
		DatatypeProperty temIdade = ontModel.createDatatypeProperty(ns + "temIdade");
		temIdade.setDomain(pessoa);
		temIdade.setRange(XSD.integer);
		
		//individuals
		Individual joao = masculino.createIndividual(ns + "João");
		Individual maria = feminino.createIndividual(ns + "Maria");
		Individual jose = masculino.createIndividual(ns + "José");
		
		//literal
		Literal idade20 = ontModel.createTypedLiteral("20", XSDDatatype.XSDint);
		
		//sentença
		Statement joaoTem20 = ontModel.createStatement(joao,  temIdade, idade20);
		
		ontModel.add(joaoTem20);
		
		//propriedade de objeto + domínio + range
		ObjectProperty temIrmao = ontModel.createObjectProperty(ns + "temIrmao");
		temIrmao.setDomain(pessoa);
		temIrmao.setRange(pessoa);
		
		//sentenças
		Statement joaoIrmaoMaria = ontModel.createStatement(joao, temIrmao, maria);
		Statement mariaIrmaoJoao = ontModel.createStatement(maria, temIrmao, joao);
		
		ontModel.add(joaoIrmaoMaria);
		ontModel.add(mariaIrmaoJoao);
		
		
		//propriedades restritivas
		ObjectProperty temConjuge = ontModel.createObjectProperty(ns + "temConjuge");
		
		temConjuge.setDomain(pessoa);
		temConjuge.setRange(pessoa);
		
		Statement joseConjMaria = ontModel.createStatement(jose,  temConjuge,  maria);
		Statement mariaConjJose = ontModel.createStatement(maria,  temConjuge,  jose);
		
		ontModel.add(joseConjMaria);
		ontModel.add(mariaConjJose);
		
		//restrições
		AllValuesFromRestriction soFeminino = ontModel.createAllValuesFromRestriction(null, temConjuge, feminino);
		
		MaxCardinalityRestriction maximo1Conjuge = ontModel.createMaxCardinalityRestriction(null,  temConjuge, 1);
		
		//restrigindo classe Masculino
		masculino.addSuperClass(soFeminino);
		masculino.addSuperClass(maximo1Conjuge);
		
		
		//classes definidas
		OntClass pessoaCasada = ontModel.createClass(ns + "PessoaCasada");
		
		//MinCardinalityRestriction createMinCardinalityRestriction(String uri, Property prop,int cardinality)
		MinCardinalityRestriction minimo1Conjuge = ontModel.createMinCardinalityRestriction(null,  temConjuge,  1);
		
		//criando lista para conter a classe Pessoa e a restrição
		RDFNode[] restricoesArray = { pessoa, minimo1Conjuge };
		RDFList restricoes = ontModel.createList(restricoesArray);
		
		IntersectionClass ic = ontModel.createIntersectionClass(null, restricoes);
		
		pessoaCasada.setEquivalentClass(ic);
		
		ontModel.write(System.out, "N-TRIPLE");
		
		String fileName = "Onto1.xml";
		FileWriter out;
		try {
			out = new FileWriter(fileName);
			ontModel.write(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
}