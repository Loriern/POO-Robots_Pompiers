import ihm.*;
import java.io.*;
import java.util.*;
import donnees.*;

public class Simulateur {
	public static void main(String[] args) {
		Simulation simulation = new Simulation();
	}
}

class Simulation implements Simulable {
// 	private Carte carte;
		// Ou plutôt DonneesSimulation ?
    private DonneesSimulation donneesSim;
	private IGSimulateur ihm;
    private long date = 0;

	public Simulation() {
		try {
		// cree l'IHM et l'associe a ce simulateur (this), qui en tant que
		// Simulable recevra les evenements suite aux actions de l'utilisateur
		// LecteurDonnees.lire(fichierDonnees);
		LecteurDonnees.lire("cartes/carteSujet.txt");
// 		donneesSim = new DonneesSimulation(this);
// 		ihm = new IGSimulateur(nbColonnes, nbLignes, this);
		ihm = new IGSimulateur(donneesSim.getCarte().getNbColonnes(), donneesSim.getCarte().getNbLignes(), this);
		dessine();    // mettre a jour l'affichage
		} catch (FileNotFoundException e) {
			System.out.println("Ce fichier n'existe pas.");
		} catch (ExceptionFormatDonnees ef){
			System.out.println("Format erroné.");
		}

	}

	@Override
	public void next() {
		date++;
		System.out.println("TODO: avancer la simulation \"d'un pas de temps\": " + date);
		System.out.println("  => On voit ce que ça donne!");
		dessine();    // mettre a jour l'affichage
	}

	@Override
	public void restart() {
		System.out.println("TODO: remettre le simulateur dans son état initial");
		date = 0;
		dessine();    // mettre a jour l'affichage
	}

	private void dessine() {
        // Afficher les donnees
// 		try {
			for (int i = 0; i < donneesSim.getCarte().getNbLignes(); i++) {
				for (int j = 0; j < donneesSim.getCarte().getNbColonnes(); j++) {
					dessineCase(i,j);
				}
			}
			// + Draw Fires
// 		} catch (MapIndexOutOfBoundsException e) {
// 			e.printStackTrace();
// 		}
	}

	private void dessineCase(int i, int j) {
		NatureTerrain natureCase = donneesSim.getCarte().getCase(i, j).getNature();
		String image = new String("");;
		switch (natureCase) {
			case EAU:
					image += "images/eau.png";
					break;
			case FORET:
					image += "images/foret.png";
					break;
			case HABITAT:
					image += "images/habitat.png";
					break;
			case ROCHE:
					image += "images/roche.png";
					break;
			case TERRAIN_LIBRE:
					image += "images/terrain_libre.png";
					break;
		}	// Launch exception with default case ?

		try {
			ihm.paintImage(i, j, image, 1, 1);
		} catch (MapIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}
}

