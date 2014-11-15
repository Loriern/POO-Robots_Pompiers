package donnees;

import java.util.*;
import donnees.robot.*;

public class DonneesSimulation{

    private LinkedList<Incendie> listeIncendies;
    private Carte carte;
    private Robot[] pompiers;

    public DonneesSimulation(int nbLignes, int nbColonnes, int tailleCases){
        this.carte = new Carte(nbLignes, nbColonnes, tailleCases);
    }

    public void initListInc(){
        this.listeIncendies = new LinkedList<Incendie>();
    }

	public void ajouteIncendie(Incendie incendie) {
		this.listeIncendies.add(incendie);
	}

	public LinkedList<Incendie> getIncendies() {
		return this.listeIncendies;
	}

	public Incendie getIncendie(int i) {
		return this.listeIncendies.get(i);
	}

    public Carte getCarte(){
        return this.carte;
    }

	public void initRobotTab(int nbRobots){
        this.pompiers = new Robot[nbRobots];
    }

    public void initRobot(int i, Case Depart, RobotType robotType){
		switch (robotType) {
				case DRONE:
						this.pompiers[i] = new Drone(Depart);
						break;
				case CHENILLES:
						this.pompiers[i] = new RobotAChenilles(Depart);
						break;
				case PATTES:
						this.pompiers[i] = new RobotAPattes(Depart);
						break;
				case ROUES:
						this.pompiers[i] = new RobotARoues(Depart);
						break;
			}
    }

    public Robot[] getRobots(){
        return this.pompiers;
    }

// 	public Robot getRobot(int i){
// 		return this.pompiers[i];
// 	}

}
