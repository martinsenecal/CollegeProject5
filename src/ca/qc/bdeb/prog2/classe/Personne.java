
package ca.qc.bdeb.prog2.classe;

import java.io.Serializable;

/**
 *Classe Personne qui est implements Sérializable car en lisant le fichier binaire, on rentre des infos dans cette classe.
 * @author Martin Senécal et Seina Assadian
 */
public class Personne implements Serializable {


    private String nom, codeAcces, motDePasse;
    /* Liste MdP
    poisson
    tournevis
    pistache
    girafe
    123456
     */
    private int age;
/**
 * Constructeur qui crée une personne avec ses attributs.
 * @param codeAcces
 * @param motDePasse
 * @param nom
 * @param age 
 */
    public Personne(String codeAcces, String motDePasse, String nom, int age) {
        this.nom = nom;
        this.codeAcces = codeAcces;
        this.motDePasse = motDePasse;
        this.age = age;
    }

    /**
     * Getter CodeAcces
     * @return codeacces
     */
    public String getCodeAcces() {
        return codeAcces;
    }
/**
 * Getter Mot de Passe
 * @return mdp
 */
    public String getMotDePasse() {
        return motDePasse;
    }
/**
 * Getter nom
 * @return nom
 */
    public String getNom() {
        return nom;
    }

    /**
     * Affiche les attributs d'une personne
     * @return chaine string (toString) override.
     */
    @Override
    public String toString() {
        return "\nNom:" + nom + ""
                + "\nCode d'accès: " + codeAcces + "\nÂge:" + age + "\n"; //To change body of generated methods, choose Tools | Templates.
    }

}
