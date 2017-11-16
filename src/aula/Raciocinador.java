package aula;

import java.io.InputStream;
import java.util.Iterator;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.ObjectProperty;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntResource;
import org.apache.jena.rdf.model.InfModel;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ReasonerRegistry;
import org.apache.jena.reasoner.ValidityReport;
import org.apache.jena.util.FileManager;
import org.apache.jena.util.iterator.ExtendedIterator;

public class Raciocinador {
	public static void main(String args[]) {

		String inputFileName  = "Onto1.xml";
		OntModel ontModel = ModelFactory.createOntologyModel();
	    InputStream in = FileManager.get().open(inputFileName);
	    
	    if (in == null) {
	        throw new IllegalArgumentException( "File: " + inputFileName + " not found");
	    }
	    ontModel.read(in, "");

	    String ns = "http://www.exemplo.com/onto1#";
	    
	    OntClass clube=ontModel.createClass(ns+"clube");
	    clube.addSameAs(ontModel.getOntClass(ns + "Time"));
	    
	    
	    Individual baea=clube.createIndividual(ns+"Baea");
	    
	    ObjectProperty pertence= ontModel.getObjectProperty("http://www.fake.org/futebol/ontology#pertence");
	    OntClass campeonato= ontModel.getOntClass("http://www.fake.org/futebol/ontology#Campeonato");
	     
	    campeonato.createIndividual(ns+"brasileirao");
	    
	    ontModel.createStatement(campeonato.createIndividual(ns+"brasileirao"),pertence, baea);
	   
	    

	    
	    //faz uma pré construção da configuração padrão do raciocinador OWL
	    Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
	    
	    //anexa a ontologia ao raciocinador
	    reasoner = reasoner.bindSchema(ontModel);
	    
	    InfModel infmodel = ModelFactory.createInfModel(reasoner,ontModel);
	    
	    //Encapsula a descrição dos componentes de um modelo de ontologa
	    //including the storage scheme, reasoner and language profile.
	    OntModelSpec ontModelSpec = OntModelSpec.OWL_DL_MEM;
	    ontModelSpec.setReasoner(reasoner);
	
	    // Cria a ontologia com suporte ao raciocinador
	    OntModel model = ModelFactory.createOntologyModel(ontModelSpec, ontModel);
	   
	    
	 // MarriedPerson has no asserted instances
	    // However, if an inference engine is used, two of the three
	    // individuals in the example presented here will be
	    // recognized as MarriedPersons
	            //ns is the uri
	    
     	
     	
        ValidityReport validity = infmodel.validate();
        if (validity.isValid()) {
            System.out.println("OK");
        } else {
            System.out.println("Conflicts");
            for (Iterator i = validity.getReports(); i.hasNext(); ) {
                System.out.println(" - " + i.next());
            }
        }
        Literal l=model.createLiteral("");
        Resource r=model.createResource("");
        
     
        
     	
     	
	    OntClass marPerson = model.getOntClass(ns + "Time"); // this is the uri for MarriedPerson class
	    ExtendedIterator married = marPerson.listInstances();
	    
	    while(married.hasNext()) {
	        OntResource mp = (OntResource)married.next();
	        System.out.println(mp.getURI());
	    } // this code returns 2 individuals with the help of reasoner

	}
}