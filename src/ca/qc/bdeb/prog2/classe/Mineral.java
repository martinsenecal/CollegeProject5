
package ca.qc.bdeb.prog2.classe;

import java.io.Serializable;

/**
 * Classe Minéral qui hérite des attributs de Spécimen (plus précis)
 * @author Martin Senécal && Seina Assadian
 */
public class Mineral extends Specimen implements Serializable {
    /**
     * Constructeur de minéral. (beaucoups d'attributs sont appeler par super)
     * @param nom
     * @param couleur
     * @param date
     * @param taille
     * @param quantite
     * @param personne 
     */
    public Mineral(String nom, String couleur, String date, int taille, int quantite, Personne personne, int numeroTransaction) {
        super(nom, couleur, date, taille, quantite, personne, numeroTransaction);
    }

    /**
     * Retourner le toString du parents
     * @return toString
     */
    @Override
    public String toString() {
        return super.toString();
    }
    
    
}
