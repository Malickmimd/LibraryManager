import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Bibliotheque bibliotheque = new Bibliotheque();
        Scanner scanner = new Scanner(System.in);

        // Menu principal
        while (true) {
            Menus.MenuPrincipal();
            int choixPrincipal = scanner.nextInt();
            scanner.nextLine(); 

            switch (choixPrincipal) {
                case 1:
                    // Gestion des livres
                    gestionLivres(bibliotheque, scanner);
                    break;
                case 2:
                    // Gestion des emprunts
                    gestionEmprunts(bibliotheque, scanner);
                    break;
                case 3:
                    // Gestion des utilisateurs
                    gestionUtilisateurs(bibliotheque, scanner);
                    break;
                case 4:
                    // Afficher les statistiques d'utilisation
                    bibliotheque.afficherStatistiques();
                    break;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
                    break;
            }
        }
    }

    public static void gestionLivres(Bibliotheque bibliotheque, Scanner scanner) {
        while (true) {
            Menus.SousMenuLivres();
            int choixLivres = scanner.nextInt();
            scanner.nextLine(); 

            switch (choixLivres) {
                case 1:
                    // Ajouter un livre
                    System.out.print("Titre du livre : ");
                    String titre = scanner.nextLine();
                    System.out.print("Auteur du livre : ");
                    String auteur = scanner.nextLine();
                    System.out.print("Année de publication : ");
                    int anneePublication = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("ISBN du livre : ");
                    String ISBN = scanner.nextLine();

                    Livre nouveauLivre = new Livre(titre, auteur, anneePublication, ISBN);
                    bibliotheque.ajouterLivre(nouveauLivre);
                    break;
                case 2:
                    // Afficher les livres
                    bibliotheque.afficherLivres();
                    break;
                case 3:
                    // Supprimer un livre
                    System.out.print("Entrez l'ISBN du livre à supprimer : ");
                    String ISBNASupprimer = scanner.nextLine();
                    bibliotheque.supprimerLivre(ISBNASupprimer);
                    break;
                case 4:
                    // Rechercher un livre par ISBN
                    System.out.print("Entrez l'ISBN du livre à rechercher : ");
                    String ISBNARechercher = scanner.nextLine();
                    Livre livreRecherche = bibliotheque.rechercherLivres(ISBNARechercher);
                    if (livreRecherche != null) {
                        System.out.println("Livre trouvé : " + livreRecherche);
                    } else {
                        System.out.println("Aucun livre trouvé avec cet ISBN.");
                    }
                    break;
                case 5:
                    // Retourner au menu principal
                    return;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
                    break;
            }
        }
    }

    public static void gestionEmprunts(Bibliotheque bibliotheque, Scanner scanner) {
        while (true) {
            Menus.SousMenuEmprunts();
            int choixEmprunts = scanner.nextInt();
            scanner.nextLine(); 

            switch (choixEmprunts) {
                case 1:
                    // Emprunter un livre
                    System.out.print("Entrez votre identifiant : ");
                    int identifiantEmprunteur = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Entrez l'ISBN du livre à emprunter : ");
                    String ISBNEmprunter = scanner.nextLine();

                    ArrayList<Utilisateur> utilisateurs = bibliotheque.rechercherUtlisateur(identifiantEmprunteur);
                    if (!utilisateurs.isEmpty()) {
                        Utilisateur emprunteur = utilisateurs.get(0);
                        Livre livreEmprunter = bibliotheque.rechercherLivres(ISBNEmprunter);
                        if (livreEmprunter != null) {
                            bibliotheque.enregistrerEmprunt(emprunteur, livreEmprunter);
                        } else {
                            System.out.println("Livre non trouvé avec cet ISBN.");
                        }
                    } else {
                        System.out.println("Utilisateur non trouvé avec cet identifiant.");
                    }
                    break;
                    case 2:
                    // Retourner un livre
                    System.out.print("Entrez votre identifiant : ");
                    int identifiantRetourneur = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Entrez l'ISBN du livre à retourner : ");
                    String ISBNRetourner = scanner.nextLine();
                
                    ArrayList<Utilisateur> utilisateursRetour = bibliotheque.rechercherUtlisateur(identifiantRetourneur);
                    if (!utilisateursRetour.isEmpty()) {
                        Utilisateur retourneur = utilisateursRetour.get(0);  // Récupérer le premier utilisateur trouvé
                        Livre livreRetourner = bibliotheque.rechercherLivres(ISBNRetourner);
                        if (livreRetourner != null) {
                            bibliotheque.enregistrerRetour(retourneur, livreRetourner);
                            System.out.println("Livre retourné avec succès");
                        } else {
                            System.out.println("Livre non trouvé avec cet ISBN.");
                        }
                    } else {
                        System.out.println("Utilisateur non trouvé avec cet identifiant.");
                    }
                    break;
                
                case 3:
                    // Afficher les emprunts de l'utilisateur
                    System.out.print("Entrez votre identifiant : ");
                    int identifiantAfficherEmprunts = scanner.nextInt();
                    scanner.nextLine(); 

                    ArrayList<Utilisateur> utilisateursAfficher = bibliotheque.rechercherUtlisateur(identifiantAfficherEmprunts);
                    if (!utilisateursAfficher.isEmpty()) {
                        Utilisateur utilisateurAfficher = utilisateursAfficher.get(0);
                        utilisateurAfficher.afficherLivresEmpruntes();
                    } else {
                        System.out.println("Utilisateur non trouvé avec cet identifiant.");
                    }
                    break;
                case 4:
                    // Retourner au menu principal
                    return;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
                    break;
            }
        }
    }

    public static void gestionUtilisateurs(Bibliotheque bibliotheque, Scanner scanner) {
        while (true) {
            Menus.SousMenuUtilisateurs();
            int choixUtilisateurs = scanner.nextInt();
            scanner.nextLine(); 

            switch (choixUtilisateurs) {
                case 1:
                    // Ajouter un utilisateur
                    System.out.print("Nom de l'utilisateur : ");
                    String nomUtilisateur = scanner.nextLine();
                    System.out.print("Prénom de l'utilisateur : ");
                    String prenomUtilisateur = scanner.nextLine();
                    System.out.print("Numéro d'identification : ");
                    int numeroIdentification = scanner.nextInt();
                    scanner.nextLine(); 

                    Utilisateur nouvelUtilisateur = new Utilisateur(nomUtilisateur, prenomUtilisateur, numeroIdentification);
                    bibliotheque.ajouterUtilisateur(nouvelUtilisateur);
                    break;
                case 2:
                    // Afficher les utilisateurs
                    bibliotheque.afficherUtilisateurs();
                    break;
                case 3:
                    // Supprimer un utilisateur
                    System.out.print("Entrez le numéro d'identification de l'utilisateur à supprimer : ");
                    int idUtilisateurASupprimer = scanner.nextInt();
                    scanner.nextLine(); 

                    ArrayList<Utilisateur> utilisateursASupprimer = bibliotheque.rechercherUtlisateur(idUtilisateurASupprimer);
                    if (!utilisateursASupprimer.isEmpty()) {
                        Utilisateur utilisateurASupprimer = utilisateursASupprimer.get(0);
                        bibliotheque.supprimerUtilisateur(utilisateurASupprimer);
                    } else {
                        System.out.println("Aucun utilisateur trouvé avec cet identifiant.");
                    }
                    break;
                case 4:
                    // Rechercher un utilisateur par identifiant
                    System.out.print("Entrez le numéro d'identification de l'utilisateur à rechercher : ");
                    int idUtilisateurARechercher = scanner.nextInt();
                    scanner.nextLine(); 

                    ArrayList<Utilisateur> utilisateursARechercher = bibliotheque.rechercherUtlisateur(idUtilisateurARechercher);
                    if (!utilisateursARechercher.isEmpty()) {
                        Utilisateur utilisateurARechercher = utilisateursARechercher.get(0);
                        System.out.println("Utilisateur trouvé : " + utilisateurARechercher);
                    } else {
                        System.out.println("Aucun utilisateur trouvé avec cet identifiant.");
                    }
                    break;
                case 5:
                    // Retourner au menu principal
                    return;
                default:
                    System.out.println("Choix invalide. Veuillez réessayer.");
                    break;
            }
        }
    }
}
