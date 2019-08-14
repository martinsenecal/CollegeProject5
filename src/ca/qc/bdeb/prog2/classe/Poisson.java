
package ca.qc.bdeb.prog2.classe;

import java.io.Serializable;

/**
 * Classe Poisson (hérite des attributs des spécimen et animaux)
 * Détemine de nouveaux attributs spécifiques aux poissons.
 * @author Martin Senécal ET Seina Assadian
 */
public class Poisson extends Animal implements Serializable {
    private Eau eau;
    /**
     * Constructuer qui instancie un poisson.
     * @param nom
     * @param couleur
     * @param date
     * @param taille
     * @param quantite
     * @param sexe
     * @param eau
     * @param personne
     * @param numeroTransaction
     * @param cri
     */
    
    public Poisson(String nom, String couleur, String date, int taille, int quantite, Sexe sexe, Eau eau, Personne personne, int numeroTransaction, String cri) {
        super(nom, couleur, date, taille, quantite, sexe, personne, numeroTransaction, cri);
        this.eau=eau;
    }

    /**
     * toSring qui sert à afficher le type d'eau
     * @return toString
     */
    @Override
    public String toString() {
        return (super.toString()
                + "\nEau: " + this.eau);
    }
}
