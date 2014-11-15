import donnees.*;
import donnees.evenements.*;

public class ManagerScenario extends Manager {

	public ManagerScenario(Simulateur simulateurToConnect){
		super(simulateurToConnect);
	}

	public void manage(){
		Robot drone = super.getSimulateur().getDonneesSimulation().getRobots()[0];
		Carte map = super.getSimulateur().getDonneesSimulation().getCarte();

// 		super.getSimulateur().ajouteEvenement(new EvenementDeplacement(i, map, drone, NORD));	// i dépendant de la vitesse
		super.getSimulateur().ajouteEvenement(new EvenementDeplacement(1, map, drone, Direction.NORD));
		super.getSimulateur().ajouteEvenement(new EvenementDeplacement(2, map, drone, Direction.NORD));
		super.getSimulateur().ajouteEvenement(new EvenementDeplacement(3, map, drone, Direction.NORD));
		super.getSimulateur().ajouteEvenement(new EvenementDeplacement(4, map, drone, Direction.NORD));
	}
}