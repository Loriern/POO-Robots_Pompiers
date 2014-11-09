package donnees;
import java.util.*;

public class DonneesSimulation{

    private LinkedList<Incendie> listeIncendies;
    private Carte carte;
    private Robot[] pompiers;
    
    public DonneesSimulation(int nbLignes, int nbColonnes, int tailleCases){

        this.carte = new Carte(nbLignes, nbColonnes, tailleCases);
    }
    
    public void initListInc(){
        listeIncendies = new LinkedList<Incendie>();
    }

    public Carte getCarte(){
        return carte;
    }
}
