import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class GestionnaireJavaAnki extends GestionnaireEcriture{
    //nom du fichier final
    //[changement heredite]public String nomFichierFinal;

    public GestionnaireJavaAnki(){
        super();
        //[changement heredite]this.nomFichierFinal = "";
    }

    //[changement heredite]public void setNomFichierFinal(String nom){this.nomFichierFinal = nom;}

    public void ecrireTexteAuFormat(Quizz quizz) {
        //File texte_question = new File ("Quizz "+quizz.getName()+".txt");
        File texte_question = new File (nomFichierFinal);

        FileWriter fw = null;
        try {
            fw = new FileWriter (texte_question);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            fw.write("# énoncé ; réponse\r\n");
            System.out.println(quizz.getMesQuestions());
            for(Question question : quizz.getMesQuestions()) {

                ///////////test
                if(question!=null){
                    /////test
                    fw.write (question.getEnonce());
                    fw.write (" ; ");
                    fw.write (question.getReponse()+"\r\n");
                }
            }
            for(Deck deck : quizz.getMesDecks()){
                for(Question question : deck.getMesQuestions()) {

                    ///////////test
                    if(question!=null){
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("A écrit (pour Anki) dans le fichier "+nomFichierFinal);

    }

}
  