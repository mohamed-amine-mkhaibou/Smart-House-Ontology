package itsudparis.application;

import com.hp.hpl.jena.rdf.model.Model;

import itsudparis.tools.FileTool;
import itsudparis.tools.JenaEngine;

public class Main {


    public static void main(String[] args) {
        //définir les parametre
        String inputDataOntology = "dataset.owl";
        final String ns = "http://www.semanticweb.org/win10/ontologies/2021/0/untitled-ontology-15#";
        final String rdfs = "http://www.w3.org/2000/01/rdf-schema#";
        final String rdf = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";
        String inputRule = "data/owlrules.txt";
        //créer le model
        Model model = JenaEngine.readModel(inputDataOntology);
        Model inferedModel = JenaEngine.readInferencedModelFromRuleFile(model, inputRule);
        //créer les Instances
        createInstance(inferedModel, ns);
        //lire les streams
        StreamInputDataset myStream = new StreamInputDataset();
        myStream.run(inferedModel);

        String prefix = "PREFIX ns: <http://www.semanticweb.org/win10/ontologies/2021/0/untitled-ontology-15#> " +
                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n" +
                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n" +
                "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                "PREFIX tg:<http://www.turnguard.com/functions#>";

        System.out.println("Les capteurs dans Kitchen:");
        System.out.println(JenaEngine.executeQuery(inferedModel, prefix + "SELECT ?capteur WHERE { \n" +
                "?capteur  rdf:type ns:Mouvement .\n" +
                "?capteur ns:estDans ns:Kitchen\n" +
                "}"));


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
                + " <http://www.w3.org/1999/02/22-rdf-syntax-ns#type> <" + ns + "Objet> }"));


        System.out.println("L'état du volet kitchen quand T=22:");
        System.out.println(JenaEngine.executeQuery(inferedModel, "SELECT ?etat ?date WHERE { <" + ns + "volet_kitchen>"
                + " <" + ns + "etat> ?etat . <" + ns + "volet_kitchen> <" + ns + "date> ?date  }"));

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
        filetool.saveOWL(inferedModel, "dataset1.owl");

    }

    public static void createInstance(Model inferedModel, String owl) {
        JenaEngine.createInstanceOfClass(inferedModel, owl, "Objet", "table_dining");
        JenaEngine.addValueOfObjectProperty(inferedModel, owl, "table_dining", "estDans", "Dining");

        JenaEngine.createInstanceOfClass(inferedModel, owl, "Objet", "bed");
        JenaEngine.addValueOfObjectProperty(inferedModel, owl, "bed", "estDans", "Bedroom_big");

        JenaEngine.createInstanceOfClass(inferedModel, owl, "Objet", "bureau");
        JenaEngine.addValueOfObjectProperty(inferedModel, owl, "bureau", "estDans", "Office");

        JenaEngine.createInstanceOfClass(inferedModel, owl, "Objet", "refrigerateur");
        JenaEngine.addValueOfObjectProperty(inferedModel, owl, "refrigerateur", "estDans", "Kitchen");

        //JenaEngine.createInstanceOfClass(inferedModel, owl,"Objet", "table_dining");
        //JenaEngine.addValueOfObjectProperty(inferedModel, owl, "table_dining", "estDans", "Dining");

        JenaEngine.createInstanceOfClass(inferedModel, owl, "Volet", "volet_living");
        JenaEngine.addValueOfObjectProperty(inferedModel, owl, "volet_living", "estDans", "Living");

        JenaEngine.createInstanceOfClass(inferedModel, owl, "Volet", "volet_kitchen");
        JenaEngine.addValueOfObjectProperty(inferedModel, owl, "volet_kitchen", "estDans", "Kitchen");
        // JenaEngine.updateValueOfDataTypeProperty(inferedModel, owl, "volet_kitchen", "etat", "fermee");

        //JenaEngine.createInstanceOfClass(inferedModel, owl,"Objet", "refrigerateur");
        //JenaEngine.addValueOfObjectProperty(inferedModel, owl, "refrigerateur", "estDans", "Kitchen");

        JenaEngine.createInstanceOfClass(inferedModel, owl, "Objet", "Cuisiniere");
        JenaEngine.addValueOfObjectProperty(inferedModel, owl, "Cuisiniere", "estDans", "Kitchen");

        JenaEngine.createInstanceOfClass(inferedModel, owl, "Objet", "Micro-ondes");
        JenaEngine.addValueOfObjectProperty(inferedModel, owl, "Micro-ondes", "estDans", "Kitchen");

        JenaEngine.createInstanceOfClass(inferedModel, owl, "Objet", "Table-a-manger");
        JenaEngine.addValueOfObjectProperty(inferedModel, owl, "Table-a-manger", "estDans", "Kitchen");

        JenaEngine.createInstanceOfClass(inferedModel, owl, "Objet", "Chaise");
        JenaEngine.addValueOfObjectProperty(inferedModel, owl, "Chaise", "estDans", "Kitchen");

        JenaEngine.createInstanceOfClass(inferedModel, owl, "Volet", "volet_kitchen");
        JenaEngine.addValueOfObjectProperty(inferedModel, owl, "volet_kitchen", "estDans", "Kitchen");

        JenaEngine.createInstanceOfClass(inferedModel, owl, "Chauffage", "chauffage_office");
        JenaEngine.addValueOfObjectProperty(inferedModel, owl, "chauffage_office", "estDans", "Office");
        JenaEngine.updateValueOfDataTypeProperty(inferedModel, owl, "chauffage_office", "etat", "OFF");

        JenaEngine.createInstanceOfClass(inferedModel, owl, "Lampe", "lampe_living");
        JenaEngine.updateValueOfDataTypeProperty(inferedModel, owl, "lampe_living", "etat", "allumee");
        JenaEngine.addValueOfObjectProperty(inferedModel, owl, "lampe_living", "estDans", "Living");

        JenaEngine.createInstanceOfClass(inferedModel, owl, "TV", "tv_living");
        JenaEngine.updateValueOfDataTypeProperty(inferedModel, owl, "tv_living", "etat", "allumee");
        JenaEngine.addValueOfObjectProperty(inferedModel, owl, "tv_living", "estDans", "Living");


        //high lever context
        JenaEngine.createInstanceOfClass(inferedModel, owl, "periode", "periode_day");
        JenaEngine.updateValueOfDataTypeProperty(inferedModel, owl, "periode_day", "periode_time", "");
        JenaEngine.updateValueOfDataTypeProperty(inferedModel, owl, "periode_day", "periode_temperature", "");

        JenaEngine.createInstanceOfClass(inferedModel, owl, "saison", "thisSaison");
        JenaEngine.updateValueOfDataTypeProperty(inferedModel, owl, "thisSaison", "Saison_Name", "");
    }
}
