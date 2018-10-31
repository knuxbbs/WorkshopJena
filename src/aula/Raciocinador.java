package aula;

import java.io.InputStream;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntResource;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerRegistry;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.iterator.ExtendedIterator;

public class Raciocinador {
	public static void main(String args[]) {
		String inputFileName  = "Onto1.xml";		
		InputStream in = FileManager.get().open(inputFileName);

		if (in == null) {
			throw new IllegalArgumentException( "File: " + inputFileName + " not found");
		}

		String ns = "http://www.exemplo.com/onto1#";  

		//faz uma pré construção da configuração padrão do raciocinador OWL
		Reasoner raciocinador = ReasonerRegistry.getOWLReasoner();
		
		// Obtendo a especificação OWL-DL
		OntModelSpec especRacioc = OntModelSpec.OWL_DL_MEM;
		especRacioc.setReasoner(raciocinador);
		
		// Criando o modelo com a especificação com suporte a inferência
		OntModel ontModel = ModelFactory.createOntologyModel(especRacioc);
		ontModel.read(in, "");
		
		OntClass pessoaCasada = ontModel.getOntClass(ns + "PessoaCasada");
		ExtendedIterator casados = pessoaCasada.listInstances();
		
		while(casados.hasNext()) {
			OntResource casado = (OntResource) casados.next();
			System.out.println(casado.getURI());
		}
	}
}