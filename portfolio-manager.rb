require 'securerandom'

class ObjetUnique
  attr_accessor :nom, :contenu

  def initialize(nom, contenu)
    @nom = nom
    @contenu = contenu
  end

  def lire_contenu
    @contenu
  end
end

class ObjetNonUnique
  attr_accessor :nom, :quantite, :montant

  def initialize(nom, quantite, montant = nil)
    @nom = nom
    @quantite = quantite
    @montant = montant
  end

  def modifier_quantite(nouvelle_quantite)
    @quantite = nouvelle_quantite
  end

  def ajouter_quantite(quantite_ajoutee)
    @quantite += quantite_ajoutee
  end

  def enlever_quantite(quantite_enlevee)
    @quantite -= quantite_enlevee
    @quantite = 0 if @quantite < 0
  end

  def ajouter_montant(montant_ajoute)
    @montant += montant_ajoute if @montant
  end

  def lire_objet
    if @montant
      "#{@nom}: #{@quantite} (Montant: #{@montant})"
    else
      "#{@nom}: #{@quantite}"
    end
  end
end

class Portefeuille
  attr_accessor :identifiant, :capacite, :objets_uniques, :objets_non_uniques

  def initialize(identifiant = nil, capacite = 10)
    @identifiant = identifiant || SecureRandom.uuid
    @capacite = capacite
    @objets_uniques = []
    @objets_non_uniques = []
  end

  def ajouter_objet_unique(objet_unique)
    @objets_uniques << objet_unique
  end

  def supprimer_objet_unique(objet_unique)
    @objets_uniques.delete(objet_unique)
  end

  def lire_contenu_objet_unique(objet_unique)
    objet_unique.lire_contenu
  end

  def ajouter_objet_non_unique(objet_non_unique)
    @objets_non_uniques << objet_non_unique
  end

  def modifier_quantite_objet_non_unique(objet_non_unique, nouvelle_quantite)
    objet_non_unique.modifier_quantite(nouvelle_quantite)
  end

  def enlever_quantite_objet_non_unique(objet_non_unique, quantite_enlevee)
    objet_non_unique.enlever_quantite(quantite_enlevee)
  end

  def supprimer_objet_non_unique(objet_non_unique)
    @objets_non_uniques.delete(objet_non_unique)
  end

  def lire_objet_non_unique(objet_non_unique)
    objet_non_unique.lire_objet
  end
end

def afficher_sous_menu_objets_uniques(portefeuille, objet_choisi = nil)
  loop do
    puts "\nSous-menu Objets Uniques:"
    puts "1. Lire le contenu de l'objet"
    puts "2. Supprimer l'objet"
    puts "3. Revenir au menu principal"

    choix_sous_menu = gets.chomp

    case choix_sous_menu
    when "1"
      # Lire le contenu de l'objet unique
      contenu = portefeuille.lire_contenu_objet_unique(objet_choisi)
      puts "Contenu de #{objet_choisi.nom}: #{contenu}"

    when "2"
      # Supprimer l'objet unique
      portefeuille.supprimer_objet_unique(objet_choisi)
      puts "#{objet_choisi.nom} supprimé du portefeuille."

    when "3"
      break

    else
      puts "Option invalide. Veuillez choisir une option valide."
    end
  end
end

def afficher_sous_menu_objets_evolutifs(portefeuille, objet_evolutif_choisi = nil)
  loop do
    puts "\nSous-menu Objets Evolutifs:"
    puts "1. Modifier la quantité de l'objet"
    puts "2. Enlever la quantité de l'objet"
    puts "3. Ajouter un montant à l'objet"
    puts "4. Soustraire un montant à l'objet"
    puts "5. Montant actuel de l'objet"
    puts "6. Supprimer l'objet"
    puts "7. Revenir au menu principal"

    choix_sous_menu = gets.chomp

    case choix_sous_menu
    when "1"
      # Modification de la quantité de l'objet évolutif
      nouvelle_quantite = nil
      loop do
        print "Nouvelle quantité de l'objet évolutif: "
        nouvelle_quantite = gets.chomp.to_i
        break if nouvelle_quantite.to_s == nouvelle_quantite.to_i.to_s
        puts "Veuillez entrer un nombre entier pour la quantité."
      end
      portefeuille.modifier_quantite_objet_non_unique(objet_evolutif_choisi, nouvelle_quantite)
      puts "Quantité de #{objet_evolutif_choisi.nom} modifiée dans le portefeuille."

    when "2"
      # Enlever la quantité de l'objet évolutif
      quantite_enlevee = nil
      loop do
        print "Quantité à enlever de l'objet évolutif: "
        quantite_enlevee = gets.chomp.to_i
        break if quantite_enlevee.to_s == quantite_enlevee.to_i.to_s
        puts "Veuillez entrer un nombre entier pour la quantité à enlever."
      end
      portefeuille.enlever_quantite_objet_non_unique(objet_evolutif_choisi, quantite_enlevee)
      puts "#{quantite_enlevee} de #{objet_evolutif_choisi.nom} enlevé du portefeuille."

    when "3"
      # Ajouter un montant à l'objet évolutif
      montant_ajoute = nil
      loop do
        print "Montant à ajouter à l'objet évolutif: "
        montant_ajoute = gets.chomp.to_f
        break if montant_ajoute.to_s == montant_ajoute.to_f.to_s
        puts "Veuillez entrer un nombre décimal pour le montant."
      end
      objet_evolutif_choisi.ajouter_montant(montant_ajoute)
      puts "Montant de #{objet_evolutif_choisi.nom} modifié dans le portefeuille."

    when "4"
      # Soustraire un montant à l'objet évolutif
      montant_soustrait = nil
      loop do
        print "Montant à soustraire à l'objet évolutif: "
        montant_soustrait = gets.chomp.to_f
        break if montant_soustrait.to_s == montant_soustrait.to_f.to_s
        puts "Veuillez entrer un nombre décimal pour le montant."
      end
      objet_evolutif_choisi.ajouter_montant(-montant_soustrait)
      puts "Montant de #{objet_evolutif_choisi.nom} soustrait dans le portefeuille."

    when "5"
      # Montant actuel de l'objet évolutif (quantité * montant)
      if objet_evolutif_choisi.quantite == 0
        puts "Montant actuel de #{objet_evolutif_choisi.nom}: #{objet_evolutif_choisi.montant}"
      else
        montant_actuel = objet_evolutif_choisi.quantite * objet_evolutif_choisi.montant
        puts "Montant actuel de #{objet_evolutif_choisi.nom}: #{montant_actuel}"
      end

    when "6"
      # Suppression de l'objet évolutif
      portefeuille.supprimer_objet_non_unique(objet_evolutif_choisi)
      puts "#{objet_evolutif_choisi.nom} supprimé du portefeuille."

    when "7"
      break

    else
      puts "Option invalide. Veuillez choisir une option valide."
    end
  end
end

def afficher_menu
  puts "\nMenu Principal:"
  puts "1. Ajouter un objet unique"
  puts "2. Ajouter un objet évolutif"
  puts "3. Liste des objets uniques"
  puts "4. Liste des objets évolutifs"
  puts "5. Quitter le programme"
end

portefeuille_default = Portefeuille.new

loop do
  afficher_menu
  choix = gets.chomp

  case choix
  when "1"
    print "Nom de l'objet unique: "
    nom_objet_unique = gets.chomp
    print "Contenu de l'objet unique: "
    contenu_objet_unique = gets.chomp
    objet_unique = ObjetUnique.new(nom_objet_unique, contenu_objet_unique)
    portefeuille_default.ajouter_objet_unique(objet_unique)
    puts "#{nom_objet_unique} ajouté au portefeuille."

  when "2"
    print "Nom de l'objet évolutif: "
    nom_objet_non_unique = gets.chomp

    quantite_objet_non_unique = nil
    loop do
      print "Quantité de l'objet évolutif: "
      quantite_objet_non_unique = gets.chomp.to_i
      break if quantite_objet_non_unique.to_s == quantite_objet_non_unique.to_i.to_s
      puts "Veuillez entrer un nombre entier pour la quantité."
    end

    print "Voulez-vous ajouter un montant à cet objet évolutif ? (Oui/Non): "
    ajouter_montant = gets.chomp.downcase
    montant_objet_non_unique = nil

    if ajouter_montant == "oui"
      loop do
        print "Montant de l'objet évolutif: "
        montant_objet_non_unique = gets.chomp.to_f
        break if montant_objet_non_unique.to_s == montant_objet_non_unique.to_f.to_s
        puts "Veuillez entrer un nombre décimal pour le montant."
      end
    end

    objet_non_unique = ObjetNonUnique.new(nom_objet_non_unique, quantite_objet_non_unique, montant_objet_non_unique)
    portefeuille_default.ajouter_objet_non_unique(objet_non_unique)

  when "3"
    if portefeuille_default.objets_uniques.empty?
      puts "Aucun objet unique n'a encore été ajouté. Veuillez d'abord en créer."
    else
      puts "\nListe des objets uniques:"
      portefeuille_default.objets_uniques.each_with_index do |objet_unique, index|
        puts "#{index + 1}. #{objet_unique.nom}: #{objet_unique.lire_contenu}"
      end

      print "\nVeuillez choisir le numéro de l'objet que vous souhaitez : "
      choix_numero_objet = gets.chomp.to_i

      if choix_numero_objet.between?(1, portefeuille_default.objets_uniques.length)
        objet_choisi = portefeuille_default.objets_uniques[choix_numero_objet - 1]
        puts "Vous avez choisi l'objet unique : #{objet_choisi.nom}"

        # Appeler la fonction avec l'objet choisi
        afficher_sous_menu_objets_uniques(portefeuille_default, objet_choisi)
      else
        puts "Numéro invalide. Veuillez choisir un numéro valide."
      end
    end

  when "4"
    if portefeuille_default.objets_non_uniques.empty?
      puts "Aucun objet évolutif n'a encore été ajouté. Veuillez d'abord en créer un."
    else
      puts "\nListe des objets évolutifs:"
      portefeuille_default.objets_non_uniques.each_with_index do |objet_evolutif, index|
        puts "#{index + 1}. #{objet_evolutif.nom}"
      end

      print "\nVeuillez choisir le numéro de l'objet évolutif que vous souhaitez : "
      choix_numero_objet_evolutif = gets.chomp.to_i

      if choix_numero_objet_evolutif.between?(1, portefeuille_default.objets_non_uniques.length)
        objet_evolutif_choisi = portefeuille_default.objets_non_uniques[choix_numero_objet_evolutif - 1]
        puts "Vous avez choisi l'objet évolutif : #{objet_evolutif_choisi.nom}"

        # Appeler la fonction avec l'objet évolutif choisi
        afficher_sous_menu_objets_evolutifs(portefeuille_default, objet_evolutif_choisi)
      else
        puts "Numéro invalide. Veuillez choisir un numéro valide."
      end
    end

  when "5"
    puts "Programme terminé. Au revoir!"
    break

  else
    puts "Option invalide. Veuillez choisir une option valide."
  end
end
