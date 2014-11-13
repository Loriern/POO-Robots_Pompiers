import java.io.*;
import java.util.*;
import java.awt.Color;

import ihm.*;

import donnees.*;

public class Simulateur {
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Syntaxe: java TestLecteurDonnees <nomDeFichier>");
			System.exit(1);
		}

		try {
		Simulation simulation = new Simulation(args[0]);
		} catch (FileNotFoundException e) {
			System.out.println("fichier " + args[0] + " inconnu ou illisible");
		} catch (ExceptionFormatDonnees e) {
			System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
		}
	}
}

class Simulation implements Simulable {
// 	private Carte carte;
		// Ou plutôt DonneesSimulation ?
    private DonneesSimulation simData;
	private IGSimulateur ihm;
    private long date = 0;

	public Simulation(String fileName)
			throws FileNotFoundException, ExceptionFormatDonnees {
		simData = LecteurDonnees.initData(fileName);
		ihm = new IGSimulateur(simData.getCarte().getNbColonnes(), simData.getCarte().getNbLignes(), this);
		dessine();    // mettre a jour l'affichage
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
		try {
			for (int i = 0; i < simData.getCarte().getNbLignes(); i++) {
				for (int j = 0; j < simData.getCarte().getNbColonnes(); j++) {
					ihm.paintBox(j, i, Color.GREEN);	// Attention, vicieux : la méthode demande la colonne d'abord
					dessineCase(j,i);
				}
			}
			// + Draw Fires
			dessineIncendies(simData.getIncendies());
			dessineRobots(simData.getRobots());
		} catch (MapIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	private void dessineCase(int j, int i)
			throws MapIndexOutOfBoundsException {
		NatureTerrain natureCase = simData.getCarte().getCase(i, j).getNature();
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
// 					image += "images/terrain_libre.png";
					break;
		}	// Launch exception with default case ?

// 		try {
			ihm.paintImage(j, i, image, 1, 1);
// 		} catch (MapIndexOutOfBoundsException e) {
// 			e.printStackTrace();
// 		}
	}

	private void dessineIncendies(LinkedList<Incendie> incendies)
			throws MapIndexOutOfBoundsException {
		for(Incendie i : incendies) {
			if (i.getIntensite()<5000) {
				ihm.paintImage((i.getPosition()).getColonne(), (i.getPosition()).getLigne(), "images/feu.png", 0.5, 0.5);
			}
			else if (i.getIntensite()<10000) {
				ihm.paintImage((i.getPosition()).getColonne(), (i.getPosition()).getLigne(), "images/feu.png", 0.7, 0.7);
			}
			else if (i.getIntensite()<15000) {
				ihm.paintImage((i.getPosition()).getColonne(), (i.getPosition()).getLigne(), "images/feu.png", 0.9, 0.9);
			}
			else {
				ihm.paintImage((i.getPosition()).getColonne(), (i.getPosition()).getLigne(), "images/feu.png", 1, 1);
			}
		}
	}

	private void dessineRobots(Robot[] pompiers)
			throws MapIndexOutOfBoundsException {
		for(int i = 0; i < pompiers.length; i++) {
			switch (pompiers[i].getType()) {
				case DRONE:
						ihm.paintImage((pompiers[i].getPosition()).getColonne(), (pompiers[i].getPosition()).getLigne(), "images/drone.png", 1, 1);
						break;
				case CHENILLES:
						ihm.paintImage((pompiers[i].getPosition()).getColonne(), (pompiers[i].getPosition()).getLigne(), "images/chenilles.png", 1, 1);
						break;
				case PATTES:
						ihm.paintImage((pompiers[i].getPosition()).getColonne(), (pompiers[i].getPosition()).getLigne(), "images/pattes.png", 1, 1);
						break;
				case ROUES:
						ihm.paintImage((pompiers[i].getPosition()).getColonne(), (pompiers[i].getPosition()).getLigne(), "images/roues.png", 1, 1);
						break;
			}
			pompiers[i].getPosition();
		}
	}
}

