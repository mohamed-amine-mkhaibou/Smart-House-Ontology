package itsudparis.application;
import java.nio.file.Files;
import java.nio.file.Paths;
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
		if(split.length<=4) return;
			System.out.print("----------------------------------------------");
			for (int i = 4; i < split.length; i++) {
				System.out.print(split[i] + " ");

			}
			System.out.println("------------------------------------------------");

	}
	public void printInfoCapteur(String action,String nomCapteur,String room,String heure,String valeurCapteur) {
		if(nomCapteur.charAt(0)=='M'||nomCapteur.charAt(0)=='D'){
			System.out.print(action+" "+nomCapteur+" estDans: "+room+" "+" date: "+heure+" etat: "+valeurCapteur);
		}else if(nomCapteur.charAt(0)=='T'){
			System.out.print(action+" "+nomCapteur+" estDans: "+room+" "+" date: "+heure+" degre: "+valeurCapteur);
		}

	}

	  public void run(Model model) {
	        // dataset path
	        String fileName = "data/data.txt";
	        //read file into stream, try-with-resources
	        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
	        	
	            stream.forEach(line -> {
	                try{
	                    //String[] split = line.split("\t");
	                    String[] split = line.trim().split("\\s+");
	                    String typeCapteur="";
	                    @SuppressWarnings("unused")
	                    String nomCapteur="";
						String heure="";
						String date="";
	                    String mesureCapteur="";
	                    String room="" ;
	                    String valeurCapteur="";

	                    switch (split[2].substring(0,1)) {
	                        case "D":
	                            typeCapteur = "Porte";
								date =split[0];
	                            heure =split[1];
	                            switch (split[2].substring(0,4)) {
                                case "D001":
                                	room ="Living";
                                	nomCapteur="D001";
                                	break;
                                case "D002":
                                	room ="Kitchen";
                                	nomCapteur="D002";
                                	break;
                                case "D003":
                                    room= "Bathroom_big";
                                	nomCapteur="D003";
                                    break;
                                case "D004":
                                    room= "Office";
                                	nomCapteur="D004";
                                    break;

	                            }

	                            break;
	                        case "T":
	                            typeCapteur = "Temperature";
								date =split[0];
	                            heure =split[1];
	                            switch (split[2].substring(0,4)) {
	                                case "T001":
	                                	room ="Bedroom";
	                                	nomCapteur="T001";
	                                	break;
	                                case "T002":
	                                	room ="Living";
	                                	nomCapteur="T002";
	                                    break;
	                                case "T003":
	                                	room ="Kitchen";
	                                	nomCapteur="T003";
	                                	break;
	                                case "T004":
	                                	room ="Bathroom_big";
	                                	nomCapteur="T004";
	                                    break;
	                                case "T005":
	                                	room ="Office";
	                                	nomCapteur="T005";
	                                	break;
	                              
	                            }
	                            break;
	                        case "M":
	                        	typeCapteur = "Mouvement";
								date =split[0];
	                            heure =split[1];
	                            switch (split[2].substring(0,4)) {
                                case "M001":
                                	room ="Bedromm";
                                	nomCapteur="M001";
                                	break;
                                case "M002":
                                	nomCapteur="M002";
                                	room ="Bedroom";
                                	break;
                                case "M003":
                                    room= "Bedroom";
                                	nomCapteur="M003";
                                    break;
                                case "M004":
                                	room ="Bedroom";
                                	nomCapteur="M004";
                                	break;
                                case "M005":
                                	nomCapteur="M005";
                                	room ="Bedroom";
                                	break;
                                case "M006":
                                    room= "Bedroom";
                                	nomCapteur="M006";
                                    break;
                                case "M007":
                                	room ="Bedroom";
                                	nomCapteur="M007";
                                	break;
                                case "M008":
                                	nomCapteur="M008";
                                	room ="Living";
                                	break;
                                case "M009":
                                    room= "Living";
                                	nomCapteur="M009";
                                    break;
                                case "M010":
                                	room ="Living";
                                	nomCapteur="M010";
                                	break;
                                case "M011":
                                	nomCapteur="M011";
                                	room ="Living";
                                	break;
                                case "M012":
                                    room= "Living";
                                	nomCapteur="M012";
                                    break;
                                case "M013":
                                	room ="Bedromm";
                                	nomCapteur="M013";
                                	break;
                                case "M014":
                                	nomCapteur="M014";
                                	room ="Dining";
                                	break;
                                case "M015":
                                    room= "Kitchen";
                                	nomCapteur="M015";
                                    break;
                                case "M016":
                                	room ="Kitchen";
                                	nomCapteur="M016";
                                	break;
                                case "M017":
                                	nomCapteur="M017";
                                	room ="Kitchen";
                                	break;
                                case "M018":
                                    room= "Kitchen";
                                	nomCapteur="M018";
                                    break;
                                case "M019":
                                	room ="Kitchen";
                                	nomCapteur="M019";
                                	break;
                                case "M020":
                                	nomCapteur="M020";
                                	room ="Living";
                                	break;
                                case "M021":
                                    room= "Dining";
                                	nomCapteur="M021";
                                    break;
                                case "M022":
                                	room ="Bedroom";
                                	nomCapteur="M022";
                                	break;
                                case "M023":
                                	nomCapteur="M023";
                                	room ="Bedroom";
                                	break;
                                case "M024":
                                    room= "Bedroom";
                                	nomCapteur="M024";
                                    break;
                                case "M025":
                                	room ="Office";
                                	nomCapteur="M025";
                                	break;
                                case "M026":
                                	nomCapteur="M026";
                                	room ="Office";
                                	break;
                                case "M027":
                                    room= "Office";
                                	nomCapteur="M027";
                                    break;
                                case "M028":
                                    room= "Office";
                                	nomCapteur="M028";
                                    break;
                                case "M029":
                                	room ="Bathroom_big";
                                	nomCapteur="M029";
                                	break;
                                case "M030":
                                	nomCapteur="M030";
                                	room ="Bathroom_big";
                                	break;
                                case "M031":
                                    room= "Bathroom_big";
                                	nomCapteur="M031";
                                    break;
	                            }

	                            break;
	                            
	                    }
	                    TimeUnit.SECONDS.sleep(0);

	                    valeurCapteur =split[3];//état:on
	                    if (model.contains(model.getResource(ns+nomCapteur),null, (RDFNode) null)) {//http://www.semanticweb.org/win10/ontologies/2021/0/untitled-ontology-15#M003
	                    	if (typeCapteur.equalsIgnoreCase("Mouvement") || typeCapteur.equalsIgnoreCase("Porte") ) {
								printActivity(split);
								printInfoCapteur("update",nomCapteur,room,heure,valeurCapteur);
	    	                    JenaEngine.updateValueOfDataTypeProperty(model, ns,nomCapteur,"etat",valeurCapteur);
	    	                    JenaEngine.updateValueOfObjectProperty(model, ns,nomCapteur, "estDans",room);
	    	                    JenaEngine.updateValueOfDataTypeProperty(model,ns,nomCapteur,"heure",heure);
								JenaEngine.updateValueOfDataTypeProperty(model,ns,nomCapteur,"date",date);
	    	             
	                    	}else {
								printActivity(split);
								printInfoCapteur("update",nomCapteur,room,heure,valeurCapteur);
	                    		JenaEngine.updateValueOfDataTypeProperty(model, ns,nomCapteur,"degre",
	    	                    		Double.parseDouble(valeurCapteur));
	    	                    JenaEngine.updateValueOfObjectProperty(model, ns,nomCapteur, "estDans",room);
								JenaEngine.updateValueOfDataTypeProperty(model,ns,nomCapteur,"heure",heure);
								JenaEngine.updateValueOfDataTypeProperty(model,ns,nomCapteur,"date",date);
	                    	}
	                    	
	                    }else {
	                    if (typeCapteur.equalsIgnoreCase("Mouvement") || typeCapteur.equalsIgnoreCase("Porte") ) {
							printActivity(split);
							printInfoCapteur("create",nomCapteur,room,heure,valeurCapteur);
							JenaEngine.createInstanceOfClass(model, ns,typeCapteur,nomCapteur);
	                    JenaEngine.addValueOfDataTypeProperty(model, ns,nomCapteur,"etat",valeurCapteur);
	                    JenaEngine.addValueOfObjectProperty(model, ns,nomCapteur, "estDans",room);
							JenaEngine.addValueOfDataTypeProperty(model,ns,nomCapteur,"heure",heure);
							JenaEngine.addValueOfDataTypeProperty(model,ns,nomCapteur,"date",date);
	                    }
	                    else {
							printActivity(split);
							printInfoCapteur("create",nomCapteur,room,heure,valeurCapteur);
	                    	JenaEngine.createInstanceOfClass(model, ns,typeCapteur,nomCapteur);
	                    JenaEngine.addValueOfDataTypeProperty(model, ns,nomCapteur,"degre",
	                    		Double.parseDouble(valeurCapteur));
	                    JenaEngine.addValueOfObjectProperty(model, ns,nomCapteur, "estDans",room);
							JenaEngine.addValueOfDataTypeProperty(model,ns,nomCapteur,"heure",heure);
							JenaEngine.addValueOfDataTypeProperty(model,ns,nomCapteur,"date",date);
	                    	
	                    }
	                    }      
	                    TimeUnit.SECONDS.sleep(0);
	                    detectAction(model.listStatements(),model);
						System.out.println();
	                } catch(Exception e){
                    e.printStackTrace();
                }
	            });
                
	        } catch(Exception e){
	        		e.printStackTrace(); 
	        		
            }
	        
	  }
	  public static void detectAction(StmtIterator geriter, Model model) throws InterruptedException {
		  int bool=0;
		  while(geriter.hasNext()) {
			  Statement stmt = geriter.nextStatement();
			  Resource subject = stmt.getSubject();
			  Property predicate =stmt.getPredicate();
			  RDFNode object = stmt.getObject();
			  if(!object.toString().startsWith("http")) {
				  boolean one= false;
				  boolean two = false;
				  boolean three= false;
				  boolean four = false;
				  //Capteur porte Bathroom D003
				  if(subject.toString().equalsIgnoreCase("http://www.semanticweb.org/win10/ontologies/2021/0/untitled-ontology-15#D003")) {
						if((object.toString().split("http://www")[0]).equalsIgnoreCase("ON^^")) {
							one = true;
						}
				  }
				  //M31 capteur de mouvement dans BATHROOM
				  if(subject.toString().equalsIgnoreCase("http://www.semanticweb.org/win10/ontologies/2021/0/untitled-ontology-15#M031")) {
						if((object.toString().split("http://www")[0]).equalsIgnoreCase("ON^^")) {
		                   two=true;
						}
				  }
				  if(two) {
						TimeUnit.SECONDS.sleep(0);
						System.out.print("-----------someone is using the Bathroom-----------");

				  }
				  //M0014 SALLE
				  if(subject.toString().equalsIgnoreCase("http://www.semanticweb.org/win10/ontologies/2021/0/untitled-ontology-15#M014")) {
						if((object.toString().split("http://www")[0]).equalsIgnoreCase("ON^^")) {
		                    TimeUnit.SECONDS.sleep(0);

							System.out.print("--------- Someone is behind the table-----------");
						}
				  }
				  //M19 KITCHEN
				  if(subject.toString().equalsIgnoreCase("http://www.semanticweb.org/win10/ontologies/2021/0/untitled-ontology-15#M019")) {
					if((object.toString().split("http://www")[0]).equalsIgnoreCase("ON^^")) {
						three = true;
					}
				  }
				  //M0015 KITCHEN
				  if(subject.toString().equalsIgnoreCase("http://www.semanticweb.org/win10/ontologies/2021/0/untitled-ontology-15#M015")) {
						if((object.toString().split("http://www")[0]).equalsIgnoreCase("ON^^")) {
							four = true;
						}
				  }
				  if (three&&four) {
	                    TimeUnit.SECONDS.sleep(0);

					  System.out.print("--------Someone is cooking in the kitchen--------");
				  }
				  //M020 living
				  if(subject.toString().equalsIgnoreCase("http://www.semanticweb.org/win10/ontologies/2021/0/untitled-ontology-15#M020")) {
					  if((object.toString().split("http://www")[0]).equalsIgnoreCase("ON^^")) {
						  System.out.print("--------Someone is at living room--------");
					  }
				  }
				  //M007 host bedroom
				  if(subject.toString().equalsIgnoreCase("http://www.semanticweb.org/win10/ontologies/2021/0/untitled-ontology-15#M007")) {
					  if((object.toString().split("http://www")[0]).equalsIgnoreCase("ON^^")) {
						  System.out.print("--------Someone is at host bedroom--------");
					  }
				  }
				  //M024 guest bedroom
				  if(subject.toString().equalsIgnoreCase("http://www.semanticweb.org/win10/ontologies/2021/0/untitled-ontology-15#M024")) {
					  if((object.toString().split("http://www")[0]).equalsIgnoreCase("ON^^")) {
						  System.out.print("--------Someone is at guest bedroom--------");
					  }
				  }
				  //M027 Office
				  if(subject.toString().equalsIgnoreCase("http://www.semanticweb.org/win10/ontologies/2021/0/untitled-ontology-15#M027")) {
					  if((object.toString().split("http://www")[0]).equalsIgnoreCase("ON^^")) {
						  System.out.print("--------Someone is at Office--------");
					  }
				  }
		  }
	  }
}
}


	                    
	                    
	                    
	                    
	                    
	                    

	                    

	           
