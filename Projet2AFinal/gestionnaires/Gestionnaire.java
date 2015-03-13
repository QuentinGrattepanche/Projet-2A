import org.xml.sax.helpers.DefaultHandler;



public abstract class Gestionnaire extends DefaultHandler{
	//nom du fichier final
		public String nomFichierFinal;
	
	public Gestionnaire(){
		super();
		this.nomFichierFinal = "";
	}
	
	public void setNomFichierFinal(String nom){this.nomFichierFinal = nom;}
	

}
