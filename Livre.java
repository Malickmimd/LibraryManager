public class Livre {
    private String titre;
    private String auteur;
    private int anneePublication;
    private String ISBN;

    public Livre(String titre, String auteur, int anneePublication, String ISBN) {
        this.titre = titre;
        this.auteur = auteur;
        this.anneePublication = anneePublication;
        this.ISBN = ISBN;
    }

    String getTitre() {
        return this.titre;
    }

    void setTitre(String new_titre) {
        this.titre = new_titre;
    }

    String getAuteur() {
        return this.auteur;
    }

    void setAuteur(String new_auteur) {
        this.auteur = new_auteur;
    }

    int getAnneePublication() {
        return this.anneePublication;
    }

    void setAnneePublication(int new_annee_publication) {
        this.anneePublication = new_annee_publication;
    }

    String getISBN() {
        return this.ISBN;
    }

    void setISBN(String new_ISBN) {
        this.ISBN = new_ISBN;
    }

    public String toString() {
        return "Livre{" +
                "titre='" + titre + '\'' +
                ", auteur='" + auteur + '\'' +
                ", anneePublication=" + anneePublication +
                ", ISBN='" + ISBN + '\'' +
                '}';
    }
}
