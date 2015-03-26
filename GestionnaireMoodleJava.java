//gestion de la transformation moodle en txt(notre format) 

import org.xml.sax.*;

public class GestionnaireMoodleJava extends GestionnaireLecture{ 
	//résultats de notre parsing 
	//[changement heredite]private Quizz quiz; 
	private Question questionEnCoursDeTraitement; 
	//flags nous indiquant la position du parseur 
	private boolean inQuiz, inQuestion, inNom, inEnonce,inText, inReponse;
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

		//Si la balise est "quiz" :
		if(qName.equals("quiz")){ 
			//on crée un quizz et on marque que l'on est en train de le traiter
			quiz = new Quizz();  
			inQuiz = true; 
			//[Test pour l'affichage]System.out.println("on réussi à passer la balise \"quiz\"");
		}

		//Si la balise n'est pas "question" et qu'aucune question est en cours de traitement : on ne fait rien 
		//(il peut y avoir des balises intermédiaires non utilisées dans notre fichier Moodle)
		else if(!(qName.equals("question")) && (inQuestion==false)){
			//[Test pour l'affichage]System.out.println("on saute une balise ouvrante");
		}

		//Si la balise est  "question" :
		else if(qName.equals("question")){ 
			//[Test pour l'affichage]System.out.println("on réussi à passer la balise \"question\"");
			//[Test pour l'affichage]System.out.println(attributes.getValue("type"));

			//On determine le type de question

			switch (attributes.getValue("type")) {

			case "multichoice" :
				questionEnCoursDeTraitement = new QuestionMultiChoix();
				inQuestion = true;
				//[Test pour l'affichage]System.out.println("on crée une question à choix multiple");
				break;
				
			case "truefalse" :
				questionEnCoursDeTraitement = new QuestionVraiFaux();
				inQuestion = true;
				//[Test pour l'affichage]System.out.println("on crée une question Vrai Faux");
				break;
				
			case "shortanswer" :
				questionEnCoursDeTraitement = new QuestionSimple();
				inQuestion = true;
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
		}

		//On traite les balises qui nous intéressent dans la question :
		else { 
			buffer = new StringBuffer(); 
			//[Test pour l'affichage]System.out.println(attributes.getValue("fraction"));
			//[Test pour l'affichage]System.out.println(qName);
			if(qName.equals("name")){ 
				inNom = true; } //on marque que l'on va lire ensuite dans le fichier xml le nom de la question
			else if(qName.equals("questiontext")){ 
				inEnonce = true; }
			else if(qName.equals("answer") && (attributes.getValue("fraction").equals("100"))){ //si la réponse est vrai
				inReponse = true; }
			else if(qName.equals("answer") && !(attributes.getValue("fraction").equals("100"))){ //si la réponse est fausse
				inReponse = false; }

			else if(qName.equals("text")){ 
				inText = true; }

			//si la balise dans la question n'est aucune de celles que l'on traite il ne se passe rien
			else if(!(qName.equals("name")) && !(qName.equals("questiontext")) &&!(qName.equals("answer"))){}

			else{ 
				//erreur, on peut lever une exception 
				throw new SAXException("Balise "+qName+" inconnue."); 
			} 
		} 
	} 

	//méthode lancée lors d'une balise fermante 
	public void endElement(String uri, String localName, String qName) 
			throws SAXException{ 

		//Si la balise fermante est "quiz" : on reinitialise le marqueur inquiz
		if(qName.equals("quiz")){ 
			inQuiz = false; }

		//Si la balise fermante est "question" et que l'on ne traitait pas de question : il ne se passe rien
		else if(!qName.equals("question") && (inQuestion==false)){
			//[Test pour l'affichage]System.out.println("on saute une balise fermante");
		}

		//Si la balise fermante est "question" : on ajoute la question au quizz et on réinitialise
		else if(qName.equals("question")){ 
			quiz.getMesQuestions().add(questionEnCoursDeTraitement); 
			questionEnCoursDeTraitement = null; 
			inQuestion = false; 
		}

		//Si la balise fermante est "name", "questiontext" ou "answer"(avec la réponse juste) : on reinitialise le marqueur associé
		else if(qName.equals("name")){  
			inNom = false; }
		else if(qName.equals("questiontext")){  
			inEnonce = false; }
		else if(qName.equals("answer") && inReponse){ 
			inReponse = false; }

		//Si la balise fermante est "text" : on récupère le texte du buffer et on le met dans l'attribut de la question qui correspond :
		else if(qName.equals("text")){ 
			if (inNom){
				questionEnCoursDeTraitement.setNom(buffer.toString()); }
			if (inEnonce){
				questionEnCoursDeTraitement.setEnonce(buffer.toString()); }
			if (inReponse){
				//[Test pour l'affichage]System.out.println(buffer.toString());
				questionEnCoursDeTraitement.setReponse(buffer.toString()); 
				inReponse = false;}
			//on réinitialise le buffer et le marqueur "inText"
			buffer = null;
			inText = false;
		}

		//Si la balise fermante n'est aucune de celle que l'on devait traiter : il ne se passe rien
		else if(!(qName.equals("name")) && !(qName.equals("questiontext")) ){}

		else{ 
			//erreur, on peut lever une exception 
			throw new SAXException("Balise "+qName+" inconnue."); 
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
