import java.io.*;
import java.util.*;
import donnees.*;
import donnees.evenements.*;

public class ManagerScenario extends Manager {

	public ManagerScenario(Simulateur simulateurToConnect){
		super(simulateurToConnect);
	}

	public void manage(){
		Carte map = super.getSimulateur().getDonneesSimulation().getCarte();
		Robot drone = super.getSimulateur().getDonneesSimulation().getRobots()[0];
		Robot roues = super.getSimulateur().getDonneesSimulation().getRobots()[1];
		LinkedList<Incendie> listeDIncendies = super.getSimulateur().getDonneesSimulation().getIncendies();
		Incendie IncendieAEteindre = super.getSimulateur().getDonneesSimulation().getIncendie(4);

// 		super.getSimulateur().ajouteEvenement(new EvenementDeplacement(i, map, drone, NORD));	// i dépendant de la vitesse
		super.getSimulateur().ajouteEvenement(new EvenementDeplacement(1, map, drone, Direction.NORD));
		super.getSimulateur().ajouteEvenement(new EvenementDeplacement(2, map, drone, Direction.NORD));
		super.getSimulateur().ajouteEvenement(new EvenementDeplacement(3, map, drone, Direction.NORD));
		super.getSimulateur().ajouteEvenement(new EvenementDeplacement(4, map, drone, Direction.NORD));

// 		for (int i = 0; i < 20; i++) {
			super.getSimulateur().ajouteEvenement(new EvenementDeverserEau(1, roues, listeDIncendies, IncendieAEteindre));
// 		}
		super.getSimulateur().ajouteEvenement(new EvenementDeplacement(2, map, roues, Direction.OUEST));

		for (int i = 0; i < 10; i++) {
			super.getSimulateur().ajouteEvenement(new EvenementRemplirReservoir(3+i, roues));
		}

		super.getSimulateur().ajouteEvenement(new EvenementDeplacement(13, map, roues, Direction.EST));

		super.getSimulateur().ajouteEvenement(new EvenementDeverserEau(14, roues, listeDIncendies, IncendieAEteindre));
	}
}