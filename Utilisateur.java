import java.util.ArrayList;

public class Utilisateur {
    private String nom;
    private String prenom;
    private int numeroIdentification;
    private ArrayList<Livre> livresEmpruntes;

    public Utilisateur(String nom,String prenom, int numeroIdentification) {
        this.nom = nom;
        this.prenom = prenom;
        this.numeroIdentification = numeroIdentification;
        this.livresEmpruntes = new ArrayList<>();
    }

    ArrayList<Livre> getLivresEmpruntes() {
        return this.livresEmpruntes;
    }

    String getNom() {
        return this.nom;
    }
    String getPrenom() {
        return this.prenom;
    }

    int getNumeroIdentification() {
        return this.numeroIdentification;
    }

    public void ajouterUtilisateur(Utilisateur utilisateur) {

    }

    // Méthode pour emprunter un livre
    public void EmpruntLivre(Livre livre) {
        livresEmpruntes.add(livre);
        System.out.println("Le livre " + livre.getTitre() + " a été emprunté par " + this.nom);
    }

    // Méthode pour retourner un livre
    public Livre RetournerLivre(Livre livre) {
        Livre livreARetourner = null;
        if (livresEmpruntes.contains(livre)) {
            livresEmpruntes.remove(livre);  // Retirer le livre de la liste des livres empruntés
            livreARetourner = livre;  // Assigner le livre retourné
            System.out.println("Le livre " + livre.getTitre() + " a été retourné par " + this.nom);
        } else {
            System.out.println("Ce livre n'est pas emprunté par " + this.nom);
        }
        return livreARetourner;
    }
    

    // Méthode pour afficher les livres empruntés par l'utilisateur
    public void afficherLivresEmpruntes() {
        if (livresEmpruntes.isEmpty()) {
            System.out.println("Aucun livre emprunté.");
        } else {
            System.out.println("Livres empruntés par " + this.nom + ":");
            for (Livre livre : livresEmpruntes) {
                System.out.println("- " + livre.getTitre());
            }
        }
    }

    public String toString() {
        return "Utilisateur{" +
                "Nom='" + nom + '\'' +
                ", Prenom='" + prenom + '\'' +
                ", identification=" + numeroIdentification +
                '}';
    }
}