package ca.qc.bdeb.prog2;

import ca.qc.bdeb.prog2.classe.GestionPokedex;

/*
Analyse de notre TP:

Nous avons bien réparti la tâche: on s'aidait constamment et on connais super bien le code de l'autres.
Notre programme fonctionne: nous n'avons trouvé aucun bug: on a testé avec plusieurs objets toutes les options
ainsi qu'avec les différentes personnes. De plus, la sérialisation fonctionne bien. On ne croit pas qu'il ait des bug.
Bonne correction et bon été!
Martin Senécal et Seina Assadian.
 */
/**
 * Classe Main: Lancer le programme.
 *
 * @author Martin et Seina
 */
public class Tp2SenecalAssadian {

    /**
     * On ne fais que démarrer le programme.    
     *
     * @param args
     */
    public static void main(String[] args) {
        GestionPokedex gestion = new GestionPokedex();
        gestion.demarrer();

    }

}
