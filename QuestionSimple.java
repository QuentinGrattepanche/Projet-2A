import java.util.Vector;

public class QuestionSimple extends Question{
	private Vector<String> reponsesAlternatives;
	public QuestionSimple(){
		super();
		this.setType("Multichoix");
		this.reponsesAlternatives = new Vector<String>();
	}
	String ajouterReponseAlternative(String reponse) {
		reponsesAlternatives.add(reponse);
		return reponse;
	}
}


