
public abstract class GestionnaireEcriture extends Gestionnaire{
	//nom du fichier final
	//[changement heredite]public String nomFichierFinal;

	//[changement heredite]public void setNomFichierFinal(String nom){this.nomFichierFinal = nom;}

	public GestionnaireEcriture(){
		super();
		//[changement heredite]this.nomFichierFinal = "";
	}
	
	abstract void ecrireTexteAuFormat(Quizz quizz);
}
