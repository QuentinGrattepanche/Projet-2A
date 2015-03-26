import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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
		File texte_question = new File (nomFichierFinal);

		FileWriter fw = null;
		try {
			fw = new FileWriter (texte_question);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}

		try {
			fw.write("# �nonc� ; r�ponse\r\n");
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
						System.out.println("hey!");
						question.setEnonce("Vrai ou faux ?\r\n"+question.getEnonce());
						break;

					case "QuestionSimple" :
						System.out.println("Oh...");
						Vector<String> autres_reponses = ((QuestionSimple) question).getReponsesAlternatives();
						question.setReponse("R�ponse : "+question.getReponse()
								+"\r\nAutres r�ponses accept�es : "
								+autres_reponses.toString());
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
							question.setReponse("R�ponse : "+question.getReponse()
									+". Autres r�ponses accept�es : "
									+autres_reponses.toString());
							break;
						}
						/////test
						fw.write (question.getEnonce());
						fw.write (" ; ");
						fw.write (question.getReponse()+"\r\n");
					}
				}
			}
			fw.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("A �crit (pour Anki) dans le fichier "+nomFichierFinal);
	}
}
