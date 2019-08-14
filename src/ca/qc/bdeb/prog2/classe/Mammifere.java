package ca.qc.bdeb.prog2.classe;

import java.io.Serializable;

/**
 * Classe Mammifere qui hérite des attributs d'animal et spécimen.
 *
 * @author Martin et Seina
 */
public class Mammifere extends Animal implements Serializable {

    private Eau eau;

    /**
     * Enum pour type de régime
     */
    public enum Nourriture {
        CARNIVORE,
        VÉGÉTARIEN;
    }
    private Nourriture nourriture;

    /**
     * Constructeur d'un Mammifere.
     *
     * @param nom
     * @param couleur
     * @param date
     * @param taille
     * @param quantite
     * @param sexe
     * @param eau //Nouveau paramètre.
     * @param personne
     * @param numeroTransaction
     * @param cri
     * @param nourriture
     */
    public Mammifere(String nom, String couleur, String date, int taille, int quantite, Sexe sexe, Eau eau, Personne personne, int numeroTransaction, String cri, Nourriture nourriture) {
        super(nom, couleur, date, taille, quantite, sexe, personne, numeroTransaction, cri);
        this.eau = eau;
        this.nourriture = nourriture;
    }

    /**
     * Retourne par un String les attributs du Spécimen
     *
     * @return toString
     */
    @Override
    public String toString() {

        return (super.toString()
                + "\nEau: " + this.eau
                + "\nType de nourriture: " + this.nourriture);
    }
}
