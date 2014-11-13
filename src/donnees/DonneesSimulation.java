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

	public void ajouteIncendie(Incendie incendie) {
		listeIncendies.add(incendie);
	}

    public Carte getCarte(){
        return carte;
    }

	public LinkedList<Incendie> getIncendies() {
		return this.listeIncendies;
	}
}
