    package ca.qc.bdeb.prog2.classe;

import ca.qc.bdeb.prog2.classe.Animal.Sexe;
import ca.qc.bdeb.prog2.classe.Mammifere.Nourriture;
import ca.qc.bdeb.prog2.classe.Plante.Flotte;
import ca.qc.bdeb.prog2.testencryption.Encryption;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 * Classe de gestion pokedex: responsable en majorité du fonctionnement du
 * pokedex
 *
 * @author Martin & Seina Assadian
 * @version 1.0
 */
public class GestionPokedex {

    static Scanner s = new Scanner(System.in);
    ArrayList<Personne> listePers = new ArrayList<Personne>();
    ArrayList<Specimen> listeSpecimen = new ArrayList<Specimen>();
    Personne utilisateur;
    int numeroTransaction;

    /**
     * Appeler par le main: démarre le programme en appelant les méthodes
     * d'ouverture de programmes soit vérifier utilisateur-lire fichier
     * pokedex-appeler menu
     */
    public void demarrer() {

        int compt = 1;
        lireFichierPersonne();
        boolean verifie = verifierUtilisateur();

        while (compt < 3 && !verifie) {
            System.out.println("Votre code d'accès ou votre mot de passe est invalide");
            verifie = verifierUtilisateur();
            compt++;
        }

        if (compt == 3) {
            System.out.println("Trop de tentatives erronées, au revoir.");
            System.exit(0);
        }

        lirePokedex();
        numeroTransaction = setNumeroTransaction();
        demanderChoix();

    }

    /**
     * Lecture du fichier Texte Personne (utilisateurs) et l'ajoute dans une
     * liste de Personnes.
     */
    private void lireFichierPersonne() {

        BufferedReader lecteur = null;

        String tabPers[] = new String[4];

        try {
            lecteur = new BufferedReader(new FileReader("personnes.txt"));
        } catch (FileNotFoundException ex) {
            System.out.println("Erreur. Fichier inexistant. Recommencez.");
            System.exit(0);
        }

        try {
            String line = lecteur.readLine();

            while (line != null) {
                tabPers = line.split(";");
                listePers.add(new Personne(tabPers[0], tabPers[1], tabPers[2], Integer.parseInt(tabPers[3])));
                line = lecteur.readLine();
            }
            lecteur.close();
        } catch (IOException e) {
            System.out.println("Erreur de lecture de fichier");
            System.exit(0);
        }

    }

    /**
     * Lecture du fichier Binaire Pokedex (informations des spécimens) et ajoute
     * le contenu dans une liste de Spécimen.
     */
    private void lirePokedex() {

        File pokedex = new File("pokedex.bin");

        if (pokedex.exists()) {
            try {
                FileInputStream fichier = new FileInputStream(pokedex);
                ObjectInputStream ois = new ObjectInputStream(fichier);

                listeSpecimen = (ArrayList<Specimen>) ois.readObject();
                ois.close();
            } catch (java.io.IOException e) {
                e.printStackTrace();
                System.out.println("Erreur d'entrées-sorties.");
            } catch (ClassNotFoundException e) {
                System.out.println("Erreur classe introuvable.");
            }
        }

    }

    /**
     * Méthode qui vient écrire un fichier binaire Pokedex en fonction d'une
     * liste de Spécimens.
     */
    private void ecrirePokedex() {
        File pokedex = new File("pokedex.bin");
        try {
            FileOutputStream fos = new FileOutputStream(pokedex);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(listeSpecimen);
            oos.flush();
            oos.close();
            fos.close();
        } catch (java.io.IOException e) {
            System.out.println("Erreur d'entrées-sorties");
        }
    }

    /**
     * Méthode qui vérifie à quel numéro de transaction, les spécimen sont
     * rendus en ouvrant le logiciel. Afin de ne pas recommencer à zéro.
     *
     * @return valeur transaction pour laquelle il faut partir de.
     */
    private int setNumeroTransaction() {

        if (listeSpecimen.size() > 0) {
            int num = 0;

            for (int i = 0; i < listeSpecimen.size(); i++) {
                if (listeSpecimen.get(i).getNumeroTransaction() > num) {
                    num = listeSpecimen.get(i).getNumeroTransaction();
                }
            }
            return num;
        } else {
            return 1;
        }
    }

    /**
     * Méthode servant à afficher les options du menu principal.
     */
    private void afficherMenu() {
        System.out.println("\n");
        System.out.println("***************Menu Principal****************");
        System.out.println("1) Consulter les spécimens\n"
                + "2) Ajouter un spécimen\n"
                + "3) Modifier un spécimen\n"
                + "4) Statistiques\n"
                + "5) Quitter");
        System.out.println("---------------------------------------------");
        System.out.print("Veuillez entrer votre choix > ");
    }

    /**
     * Méthode universelle demandant une valeur numérique (integer) en fonction
     * de deux bornes.
     *
     * @param borneA Valeur possible minimum a entrer.
     * @param borneB Valeur possible maximum a entrer.
     * @return Valeur numérique qui respecte les bornes.
     */
    private int demanderValNumerique(int borneA, int borneB) {
        int valeur = borneA - 1;//inutile???
        boolean erreur = true;

        while (erreur) {
            try {

                valeur = Integer.parseInt(s.nextLine());

                if (valeur < borneA || valeur > borneB) {
                    System.out.println("Veuillez entrer un nombre entre " + borneA + " et " + borneB);
                } else {
                    erreur = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Choix Erroné. Veuillez entrer que des nombres.\n");
            }
        }
        return valeur;
    }

    /**
     * Méthode qui s'occupe de demander le choix à exécuter du pokedex en
     * affichant le menu, ainsi que demandant son choix et dépendant de cette
     * valeur: appeler les autres méthodes associées.
     */
    private void demanderChoix() {

        boolean quitter = false;
        while (!quitter) {

            int choix = 0;
            afficherMenu();
            choix = demanderValNumerique(1, 5);

            switch (choix) {
                case 1: //Consulter Spécimen
                    consulterSpecimen();
                    break;
                case 2: //Saisir un Spécimen
                    saisirSpecimen();
                    break;
                case 3://Modifier Spécimen
                    menuModifierSpecimen();
                    break;
                case 4://Statistiques
                    afficherEntreePersonne();
                    afficherEntreeSpecimen();
                    afficherInfoPersonne();
                    break;
                case 5://Quitter et mettre le contenu dans un fichier.
                    quitter = true;
                    ecrirePokedex();
                    break;
            }
        }
    }

    /**
     * Méthode qui demande de rentrer son code d'accès.
     *
     * @return Chaine de String contenant son code d'accès.
     */
    private String demanderUsername() {
        System.out.println("Veuillez entrer votre username.");
        return s.nextLine();
    }

    /**
     * Méthode qui demande de rentrer son mot de passe.
     *
     * @return Chaine de String contenant son mot de passe en l'encryptant.
     */
    private String demanderMDP() {

        Encryption encrypt = null;
        try {
            encrypt = new Encryption();
        } catch (Exception e) {
        }
        System.out.println("Veuillez entrer votre mot de passe.");
        return encrypt.encrypt(s.nextLine());
    }

    /**
     * Méthode qui valide si l'utilisateur existe bien ainsi que vérifier si son
     * mot de passe (encrypté) est le bon que celui du fichier. Si 3 tentatives
     * ont été échoué, impossible d'accéder au pokedex.
     *
     * @return Boolean qui permet de conclure si l'utilisateur a accès au
     * programme.
     */
    private boolean verifierUtilisateur() {
        String user, passe;

        user = demanderUsername();
        passe = demanderMDP();

        for (int i = 0; i < listePers.size(); i++) {
            if (listePers.get(i).getCodeAcces().equals(user) && listePers.get(i).getMotDePasse().equals(passe)) {
                utilisateur = listePers.get(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Méthode qui permet de saisir un nouveau spécimen en le créant (objet
     * instancié) On lui demande toutes les paramètres à rentrer dépendant du
     * type (facilitée par l'héritage et l'encapsulation).
     */
    private void saisirSpecimen() {
        String date = demanderDate();
        int type = demanderType();
        System.out.println("Donner le nom:");
        String nom = s.nextLine();
        System.out.println("Donner une couleur:");
        String couleur = s.nextLine();
        System.out.println("Donner une taille (cm).");
        int taille = 0;
        while (taille <= 0) {
            taille = demanderValNumerique(1, Integer.MAX_VALUE);
        }

        System.out.println("Donner la quantité de Specimens.");
        int quantite = 0;
        while (quantite <= 0) {
            quantite = demanderValNumerique(1, Integer.MAX_VALUE);
        }
        numeroTransaction++;
        
        switch (type) {

            case 1://poisson
                listeSpecimen.add(new Poisson(nom, couleur, date, taille, quantite, demanderSexe(), demanderEau(), utilisateur, numeroTransaction, demanderCri()));

                break;
            case 2://mammifere
                listeSpecimen.add(new Mammifere(nom, couleur, date, taille, quantite, demanderSexe(), demanderEau(), utilisateur, numeroTransaction, demanderCri(), demanderNourriture()));
                break;
            case 3://plante
               
                listeSpecimen.add(new Plante(nom, couleur, date, taille, quantite, demanderEau(), demanderFlotte(), utilisateur, numeroTransaction));
                break;
                
            case 4://minéral
                listeSpecimen.add(new Mineral(nom, couleur, date, taille, quantite, utilisateur, numeroTransaction));
                break;
            case 5://Autre
                listeSpecimen.add(new Autre(nom, couleur, date, taille, quantite, demanderSexe(), utilisateur, numeroTransaction, demanderCri()));

                break;
        }

    }

    /**
     * Demande choix du Sexe
     *
     * @return enum déterminant le sexe du spécimen.
     */
    private Sexe demanderSexe() {
        Sexe sexe;

        System.out.println("Quel est votre choix?\n"
                + "1) Mâle\n"
                + "2) Femelle");

        if (demanderValNumerique(1, 2) == 1) {
            sexe = Sexe.MÂLE;
        } else {
            sexe = Sexe.FEMELLE;
        }
        return sexe;
    }

    /**
     * Demande choix du type d'eau.
     *
     * @return enum déterminant le type d'eau.
     */
    private Eau demanderEau() {
        Eau eau;

        System.out.println("Quel est votre choix?\n"
                + "1) Eau Salée\n"
                + "2) Eau douce");

        if (demanderValNumerique(1, 2) == 1) {
            eau = Eau.SALÉE;
        } else {
            eau = Eau.DOUCE;
        }
        return eau;
    }

    /**
     * Demande choix du type Flotte.
     *
     * @return enum déterminant si flotte ou immergée.
     */
    private Flotte demanderFlotte() {
        Flotte flotte;

        System.out.println("Quel est votre choix?\n"
                + "1) Flottante\n"
                + "2) Immergée");
        if (demanderValNumerique(1, 2) == 1) {
            flotte = Flotte.FLOTTANTE;
        } else {
            flotte = Flotte.IMMERGÉE;
        }
        return flotte;
    }

    /**
     * Méthode demandant choix de nourriture
     *
     * @return un enum avec les deux choix possibles.
     */
    private Nourriture demanderNourriture() {
        Nourriture nourriture;

        System.out.println("Quel est votre choix?\n"
                + "1) Carnivore\n"
                + "2) Végétarien");
        if (demanderValNumerique(1, 2) == 1) {
            nourriture = Nourriture.CARNIVORE;
        } else {
            nourriture = Nourriture.VÉGÉTARIEN;
        }
        return nourriture;
    }

    /**
     * Demande de rentrer une date. Recommence tant que la date n'est pas 8
     * valeurs numériques (Integer).
     *
     * @return String contenant la date.
     */
    private String demanderDate() {

        String date = "";
        boolean erreur = true;

        while (erreur) {
            erreur = true;
            System.out.println("Veuillez entrer la date (8 caractères annee/mois/jour)");
            try {
                date = s.nextLine();
                Integer.parseInt(date);
                if (date.length() != 8) {
                    System.out.println("Format de date invalide!");
                } else {
                    erreur = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrez un nombre valide!");
            }
        }
        return date;
    }

    /**
     * Méthode qui demande le cri
     *
     * @return string contenant le cri.
     */
    private String demanderCri() {
        System.out.println("Veuillez entrer un cri.");
        return s.nextLine();
    }

    /**
     * Demander quel Type de Spécimen.
     *
     * @return une valeur numérique (choix) entre 1 et 5 déterminant le type.
     */
    private int demanderType() {
        int choix = 0;

        System.out.println("Veuillez donner le type d'entrée:\n"
                + "1) Poisson\n"
                + "2) Mammifère Marin\n"
                + "3) Plante aquatique\n"
                + "4) Minéral\n"
                + "5) Autre");

        choix = demanderValNumerique(1, 5);

        return choix;
    }

    /**
     * Méthode destinée à afficher les Spécimen trier selon choix de
     * l'utilisateur.
     */
    private void consulterSpecimen() {
        if (listeSpecimen.size() > 0) {
            int choix;
            String menu1 = "1. Afficher toutes les entrées\n"
                    + "2. Afficher toutes les entrées d'un seul type\n"
                    + "3. Afficher tous les animaux \n";
            System.out.println(menu1);

            choix = demanderValNumerique(1, 3);
            switch (choix) {
                case 1:
                    triCroissanteInsertion();
                    pileDecroissante();
                    break;
                case 2:
                    triCroissanteBulle();
                    break;
                case 3:
                    triCroissanteParSelection();
                    break;
            }
        } else {
            System.out.println("Il n'y a pas de spécimens enregistrés!");
        }
    }

    /**
     * Méthode servant à trier/afficher la liste de Spécimen alphabétiquement de
     * façon croissante.
     */
    private void triCroissanteInsertion() {
        System.out.println("=======================\n"
                + "Croissante:\n"
                + "=======================");
        for (int i = 1; i < listeSpecimen.size(); i++) {
            Specimen specimen = listeSpecimen.get(i);
            int position = i;

            while (position > 0 && specimen.getNom().compareTo(listeSpecimen.get(position - 1).getNom()) < 0) {
                listeSpecimen.set(position, listeSpecimen.get(position - 1));
                position--;
            }
            listeSpecimen.set(position, specimen);
        }

        for (int i = 0; i < listeSpecimen.size(); i++) {
            System.out.println(listeSpecimen.get(i));
        }

        System.out.println();
    }

    /**
     * Méthode servant à trier/afficher la liste de Spécimen alphabétiquement de
     * façon décroissante en utilisant une pile de la liste précédemment trier
     * croissante.
     */
    private void pileDecroissante() {
        Stack<Specimen> pileSpecimen = new Stack<>();
        pileSpecimen.addAll(listeSpecimen);
        System.out.println("=======================\n"
                + "Décroissante:\n"
                + "=======================");
        for (int i = 0; i < listeSpecimen.size(); i++) {
            System.out.println(pileSpecimen.pop());
        }
    }

    /**
     * Méthode servant à trier/afficher toutes les entrées d'un seul type
     * demandé par l'utilisateur. Trié de façons croissante par taille.
     */
    private void triCroissanteBulle() {
        Specimen temp;

        for (int i = 0; i < listeSpecimen.size() - 1; i++) {
            for (int j = 0; j < listeSpecimen.size() - i - 1; j++) {
                if (listeSpecimen.get(j).getTaille() > listeSpecimen.get(j + 1).getTaille()) {
                    temp = listeSpecimen.get(j);
                    listeSpecimen.set(j, listeSpecimen.get(j + 1));
                    listeSpecimen.set(j + 1, temp);
                }
            }
        }
        int type = demanderType();
        afficherTypeSpecimen(type);

    }

    /**
     * Méthode servant à trier et afficher tous les animaux, triés par date
     * croissante d'observation.
     */
    private void triCroissanteParSelection() {
        int posDateMin;
        Specimen temp;
        boolean existe = false;

        for (int i = 0; i < listeSpecimen.size() - 1; i++) {
            posDateMin = i;
            for (int j = i + 1; j < listeSpecimen.size(); j++) {
                if (Integer.parseInt(listeSpecimen.get(j).getDate()) < Integer.parseInt(listeSpecimen.get(posDateMin).getDate())) {
                    posDateMin = j;
                }
            }
            temp = listeSpecimen.get(posDateMin);
            listeSpecimen.set(posDateMin, listeSpecimen.get(i));
            listeSpecimen.set(i, temp);
        }

        for (int i = 0; i < listeSpecimen.size(); i++) {
            if (listeSpecimen.get(i) instanceof Animal) {
                System.out.println(listeSpecimen.get(i));
                existe = true;
            }
        }
        if (!existe) {
            System.out.println("Il n'y a pas d'animaux enregistrés!");
        }
    }

    /**
     * Méthode servant à modifier un spécimen et lui demander un choix à faire
     * entre (supprimer, modifier et annuler son action). Dépendant du choix,
     * modifier le spécimen.
     */
    private void menuModifierSpecimen() {
        int posSpecimen;
        if (listeSpecimen.size() > 0) {
            System.out.println("\n");
            System.out.println("Modifier un spécimen:");
            System.out.println("1) Supprimer un spécimen\n"
                    + "2) Modifier la quantité aperçue d'un spécimen\n"
                    + "3) Retourner au menu principal.");
            System.out.println("---------------------------------------------");
            System.out.print("Veuillez entrer votre choix > ");
            int choix = demanderValNumerique(1, 3);
            int type;

            switch (choix) {
                case 1:
                    type = demanderType();
                    posSpecimen = afficherTypeSpecimen(type);
                    if (posSpecimen > 0) {
                        listeSpecimen.remove(choixSpecimen(posSpecimen, type));
                        System.out.println("Le spécimen a été supprimé!");
                    }
                    break;
                case 2:
                    Specimen specimenChoisi;
                    type = demanderType();
                    posSpecimen = afficherTypeSpecimen(type);
                    if (posSpecimen > 0) {
                        specimenChoisi = listeSpecimen.get(choixSpecimen(posSpecimen, type));
                        System.out.println("Veuillez entrer la nouvelle quantité");
                        int quantite = demanderValNumerique(1, Integer.MAX_VALUE);
                        specimenChoisi.setQuantite(quantite);
                        System.out.println("Quantité modifiée!");
                    }
                    break;
                case 3:
                    System.out.println("Choix annulé");
                    break;
            }
        } else {
            System.out.println("Il n'y a pas de spécimens enregistrés!");
        }
    }

    /**
     * Méthode universel qui affiche un type de Spécimen. Parcours la liste de
     * spécimen et si c'est le bon type, on affiche.
     *
     * @param type Demandé précédemment; permet de dire quel type on affiche.
     * @return compteur qui sert à dire le nombre d'objets (instances) du type
     * choisi.
     */
    private int afficherTypeSpecimen(int type) {
        int compteur = 0;
        boolean existe = false;
        for (int i = 0; i < listeSpecimen.size(); i++) {

            switch (type) {
                case 1:
                    if (listeSpecimen.get(i) instanceof Poisson) {
                        compteur++;
                        System.out.println("===================\n"
                                + "Specimen no: " + compteur);
                        System.out.println(listeSpecimen.get(i));

                        existe = true;
                    }
                    break;
                case 2:

                    if (listeSpecimen.get(i) instanceof Mammifere) {
                        compteur++;
                        System.out.println("===================\n"
                                + "Specimen no: " + compteur);
                        System.out.println(listeSpecimen.get(i));

                        existe = true;
                    }
                    break;
                case 3:

                    if (listeSpecimen.get(i) instanceof Plante) {
                        compteur++;
                        System.out.println("===================\n"
                                + "Specimen no: " + compteur);
                        System.out.println(listeSpecimen.get(i));

                        existe = true;
                    }
                    break;
                case 4:

                    if (listeSpecimen.get(i) instanceof Mineral) {
                        compteur++;
                        System.out.println("===================\n"
                                + "Specimen no: " + compteur);
                        System.out.println(listeSpecimen.get(i));

                        existe = true;
                    }
                    break;
                case 5:

                    if (listeSpecimen.get(i) instanceof Autre) {
                        compteur++;
                        System.out.println("===================\n"
                                + "Specimen no: " + compteur);
                        System.out.println(listeSpecimen.get(i));

                        existe = true;
                    }
                    break;
            }
        }
        if (!existe) {
            System.out.println("Il n'existe pas de spécimens de ce type!");
        }
        return compteur;
    }

    /**
     * Méthode servant à trouver la position du spécimen EXACT dans la liste de
     * Spécimen car on sait déjà le type ainsi que le nombre de spécimen du
     * type, ce qui nous permet par calcul de trouver la position exacte.
     *
     * @param compt Nombre de Spécimen MAX du types
     * @param type Type de Spécimen (5 choix)
     * @return index: position du spécimen exacte dans la liste de Spécimen.
     */
    private int choixSpecimen(int compt, int type) {
        int choix = 0, index = -1, numSpec = 0;
        boolean trouve = false;
        System.out.println("Choisissez votre Spécimen:");
        choix = demanderValNumerique(1, compt);

        while (!trouve && index < listeSpecimen.size() - 1) {
            index++;
            switch (type) {
                case 1:
                    if (listeSpecimen.get(index) instanceof Poisson) {
                        numSpec++;
                    }
                    break;
                case 2:
                    if (listeSpecimen.get(index) instanceof Mammifere) {
                        numSpec++;
                    }
                    break;
                case 3:
                    if (listeSpecimen.get(index) instanceof Plante) {
                        numSpec++;
                    }
                    break;
                case 4:
                    if (listeSpecimen.get(index) instanceof Mineral) {
                        numSpec++;
                    }
                    break;
                case 5:
                    if (listeSpecimen.get(index) instanceof Autre) {
                        numSpec++;
                    }
                    break;
            }
            if (numSpec == choix) {
                trouve = !trouve;
            }
        }

        return index;
    }

    /**
     * Calculer le nombre totales de chaque Type de Spécimen en les affichant.
     */
    private void afficherEntreeSpecimen() {
        int nbPoisson = 0, nbMammifere = 0, nbPlante = 0, nbMineral = 0, nbAutre = 0;
        for (int i = 0; i < listeSpecimen.size(); i++) {
            if (listeSpecimen.get(i) instanceof Poisson) {
                nbPoisson++;
            } else if (listeSpecimen.get(i) instanceof Mammifere) {
                nbMammifere++;
            } else if (listeSpecimen.get(i) instanceof Plante) {
                nbPlante++;
            } else if (listeSpecimen.get(i) instanceof Mineral) {
                nbMineral++;
            } else {
                nbAutre++;
            }
        }

        System.out.println("Nombre de poissons: " + nbPoisson);
        System.out.println("Nombre de mammifères: " + nbMammifere);
        System.out.println("Nombre de plantes: " + nbPlante);
        System.out.println("Nombre de minéraux: " + nbMineral);
        System.out.println("Nombre autres: " + nbAutre);
    }

    /**
     * Afficher le nombre d'entrées, pour chacune des personnes.
     */
    private void afficherEntreePersonne() {
        int compt;
        for (int i = 0; i < listePers.size(); i++) {
            compt = 0;
            for (int j = 0; j < listeSpecimen.size(); j++) {
                if (listeSpecimen.get(j).getPersonne().getNom().equals(listePers.get(i).getNom())) {
                    compt++;
                }
            }
            System.out.println(listePers.get(i).getNom() + ": " + compt + " entrées");
        }
    }

    /**
     * Afficher les informations des personnes du système (sauf mot de passe) en
     * retournant la liste de personne.toString (qui retourne toutes les infos).
     */
    private void afficherInfoPersonne() {
        System.out.println(listePers);
    }

}
