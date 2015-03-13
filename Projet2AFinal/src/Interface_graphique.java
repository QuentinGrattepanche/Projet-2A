import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Interface_graphique {    

    public String[] demande_mode_conversion() {

        String[] mode = {"Moodle vers Java", "Anki vers Java", "Java vers Moodle", "Java vers Anki"};
        JOptionPane jop = new JOptionPane(), jop2 = new JOptionPane();
        int choix = JOptionPane.showOptionDialog(null,
                "Quelle conversion souhaitez-vous effectuer ?",
                "Sélection mode de conversion",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                mode,
                mode[2]);
        JOptionPane jop3=new JOptionPane();
        int option = jop3.showConfirmDialog(null, "Vous avez demandé une conversion " + mode[choix], "Sélection mode de conversion", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(option == JOptionPane.NO_OPTION ||
                option == JOptionPane.CANCEL_OPTION ||
                option == JOptionPane.CLOSED_OPTION){
            this.demande_mode_conversion();
        }
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
        String[] sortie = {Integer.toString(choix),extension_entree,extension_sortie};
        return sortie;
    }

    public String demande_fichier_source(String extension_entree) {
        FileFilter filtre = new FileNameExtensionFilter("type pouvant être converti", extension_entree);

        JFileChooser dialogue = new JFileChooser();
        dialogue.setDialogTitle("Sélectionner le fichier à convertir");
        dialogue.setFileFilter(filtre);
        int retour = dialogue.showOpenDialog(null);
        String nom_fichier = null;
        String emplacement_fichier = null;

        JOptionPane jop3=new JOptionPane();
        int option = 0;
        if (retour == JFileChooser.APPROVE_OPTION) {
            nom_fichier = dialogue.getSelectedFile().getName();
            emplacement_fichier = dialogue.getSelectedFile().getAbsolutePath();
            option = jop3.showConfirmDialog(null, "Vous avez sélectionné le fichier à convertir : " + emplacement_fichier, "Sélection fichier source", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        }
        if (retour == JFileChooser.CANCEL_OPTION) {
            System.out.println("Vous devez sélectionner un fichier source");
            option = jop3.showConfirmDialog(null, "Vous devez sélectionner un fichier à convertir.", "Sélection fichier source", JOptionPane.OK_OPTION, JOptionPane.QUESTION_MESSAGE);
            this.demande_fichier_source(extension_entree);
        }        
        
        if(option == JOptionPane.NO_OPTION ||
                option == JOptionPane.CANCEL_OPTION ||
                option == JOptionPane.CLOSED_OPTION){
            this.demande_fichier_source(extension_entree);
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
            System.out.println("Vous devez sélectionner un fichier source");
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
        JOptionPane jop3=new JOptionPane();
        int option = jop3.showConfirmDialog(null, "Vous avez sélectionné le fichier cible : " + emplacement_fichier, "Sélection fichier destination", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(option == JOptionPane.NO_OPTION ||
                option == JOptionPane.CANCEL_OPTION ||
                option == JOptionPane.CLOSED_OPTION){
            this.demande_fichier_destination(extension_sortie);
        }
        return emplacement_fichier;

    }

    public Boolean demande_fin_operation() {
        JOptionPane jop = new JOptionPane();  
        Boolean retour = true;

        int option = jop.showConfirmDialog(null, "Opération terminée.\r\nVoulez-vous effectuer une nouvelle conversion ?", "Lancement de l'animation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(option == JOptionPane.NO_OPTION ||
                option == JOptionPane.CANCEL_OPTION ||
                option == JOptionPane.CLOSED_OPTION){
            retour=false;
        }
        return retour;
    }
}
  