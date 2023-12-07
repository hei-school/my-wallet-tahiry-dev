import uuid
class ObjetUnique:
    def __init__(self, nom, contenu):
        self.nom = nom
        self.contenu = contenu

    def lire_contenu(self):
        return self.contenu

class ObjetNonUnique:
    def __init__(self, nom, quantite, montant=None):
        self.nom = nom
        self.quantite = quantite
        self.montant = montant

    def modifier_quantite(self, nouvelle_quantite):
        self.quantite = nouvelle_quantite

    def ajouter_quantite(self, quantite_ajoutee):
        self.quantite += quantite_ajoutee

    def enlever_quantite(self, quantite_enlevee):
        self.quantite -= quantite_enlevee
        if self.quantite < 0:
            self.quantite = 0

    def ajouter_montant(self, montant_ajoute):
        self.montant += montant_ajoute

    def lire_objet(self):
        if self.montant is not None:
            return f"{self.nom}: {self.quantite} (Montant: {self.montant})"
        else:
            return f"{self.nom}: {self.quantite}"

class Portefeuille:
    def __init__(self, identifiant=None, capacite=10):
        self.identifiant = identifiant if identifiant else str(uuid.uuid4())
        self.capacite = capacite
        self.objets_uniques = []
        self.objets_non_uniques = []

    def ajouter_objet_unique(self, objet_unique):
        self.objets_uniques.append(objet_unique)

    def supprimer_objet_unique(self, objet_unique):
        self.objets_uniques.remove(objet_unique)

    def lire_contenu_objet_unique(self, objet_unique):
        return objet_unique.lire_contenu()

    def ajouter_objet_non_unique(self, objet_non_unique):
        self.objets_non_uniques.append(objet_non_unique)

    def modifier_quantite_objet_non_unique(self, objet_non_unique, nouvelle_quantite):
        objet_non_unique.modifier_quantite(nouvelle_quantite)

    def enlever_quantite_objet_non_unique(self, objet_non_unique, quantite_enlevee):
        objet_non_unique.enlever_quantite(quantite_enlevee)

    def supprimer_objet_non_unique(self, objet_non_unique):
        self.objets_non_uniques.remove(objet_non_unique)

    def lire_objet_non_unique(self, objet_non_unique):
        return objet_non_unique.lire_objet()

def afficher_sous_menu_objets_uniques(portefeuille, objet_choisi=None):
    while True:
        print("\nSous-menu Objets Uniques:")
        print("1. Lire le contenu de l'objet")
        print("2. Supprimer l'objet")
        print("3. Revenir au menu principal")

        choix_sous_menu = input("Choisissez une option: ")

        if choix_sous_menu == "1" and objet_choisi:
            # Lire le contenu de l'objet unique
            contenu = portefeuille.lire_contenu_objet_unique(objet_choisi)
            print(f"Contenu de {objet_choisi.nom}: {contenu}")

        elif choix_sous_menu == "2" and objet_choisi:
            # Supprimer l'objet unique
            portefeuille.supprimer_objet_unique(objet_choisi)
            print(f"{objet_choisi.nom} supprimé du portefeuille.")

        elif choix_sous_menu == "3":
            break

        else:
            print("Option invalide. Veuillez choisir une option valide.")



# Fonction pour afficher le sous-menu des objets évolutifs
def afficher_sous_menu_objets_evolutifs(portefeuille, objet_evolutif_choisi=None):
    while True:
        print("\nSous-menu Objets Evolutifs:")
        print("1. Modifier la quantité de l'objet évolutif")
        print("2. Enlever la quantité de l'objet évolutif")
        print("3. Ajouter un montant à l'objet évolutif")
        print("4. Soustraire un montant à l'objet évolutif")
        print("5. Montant actuel de l'objet évolutif")
        print("6. Supprimer l'objet évolutif")
        print("7. Revenir au menu principal")

        choix_sous_menu = input("Choisissez une option: ")

        if choix_sous_menu == "1" and objet_evolutif_choisi:
            # Modification de la quantité de l'objet évolutif
            while True:
                try:
                    nouvelle_quantite = int(input("Nouvelle quantité de l'objet évolutif: "))
                    break
                except ValueError:
                    print("Veuillez entrer un nombre entier pour la quantité.")
            portefeuille.modifier_quantite_objet_non_unique(objet_evolutif_choisi, nouvelle_quantite)
            print(f"Quantité de {objet_evolutif_choisi.nom} modifiée dans le portefeuille.")

        elif choix_sous_menu == "2" and objet_evolutif_choisi:
            # Enlever la quantité de l'objet évolutif
            while True:
                try:
                    quantite_enlevee = int(input("Quantité à enlever de l'objet évolutif: "))
                    break
                except ValueError:
                    print("Veuillez entrer un nombre entier pour la quantité à enlever.")
            portefeuille.enlever_quantite_objet_non_unique(objet_evolutif_choisi, quantite_enlevee)
            print(f"{quantite_enlevee} de {objet_evolutif_choisi.nom} enlevé du portefeuille.")

        elif choix_sous_menu == "3" and objet_evolutif_choisi:
            # Ajouter un montant à l'objet évolutif
            while True:
                try:
                    montant_ajoute = float(input("Montant à ajouter à l'objet évolutif: "))
                    break
                except ValueError:
                    print("Veuillez entrer un nombre décimal pour le montant.")
            objet_evolutif_choisi.ajouter_montant(montant_ajoute)
            print(f"Montant de {objet_evolutif_choisi.nom} modifié dans le portefeuille.")

        elif choix_sous_menu == "4" and objet_evolutif_choisi:
            # Soustraire un montant à l'objet évolutif
            while True:
                try:
                    montant_soustrait = float(input("Montant à soustraire à l'objet évolutif: "))
                    break
                except ValueError:
                    print("Veuillez entrer un nombre décimal pour le montant.")
            objet_evolutif_choisi.ajouter_montant(-montant_soustrait)
            print(f"Montant de {objet_evolutif_choisi.nom} soustrait dans le portefeuille.")

        elif choix_sous_menu == "5" and objet_evolutif_choisi:
            # Montant actuel de l'objet évolutif (quantité * montant)
            if objet_evolutif_choisi.quantite == 0:
                print(f"Montant actuel de {objet_evolutif_choisi.nom}: {objet_evolutif_choisi.montant}")
            else:
                montant_actuel = objet_evolutif_choisi.quantite * objet_evolutif_choisi.montant
                print(f"Montant actuel de {objet_evolutif_choisi.nom}: {montant_actuel}")


        elif choix_sous_menu == "6" and objet_evolutif_choisi:
            # Suppression de l'objet évolutif
            portefeuille.supprimer_objet_non_unique(objet_evolutif_choisi)
            print(f"{objet_evolutif_choisi.nom} supprimé du portefeuille.")

        elif choix_sous_menu == "7":
            break

        else:
            print("Option invalide. Veuillez choisir une option valide.")


# Fonction pour afficher le menu
def afficher_menu():
    print("\nMenu Principal:")
    print("1. Ajouter un objet unique")
    print("2. Ajouter un objet évolutif")
    print("3. Liste des objets uniques")
    print("4. Liste des objets évolutifs")
    print("5. Quitter le programme")

portefeuille_default = Portefeuille()

while True:
    afficher_menu()
    choix = input("Choisissez une option: ")

    if choix == "1":
        nom_objet_unique = input("Nom de l'objet unique: ")
        contenu_objet_unique = input("Contenu de l'objet unique: ")
        objet_unique = ObjetUnique(nom_objet_unique, contenu_objet_unique)
        portefeuille_default.ajouter_objet_unique(objet_unique)
        print(f"{nom_objet_unique} ajouté au portefeuille.")

    elif choix == "2":
        nom_objet_non_unique = input("Nom de l'objet évolutif: ")

        while True:
            try:
                quantite_objet_non_unique = int(input("Quantité de l'objet évolutif: "))
                break
            except ValueError:
                print("Veuillez entrer un nombre entier pour la quantité.")

        ajouter_montant = input("Voulez-vous ajouter un montant à cet objet évolutif ? (Oui/Non): ").lower()
        montant_objet_non_unique = None

        if ajouter_montant == "oui":
            while True:
                try:
                    montant_objet_non_unique = float(input("Montant de l'objet évolutif: "))
                    break
                except ValueError:
                    print("Veuillez entrer un nombre décimal pour le montant.")

        objet_non_unique = ObjetNonUnique(nom_objet_non_unique, quantite_objet_non_unique, montant_objet_non_unique)
        portefeuille_default.ajouter_objet_non_unique(objet_non_unique)


    elif choix == "3":

        if not portefeuille_default.objets_uniques:
            print("Aucun objet unique n'a encore été ajouté.  Veuillez d'abord créer")
        else:
            print("\nListe des objets uniques:")
            for numero, objet_unique in enumerate(portefeuille_default.objets_uniques, start=1):
                print(f"{numero}. {objet_unique.nom}: {objet_unique.lire_contenu()}")
                # Demander à l'utilisateur de choisir un numéro
                choix_numero_objet = input("\nVeuillez choisir le numéro de l'objet souhaitez : ")

                # Vérifier si l'entrée de l'utilisateur est un nombre entier
                if choix_numero_objet.isdigit():
                    choix_numero_objet = int(choix_numero_objet)

                    # Vérifier si le numéro choisi est valide
                    if 1 <= choix_numero_objet <= len(portefeuille_default.objets_uniques):
                        objet_choisi = portefeuille_default.objets_uniques[choix_numero_objet - 1]
                        print(f"Vous avez choisi l'objet unique : {objet_choisi.nom}")

                        # Appeler la fonction avec l'objet choisi
                        afficher_sous_menu_objets_uniques(portefeuille_default, objet_choisi)
                    else:
                        print("Numéro invalide. Veuillez choisir un numéro valide.")
                else:
                    print("Veuillez entrer un numéro valide.")

    elif choix == "4":
        if not portefeuille_default.objets_non_uniques:
            print("Aucun objet évolutif n'a encore été ajouté. Veuillez d'abord créer un.")
        else:
            # Afficher la liste des objets évolutifs avec leur numéro
            print("\nListe des objets évolutifs:")
            for numero, objet_evolutif in enumerate(portefeuille_default.objets_non_uniques, start=1):
                print(f"{numero}. {objet_evolutif.nom}")

            # Demander à l'utilisateur de choisir un numéro
            choix_numero_objet_evolutif = input("\nVeuillez choisir le numéro de l'objet évolutif souhaité : ")

            # Vérifier si l'entrée de l'utilisateur est un nombre entier
            if choix_numero_objet_evolutif.isdigit():
                choix_numero_objet_evolutif = int(choix_numero_objet_evolutif)

                # Vérifier si le numéro choisi est valide
                if 1 <= choix_numero_objet_evolutif <= len(portefeuille_default.objets_non_uniques):
                    objet_evolutif_choisi = portefeuille_default.objets_non_uniques[choix_numero_objet_evolutif - 1]
                    print(f"Vous avez choisi l'objet évolutif : {objet_evolutif_choisi.nom}")

                    # Appeler la fonction avec l'objet évolutif choisi
                    afficher_sous_menu_objets_evolutifs(portefeuille_default, objet_evolutif_choisi)
                else:
                    print("Numéro invalide. Veuillez choisir un numéro valide.")
            else:
                print("Veuillez entrer un numéro valide.")

    elif choix == "5":
        print("Programme terminé. Au revoir!")
        break

    else:
        print("Option invalide. Veuillez choisir une option valide.")
