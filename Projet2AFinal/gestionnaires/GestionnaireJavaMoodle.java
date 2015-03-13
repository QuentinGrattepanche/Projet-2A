import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


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
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            fw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n"
                    +"<quiz>\r\n");
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        for(Question question : quizz.getMesQuestions()) {
            ///////////test
            if(question!=null){
                /////test
                if(question.getReponse() == "false") {
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
                                +"<hidden>0</hidden>\r\n"
                                +"<answer fraction=\"0\" format=\"moodle_auto_format\">\r\n"
                                +"<text>true</text>\r\n"
                                +"<feedback format=\"html\">\r\n"
                                +"<text></text>\r\n"
                                +"</feedback>\r\n"
                                +"</answer>\r\n"
                                +"<answer fraction=\"100\" format=\"moodle_auto_format\">\r\n"
                                +"<text>false</text>"
                                +"<feedback format=\"html\">\r\n"
                                +"<text></text>\r\n"
                                +"</feedback>\r\n"
                                +"</answer>\r\n"
                                +"</question>\r\n\r\n"

                                );

                    }
                    catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                else {
                    try{
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
                                +"<penalty></penalty>\r\n"
                                +"<hidden>0</hidden>\r\n"
                                +"<answer fraction=\"100\" format=\"moodle_auto_format\">\r\n"
                                +"<text>true</text>\r\n"
                                +"<feedback format=\"html\">\r\n"
                                +"<text></text>\r\n"
                                +"</feedback>\r\n"
                                +"</answer>\r\n"
                                +"<answer fraction=\"0\" format=\"moodle_auto_format\">\r\n"
                                +"<text>false</text>"
                                +"<feedback format=\"html\">\r\n"
                                +"<text></text>\r\n"
                                +"</feedback>\r\n"
                                +"</answer>\r\n"
                                +"</question>\r\n\r\n"

                                );
                    }
                    catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }
        
        for(Deck deck : quizz.getMesDecks()) {
            for(Question question : deck.getMesQuestions()) {
                ///////////test
                if(question!=null){
                    /////test
                    if(question.getReponse() == "false") {
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
                                    +"<hidden>0</hidden>\r\n"
                                    +"<answer fraction=\"0\" format=\"moodle_auto_format\">\r\n"
                                    +"<text>true</text>\r\n"
                                    +"<feedback format=\"html\">\r\n"
                                    +"<text></text>\r\n"
                                    +"</feedback>\r\n"
                                    +"</answer>\r\n"
                                    +"<answer fraction=\"100\" format=\"moodle_auto_format\">\r\n"
                                    +"<text>false</text>"
                                    +"<feedback format=\"html\">\r\n"
                                    +"<text></text>\r\n"
                                    +"</feedback>\r\n"
                                    +"</answer>\r\n"
                                    +"</question>\r\n\r\n"

                                    );

                        }
                        catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }

                    else {
                        try{
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
                                    +"<penalty></penalty>\r\n"
                                    +"<hidden>0</hidden>\r\n"
                                    +"<answer fraction=\"100\" format=\"moodle_auto_format\">\r\n"
                                    +"<text>true</text>\r\n"
                                    +"<feedback format=\"html\">\r\n"
                                    +"<text></text>\r\n"
                                    +"</feedback>\r\n"
                                    +"</answer>\r\n"
                                    +"<answer fraction=\"0\" format=\"moodle_auto_format\">\r\n"
                                    +"<text>false</text>"
                                    +"<feedback format=\"html\">\r\n"
                                    +"<text></text>\r\n"
                                    +"</feedback>\r\n"
                                    +"</answer>\r\n"
                                    +"</question>\r\n\r\n"

                                    );
                        }
                        catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        
        
        
        
        
        
        try {
            fw.write("</quiz>");
            fw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("A écrit (pour moodle) dans le fichier "+nomFichierFinal);
    }   
}