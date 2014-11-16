import java.io.*;
import java.util.*;
import java.awt.Color;

import ihm.*;
import donnees.*;
import donnees.robot.*;


public class Simulateur implements Simulable {

    private DonneesSimulation simData;
    private long dateSimulation;
    private PriorityQueue<Evenement> events;
    private Manager manager;
    private IGSimulateur ihm;

    public Simulateur(String fileName)
        throws FileNotFoundException, ExceptionFormatDonnees {
        this.dateSimulation = 0;
        // 		this.nomDuFichier = filename;
        this.events = new PriorityQueue<Evenement>();
        this.manager = new ManagerScenario(this);
        simData = LecteurDonnees.initData(fileName);
        this.manager.manage();
        ihm = new IGSimulateur(simData.getCarte().getNbColonnes(), simData.getCarte().getNbLignes(), this);
        dessine();    // mettre a jour l'affichage
	}

	@Override
	public void next() {
		incrementeDate();
		System.out.println("" + dateSimulation);
// 		System.out.println("TODO: avancer la simulation \"d'un pas de temps\": " + dateSimulation);
// 		System.out.println("  => On voit ce que ça donne!");
		dessine();    // mettre a jour l'affichage
	}

	@Override
	public void restart() {
		System.out.println("TODO: remettre le simulateur dans son état initial");
//	this.Simulateur(nomDuFichier);
		dateSimulation = 0;
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
            if(pompiers[i].getClass() == Drone.class)
                    ihm.paintImage((pompiers[i].getPosition()).getColonne(), (pompiers[i].getPosition()).getLigne(), "images/drone.png", 1, 1);
            else if (pompiers[i].getClass() == RobotAChenilles.class)
                ihm.paintImage((pompiers[i].getPosition()).getColonne(), (pompiers[i].getPosition()).getLigne(), "images/chenilles.png", 1, 1);
			else if (pompiers[i].getClass() == RobotAPattes.class)
                ihm.paintImage((pompiers[i].getPosition()).getColonne(), (pompiers[i].getPosition()).getLigne(), "images/pattes.png", 1, 1);
			else if (pompiers[i].getClass() == RobotARoues.class)
                ihm.paintImage((pompiers[i].getPosition()).getColonne(), (pompiers[i].getPosition()).getLigne(), "images/roues.png", 1, 1);
			
		}
	}

	public void ajouteEvenement(Evenement e){
		this.events.add(e);
	}

	private void incrementeDate(){
		this.dateSimulation++;
		while (!simulationTerminee() && (this.events.peek().getDate() == this.dateSimulation)) {
			this.events.poll().execute();
		}
	}

	private boolean simulationTerminee(){
		if (this.events.peek() != null) {
			return false;
		}
		else {
			return true;
		}
	}

	public DonneesSimulation getDonneesSimulation(){
		return this.simData;
	}
}
