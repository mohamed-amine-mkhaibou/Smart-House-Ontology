package itsudparis.application;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.Statement;
import com.hp.hpl.jena.rdf.model.StmtIterator;

import itsudparis.tools.FileTool;
import itsudparis.tools.JenaEngine;

public class StreamInputDataset {

    public static final String ns = "http://www.semanticweb.org/win10/ontologies/2021/0/untitled-ontology-15#";
    public static final String rdfs = "http://www.w3.org/2000/01/rdf-schema#";
    public static final String rdf = "http://www.w3.org/1999/02/22-rdf-syntax-ns#";

    public void printActivity(String[] split) {
        if (split.length <= 4) return;
        System.out.print("----------------------------------------------");
        for (int i = 4; i < split.length; i++) {
            System.out.print(split[i] + " ");

        }
        System.out.println("------------------------------------------------");

    }

    public void printInfoCapteur(String action, String nomCapteur, String room, String heure, String valeurCapteur) {
        if (nomCapteur.charAt(0) == 'M' || nomCapteur.charAt(0) == 'D') {
            System.out.print(action + " " + nomCapteur + " estDans: " + room + " " + " date: " + heure + " etat: " + valeurCapteur);
        } else if (nomCapteur.charAt(0) == 'T') {
            System.out.print(action + " " + nomCapteur + " estDans: " + room + " " + " date: " + heure + " degre: " + valeurCapteur);
        }

    }

    public  void addCommunValueOfDataTypeProperty(Model model, String room, String heure, String date, String nomCapteur) {
        JenaEngine.addValueOfObjectProperty(model, ns, nomCapteur, "estDans", room);
        JenaEngine.addValueOfDataTypeProperty(model, ns, nomCapteur, "heure_precise", heure);
        JenaEngine.addValueOfDataTypeProperty(model, ns, nomCapteur, "date", date);
        JenaEngine.addValueOfDataTypeProperty(model, ns, nomCapteur, "mois", Integer.valueOf(date.split("-")[1]));
        JenaEngine.addValueOfDataTypeProperty(model, ns, nomCapteur, "heure", Integer.valueOf(heure.split(":")[0]));
    }
    public  void updateCommunValueOfDataTypeProperty(Model model, String room, String heure, String date, String nomCapteur) {
        JenaEngine.updateValueOfObjectProperty(model, ns, nomCapteur, "estDans", room);
        JenaEngine.updateValueOfDataTypeProperty(model, ns, nomCapteur, "heure_precise", heure);
        JenaEngine.updateValueOfDataTypeProperty(model, ns, nomCapteur, "date", date);
        JenaEngine.updateValueOfDataTypeProperty(model, ns, nomCapteur, "mois", Integer.valueOf(date.split("-")[1]));
        JenaEngine.updateValueOfDataTypeProperty(model, ns, nomCapteur, "heure", Integer.valueOf(heure.split(":")[0]));
    }

    public void run(Model model) {
        // dataset path
        String fileName = "data/data.txt";
        //read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

            stream.forEach(line -> {
                try {
                    //String[] split = line.split("\t");
                    String[] split = line.trim().split("\\s+");
                    String typeCapteur = "";
                    @SuppressWarnings("unused")
                    String nomCapteur = "";
                    String heure = "";
                    String date = "";
                    String mesureCapteur = "";
                    String room = "";
                    String valeurCapteur = "";

                    switch (split[2].substring(0, 1)) {
                        case "D":
                            typeCapteur = "Porte";
                            date = split[0];
                            heure = split[1];
                            switch (split[2].substring(0, 4)) {
                                case "D001":
                                    room = "Living";
                                    nomCapteur = "D001";
                                    break;
                                case "D002":
                                    room = "Kitchen";
                                    nomCapteur = "D002";
                                    break;
                                case "D003":
                                    room = "Bathroom_big";
                                    nomCapteur = "D003";
                                    break;
                                case "D004":
                                    room = "Office";
                                    nomCapteur = "D004";
                                    break;

                            }

                            break;
                        case "T":
                            typeCapteur = "Temperature";
                            date = split[0];
                            heure = split[1];
                            switch (split[2].substring(0, 4)) {
                                case "T001":
                                    room = "Bedroom";
                                    nomCapteur = "T001";
                                    break;
                                case "T002":
                                    room = "Living";
                                    nomCapteur = "T002";
                                    break;
                                case "T003":
                                    room = "Kitchen";
                                    nomCapteur = "T003";
                                    break;
                                case "T004":
                                    room = "Bathroom_big";
                                    nomCapteur = "T004";
                                    break;
                                case "T005":
                                    room = "Office";
                                    nomCapteur = "T005";
                                    break;

                            }
                            break;
                        case "M":
                            typeCapteur = "Mouvement";
                            date = split[0];
                            heure = split[1];
                            switch (split[2].substring(0, 4)) {
                                case "M001":
                                    room = "Bedromm";
                                    nomCapteur = "M001";
                                    break;
                                case "M002":
                                    nomCapteur = "M002";
                                    room = "Bedroom";
                                    break;
                                case "M003":
                                    room = "Bedroom";
                                    nomCapteur = "M003";
                                    break;
                                case "M004":
                                    room = "Bedroom";
                                    nomCapteur = "M004";
                                    break;
                                case "M005":
                                    nomCapteur = "M005";
                                    room = "Bedroom";
                                    break;
                                case "M006":
                                    room = "Bedroom";
                                    nomCapteur = "M006";
                                    break;
                                case "M007":
                                    room = "Bedroom";
                                    nomCapteur = "M007";
                                    break;
                                case "M008":
                                    nomCapteur = "M008";
                                    room = "Living";
                                    break;
                                case "M009":
                                    room = "Living";
                                    nomCapteur = "M009";
                                    break;
                                case "M010":
                                    room = "Living";
                                    nomCapteur = "M010";
                                    break;
                                case "M011":
                                    nomCapteur = "M011";
                                    room = "Living";
                                    break;
                                case "M012":
                                    room = "Living";
                                    nomCapteur = "M012";
                                    break;
                                case "M013":
                                    room = "Bedromm";
                                    nomCapteur = "M013";
                                    break;
                                case "M014":
                                    nomCapteur = "M014";
                                    room = "Dining";
                                    break;
                                case "M015":
                                    room = "Kitchen";
                                    nomCapteur = "M015";
                                    break;
                                case "M016":
                                    room = "Kitchen";
                                    nomCapteur = "M016";
                                    break;
                                case "M017":
                                    nomCapteur = "M017";
                                    room = "Kitchen";
                                    break;
                                case "M018":
                                    room = "Kitchen";
                                    nomCapteur = "M018";
                                    break;
                                case "M019":
                                    room = "Kitchen";
                                    nomCapteur = "M019";
                                    break;
                                case "M020":
                                    nomCapteur = "M020";
                                    room = "Living";
                                    break;
                                case "M021":
                                    room = "Dining";
                                    nomCapteur = "M021";
                                    break;
                                case "M022":
                                    room = "Bedroom";
                                    nomCapteur = "M022";
                                    break;
                                case "M023":
                                    nomCapteur = "M023";
                                    room = "Bedroom";
                                    break;
                                case "M024":
                                    room = "Bedroom";
                                    nomCapteur = "M024";
                                    break;
                                case "M025":
                                    room = "Office";
                                    nomCapteur = "M025";
                                    break;
                                case "M026":
                                    nomCapteur = "M026";
                                    room = "Office";
                                    break;
                                case "M027":
                                    room = "Office";
                                    nomCapteur = "M027";
                                    break;
                                case "M028":
                                    room = "Office";
                                    nomCapteur = "M028";
                                    break;
                                case "M029":
                                    room = "Bathroom_big";
                                    nomCapteur = "M029";
                                    break;
                                case "M030":
                                    nomCapteur = "M030";
                                    room = "Bathroom_big";
                                    break;
                                case "M031":
                                    room = "Bathroom_big";
                                    nomCapteur = "M031";
                                    break;
                            }

                            break;

                    }
                    TimeUnit.SECONDS.sleep(0);

                    valeurCapteur = split[3];//état:on
                    //si le capteur existe dans ontology, nous allons update ces valeurs
                    if (model.contains(model.getResource(ns + nomCapteur), null, (RDFNode) null)) {//http://www.semanticweb.org/win10/ontologies/2021/0/untitled-ontology-15#M003
                        //si le capteur coresponds au type Mouvement ou porte
                        if (typeCapteur.equalsIgnoreCase("Mouvement") || typeCapteur.equalsIgnoreCase("Porte")) {
                            printActivity(split);
                            printInfoCapteur("update", nomCapteur, room, heure, valeurCapteur);
                            updateCommunValueOfDataTypeProperty(model, room, heure, date, nomCapteur);
                            JenaEngine.updateValueOfDataTypeProperty(model, ns, nomCapteur, "etat", valeurCapteur);

                            //my objet
//								JenaEngine.updateValueOfDataTypeProperty(model,ns,nomCapteur,"periode_time","matin");
//								JenaEngine.updateValueOfDataTypeProperty(model,ns,nomCapteur,"Saison_Name","printemps");

                        } else {//si le capteur coresponds au type temperature
                            printActivity(split);
                            printInfoCapteur("update", nomCapteur, room, heure, valeurCapteur);
                            updateCommunValueOfDataTypeProperty(model, room, heure, date, nomCapteur);
                            JenaEngine.updateValueOfDataTypeProperty(model, ns, nomCapteur, "degre",
                                    Double.parseDouble(valeurCapteur));

                        }

                    } else {//si le capteur n'existe pas dans ontology, nous allons create ces valeurs
                        if (typeCapteur.equalsIgnoreCase("Mouvement") || typeCapteur.equalsIgnoreCase("Porte")) {
                            printActivity(split);
                            printInfoCapteur("create", nomCapteur, room, heure, valeurCapteur);
                            JenaEngine.createInstanceOfClass(model, ns, typeCapteur, nomCapteur);
                            addCommunValueOfDataTypeProperty(model, room, heure, date, nomCapteur);
                            JenaEngine.addValueOfDataTypeProperty(model, ns, nomCapteur, "etat", valeurCapteur);

                        } else {
                            printActivity(split);
                            printInfoCapteur("create", nomCapteur, room, heure, valeurCapteur);
                            JenaEngine.createInstanceOfClass(model, ns, typeCapteur, nomCapteur);
                            addCommunValueOfDataTypeProperty(model, room, heure, date, nomCapteur);
                            JenaEngine.addValueOfDataTypeProperty(model, ns, nomCapteur, "degre",
                                    Double.parseDouble(valeurCapteur));

                        }
                    }
                    TimeUnit.SECONDS.sleep(0);
                    detectAction(model.listStatements(), model);
                    System.out.println();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();

        }

    }
    public static boolean equalToOneCapteursInList(Resource subject,String [] listCapteur){
        boolean flag=false;
        String prefix="http://www.semanticweb.org/win10/ontologies/2021/0/untitled-ontology-15#";
        for(String capteur:listCapteur){
            flag=flag||subject.toString().equalsIgnoreCase(prefix+capteur);
        }
        return flag;
    }
    public static void detectAction(StmtIterator geriter, Model model) throws InterruptedException {
        boolean frontDoor=false,corridor=false,Bathroom=false,table=false,kitchen=false,livingRoom=false,hostBedroom=false,guestBedroom=false,wardrobe=false,Office=false;
        String[] frontDoorCapteurs=new String[]{"D001"};
        String[] BathroomCapteurs=new String[]{"M031"};
        String[] tableCapteurs=new String[]{"M014"};
        String[] kitchenCapteurs=new String[]{"M015","M016","M017","M018","M019"};
        String[] livingRoomCapteurs=new String[]{"M009","M010","M011","M012","M013","M020"};
        String[] hostBedroomCapteurs=new String[]{"M001","M002","M003","M004","M005","M006","M007"};
        String[] guestBedroomCapteurs=new String[]{"M024"};
        String[] officeCapteurs=new String[]{"M025","M026","M027"};
        String[] wardrobeCapteurs=new String[]{"M031"};
        String[] corridorCapteurs=new String[]{"M021","M022"};
        //Map<String[],boolean> capteurs=new String[][]{frontDoorCapteurs,BathroomCapteurs,tableCapteurs,kitchenCapteurs,livingRoomCapteurs,hostBedroomCapteurs,guestBedroomCapteurs,officeCapteurs,wardrobeCapteurs,corridorCapteurs};
        Map<String[],Boolean> capteurs=new HashMap<>();
        capteurs.put(frontDoorCapteurs,false);
        capteurs.put(BathroomCapteurs,false);
        capteurs.put(tableCapteurs,false);
        capteurs.put(kitchenCapteurs,false);
        capteurs.put(livingRoomCapteurs,false);
        capteurs.put(hostBedroomCapteurs,false);
        capteurs.put(guestBedroomCapteurs,false);
        capteurs.put(officeCapteurs,false);
        capteurs.put(wardrobeCapteurs,false);
        capteurs.put(corridorCapteurs,false);


        while (geriter.hasNext()) {
            Statement stmt = geriter.nextStatement();
            Resource subject = stmt.getSubject();
            Property predicate = stmt.getPredicate();
            RDFNode object = stmt.getObject();
            if (!object.toString().startsWith("http")) {
                //pour chaque groupe de capteur détecte si un capteur entre eux est activé
                for (Map.Entry<String[],Boolean> entry : capteurs.entrySet()) {
                    String[] key=entry.getKey();
                    if (equalToOneCapteursInList(subject,key)){
                        if ((object.toString().split("http://www")[0]).equalsIgnoreCase("ON^^")) {
                            capteurs.put(key,true);
                        }
                    }
                }

//                //Capteur porte front D001
//                if (equalToOneCapteursInList(subject,frontDoorCapteurs)){
//                    if ((object.toString().split("http://www")[0]).equalsIgnoreCase("ON^^")) {
//                        corridor=true;
//
//                    }
//                }
//                //M31 capteur de mouvement dans BATHROOM
//                if (equalToOneCapteursInList(subject,BathroomCapteurs)) {
//                    if ((object.toString().split("http://www")[0]).equalsIgnoreCase("ON^^")) {
//                        Bathroom=true;
//
//                    }
//                }
//
//                //M0014 table
//                if (equalToOneCapteursInList(subject,tableCapteurs)) {
//                    if ((object.toString().split("http://www")[0]).equalsIgnoreCase("ON^^")) {
//                        table=true;
//
//
//                    }
//                }
//                //M19 KITCHEN
//                if (equalToOneCapteursInList(subject,kitchenCapteurs)) {
//                    if ((object.toString().split("http://www")[0]).equalsIgnoreCase("ON^^")) {
//                        kitchen=true;
//
//                    }
//                }
//
//                //M020 living
//                if (equalToOneCapteursInList(subject,livingRoomCapteurs)) {
//                    if ((object.toString().split("http://www")[0]).equalsIgnoreCase("ON^^")) {
//                        livingRoom=true;
//
//                    }
//                }
//                //M007 host bedroom
//                if (equalToOneCapteursInList(subject,hostBedroomCapteurs)) {
//                    if ((object.toString().split("http://www")[0]).equalsIgnoreCase("ON^^")) {
//                        hostBedroom=true;
//
//                    }
//                }
//                //M024 guest bedroom
//                if (equalToOneCapteursInList(subject,guestBedroomCapteurs)) {
//                    if ((object.toString().split("http://www")[0]).equalsIgnoreCase("ON^^")) {
//                        guestBedroom=true;
//
//                    }
//                }
//                //M027 Office
//                if (equalToOneCapteursInList(subject,officeCapteurs)) {
//                    if ((object.toString().split("http://www")[0]).equalsIgnoreCase("ON^^")) {
//                        guestBedroom=true;
//
//                    }
//                }
//                //M005 wardrobe
//                if (equalToOneCapteursInList(subject,wardrobeCapteurs)) {
//                    if ((object.toString().split("http://www")[0]).equalsIgnoreCase("ON^^")) {
//                        wardrobe=true;
//
//                    }
//                }
//
//                boolean hostToillete=false;
//                String[] hostToilleteCapteurs=new String[]{"M004"};
//                if (equalToOneCapteursInList(subject,wardrobeCapteurs)) {
//                    if ((object.toString().split("http://www")[0]).equalsIgnoreCase("ON^^")) {
//                        hostToillete=true;
//
//                    }
//                }


            }
        }
//        if(frontDoor) System.out.print("-----------someone is opening the front door-----------");
//        if(Bathroom) System.out.print("-----------someone is using the Bathroom-----------");
//        if(kitchen) System.out.print("--------Someone is in the kitchen--------");
//        if(livingRoom) System.out.print("--------Someone is at living room--------");
//        if(hostBedroom) System.out.print("--------Someone is at host bedroom--------");
//        if(guestBedroom) System.out.print("--------Someone is at guest bedroom--------");
//        if(Office) System.out.print("--------Someone is at Office--------");
//        if(corridor)  System.out.print("---------Someone is the corridor-----------");
//        if(wardrobe)System.out.print("-------someone is in front of the wardrobe---------");
//        if(table)  System.out.print("---------Someone is behind the table-----------");

        if(capteurs.get(frontDoorCapteurs)) System.out.print("-----------someone is opening the front door-----------");
        if(capteurs.get(BathroomCapteurs)) System.out.print("-----------someone is using the Bathroom-----------");
        if(capteurs.get(kitchenCapteurs)) System.out.print("--------Someone is in the kitchen--------");
        if(capteurs.get(livingRoomCapteurs)) System.out.print("--------Someone is at living room--------");
        if(capteurs.get(hostBedroomCapteurs)) System.out.print("--------Someone is at host bedroom--------");
        if(capteurs.get(guestBedroomCapteurs)) System.out.print("--------Someone is at guest bedroom--------");
        if(capteurs.get(officeCapteurs)) System.out.print("--------Someone is at Office--------");
        if(capteurs.get(corridorCapteurs))  System.out.print("---------Someone is in the corridor-----------");
        if(capteurs.get(wardrobeCapteurs))System.out.print("-------someone is in front of the wardrobe---------");
        if(capteurs.get(tableCapteurs))  System.out.print("---------Someone is behind the table-----------");


    }
}


	                    
	                    
	                    
	                    
	                    
	                    

	                    

	           
