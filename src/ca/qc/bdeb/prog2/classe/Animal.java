
package ca.qc.bdeb.prog2.classe;

import java.io.Serializable;

/**
 *Classe Animal (hérite de Spécimen)
 * @author 1737787
 */
public abstract class Animal extends Specimen implements Serializable {
/**
 * Création d'un énum pour le sexe.
 */
    public enum Sexe {
        MÂLE,
        FEMELLE;
    }
    protected Sexe sexe;
    protected String cri;
/**
 * Constructeur d'un Animal (hérite beaucoup des attributs de spécimen)
 * @param nom
 * @param couleur
 * @param date
 * @param taille
 * @param quantite
 * @param sexe //Nouvel attribut créée.
 * @param personne 
 * @param numeroTransaction 
 * @param cri //Nouvel attribut créer
 */
    public Animal(String nom, String couleur, String date, int taille, int quantite, Sexe sexe, Personne personne, int numeroTransaction, String cri) {
        super(nom, couleur, date, taille, quantite, personne, numeroTransaction);
        this.sexe = sexe;
        this.cri = cri;
    }
/**
 * Retourne les attributs + le nouvel attribut
 * @return toString.
 */
    @Override
    public String toString() {
        return (super.toString() 
                + "\nSexe: " + this.sexe
                + "\nCri: " + this.cri); //To change body of generated methods, choose Tools | Templates.
    }

}
