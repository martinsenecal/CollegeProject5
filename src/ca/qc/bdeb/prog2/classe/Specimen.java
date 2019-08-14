
package ca.qc.bdeb.prog2.classe;

import java.io.Serializable;



/**
 * Classe qui instancie les Spécimens (plus général possible)
 * Abstract car c'est pas vraiment un spécimen (plus ou moins précis)
 * Implémenté sérialisable afin de les créer immédiatement dans la lecture du 
 * fichier binaire.
 * @author Martin Senécal && Seina Assadian
 */
public abstract class Specimen implements Serializable {
    protected String nom,couleur,date;
    protected int taille, quantite;
    protected int numeroTransaction=0;
    protected Personne personne;
    
    /**
     * Constructeur abstract qui instancie les spécimens.
     * @param nom Nom du Spécimen
     * @param couleur Couleur du Spécimen
     * @param date Date d'observation du Spécimen.
     * @param taille Taille du spécimen.
     * @param quantite Quantité observée.
     * @param personne  Personne qui a observée le spécimen (classe Personne)
     */
    public Specimen(String nom, String couleur, String date, int taille, int quantite, Personne personne, int numeroTransaction) {
        this.nom = nom;
        this.couleur = couleur;
        this.date = date;
        this.taille = taille;
        this.quantite = quantite;
        this.personne = personne;
        this.numeroTransaction = numeroTransaction;
    }

    public int getNumeroTransaction() {
        return numeroTransaction;
    }

    /**
     * Getter Personne
     * @return personne
     */
    public Personne getPersonne() {
        return personne;
    }
/**
 * Getter Nom
 * @return nom
 */
    public String getNom() {
        return nom;
    }
/**
 * Getter Date
 * @return date
 */
    public String getDate() {
        return date;
    }

    /**
     * ToString du Spécimen (afficher infos)
     * @return  Retourne une String contenant les infos du Spécimen
     */
    @Override
    public String toString() {
        return ("===================\n"
                + "Numéro de transaction: " + this.numeroTransaction
                + "\nNom: " + this.nom
                + "\nCouleur: " + this.couleur
                + "\nDate: " + this.date
                + "\nTaille: " + this.taille + " cm"
                + "\nQuantité: " + this.quantite
                + "\nUtilisateur: " + this.personne.getCodeAcces());
    }
/**
 * Getter Taille
 * @return taille
 */
    public int getTaille() {
        return taille;
    }
/**
 * Getter Quantite
 * @return quantite 
 */
    public int getQuantite() {
        return quantite;
    }

    /**
     * Setter Quantite
     * @param quantite nombre observés.
     */
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    

  
    
    
}
