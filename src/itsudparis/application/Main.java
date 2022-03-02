package itsudparis.application;

import com.hp.hpl.jena.rdf.model.Model;

import itsudparis.tools.FileTool;
import itsudparis.tools.JenaEngine;

public class Main {

	public static void main(String[] args) {
		
	    String inputDataOntology = "data/SmartHome_test.owl";
	    final String ns = "http://www.semanticweb.org/win10/ontologies/2021/0/untitled-ontology-15#";
	    final String rdfs = "http://www.w3.org/2000/01/rdf-schema#";
	    final String rdf = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
	    String inputRule = "data/rules4.txt";

	    Model model = JenaEngine.readModel(inputDataOntology);

	     Model inferedModel = JenaEngine.readInferencedModelFromRuleFile(model, inputRule);

       
        JenaEngine.createInstanceOfClass(inferedModel, ns,"Objet", "table_dining");
        JenaEngine.addValueOfObjectProperty(inferedModel, ns, "table_dining", "estDans", "Dining");

        JenaEngine.createInstanceOfClass(inferedModel, ns,"Objet", "bed");
        JenaEngine.addValueOfObjectProperty(inferedModel, ns, "bed", "estDans", "Bedroom_big");
        
        JenaEngine.createInstanceOfClass(inferedModel, ns,"Objet", "bureau");
        JenaEngine.addValueOfObjectProperty(inferedModel, ns, "bureau", "estDans", "Office");
        
        JenaEngine.createInstanceOfClass(inferedModel, ns,"Objet", "refrigerateur");
        JenaEngine.addValueOfObjectProperty(inferedModel, ns, "refrigerateur", "estDans", "Kitchen");
        
        //JenaEngine.createInstanceOfClass(inferedModel, ns,"Objet", "table_dining");
        //JenaEngine.addValueOfObjectProperty(inferedModel, ns, "table_dining", "estDans", "Dining");
        
 
        
        JenaEngine.createInstanceOfClass(inferedModel, ns,"Volet", "volet_living");
        JenaEngine.addValueOfObjectProperty(inferedModel, ns, "volet_living", "estDans", "Living");

        JenaEngine.createInstanceOfClass(inferedModel, ns,"Volet", "volet_kitchen");
        JenaEngine.addValueOfObjectProperty(inferedModel, ns, "volet_kitchen", "estDans", "Kitchen");
       // JenaEngine.updateValueOfDataTypeProperty(inferedModel, ns, "volet_kitchen", "etat", "fermee");

        //JenaEngine.createInstanceOfClass(inferedModel, ns,"Objet", "refrigerateur");
        //JenaEngine.addValueOfObjectProperty(inferedModel, ns, "refrigerateur", "estDans", "Kitchen");
        
        JenaEngine.createInstanceOfClass(inferedModel, ns,"Objet", "Cuisiniere");
        JenaEngine.addValueOfObjectProperty(inferedModel, ns, "Cuisiniere", "estDans", "Kitchen");
        
        JenaEngine.createInstanceOfClass(inferedModel, ns,"Objet", "Micro-ondes");
        JenaEngine.addValueOfObjectProperty(inferedModel, ns, "Micro-ondes", "estDans", "Kitchen");
        
        JenaEngine.createInstanceOfClass(inferedModel, ns,"Objet", "Table-a-manger");
        JenaEngine.addValueOfObjectProperty(inferedModel, ns, "Table-a-manger", "estDans", "Kitchen");
        
        JenaEngine.createInstanceOfClass(inferedModel, ns,"Objet", "Chaise");
        JenaEngine.addValueOfObjectProperty(inferedModel, ns, "Chaise", "estDans", "Kitchen");
        
        JenaEngine.createInstanceOfClass(inferedModel, ns,"Volet", "volet_kitchen");
        JenaEngine.addValueOfObjectProperty(inferedModel, ns, "volet_kitchen", "estDans", "Kitchen");
        
        JenaEngine.createInstanceOfClass(inferedModel, ns,"Chauffage", "chauffage_office");
        JenaEngine.addValueOfObjectProperty(inferedModel, ns, "chauffage_office", "estDans", "Office");
        JenaEngine.updateValueOfDataTypeProperty(inferedModel, ns, "chauffage_office", "etat", "OFF");
        
        JenaEngine.createInstanceOfClass(inferedModel, ns,"Lampe", "lampe_living");
        JenaEngine.updateValueOfDataTypeProperty(inferedModel, ns, "lampe_living", "etat", "allumee");
        JenaEngine.addValueOfObjectProperty(inferedModel, ns, "lampe_living", "estDans", "Living");
        
        JenaEngine.createInstanceOfClass(inferedModel, ns,"TV", "tv_living");
        JenaEngine.updateValueOfDataTypeProperty(inferedModel, ns, "tv_living", "etat", "allumee");
        JenaEngine.addValueOfObjectProperty(inferedModel, ns, "tv_living", "estDans", "Living");
        	
		StreamInputDataset stream = new StreamInputDataset();
		stream.run(inferedModel);
		
		System.out.println("Les capteurs dans Kitchen:");
		System.out.println(JenaEngine.executeQuery(inferedModel, "SELECT ?capteur WHERE { ?capteur <http://www.w3.org/1999/02/22-rdf-syntax-ns#type>"
				+ " <http://www.semanticweb.org/win10/ontologies/2021/0/untitled-ontology-15#Mouvement> ."
				+ "?capteur <http://www.semanticweb.org/win10/ontologies/2021/0/untitled-ontology-15#estDans> "
				+ "<http://www.semanticweb.org/win10/ontologies/2021/0/untitled-ontology-15#Kitchen>}"));
		
		
		System.out.println("L'état du capteur M020:");
		System.out.println(JenaEngine.executeQuery(inferedModel, "SELECT ?etat WHERE { <http://www.semanticweb.org/win10/ontologies/2021/0/untitled-ontology-15#M020>"
				+ " <http://www.semanticweb.org/win10/ontologies/2021/0/untitled-ontology-15#etat> ?etat }"));  
		
		System.out.println("Etat de la télé:");
		System.out.println(JenaEngine.executeQuery(inferedModel, "SELECT ?etat WHERE { <http://www.semanticweb.org/win10/ontologies/2021/0/untitled-ontology-15#tv_living>"
				+ " <http://www.semanticweb.org/win10/ontologies/2021/0/untitled-ontology-15#etat> ?etat }"));   
		
		System.out.println("Etat de la lampe:");
		System.out.println(JenaEngine.executeQuery(inferedModel, "SELECT ?etat WHERE { <http://www.semanticweb.org/win10/ontologies/2021/0/untitled-ontology-15#lampe_living>"
				+ " <http://www.semanticweb.org/win10/ontologies/2021/0/untitled-ontology-15#etat> ?etat }"));  
		    
       
         /*FileTool filetool = new FileTool();
         filetool.saveOWL(model, "data/home_test");
         */
		System.out.println("\n Les objets du kitchen: ");
		
		System.out.println(JenaEngine.executeQuery(inferedModel, "SELECT ?obj WHERE { ?obj"
				+ " <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <"+ns+"Objet> }"));
		
		
		System.out.println("L'état du volet kitchen quand T=22:");
		System.out.println(JenaEngine.executeQuery(inferedModel, "SELECT ?etat ?date WHERE { <"+ns+"volet_kitchen>"
				+ " <"+ns+"etat> ?etat . <"+ns+"volet_kitchen> <"+ns+"date> ?date  }"));
		
		System.out.println("L'état du volet de living T<20 (T=19):");
		System.out.println(JenaEngine.executeQuery(inferedModel, "SELECT ?etat ?date WHERE { <http://www.semanticweb.org/win10/ontologies/2021/0/untitled-ontology-15#volet_living>"
				+ " <http://www.semanticweb.org/win10/ontologies/2021/0/untitled-ontology-15#etat> ?etat ."
				+ "<http://www.semanticweb.org/win10/ontologies/2021/0/untitled-ontology-15#volet_living> "
				+ "<http://www.semanticweb.org/win10/ontologies/2021/0/untitled-ontology-15#date> ?date }"));
		
		System.out.println("Le dernier état du volet Living:");
		System.out.println(JenaEngine.executeQuery(inferedModel, "SELECT ?etat ?date WHERE { <http://www.semanticweb.org/win10/ontologies/2021/0/untitled-ontology-15#volet_living>"
				+ " <http://www.semanticweb.org/win10/ontologies/2021/0/untitled-ontology-15#etat> ?etat ."
				+ "<http://www.semanticweb.org/win10/ontologies/2021/0/untitled-ontology-15#volet_living> "
				+ "<http://www.semanticweb.org/win10/ontologies/2021/0/untitled-ontology-15#date> ?date  }"));
		
		System.out.println("L'état de la télé du Living room:");
		System.out.println(JenaEngine.executeQuery(inferedModel, "SELECT ?etat ?date WHERE { <http://www.semanticweb.org/win10/ontologies/2021/0/untitled-ontology-15#tv_living>"
				+ " <http://www.semanticweb.org/win10/ontologies/2021/0/untitled-ontology-15#etat> ?etat ."
				+ "<http://www.semanticweb.org/win10/ontologies/2021/0/untitled-ontology-15#tv_living> "
				+ "<http://www.semanticweb.org/win10/ontologies/2021/0/untitled-ontology-15#date> ?date }"));
		
		FileTool filetool = new FileTool();
        filetool.saveOWL(model, "data/home_test");
		
	}

}
