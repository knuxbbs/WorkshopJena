package aula;

import java.io.InputStream;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntResource;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerRegistry;
import org.apache.jena.reasoner.rulesys.GenericRuleReasoner;
import org.apache.jena.reasoner.rulesys.Rule;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.iterator.ExtendedIterator;

public class RuleReasoner {
	public static void main(String args[]) {
		String inputFileName = "swrl.xml";
		Model model = FileManager.get().loadModel(inputFileName);

		if (model == null) {
			throw new IllegalArgumentException("File: " + inputFileName + " not found");
		}

		String ns = "http://www.semanticweb.org/bruno/ontologies/2018/9/untitled-ontology-15#";

		// Setting up rules
		String rule = "[rule1:(?a http://www.ramayana.org/hasSon ?b) ";
		rule += "(?b http://www.ramayana.org/hasSon ?c) ";
		rule += "-> (?a http://www.ramayana.org/grandfatherOf ?c)]";

		// set up reasoner
		Reasoner reasoner = new GenericRuleReasoner(Rule.parseRules(rule));

		InfModel inf = ModelFactory.createInfModel(reasoner, model);
	}
}
