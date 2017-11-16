package aula;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.ObjectProperty;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Statement;

public class Ontologia {

	public static void main(String args[]) {

		OntModel futebolOntModel = ModelFactory.createOntologyModel();

		String ns = "http://www.fake.org/futebol/ontology#";

		OntClass time = futebolOntModel.createClass(ns + "Time");
		OntClass pessoa = futebolOntModel.createClass(ns + "Pessoa");
		OntClass campeonato = futebolOntModel.createClass(ns + "Campeonato");

		OntClass jogador = futebolOntModel.createClass(ns + "Jogador");
		OntClass tecnico = futebolOntModel.createClass(ns + "Tecnico");
		OntClass copaDoMundo = futebolOntModel.createClass(ns + "Copa");
		OntClass liga = futebolOntModel.createClass(ns + "Liga");

		pessoa.addSubClass(jogador);
		pessoa.addSubClass(tecnico);

		campeonato.addSubClass(copaDoMundo);
		campeonato.addSubClass(liga);

		liga.addDisjointWith(copaDoMundo);
		tecnico.addDisjointWith(jogador);

		ObjectProperty jogaContra = futebolOntModel.createObjectProperty(ns
				+ "jogaCom");
		ObjectProperty enfrenta = futebolOntModel.createObjectProperty(ns
				+ "enfrenta");
		ObjectProperty pertence = futebolOntModel.createObjectProperty(ns
				+ "pertence");
		ObjectProperty titular = futebolOntModel.createObjectProperty(ns
				+ "titular");
		ObjectProperty escolhe = futebolOntModel.createObjectProperty(ns
				+ "escolhe");
		ObjectProperty escolhido = futebolOntModel.createObjectProperty(ns
				+ "escolhido") ;
		// OntProperty disputadas =
		// futebolOntModel.createObjectProperty("Disputa");

		jogaContra.addDomain(time);
		jogaContra.addRange(time);

		futebolOntModel.createMaxCardinalityRestriction(ns + "pertence",
				pertence, 1);
		futebolOntModel.createMaxCardinalityRestriction(ns + "maxTitular",
				titular, 11);

		pertence.addDomain(jogador);
		pertence.addRange(time);

		titular.addDomain(time);
		titular.addRange(jogador);

		escolhe.addDomain(tecnico);
		escolhe.addRange(jogador);

		escolhe.addInverseOf(escolhido);

		jogaContra.addEquivalentProperty(enfrenta);
		
		Individual flamengo = time.createIndividual(ns+"flamengo");
		Individual corinthians = time.createIndividual(ns+"timao");
		
		
		Statement jogoDeHj = futebolOntModel.createStatement(flamengo, enfrenta, corinthians);
		
		futebolOntModel.add(jogoDeHj);
		
		
		
		
	}
}