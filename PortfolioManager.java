import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

class ObjetUnique {
    String nom;
    String contenu;

    public ObjetUnique(String nom, String contenu) {
        this.nom = nom;
        this.contenu = contenu;
    }

    public String lireContenu() {
        return this.contenu;
    }
}

class ObjetNonUnique {
    String nom;
    int quantite;
    double montant;

    public ObjetNonUnique(String nom, int quantite, double montant) {
        this.nom = nom;
        this.quantite = quantite;
        this.montant = montant;
    }

    public void modifierQuantite(int nouvelleQuantite) {
        this.quantite = nouvelleQuantite;
    }

    public void enleverQuantite(int quantiteEnlevee) {
        this.quantite -= quantiteEnlevee;
    }

    public void ajouterMontant(double montantAjoute) {
        this.montant += montantAjoute;
    }


    public void soustraireMontant(double montantSoustrait) {
        this.montant -= montantSoustrait;
    }

    public double getMontant() {
        return this.montant;
    }

    public int getQuantite() {
        return this.quantite;
    }

    public String getNom() {
        return this.nom;
    }


    public String lireObjet() {
        return this.nom + " - Quantité: " + this.quantite + " - Montant: " + this.montant;
    }
}

class GestionnairePortefeuille {
    UUID identifiant;
    int capacite;
    List<ObjetUnique> objetsUniques;
    List<ObjetNonUnique> objetsNonUniques;

    public GestionnairePortefeuille(UUID identifiant, int capacite) {
        this.identifiant = identifiant;
        this.capacite = capacite;
        this.objetsUniques = new ArrayList<>();
        this.objetsNonUniques = new ArrayList<>();
    }

    public void ajouterObjetUnique(String nom, String contenu) {
        ObjetUnique objetUnique = new ObjetUnique(nom, contenu);
        this.objetsUniques.add(objetUnique);
        System.out.println("Objet unique ajouté : " + nom);
    }

    public void ajouterObjetNonUnique(String nom, int quantite, double montant) {
        ObjetNonUnique objetNonUnique = new ObjetNonUnique(nom, quantite, montant);
        this.objetsNonUniques.add(objetNonUnique);
        System.out.println("Objet évolutif ajouté : " + nom);
    }

    public String lireContenuObjetUnique(ObjetUnique objetUnique) {
        return objetUnique.lireContenu();
    }


    public void afficherSousMenuObjetsUniques(ObjetUnique objetChoisi) {
        System.out.println("\nSous-menu Objets Uniques:");
        System.out.println("1. Lire le contenu de l'objet unique");
        System.out.println("2. Supprimer l'objet unique");
        System.out.println("3. Revenir au menu principal");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Choisissez une option: ");
        String choix = scanner.nextLine();

        switch (choix) {
            case "1":
                System.out.println("Contenu de l'objet unique: " + lireContenuObjetUnique(objetChoisi));
                break;

            case "2":
                supprimerObjetUnique(objetChoisi);
                System.out.println("Objet unique supprimé.");
                break;

            case "3":
                // Revenir au menu principal
                break;

            default:
                System.out.println("Option invalide. Veuillez choisir une option valide.");
                break;
        }
    }

    public void afficherMontantActuelObjetNonUnique(ObjetNonUnique objetChoisi) {
        if (objetChoisi.getQuantite() == 0) {
            System.out.println("Montant actuel de " + objetChoisi.getNom() + ": " + objetChoisi.getMontant());
        } else {
            double montantActuel = objetChoisi.getMontant() * objetChoisi.getQuantite();
            System.out.println("Montant actuel de " + objetChoisi.getNom() + ": " + montantActuel);
        }
    }
    public void afficherSousMenuObjetsNonUniques(ObjetNonUnique objetChoisi) {
        System.out.println("\nSous-menu Objets Non Uniques:");
        System.out.println("1. Lire l'objet");
        System.out.println("2. Modifier la quantité de l'objet");
        System.out.println("3. Soustraire la quantité de l'objet");
        System.out.println("4. Ajouter un montant à l'objet");
        System.out.println("5. Soustraire le montant de l'objet");
        System.out.println("6. Montant actuel de l'objet");
        System.out.println("7. Supprimer l'objet");
        System.out.println("8. Revenir au menu principal");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Choisissez une option: ");
        String choix = scanner.nextLine();

        switch (choix) {
            case "1":
                System.out.println("L'objet évolutif: " + objetChoisi.lireObjet());
                break;

            case "2":
                while (true) {
                    try {
                        System.out.print("Nouvelle quantité de l'objet évolutif: ");
                        int nouvelleQuantite = Integer.parseInt(scanner.nextLine());
                        modifierQuantiteObjetNonUnique(objetChoisi, nouvelleQuantite);
                        System.out.println("Quantité modifiée.");
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Veuillez entrer un nombre entier pour la quantité.");
                    }
                }
                break;

            case "3":
                while (true) {
                    try {
                        System.out.print("Quantité à enlever de l'objet évolutif: ");
                        int quantiteEnlevee = Integer.parseInt(scanner.nextLine());
                        enleverQuantiteObjetNonUnique(objetChoisi, quantiteEnlevee);
                        System.out.println("Quantité enlevée.");
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Veuillez entrer un nombre entier pour la quantité à enlever.");
                    }
                }
                break;

            case "4":
                while (true) {
                    try {
                        System.out.print("Montant à ajouter à l'objet évolutif: ");
                        double montantAjoute = Double.parseDouble(scanner.nextLine());
                        objetChoisi.ajouterMontant(montantAjoute);
                        System.out.println("Montant ajouté.");
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Veuillez entrer un nombre décimal pour le montant.");
                    }
                }
                break;

            case "5":
                while (true) {
                    try {
                        System.out.print("Montant à soustraire à l'objet évolutif: ");
                        double montantSoustrait = Double.parseDouble(scanner.nextLine());
                        objetChoisi.soustraireMontant(montantSoustrait);
                        System.out.println("Montant soustrait.");
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Veuillez entrer un nombre décimal pour le montant.");
                    }
                }
                break;

            case "6":
                afficherMontantActuelObjetNonUnique(objetChoisi);
                break;

            case "7":
                supprimerObjetNonUnique(objetChoisi);
                System.out.println(objetChoisi.getNom() + " supprimé du portefeuille.");
                break;

            case "8":
                // Revenir au menu principal
                break;

            default:
                System.out.println("Option invalide. Veuillez choisir une option valide.");
                break;
        }
    }


    private void supprimerObjetUnique(ObjetUnique objetUnique) {
        this.objetsUniques.remove(objetUnique);
    }

    private void supprimerObjetNonUnique(ObjetNonUnique objetNonUnique) {
        this.objetsNonUniques.remove(objetNonUnique);
    }

    private void modifierQuantiteObjetNonUnique(ObjetNonUnique objetNonUnique, int nouvelleQuantite) {
        objetNonUnique.modifierQuantite(nouvelleQuantite);
    }

    private void enleverQuantiteObjetNonUnique(ObjetNonUnique objetNonUnique, int quantiteEnlevee) {
        objetNonUnique.enleverQuantite(quantiteEnlevee);
    }

    private void ajouterMontantObjetNonUnique(ObjetNonUnique objetNonUnique, double montantAjoute) {
        objetNonUnique.ajouterMontant(montantAjoute);
    }
}

public class PortfolioManager {
    public static void main(String[] args) {
        GestionnairePortefeuille portefeuille_default = new GestionnairePortefeuille(UUID.randomUUID(), 10);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            afficherMenu();
            System.out.print("Choisissez une option: ");
            String choix = scanner.nextLine();

            switch (choix) {
                case "1":
                    System.out.print("Nom de l'objet unique: ");
                    String nomObjetUnique = scanner.nextLine();
                    System.out.print("Contenu de l'objet unique: ");
                    String contenuObjetUnique = scanner.nextLine();
                    portefeuille_default.ajouterObjetUnique(nomObjetUnique, contenuObjetUnique);
                    System.out.println(nomObjetUnique + " ajouté au portefeuille.");
                    break;

                case "2":
                    System.out.print("Nom de l'objet évolutif: ");
                    String nomObjetNonUnique = scanner.nextLine();

                    while (true) {
                        try {
                            System.out.print("Quantité de l'objet évolutif: ");
                            int quantiteObjetNonUnique = Integer.parseInt(scanner.nextLine());
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("Veuillez entrer un nombre entier pour la quantité.");
                        }
                    }

                    System.out.print("Voulez-vous ajouter un montant à cet objet évolutif ? (Oui/Non): ");
                    String ajouterMontant = scanner.nextLine().toLowerCase();
                    Double montantObjetNonUnique = null;

                    if (ajouterMontant.equals("oui")) {
                        while (true) {
                            try {
                                System.out.print("Montant de l'objet évolutif: ");
                                montantObjetNonUnique = Double.parseDouble(scanner.nextLine());
                                break;
                            } catch (NumberFormatException e) {
                                System.out.println("Veuillez entrer un nombre décimal pour le montant.");
                            }
                        }
                    }

                    portefeuille_default.ajouterObjetNonUnique(nomObjetNonUnique, 0, montantObjetNonUnique);
                    break;

                case "3":
                    if (portefeuille_default.objetsUniques.isEmpty()) {
                        System.out.println("Aucun objet unique n'a encore été ajouté. Veuillez d'abord en créer.");
                    } else {
                        System.out.println("\nListe des objets uniques:");
                        for (int i = 0; i < portefeuille_default.objetsUniques.size(); i++) {
                            ObjetUnique objetUnique = portefeuille_default.objetsUniques.get(i);
                            System.out.println((i + 1) + ". " + objetUnique.nom + ": " + objetUnique.lireContenu());
                        }

                        System.out.print("\nVeuillez choisir le numéro de l'objet souhaité : ");
                        int choixNumeroObjet = Integer.parseInt(scanner.nextLine());

                        if (choixNumeroObjet >= 1 && choixNumeroObjet <= portefeuille_default.objetsUniques.size()) {
                            ObjetUnique objetChoisi = portefeuille_default.objetsUniques.get(choixNumeroObjet - 1);
                            System.out.println("Vous avez choisi l'objet unique : " + objetChoisi.nom);
                            portefeuille_default.afficherSousMenuObjetsUniques(objetChoisi);
                        } else {
                            System.out.println("Numéro invalide. Veuillez choisir un numéro valide.");
                        }
                    }
                    break;

                case "4":
                    if (portefeuille_default.objetsNonUniques.isEmpty()) {
                        System.out.println("Aucun objet évolutif n'a encore été ajouté. Veuillez d'abord en créer un.");
                    } else {
                        System.out.println("\nListe des objets évolutifs:");
                        for (int i = 0; i < portefeuille_default.objetsNonUniques.size(); i++) {
                            ObjetNonUnique objetNonUnique = portefeuille_default.objetsNonUniques.get(i);
                            System.out.println((i + 1) + ". " + objetNonUnique.nom);
                        }

                        System.out.print("\nVeuillez choisir le numéro de l'objet évolutif souhaité : ");
                        int choixNumeroObjetEvolutif = Integer.parseInt(scanner.nextLine());

                        if (choixNumeroObjetEvolutif >= 1 && choixNumeroObjetEvolutif <= portefeuille_default.objetsNonUniques.size()) {
                            ObjetNonUnique objetEvolutifChoisi = portefeuille_default.objetsNonUniques.get(choixNumeroObjetEvolutif - 1);
                            System.out.println("Vous avez choisi l'objet évolutif : " + objetEvolutifChoisi.nom);
                            portefeuille_default.afficherSousMenuObjetsNonUniques(objetEvolutifChoisi);
                        } else {
                            System.out.println("Numéro invalide. Veuillez choisir un numéro valide.");
                        }
                    }
                    break;

                case "5":
                    System.out.println("Programme terminé. Au revoir!");
                    System.exit(0);

                default:
                    System.out.println("Option invalide. Veuillez choisir une option valide.");
                    break;
            }
        }
    }

    private static void afficherMenu() {
        System.out.println("\nMenu Principal:");
        System.out.println("1. Ajouter un objet unique");
        System.out.println("2. Ajouter un objet évolutif");
        System.out.println("3. Afficher les objets uniques");
        System.out.println("4. Afficher les objets évolutifs");
        System.out.println("5. Quitter");
    }
}
