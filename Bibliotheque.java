import java.util.ArrayList;
import java.util.HashMap;

public class Bibliotheque {
    public int MAX_LIVRES_EMPRUNTES = 3; // nombre max de livres empruntés par un utilisateur
    private ArrayList<Livre> listeLivres;
    private ArrayList<Utilisateur> listeUtilisateurs;
    private HashMap<Utilisateur, ArrayList<Livre>> empruntsUtilisateurs;

    public Bibliotheque() {
        this.listeLivres = new ArrayList<>();
        this.listeUtilisateurs = new ArrayList<>();
        this.empruntsUtilisateurs = new HashMap<>();
    }

    // Méthode pour ajouter un livre à la bibliothèque
    public void ajouterLivre(Livre livre) {
        listeLivres.add(livre);
        System.out.println("Le livre " + livre.getTitre() + " a été ajouté à la bibliothèque.");
    }
    // My own personnal update
    public void afficherLivres() {
        for (Livre livre : listeLivres) {
            System.out.println(livre);
        }
    }

    // Méthode pour supprimer un livre de la bibliothèque
    public void supprimerLivre(String ISBN) {
        Livre livreASupprimer = rechercherLivres(ISBN);
                
        // Vérifier si le livre correspondant à l'ISBN a été trouvé
        if (livreASupprimer != null) {
            // Supprimer le livre de la liste
            listeLivres.remove(livreASupprimer);
            System.out.println("Le livre \"" + livreASupprimer.getTitre() + "\" a été supprimé de la bibliothèque.");
        } else {
            System.out.println("Aucun livre avec l'ISBN \"" + ISBN + "\" n'est présent dans la bibliothèque.");
        }
    }
    

    // Méthode pour rechercher un livre par ISBN
    public Livre rechercherLivres(String ISBN) {
        Livre livreTrouve = null;
        for (Livre livre : listeLivres) {
            if (livre.getISBN().equals(ISBN)) {
                livreTrouve = livre;
                break; // Arrêter la boucle une fois le livre trouvé
            }
        }
        return livreTrouve;
    }


    // Méthode pour ajouter un utilisateur à la bibliothèque
    public void ajouterUtilisateur(Utilisateur utilisateur) {
        listeUtilisateurs.add(utilisateur);
        System.out.println("L'utilisateur " + utilisateur.getPrenom() + " " + utilisateur.getNom() + " a été ajouté.");
    }

    // Méthode pour afficher les utilisateurs
    public void afficherUtilisateurs() {
        for (Utilisateur utilisateur : listeUtilisateurs) {
            System.out.println(utilisateur);
        }
    }
    // Méthode pour supprimer un utilisateur
    public void supprimerUtilisateur(Utilisateur utilisateur) {
        if (listeUtilisateurs.contains(utilisateur)) {
            listeUtilisateurs.remove(utilisateur);
            System.out.println("L'utilisateur " + utilisateur.getPrenom() + " " + utilisateur.getNom() + " a été supprimé de la bibliothèque.");
        } else {
            System.out.println("L'utilisateur " + utilisateur.getPrenom() + " " + utilisateur.getNom() + " n'est pas présent dans la bibliothèque.");
        }
    }
    
    // Méthode pour rechercher un utilisateur par son identifiant
    public ArrayList<Utilisateur> rechercherUtlisateur(int identifiant) {
        ArrayList<Utilisateur> UtilisateurTrouve = new ArrayList<>();
        for (Utilisateur utilisateur : listeUtilisateurs) {
            if (utilisateur.getNumeroIdentification() == identifiant) {
                UtilisateurTrouve.add(utilisateur);
            }
        }
        return UtilisateurTrouve;
    }


    public boolean verifierEligibilite(Utilisateur utilisateur) {
        return utilisateur.getLivresEmpruntes().size() < MAX_LIVRES_EMPRUNTES;
    }

    public void enregistrerEmprunt(Utilisateur utilisateur, Livre livre) {
        // Vérifier l'éligibilité de l'utilisateur
        if (verifierEligibilite(utilisateur)) {
            // Vérifier si le livre est disponible dans la bibliothèque
            if (listeLivres.contains(livre)) {
                // Retirer le livre de la bibliothèque
                listeLivres.remove(livre);
                // Ajouter le livre emprunté à la liste de l'utilisateur
                utilisateur.EmpruntLivre(livre);
                // Enregistrer l'emprunt
                if (!empruntsUtilisateurs.containsKey(utilisateur)) {
                    empruntsUtilisateurs.put(utilisateur, new ArrayList<>());
                }
                empruntsUtilisateurs.get(utilisateur).add(livre);
                System.out.println("Emprunt enregistré : " + utilisateur.getNom() + " a emprunté " + livre.getTitre());
            } else {
                System.out.println("Le livre demandé n'est pas disponible.");
            }
        } else {
            System.out.println("Vous avez atteint la limite du nombre de livres empruntales simultanément.");
        }
    }

    public void enregistrerRetour(Utilisateur utilisateur, Livre livre) {
        if (empruntsUtilisateurs.containsKey(utilisateur) && empruntsUtilisateurs.get(utilisateur).contains(livre)) {
            // Retourner le livre par l'utilisateur
            Livre livreRetourne = utilisateur.RetournerLivre(livre);
    
            if (livreRetourne != null) {
                // Ajouter le livre retourné à la bibliothèque
                listeLivres.add(livreRetourne);
    
                // Enregistrer le retour en retirant le livre de la liste des emprunts de l'utilisateur
                empruntsUtilisateurs.get(utilisateur).remove(livreRetourne);
                System.out.println("Retour enregistré : " + utilisateur.getNom() + " a retourné " + livreRetourne.getTitre());
            } else {
                System.out.println("Une erreur s'est produite lors du retour du livre.");
            }
        } else {
            System.out.println("Vous n'avez pas emprunté ce livre.");
        }
    }
    

    public void afficherStatistiques() {
        System.out.println("Statistiques de la bibliothèque :");
        System.out.println("Nombre total de livres : " + listeLivres.size());
        int totalEmprunts = 0;
        for (ArrayList<Livre> emprunts : empruntsUtilisateurs.values()) {
            totalEmprunts += emprunts.size();
        }
        System.out.println("Nombre total de livres empruntés : " + totalEmprunts);
        // Ajouter d'autres statistiques selon les besoins
    }
}