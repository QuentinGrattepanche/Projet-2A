import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class GestionnaireJavaMoodle extends GestionnaireEcriture{
	//nom du fichier final
	//[changement heredite]public String nomFichierFinal;

	public GestionnaireJavaMoodle(){
		super();
		//[changement heredite]this.nomFichierFinal = "";
	}

	//[changement heredite]public void setNomFichierFinal(String nom){this.nomFichierFinal = nom;} 

	public void ecrireTexteAuFormat(Quizz quizz) {
		//File texte_question = new File ("Quizz "+quizz.getName()+".xml");
		File texte_question = new File (nomFichierFinal);

		FileWriter fw = null;
		try {
			fw = new FileWriter (texte_question);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}

		try {
			fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
					+"<quiz>\r\n");
		} 
		catch (IOException e1) {
			e1.printStackTrace();
		}

		for(Question question : quizz.getMesQuestions()) {
			///////////test
			if(question!=null){
				switch (question.getType()) {
				//	case "Multichoix" :
				//		break;
				case "QuestionSimple" :
					ecrireQuestionSimple(question, fw);
					break;
				case "VraiFaux" :
					ecrireQuestionVraiFaux(question, fw);
					break;
				}
			}
		}

		for(Deck deck : quizz.getMesDecks()) {
			for(Question question : deck.getMesQuestions()) {
				///////////test
				if(question!=null){
					switch (question.getType()) {
					//	case "Multichoix" :
					//		break;
					case "QuestionSimple" :
						ecrireQuestionSimple(question, fw);
						break;
					case "VraiFaux" :
						ecrireQuestionVraiFaux(question, fw);
						break;
					}
				}
			}
		}

		try {
			fw.write("</quiz>");
			fw.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void ecrireQuestionSimple(Question question, OutputStreamWriter fw) {
		if(question.getReponse().equals("true")){	
		};
		try {
			fw.write("<!-- question: "+question.getId()+"  -->\r\n"+"<question type=\"truefalse\">\r\n"
					+"<name>\r\n"
					+"<text>"+question.getNom()+"</text>\r\n"
					+"</name>\r\n"
					+"<questiontext format=\"html\">\r\n"
					+"<text><![CDATA[<p>"+question.getEnonce()+"</p>]]></text>\r\n"
					+"</questiontext>\r\n"
					+"<generalfeedback format=\"html\">\r\n"
					+"<text></text>\r\n"
					+"</generalfeedback>\r\n"
					+"<defaultgrade>1.0000000</defaultgrade>\r\n"
					+"<penalty>1.0000000</penalty>\r\n"
					+"<answer fraction=\"100\" format=\"moodle_auto_format\">\r\n"
					+"<text>"+question.getReponse()+"</text>\r\n"
					+"<feedback format=\"html\">\r\n"
					+"<text></text>\r\n"
					+"</feedback>\r\n"
					+"</answer>\r\n");

			for(String reponse : ((QuestionSimple) question).getReponsesAlternatives()) {
				fw.write("<answer fraction=\"100\" format=\"moodle_auto_format\">\r\n"
						+"<text>"+reponse+"</text>\r\n"
						+"<feedback format=\"html\">\r\n"
						+"<text></text>\r\n"
						+"</feedback>\r\n"
						+"</answer>\r\n");
			}
			fw.write("</question>\r\n\r\n");
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void ecrireQuestionVraiFaux(Question question, OutputStreamWriter fw) {
		int fraction_true = 0;
		if(question.getReponse().equals("true")){
			fraction_true = 100;			
		}
		int fraction_false = 100 - fraction_true;
		try {
			fw.write("<!-- question: "+question.getId()+"  -->\r\n"+"<question type=\"truefalse\">\r\n"
					+"<name>\r\n"
					+"<text>"+question.getNom()+"</text>\r\n"
					+"</name>\r\n"
					+"<questiontext format=\"html\">\r\n"
					+"<text><![CDATA[<p>"+question.getEnonce()+"</p>]]></text>\r\n"
					+"</questiontext>\r\n"
					+"<generalfeedback format=\"html\">\r\n"
					+"<text></text>\r\n"
					+"</generalfeedback>\r\n"
					+"<defaultgrade>1.0000000</defaultgrade>\r\n"
					+"<penalty>1.0000000</penalty>\r\n"
					+"<answer fraction=\""+fraction_true+"\" format=\"moodle_auto_format\">\r\n"
					+"<text>true</text>\r\n"
					+"<feedback format=\"html\">\r\n"
					+"<text></text>\r\n"
					+"</feedback>\r\n"
					+"</answer>\r\n"
					+"<answer fraction=\""+fraction_false+"\" format=\"moodle_auto_format\">\r\n"
					+"<text>false</text>"
					+"<feedback format=\"html\">\r\n"
					+"<text></text>\r\n"
					+"</feedback>\r\n"
					+"</answer>\r\n"
					+"</question>\r\n\r\n"
					);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}   

