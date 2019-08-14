
package ca.qc.bdeb.prog2.classe;

import java.io.Serializable;

/**
 * Classe Plante qui hérite des attributs de spécimens
 * @author 1737787
 */
public class Plante extends Specimen implements Serializable {
    private Eau eau;
    /**
     * Enum
     */
    public enum Flotte {
        FLOTTANTE,
        IMMERGÉE;
    }
    private Flotte flotte;
/**
 * Constructeur qui instancie une Plante. (reprend des anciens attributs par super())
 * @param nom
 * @param couleur
 * @param date
 * @param taille
 * @param quantite
 * @param eau
 * @param flotte
 * @param personne 
 */
    public Plante(String nom, String couleur, String date, int taille, int quantite, Eau eau, Flotte flotte, Personne personne, int numeroTransaction) {
        super(nom, couleur, date, taille, quantite, personne, numeroTransaction);
        this.eau = eau;
        this.flotte = flotte;
    }

    /**
     * ToString affichant les nouveaux attributs
     * @return toString.
     */
    @Override
    public String toString() {
        return (super.toString()
                + "\nEau: " + this.eau
                + "\nFlotte: " + this.flotte);
    }
    
    

}
