import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
<<<<<<< HEAD
=======

import javax.swing.JFileChooser;
>>>>>>> BrancheQuentin
import javax.swing.JOptionPane;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

public class Lancement {

	//constructeur
	public Lancement(String uri) throws SAXException, IOException, ParserConfigurationException {

	}

	//main
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException{
		//creation de l interface graphique
		Interface_graphique ig = new Interface_graphique();
		//lancement de la boucle
		boucle(ig);        
	}

	static void boucle(Interface_graphique ig) {

		//demande d'opération
		String[] demande = ig.demande_mode_conversion();
		String mode=demande[0];
		String extension_entree=demande[1];
		String extension_sortie=demande[2];

		//demande de fichier source
		String emplacement_source = ig.demande_fichier_source(extension_entree);

		//demande du nom du fichier à créer
		String emplacement_destination = ig.demande_fichier_destination(extension_sortie);

		switch (mode) {
		case "0" :
			try {
				lancementMoodleJava(emplacement_source,emplacement_destination);
//TEST Quentin
				String message = "Vous avec sauvegardez : \n\r";
				Quizz quizzRecup = new Quizz();
				message = message + "Quizz : \n\r\n\r";
				quizzRecup = quizzRecup.deserialization(emplacement_destination);
				for(Question question : quizzRecup.getMesQuestions()) {
					if(question!=null){
						message = message + question.getEnonce() +
								" ; "+
								question.getReponse()+"\r\n";
					}
				}
				message = message + "\n\r\n\r";
				for(Deck deck : quizzRecup.getMesDecks()){
					message = message + "\t" + deck.getName() + "\n\r\n\r";
					for(Question question : deck.getMesQuestions()) {
						if(question!=null){
							message = message + "\t" + question.getEnonce()
									+ " ; " +
									question.getReponse() +"\r\n";
						}
					}
					message = message + "\n\r\n\r";
				}
				String titre = "Sauvegarde dans le .ser";

				JFileChooser dialogue = new JFileChooser();
				dialogue.setDialogTitle("Sauvegarde dans le .ser\n\r");
				JOptionPane jop3=new JOptionPane();
				jop3.showConfirmDialog(null, message, titre, JOptionPane.OK_CANCEL_OPTION);

				//FIN TEST QUentin
			} 
			catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "1" :
			try {
				lancementAnkiJava(emplacement_source,emplacement_destination);
//TEST Quentin
				String message = "Vous avec sauvegardez : \n\r";
				Quizz quizzRecup = new Quizz();
				message = message + "Quizz : \n\r\n\r";
				quizzRecup = quizzRecup.deserialization(emplacement_destination);
				for(Question question : quizzRecup.getMesQuestions()) {
					if(question!=null){
						message = message + question.getEnonce() +
								" ; "+
								question.getReponse()+"\r\n";
					}
				}
				message = message + "\n\r\n\r";
				for(Deck deck : quizzRecup.getMesDecks()){
					message = message + "\t" + deck.getName() + "\n\r\n\r";
					for(Question question : deck.getMesQuestions()) {
						if(question!=null){
							message = message + "\t" + question.getEnonce()
									+ " ; " +
									question.getReponse() +"\r\n";
						}
					}
					message = message + "\n\r\n\r";
				}
				String titre = "Sauvegarde dans le .ser";

				JFileChooser dialogue = new JFileChooser();
				dialogue.setDialogTitle("Sauvegarde dans le .ser\n\r");
				JOptionPane jop3=new JOptionPane();
				jop3.showConfirmDialog(null, message, titre, JOptionPane.OK_CANCEL_OPTION);

				//FIN TEST QUentin

			} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
			break;
		case "2" :
			lancementJavaMoodle(emplacement_source,emplacement_destination);
			break;
		case "3"	 :
			lancementJavaAnki(emplacement_source,emplacement_destination);
			break;
		default :
			System.out.println("Erreur...");
		}
		//On demande si on a finit :
		if(ig.demande_fin_operation()) {
			boucle(ig);
		}
		else {
			JOptionPane.showMessageDialog(null, "Merci, au revoir !", "Quitter", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	static void lancementMoodleJava(String emplacement_source, String emplacement_destination) throws ParserConfigurationException, SAXException, IOException{

		//on crée et instancie le parseur
		SAXParserFactory fabrique = SAXParserFactory.newInstance(); 
		SAXParser parseur = fabrique.newSAXParser(); 

		//fichier xml que l'on veut parser
		File fichier = new File(emplacement_source); 

		//creation du gestionnaire et parsing
		GestionnaireMoodleJava gestionnaire = new GestionnaireMoodleJava();
		gestionnaire.setNomFichierFinal(emplacement_destination);
		parseur.parse(fichier, gestionnaire);

	}

	static void lancementAnkiJava(String emplacement_source, String emplacement_destination) throws IOException{
		//fichier est le nom du fichier à traiter

		BufferedReader reader = new BufferedReader(new FileReader(emplacement_source));
		GestionnaireAnkiJava parseur = new GestionnaireAnkiJava();
		parseur.setNomFichierFinal(emplacement_destination);
		parseur.lectureTextAuFormat(reader);
	}

	static void lancementJavaMoodle(String emplacement_source, String emplacement_destination){
		GestionnaireJavaMoodle gestionnaire = new GestionnaireJavaMoodle();
		gestionnaire.setNomFichierFinal(emplacement_destination);
		//SerializationDeserialization Serializeur = new SerializationDeserialization();

		//on deserialise
		//Quizz quizz = Serializeur.deserialization();
		Quizz quizz = new Quizz();
		quizz = quizz.deserialization(emplacement_source);
		System.out.println(quizz.getMesQuestions());


		//on transforme
		gestionnaire.ecrireTexteAuFormat(quizz);
	}

	static void lancementJavaAnki(String emplacement_source, String emplacement_destination){
		GestionnaireJavaAnki gestionnaire = new GestionnaireJavaAnki();
		gestionnaire.setNomFichierFinal(emplacement_destination);
		//SerializationDeserialization Serializeur = new SerializationDeserialization();

		//on deserialise
		//Quizz quizz = Serializeur.deserialization();
		Quizz quizz = new Quizz();
		quizz = quizz.deserialization(emplacement_source);
		System.out.println(quizz.getMesQuestions());

		//on transforme
		gestionnaire.ecrireTexteAuFormat(quizz);
	}
}
