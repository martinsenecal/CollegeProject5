
package ca.qc.bdeb.prog2.classe;

import java.io.Serializable;

/**
 *Classe Autre: hérite des attributs de spécimen et animal
 * @author martin et seina
 */
public class Autre extends Animal implements Serializable {
    
    /**
     * Constructeur de autre. (hérite à 100% des attributs des autres)
     * @param nom
     * @param couleur
     * @param date
     * @param taille
     * @param quantite
     * @param sexe
     * @param personne 
     * @param numeroRransaction
     * @param cri
     */
    public Autre(String nom, String couleur, String date, int taille, int quantite, Sexe sexe, Personne personne, int numeroTransaction, String cri) {
        super(nom, couleur, date, taille, quantite, sexe, personne, numeroTransaction, cri);
    }

    /**
     * Retourner un string des attributs 
     * @return toString
     */
    @Override
    public String toString() {
        return super.toString();
    }
}
