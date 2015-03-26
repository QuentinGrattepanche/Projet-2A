import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Interface_graphique {	

	/* 
	 * but : methode permettant a l utilisateur de selectionner un mode de conversion.
	 * entree : pas d arguments en entree.
	 * sortie : on renvoie en sortie l entier correspondant au choix du mode de conversion, l extension d entre et l extension de sortie dans un tableau de chaines de caracteres
	 */
	public String[] demande_mode_conversion() {

		//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		// Premiere fenetre : proposition des differents modes de conversion
		//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

		// definition des choix de mode de conversion possibles :
		String[] mode = {"Moodle vers Java", "Anki vers Java", "Java vers Moodle", "Java vers Anki"};
		// on affiche une fenetre proposant le choix, et la reponse de l utilisateur est contenue dans l entier "choix" :
		int choix = JOptionPane.showOptionDialog(null, "Quelle conversion souhaitez-vous effectuer ?","Sélection mode de conversion",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, mode, mode[2]);
		// si l utilisateur ferme la fenetre, on lui propose de quitter le programme :
		if(choix == JOptionPane.CLOSED_OPTION) {
			this.quitter("demande_mode_conversion");
		}
		//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		// Deuxieme fenetre : on demande confirmation sur le choix du mode
		//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

		// on affiche une fenetre donnant le choix ayant ete enregistre (et la reaction de l utilisateur est stockee dans l entier "option") :
		int option = JOptionPane.showConfirmDialog(null, "Vous avez demandé une conversion " + mode[choix], "Sélection mode de conversion", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		// si l utilisateur ferme la fenetre, on lui propose de quitter le programme :
		if(option == JOptionPane.CLOSED_OPTION) {
			this.quitter("demande_mode_conversion");
		}
		// si l utilisateur selectionne "annuler" on recommence le choix du mode de conversion :
		if(option == JOptionPane.CANCEL_OPTION){
			this.demande_mode_conversion();
		}
		// sinon, on continue.

		// on donne les bonnes valeurs d extensions d entree et de sortie necessaires pour le mode selectionne :
		String extension_entree = null;	
		String extension_sortie = null;	
		switch (choix) {
		case 0 :
			extension_entree="xml";
			extension_sortie="ser";
			break;
		case 1 :
			extension_entree="txt";
			extension_sortie="ser";
			break;
		case 2 :
			extension_entree="ser";
			extension_sortie="xml";
			break;
		case 3 :
			extension_entree="ser";
			extension_sortie="txt";
			break;
		}

		// En sortie du programme, on recupere l entier correspondant au choix du mode de conversion, l extension d entre et l extension de sortie dans un tableau :
		String[] sortie = {Integer.toString(choix),extension_entree,extension_sortie};
		return sortie;
	}

	/* 
	 * but : methode permettant a l utilisateur de selectionner le fichier source qu il souhaite convertir.
	 * entree : l argument en entree est le type d extension que doit avoir le fichier source.
	 * sortie : on renvoie en sortie la chaine de caractere correspondant a l emplacement du fichier source.
	 */
	public String demande_fichier_source(String extension_entree) {

		//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
		// Premiere fenetre : selection du fichier source
		//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

		// pour pouvoir parcourir les fichiers et selectionner le fichier source, on utilise JFileChooser :
		JFileChooser dialogue = new JFileChooser();
		dialogue.setDialogTitle("Sélectionner le fichier à convertir");

		// on cree un filtre pour que l utilisateur ne puisse choisir le fichier que parmi ceux qui ont la bonne extension :
		FileFilter filtre = new FileNameExtensionFilter("type pouvant être converti ("+extension_entree+")", extension_entree);
		dialogue.setFileFilter(filtre);

		// on stocke dans l entier "retour" le bouton sur lequel a clique l utilisateur :
		int retour = dialogue.showOpenDialog(null); 

		// initialisation des variables qui prendront leurs vraies valeurs dans un test "if"
		String emplacement_fichier = null;

		// si l utilisateur a clique sur OK...
		if (retour == JFileChooser.APPROVE_OPTION) {
			// on recupere l adresse du fichier selectionne :
			emplacement_fichier = dialogue.getSelectedFile().getAbsolutePath();
			
			//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
			// Deuxieme fenetre dans le cas 1 : confirmation du fichier source
			//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

			// on ouvre une fenetre de confirmation dans laquelle on donne le fichier precedemment selectionne (et la reaction de l utilisateur est stockee dans l entier "option") :
			int option = JOptionPane.showConfirmDialog(null, "Vous avez sélectionné le fichier à convertir : " + emplacement_fichier, "Sélection fichier source", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			if(option == JOptionPane.CANCEL_OPTION){
				this.demande_fichier_source(extension_entree);
			}
			if(option == JOptionPane.CLOSED_OPTION) {
				this.quitter("demande_fichier_source");
			}
		}
		// si l utilisateur a clique sur annuler (pour la premiere fenetre)
		if (retour == JFileChooser.CANCEL_OPTION) {
			
			//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
			// Deuxieme fenetre dans le cas 2 : aucun fichier source n a ete selectionne, on demande a l utilisateur de le faire
			//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
			JOptionPane.showMessageDialog(null, "Vous devez sélectionner un fichier à convertir.\r\nSi vous souhaitez quitter, fermez la fenêtre suivante.", "Sélection fichier source", JOptionPane.PLAIN_MESSAGE);
			this.demande_fichier_source(extension_entree);
		}
		// si l utilisateur a ferme la premiere fenetre
		if(retour == JOptionPane.CLOSED_OPTION) {
			this.quitter("demande_fichier_source");
		}
		return emplacement_fichier;
	}

	public String demande_fichier_destination(String extension_sortie) {
		FileFilter filtre=new FileNameExtensionFilter("Fichiers ."+extension_sortie, extension_sortie);

		JFileChooser dialogue = new JFileChooser();

		dialogue.addChoosableFileFilter(filtre);
		dialogue.setDialogTitle("Sélectionner l'emplacement cible pour le fichier converti");
		int retour = dialogue.showSaveDialog(null);
		String nom_fichier = null;
		String emplacement_fichier = null;

		if (retour == JFileChooser.APPROVE_OPTION) {
			nom_fichier = dialogue.getSelectedFile().getName();
			emplacement_fichier = dialogue.getSelectedFile().getAbsolutePath();
		}
		if (retour == JFileChooser.CANCEL_OPTION) {
			System.out.println("Vous devez sélectionner un emplacement de destination");
		}
		//on recupere l extension
		String extension = null;
		if (nom_fichier.contains(".")){
			extension = nom_fichier.substring(nom_fichier.lastIndexOf("."));
		}
		if (extension!=null && extension.equalsIgnoreCase(extension_sortie)) {
			// l extension est la bonne
		} 
		else {
			// on ajoute la bonne extension
			nom_fichier = nom_fichier+"."+extension_sortie; 
			emplacement_fichier = emplacement_fichier+"."+extension_sortie;
		}
		int option = JOptionPane.showConfirmDialog(null, "Vous avez sélectionné le fichier cible : " + emplacement_fichier, "Sélection fichier destination", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if(option == JOptionPane.NO_OPTION || 
				option == JOptionPane.CANCEL_OPTION || 
				option == JOptionPane.CLOSED_OPTION){
			this.demande_fichier_destination(extension_sortie);
		}
		return emplacement_fichier;

	}

	public Boolean demande_fin_operation() {
		Boolean retour = true;

		int option = JOptionPane.showConfirmDialog(null, "Opération terminée.\r\nVoulez-vous effectuer une nouvelle conversion ?", "Fin opération", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
		if(option == JOptionPane.NO_OPTION || 
				option == JOptionPane.CANCEL_OPTION || 
				option == JOptionPane.CLOSED_OPTION){
			retour=false;
		}
		return retour;
	}

	public void quitter(String nom_methode) {
		int option = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter ?", "Quitter", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
		if(option == JOptionPane.YES_OPTION){
			System.exit(0);
		}
		else {
			Method m = null;
			try {
				m = this.getClass().getMethod(nom_methode);
			} 
			catch (NoSuchMethodException | SecurityException e1) {
				e1.printStackTrace();
			}
			try {
				m.invoke(this);
			} 
			catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		}
	}
}