import java.util.Vector;

public class QuestionSimple extends Question{
	private Vector<String> reponsesAlternatives;
	public QuestionSimple(){
		super();
		this.setType("QuestionSimple");
		this.reponsesAlternatives = new Vector<String>();
	}
	public String ajouterReponseAlternative(String reponse) {
		reponsesAlternatives.add(reponse);
		return reponse;
	}
	public Vector<String> getReponsesAlternatives() {return reponsesAlternatives;}
}


