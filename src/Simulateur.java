import ihm.*;


// Rapport à LecteurDonnees ?
public class Simulation {
	public static void main(String[] args) {
		Simulateur simulateur = new Simulateur();
	}
}


class Simulateur implements Simulable {
	private Carte carte;
	private IGSSimulateur ihm;
    private long date = 0;
}

