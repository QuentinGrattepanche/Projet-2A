//gestion de la transformation moodle en txt(notre format) 

import org.xml.sax.*;

public class GestionnaireMoodleJava extends GestionnaireLecture{ 
	//résultats de notre parsing 
	//[changement heredite]private Quizz quiz; 
	private Question questionEnCoursDeTraitement; 
	//type de la question en cours de traitement
	private String type;
	//flags nous indiquant la position du parseur 
	private boolean inQuiz, inQuestion, inNom, inEnonce,inText, inReponse, inReponsePrincipale, a_deja_eu_reponse, inReponseAlternative;
	//buffer nous permettant de récupérer les données  
	private StringBuffer buffer; 
	//nom du fichier final
	//[changement heredite]public String nomFichierFinal;

	// simple constructeur 
	public GestionnaireMoodleJava(){ 
		super();
		//[changement heredite]this.nomFichierFinal = "";
	} 

	//[changement heredite]public void setNomFichierFinal(String nom){this.nomFichierFinal = nom;} 

	//méthode lancée lors d'une balise ouvrante
	public void startElement(String uri, String localName, 
			String qName, Attributes attributes) throws SAXException{
		//[Test pour l'affichage]System.out.println(qName);
		//[Test pour l'affichage]System.out.println(attributes.getValue("type"));

		switch (qName) {
		//Si la balise est "quiz" :
		case "quiz":
			//on crée un quizz et on marque que l'on est en train de le traiter
			quiz = new Quizz();  
			inQuiz = true; 
			//[Test pour l'affichage]System.out.println("on réussi à passer la balise \"quiz\"");
			break;

			//Si la balise est  "question" :
		case "question":
			//[Test pour l'affichage]System.out.println("on réussi à passer la balise \"question\"");
			//[Test pour l'affichage]System.out.println(attributes.getValue("type"));

			// On signale que l on est dans une question :
			inQuestion = true;
			type = attributes.getValue("type");

			//On determine le type de question
			switch (type) {

			case "multichoice" :
				questionEnCoursDeTraitement = new QuestionMultiChoix();
				//[Test pour l'affichage]System.out.println("on crée une question à choix multiple");
				break;

			case "truefalse" :
				questionEnCoursDeTraitement = new QuestionVraiFaux();
				//[Test pour l'affichage]System.out.println("on crée une question Vrai Faux");
				break;

			case "shortanswer" :
				questionEnCoursDeTraitement = new QuestionSimple();
				//[Test pour l'affichage]System.out.println("on crée une question à réponse courte");
				break;

			case "numerical" :
				break;

			case "matching" :
				break;

			case "cloze" :
				break;

			case "essay" :
				break;

			case "description" :
				break;
			}

			//TODO Si on veut rajouter un identifiant (A REVOIR)
			//try{ 
			//int id = Integer.parseInt(attributes.getValue("id")); 
			//question.setId(id); }
			//catch(Exception e){ 
			//erreur, le contenu de id n'est pas un entier 
			//throw new SAXException(e); } 
			//inQuestion = true;

			break;

		default:
			//Si la balise n'est pas "question" et qu'aucune question est en cours de traitement : on ne fait rien 
			//(il peut y avoir des balises intermédiaires non utilisées dans notre fichier Moodle)
			if(!inQuestion){
				//[Test pour l'affichage]System.out.println("on saute une balise ouvrante");
			}

			// sinon, on est deja dans une question.
			else {
				buffer = new StringBuffer(); 
				//[Test pour l'affichage]System.out.println("qName courant : "+qName);

				// le nom et l enonce de la question est le meme quel que soit le type.
				switch (qName) {
				case "name" :
					inNom = true;  //on marque que l'on va lire ensuite dans le fichier xml le nom de la question
					break;

				case "questiontext" : 
					inEnonce = true; 
					break;

				default : //pour les autres champs, cela depend du type de question :
					switch (type) {
					case "multichoice" :
						break;

					case "truefalse" :
						switch (qName) {
						case "answer" :
							//[Test pour l'affichage]System.out.println("La fraction associée à la réponse suivante est : "+ attributes.getValue("fraction"));
							if(attributes.getValue("fraction").equals("100")){ //si la réponse est vrai
								inReponse = true; 
							}
							break;
						case "text" :
							inText = true; 
							break;
							//si la balise dans la question n'est aucune de celles que l'on traite il ne se passe rien
						}
						break;

					case "shortanswer" :
						switch (qName) {
						case "answer" :
							if(attributes.getValue("fraction").equals("100")){//si la réponse est vrai
								if(!a_deja_eu_reponse){
									inReponsePrincipale = true; 
									a_deja_eu_reponse = true;
								}
								else {
									inReponseAlternative = true;
								}
							}
							break;
						case "text" :
							inText = true; 
							break;
							//si la balise dans la question n'est aucune de celles que l'on traite il ne se passe rien
						}
						break;

					case "numerical" :
						break;

					case "matching" :
						break;

					case "cloze" :
						break;

					case "essay" :
						break;

					case "description" :
						break;
					}
					break;
				}
			}
		}
	}


	//méthode lancée lors d'une balise fermante 
	public void endElement(String uri, String localName, String qName) 
			throws SAXException{ 

		switch (qName) {
		case "quiz" :
			//Si la balise fermante est "quiz" : on reinitialise le marqueur inquiz
			inQuiz = false; 
			break;

		case "question" :
			//Si la balise fermante est "question" : on ajoute la question au quizz et on réinitialise 
			quiz.getMesQuestions().add(questionEnCoursDeTraitement); 
			questionEnCoursDeTraitement = null; 
			inQuestion = false; 
			a_deja_eu_reponse = false;
			break;

			//Si la balise fermante est "name", "questiontext" ou "answer"(avec la réponse juste) : on reinitialise le marqueur associé
		case "name" :  
			inNom = false; 
			break;
		case "questiontext" :  
			inEnonce = false; 
			break;
		case "answer" :
			if(inReponse){ 
				inReponse = false; 
			}
			if(inReponsePrincipale){ 
				inReponsePrincipale = false; 
			}
			if(inReponseAlternative){
				inReponseAlternative = false;
			}
			break;

			//Si la balise fermante est "text" : on récupère le texte du buffer et on le met dans l'attribut de la question qui correspond :
		case "text" : 
			if (inNom){
				questionEnCoursDeTraitement.setNom(buffer.toString());
				//[Test pour l'affichage]System.out.println("Nom de la question : "+buffer.toString());
				
			}
			if (inEnonce){
				questionEnCoursDeTraitement.setEnonce(buffer.toString()); 
				//[Test pour l'affichage]System.out.println("Enoncé de la question : "+buffer.toString());
			}
			if (inReponse){
				//[Test pour l'affichage]System.out.println("Réponse : "+buffer.toString());
				questionEnCoursDeTraitement.setReponse(buffer.toString()); 
			}
			if (inReponsePrincipale){
				//[Test pour l'affichage]System.out.println("Réponse principale : "+buffer.toString());
				questionEnCoursDeTraitement.setReponse(buffer.toString()); 
			}
			if (inReponseAlternative){
				//[Test pour l'affichage]System.out.println("Réponse alternative : "+ buffer.toString());
				((QuestionSimple) questionEnCoursDeTraitement).ajouterReponseAlternative(buffer.toString()); 
			}
			//on réinitialise le buffer et le marqueur "inText"
			buffer = null;
			inText = false;
			inReponse = false;
			inReponsePrincipale = false;
			inReponseAlternative = false;

		default :
			//Si la balise fermante n est pas une de celle que l on devait traiter : il ne se passe rien
			//[Test pour l'affichage]System.out.println("on ferme la balise");
		}
	}

	//détection de caractères 
	public void characters(char[] ch,int start, int length) 
			throws SAXException{ 
		String lecture = new String(ch,start,length); 
		if(buffer != null) buffer.append(lecture);        
	} 

	//début du parsing 
	public void startDocument() throws SAXException { 
		System.out.println("Début du parsing"); 
	} 

	//fin du parsing 
	public void endDocument() throws SAXException { 
		System.out.println("Fin du parsing"); 
		System.out.println("Resultats du parsing"); 


		//sérialization
		//SerializationDeserialization Test = new SerializationDeserialization();
		//Test.serialization(quiz, nomFichierFinal);

		//ou 
		quiz.serialization(nomFichierFinal);
		//[Test pour l'affichage]System.out.println(quiz.getMesQuestions());

		//Deserialization (TEST)
		//Test.testDeserialization();

	} 
}
