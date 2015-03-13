import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Vector;

public class Quizz implements Serializable {
	private String nom;
	private Vector<Question> MesQuestions;
	private Vector<Deck> MesDecks;

	public Quizz(){
		this.setName("");
		this.setMesQuestions(new Vector<Question>());
		this.setMesDecks(new Vector<Deck>());
	}

	public String getName() {return nom;}
	public Vector<Deck> getMesDecks() {return MesDecks;}
	public Vector<Question> getMesQuestions() {return MesQuestions;}

	public void setName(String name) {this.nom = name;}
	public void setMesQuestions(Vector<Question> mesQuestions) {MesQuestions = mesQuestions;}
	public void setMesDecks(Vector<Deck> mesDecks) {MesDecks = mesDecks;}

	public void addQuestion(Question question){MesQuestions.addElement(question);}
	public void addDeck(Deck deck){MesDecks.addElement(deck);}

	
//ajout d'une methode retirer ?

	//serialization
	public void serialization(String fichierACreer){
		try {
			FileOutputStream fos = new FileOutputStream(fichierACreer);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(this);
			oos.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//deserialization
	public Quizz deserialization(String fichierATraiter){
		FileInputStream fis;
		Quizz LeQuizzRécupéré = new Quizz();
		try {
			fis = new FileInputStream(fichierATraiter);
			ObjectInputStream ois = new ObjectInputStream(fis);
			LeQuizzRécupéré = (Quizz) ois.readObject();
			ois.close();
		} 
		catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return LeQuizzRécupéré;
	}
}

