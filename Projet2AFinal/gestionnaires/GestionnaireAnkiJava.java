
///package supprimertexte;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

public class GestionnaireAnkiJava extends GestionnaireLecture{
	//résultats de notre parsing 
	//[changement heredite]private Quizz quiz;  
	private String TypeDEspaceur[] =  {";",",",""};
	//[changement heredite]public String nomFichierFinal;
	public static final String UTF8_BOM = "\u00EF\u00BB\u00BF";

	public GestionnaireAnkiJava(){
		super();
		this.quiz = new Quizz();
		//[changement heredite]this.nomFichierFinal = "";
	}

	//[changement heredite]public void setNomFichierFinal(String nom){this.nomFichierFinal = nom;} 

	public void lectureTextAuFormat(BufferedReader reader) throws IOException{
		String line; //l'ensemble de la ligne récupérée par le lecteur
		String espaceur = null;//type d'espaceur dans le fichier Anki
		int cpt=0;

		while((line = reader.readLine()) != null){//tant qu'il y a une ligne...

			//cas de la première ligne :
			// pour enlever le byte order marker (bom) lorsqu il est present :
			if (line.startsWith(UTF8_BOM)) {
				line = line.substring(3);
			}

			//La premiere ligne du fichier anki, quand elle commence par # definit
			// le marqueur utilise dans la suite du fichier, parmi "," , "tabulation"
			// et ";"
			if (line.startsWith("#")) {
				if(line.contains(";")) {
					espaceur = ";";
				}
				else {
					if(line.contains(",")) {
						espaceur = ",";
					}
					else {
						if(line.contains("\t")) {
							espaceur = "\t";
						}
					}
				}
			}
			else{
				//Pour les lignesz suivantes, correspondant aux questions
				Question LaQuestion = new Question();

				StringTokenizer separateur = new StringTokenizer(line,espaceur);
				//[Test pour l'affichage]System.out.println(separateur.nextToken());
				//[Test pour l'affichage]System.out.println(separateur.nextToken());
				//[Test pour l'affichage]System.out.println(separateur.nextToken());
				LaQuestion.setNom("Question"+cpt);
				LaQuestion.setEnonce(separateur.nextToken());
				LaQuestion.setReponse(separateur.nextToken());
				//[Test pour l'affichage]System.out.println(LaQuestion.getEnonce());

				//gestion des marqueurs :  creation des decks
				String ensembleDesMarqueurs = separateur.nextToken();
				if(ensembleDesMarqueurs != null){//s il existe un troisieme bloc pour les marqueurs
					StringTokenizer separateurDeck = new StringTokenizer(ensembleDesMarqueurs," ");
					String unMarqueur;
					//[Test pour l'affichage]System.out.println(separateurDeck.nextToken());
					Boolean deckTrouvé = false;
					while( separateurDeck.hasMoreTokens() && (unMarqueur = separateurDeck.nextToken()) != null){//tant qu'il y a un marqueur de deck...
						//[Test pour l'affichage]System.out.println(unMarqueur + 1);
						if(!(quiz.getMesDecks().isEmpty())){
							//[Test pour l'affichage]System.out.println(quiz.getMesDecks().isEmpty());
							for(Deck a : quiz.getMesDecks()) {
								if(unMarqueur.equals(a.getName())){
									a.addQuestion(LaQuestion);
									deckTrouvé = true;
								}
							}
						}
						if(deckTrouvé == false){//si aucun deck ne porte ce nom

							Deck unDeck= new Deck();
							unDeck.setName(unMarqueur);
							quiz.addDeck(unDeck);
							unDeck.addQuestion(LaQuestion);
						}
						else{}

					}

				}
				else{
					quiz.addQuestion(LaQuestion);
				}
			}
			cpt++;
		}
		reader.close();

		//sérialization
		//SerializationDeserialization Test = new SerializationDeserialization();
		//Test.serialization(quiz, nomFichierFinal);


		//ou
		quiz.serialization(nomFichierFinal);

		//Deserialization (TEST)
		//Test.testDeserialization();
	}
}
