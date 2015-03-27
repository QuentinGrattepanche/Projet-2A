import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Vector;

public class GestionnaireJavaAnki extends GestionnaireEcriture{
	//nom du fichier final
	//[changement heredite]public String nomFichierFinal;

	public GestionnaireJavaAnki(){
		super();
		//[changement heredite]this.nomFichierFinal = "";
	}
	//[changement heredite]public void setNomFichierFinal(String nom){this.nomFichierFinal = nom;} 

	public void ecrireTexteAuFormat(Quizz quizz) {
				
		BufferedWriter fw = null;
		try {
			fw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(nomFichierFinal), "UTF-8"));
		} 
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			fw.write("# énoncé ; réponse\r\n");
			System.out.println(quizz.getMesQuestions());
			for(Question question : quizz.getMesQuestions()) {

				///////////test
				if(question!=null){
					/////test
					System.out.println(question.getType());
					switch (question.getType()) {
					case "Multichoix" :
						break;

					case "VraiFaux" :
						question.setEnonce("Vrai ou faux ? "+question.getEnonce());
						break;

					case "QuestionSimple" :
						Vector<String> autres_reponses = ((QuestionSimple) question).getReponsesAlternatives();
						question.setReponse("Réponse : "+question.getReponse()+". Autres réponses acceptées : "+autres_reponses.toString()+".");
						break;
					}
					fw.write (question.getEnonce());
					fw.write (" ; ");
					fw.write (question.getReponse()+"\r\n");
				}
			}
			for(Deck deck : quizz.getMesDecks()){
				for(Question question : deck.getMesQuestions()) {

					///////////test
					if(question!=null){
						switch (question.getType()) {
						case "Multichoix" :
							break;

						case "VraiFaux" :
							question.setEnonce("Vrai ou faux ? "+question.getEnonce());
							break;

						case "QuestionSimple" :
							Vector<String> autres_reponses = ((QuestionSimple) question).getReponsesAlternatives();
							question.setReponse("Réponse : "+question.getReponse()+". Autres réponses acceptées : "+autres_reponses.toString()+".");
							break;
						}
						/////test
						fw.write (question.getEnonce());
						fw.write (" ; ");
						fw.write (question.getReponse()+" ; "+deck.getName()+"\r\n");
					}
				}
			}
			fw.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("A écrit (pour Anki) dans le fichier "+nomFichierFinal);
	}
}
