public abstract class GestionnaireEcriture extends Gestionnaire{
	//nom du fichier final
	public String nomFichierFinal;

	public void setNomFichierFinal(String nom){this.nomFichierFinal = nom;}

	public GestionnaireEcriture(){
		super();
		//[changement heredite]this.nomFichierFinal = "";
	}
	
	abstract void ecrireTexteAuFormat(Quizz quizz);
}
