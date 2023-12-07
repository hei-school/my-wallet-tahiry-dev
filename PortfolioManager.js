const prompt = require('prompt-sync')();
const { v4: uuidv4 } = require('uuid');

class ObjetUnique {
    constructor(nom, contenu) {
        this.nom = nom;
        this.contenu = contenu;
    }

    lireContenu() {
        return this.contenu;
    }
}

class ObjetNonUnique {
    constructor(nom, quantite, montant = null) {
        this.nom = nom;
        this.quantite = quantite;
        this.montant = montant;
    }

    modifierQuantite(nouvelleQuantite) {
        this.quantite = nouvelleQuantite;
    }

    ajouterQuantite(quantiteAjoutee) {
        this.quantite += quantiteAjoutee;
    }

    enleverQuantite(quantiteEnlevee) {
        this.quantite -= quantiteEnlevee;
        if (this.quantite < 0) {
            this.quantite = 0;
        }
    }

    ajouterMontant(montantAjoute) {
        this.montant += montantAjoute;
    }

    lireObjet() {
        if (this.montant !== null) {
            return `${this.nom}: ${this.quantite} (Montant: ${this.montant})`;
        } else {
            return `${this.nom}: ${this.quantite}`;
        }
    }
}

class Portefeuille {
    constructor(identifiant = null, capacite = 10) {
        this.identifiant = identifiant || uuidv4();
        this.capacite = capacite;
        this.objetsUniques = [];
        this.objetsNonUniques = [];
    }

    ajouterObjetUnique(objetUnique) {
        this.objetsUniques.push(objetUnique);
    }

    supprimerObjetUnique(objetUnique) {
        const index = this.objetsUniques.indexOf(objetUnique);
        if (index !== -1) {
            this.objetsUniques.splice(index, 1);
        }
    }

    lireContenuObjetUnique(objetUnique) {
        return objetUnique.lireContenu();
    }

    ajouterObjetNonUnique(objetNonUnique) {
        this.objetsNonUniques.push(objetNonUnique);
    }

    modifierQuantiteObjetNonUnique(objetNonUnique, nouvelleQuantite) {
        objetNonUnique.modifierQuantite(nouvelleQuantite);
    }

    enleverQuantiteObjetNonUnique(objetNonUnique, quantiteEnlevee) {
        objetNonUnique.enleverQuantite(quantiteEnlevee);
    }

    supprimerObjetNonUnique(objetNonUnique) {
        const index = this.objetsNonUniques.indexOf(objetNonUnique);
        if (index !== -1) {
            this.objetsNonUniques.splice(index, 1);
        }
    }

    lireObjetNonUnique(objetNonUnique) {
        return objetNonUnique.lireObjet();
    }
}

function afficherSousMenuObjetsUniques(portefeuille, objetChoisi) {
    while (true) {
        console.log("\nSous-menu Objets Uniques:");
        console.log("1. Lire le contenu de l'objet");
        console.log("2. Supprimer l'objet");
        console.log("3. Revenir au menu principal");

        const choixSousMenu = prompt("Choisissez une option: ");

        if (choixSousMenu === "1" && objetChoisi) {
            // Lire le contenu de l'objet unique
            const contenu = portefeuille.lireContenuObjetUnique(objetChoisi);
            console.log(`Contenu de ${objetChoisi.nom}: ${contenu}`);
        } else if (choixSousMenu === "2" && objetChoisi) {
            // Supprimer l'objet unique
            portefeuille.supprimerObjetUnique(objetChoisi);
            console.log(`${objetChoisi.nom} supprimé du portefeuille.`);
        } else if (choixSousMenu === "3") {
            break;
        } else {
            console.log("Option invalide. Veuillez choisir une option valide.");
        }
    }
}

function afficherSousMenuObjetsEvolutifs(portefeuille, objetEvolutifChoisi) {
    while (true) {
        console.log("\nSous-menu Objets Evolutifs:");
        console.log("1. Modifier la quantité de l'objet évolutif");
        console.log("2. Enlever la quantité de l'objet évolutif");
        console.log("3. Ajouter un montant à l'objet évolutif");
        console.log("4. Soustraire un montant à l'objet évolutif");
        console.log("5. Montant actuel de l'objet évolutif");
        console.log("6. Supprimer l'objet évolutif");
        console.log("7. Revenir au menu principal");

        const choixSousMenu = prompt("Choisissez une option: ");

        if (choixSousMenu === "1" && objetEvolutifChoisi) {
            // Modifier la quantité de l'objet évolutif
            const nouvelleQuantite = parseInt(prompt("Nouvelle quantité de l'objet évolutif: "));
            portefeuille.modifierQuantiteObjetNonUnique(objetEvolutifChoisi, nouvelleQuantite);
            console.log(`Quantité de ${objetEvolutifChoisi.nom} modifiée dans le portefeuille.`);
        } else if (choixSousMenu === "2" && objetEvolutifChoisi) {
            // Enlever la quantité de l'objet évolutif
            const quantiteEnlevee = parseInt(prompt("Quantité à enlever de l'objet évolutif: "));
            portefeuille.enleverQuantiteObjetNonUnique(objetEvolutifChoisi, quantiteEnlevee);
            console.log(`${quantiteEnlevee} de ${objetEvolutifChoisi.nom} enlevé du portefeuille.`);
        } else if (choixSousMenu === "3" && objetEvolutifChoisi) {
            // Ajouter un montant à l'objet évolutif
            const montantAjoute = parseFloat(prompt("Montant à ajouter à l'objet évolutif: "));
            objetEvolutifChoisi.ajouterMontant(montantAjoute);
            console.log(`Montant de ${objetEvolutifChoisi.nom} modifié dans le portefeuille.`);
        } else if (choixSousMenu === "4" && objetEvolutifChoisi) {
            // Soustraire un montant à l'objet évolutif
            const montantSoustrait = parseFloat(prompt("Montant à soustraire à l'objet évolutif: "));
            objetEvolutifChoisi.ajouterMontant(-montantSoustrait);
            console.log(`Montant de ${objetEvolutifChoisi.nom} soustrait dans le portefeuille.`);
        } else if (choixSousMenu === "5" && objetEvolutifChoisi) {
            // Montant actuel de l'objet évolutif (quantité * montant)
            if (objetEvolutifChoisi.quantite === 0) {
                console.log(`Montant actuel de ${objetEvolutifChoisi.nom}: ${objetEvolutifChoisi.montant}`);
            } else {
                const montantActuel = objetEvolutifChoisi.quantite * objetEvolutifChoisi.montant;
                console.log(`Montant actuel de ${objetEvolutifChoisi.nom}: ${montantActuel}`);
            }
        } else if (choixSousMenu === "6" && objetEvolutifChoisi) {
            // Suppression de l'objet évolutif
            portefeuille.supprimerObjetNonUnique(objetEvolutifChoisi);
            console.log(`${objetEvolutifChoisi.nom} supprimé du portefeuille.`);
        } else if (choixSousMenu === "7") {
            break;
        } else {
            console.log("Option invalide. Veuillez choisir une option valide.");
        }
    }
}

function afficherMenu() {
    console.log("\nMenu Principal:");
    console.log("1. Ajouter un objet unique");
    console.log("2. Ajouter un objet évolutif");
    console.log("3. Liste des objets uniques");
    console.log("4. Liste des objets évolutifs");
    console.log("5. Quitter le programme");
}

const portefeuilleDefault = new Portefeuille();

function gestionMenu() {
    while (true) {
        console.log("\nMenu Principal:");
        console.log("1. Ajouter un objet unique");
        console.log("2. Ajouter un objet évolutif");
        console.log("3. Liste des objets uniques");
        console.log("4. Liste des objets évolutifs");
        console.log("5. Quitter le programme");

        const choix = prompt("Choisissez une option: ");

        if (choix === "1") {
            const nomObjetUnique = prompt("Nom de l'objet unique: ");
            const contenuObjetUnique = prompt("Contenu de l'objet unique: ");
            const objetUnique = new ObjetUnique(nomObjetUnique, contenuObjetUnique);
            portefeuilleDefault.ajouterObjetUnique(objetUnique);
            console.log(`${nomObjetUnique} ajouté au portefeuille.`);
        } else if (choix === "2") {
            const nomObjetNonUnique = prompt("Nom de l'objet évolutif: ");
            const quantiteObjetNonUnique = parseInt(prompt("Quantité de l'objet évolutif: "));

            let montantObjetNonUnique = null;
            const ajouterMontant = prompt("Voulez-vous ajouter un montant à cet objet évolutif ? (Oui/Non): ").toLowerCase();

            if (ajouterMontant === "oui") {
                montantObjetNonUnique = parseFloat(prompt("Montant de l'objet évolutif: "));
            }

            const objetNonUnique = new ObjetNonUnique(nomObjetNonUnique, quantiteObjetNonUnique, montantObjetNonUnique);
            portefeuilleDefault.ajouterObjetNonUnique(objetNonUnique);
        } else if (choix === "3") {
            if (!portefeuilleDefault.objetsUniques.length) {
                console.log("Aucun objet unique n'a encore été ajouté. Veuillez d'abord en créer un.");
            } else {
                console.log("\nListe des objets uniques:");
                for (let i = 0; i < portefeuilleDefault.objetsUniques.length; i++) {
                    console.log(`${i + 1}. ${portefeuilleDefault.objetsUniques[i].nom}: ${portefeuilleDefault.objetsUniques[i].lireContenu()}`);
                }

                const choixNumeroObjet = prompt("\nVeuillez choisir le numéro de l'objet que vous souhaitez : ");

                if (!isNaN(choixNumeroObjet) && choixNumeroObjet >= 1 && choixNumeroObjet <= portefeuilleDefault.objetsUniques.length) {
                    const objetChoisi = portefeuilleDefault.objetsUniques[choixNumeroObjet - 1];
                    console.log(`Vous avez choisi l'objet unique : ${objetChoisi.nom}`);

                    // Appel du sous-menu des objets uniques
                    afficherSousMenuObjetsUniques(portefeuilleDefault, objetChoisi);
                } else {
                    console.log("Numéro invalide. Veuillez choisir un numéro valide.");
                }
            }

        } else if (choix === "4") {
            if (!portefeuilleDefault.objetsNonUniques.length) {
                console.log("Aucun objet évolutif n'a encore été ajouté. Veuillez d'abord en créer un.");
            } else {
                console.log("\nListe des objets évolutifs:");
                for (let i = 0; i < portefeuilleDefault.objetsNonUniques.length; i++) {
                    console.log(`${i + 1}. ${portefeuilleDefault.objetsNonUniques[i].nom}`);
                }

                const choixNumeroObjetEvolutif = prompt("\nVeuillez choisir le numéro de l'objet évolutif que vous souhaitez : ");

                if (!isNaN(choixNumeroObjetEvolutif) && choixNumeroObjetEvolutif >= 1 && choixNumeroObjetEvolutif <= portefeuilleDefault.objetsNonUniques.length) {
                    const objetEvolutifChoisi = portefeuilleDefault.objetsNonUniques[choixNumeroObjetEvolutif - 1];
                    console.log(`Vous avez choisi l'objet évolutif : ${objetEvolutifChoisi.nom}`);

                    // Appel du sous-menu des objets évolutifs
                    afficherSousMenuObjetsEvolutifs(portefeuilleDefault, objetEvolutifChoisi);
                } else {
                    console.log("Numéro invalide. Veuillez choisir un numéro valide.");
                }
            }
        } else if (choix === "5") {
            console.log("Programme terminé. Au revoir!");
            break;
        }else {
            console.log("Option invalide. Veuillez choisir une option valide.");
        }

    }

}

gestionMenu();