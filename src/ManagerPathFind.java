import java.util.*;
import donnees.*;
import evenements.*;

public class ManagerPathFind extends Manager {

	public ManagerPathFind(Simulateur simulateurToConnect/*, Case destination*/){
		super(simulateurToConnect);
	}

	public void manage(){
		int j = 1;
		Carte map = getSimulateur().getDonneesSimulation().getCarte();
		Robot roues = getSimulateur().getDonneesSimulation().getRobots()[1];
		Incendie incendieAEteindre = getSimulateur().getDonneesSimulation().getIncendie(0);

//																	OBJECTIF
		PathFinding chemin = new PathFinding(map, roues, incendieAEteindre.getPosition());
		LinkedList<Case> solution;


		solution = chemin.getSolution();

		Case previous = roues.getPosition();
		for (Case c : solution) {
			getSimulateur().ajouteEvenement(new EvenementDeplacement(j, map, roues, previous.getOrientation(c)));
			previous = c;
			j++;
		}

		solution.clear();
	}
}