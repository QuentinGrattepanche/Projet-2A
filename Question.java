import java.io.Serializable;
import java.util.Vector;

public class Question implements Serializable{ 
	//identifiant de la question
	private int id; 
	//nom, enonce, reponse de la question
	private String nom, enonce, reponse; 
  
	//indices et explications de la question
	private Vector<String> liste_indices;
	private Vector<String> liste_explications;
	
	
	//constructeur
	public Question(){
		this.nom = "";
		this.enonce = "";
		this.reponse = "";
		this.liste_indices = new Vector<String>();
		this.liste_explications = new Vector<String>();
	} 
  
	//recuperer des info de la question
	public int getId(){return id;} 
	public String getNom(){return nom;} 
	public String getEnonce(){return enonce;} 
	public String getReponse(){return reponse;}
	public Vector<String> getListe_Indices(){return liste_indices;}
	public Vector<String> getliste_Explications(){return liste_explications;}
	
  
	//changer les attributs de la question
	public void setId(int id){this.id = id;} 
	public void setNom(String nom){this.nom = nom;} 
	public void setEnonce(String enonce){this.enonce = enonce;} 
	public void setReponse(String reponse){this.reponse = reponse;}
	public void setListe_Indices(Vector<String> liste_indices){this.liste_indices = liste_indices;} 
	public void setListe_Explications(Vector<String> liste_explications){this.liste_explications = liste_explications;} 
  
	//??
	public String toString(){ 
		return new StringBuffer("Nom : ").append(nom).append(", ") 
			.append("Enonce : ").append(enonce).append(", ") 
			.append("Reponse : ").append(reponse).append(", ") 
			.append("Indices : ").append(liste_indices).append(", ") 
			.append("Explications : ").append(liste_explications) 
			.toString(); 
	} 
}