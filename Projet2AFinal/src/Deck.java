import java.io.Serializable;
import java.util.Vector;


public class Deck implements Serializable {
	private String nom;
	private Vector<Question> MesQuestions;

	public Deck(){
		this.nom ="";
		this.MesQuestions = new Vector<Question>();
	}
	
	public String getName() {return nom;}
	public Vector<Question> getMesQuestions() {return MesQuestions;}

	public void setName(String name) {this.nom = name;}
	public void setMesQuestions(Vector<Question> mesQuestions) {MesQuestions = mesQuestions;}

	public void addQuestion(Question question){MesQuestions.addElement(question);}
	
	//ajout d'une methode retirer, mélanger ?
}
