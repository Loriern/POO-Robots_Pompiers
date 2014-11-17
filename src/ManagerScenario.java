import java.util.*;
import donnees.*;
import evenements.*;

public class ManagerScenario extends Manager {

	public ManagerScenario(Simulateur simulateurToConnect){
		super(simulateurToConnect);
	}

	public void manage(){
		Carte map = getSimulateur().getDonneesSimulation().getCarte();
		Robot drone = getSimulateur().getDonneesSimulation().getRobots()[0];
		Robot roues = getSimulateur().getDonneesSimulation().getRobots()[1];
		LinkedList<Incendie> listeDIncendies = getSimulateur().getDonneesSimulation().getIncendies();
		Incendie incendieAEteindre = getSimulateur().getDonneesSimulation().getIncendie(4);

		int nbInterventionsRoues = (roues.getCapaciteMax())/(roues.quantiteIntervention());

// 		super.getSimulateur().ajouteEvenement(new EvenementDeplacement(i, map, drone, NORD));	// i dépendant de la vitesse
		getSimulateur().ajouteEvenement(new EvenementDeplacement(1, map, drone, Direction.NORD));
		getSimulateur().ajouteEvenement(new EvenementDeplacement(2, map, drone, Direction.NORD));
		getSimulateur().ajouteEvenement(new EvenementDeplacement(3, map, drone, Direction.NORD));
		getSimulateur().ajouteEvenement(new EvenementDeplacement(4, map, drone, Direction.NORD));
/////////////////////////////////// - Fin Drône
		int j = 1;
		getSimulateur().ajouteEvenement(new EvenementDeplacement(j, map, roues, Direction.NORD));

		j++;
		// A automatiser
		if ((incendieAEteindre.getIntensite())/(roues.quantiteIntervention()) < nbInterventionsRoues) {
			nbInterventionsRoues = (incendieAEteindre.getIntensite())/(roues.quantiteIntervention());
		}
		for (int i = 0; i < nbInterventionsRoues; i++) {
			getSimulateur().ajouteEvenement(new EvenementDeverserEau(j, roues, listeDIncendies, incendieAEteindre));
			j += roues.tempsIntervention();
		}
		getSimulateur().ajouteEvenement(new EvenementDeplacement(j, map, roues, Direction.OUEST));
		j++;
		getSimulateur().ajouteEvenement(new EvenementDeplacement(j, map, roues, Direction.OUEST));
		j++;

		for (int i = 0; i < roues.tempsPourRemplir(); i++) {
			super.getSimulateur().ajouteEvenement(new EvenementRemplirReservoir(j, map, roues));
			j++;
		}

		getSimulateur().ajouteEvenement(new EvenementDeplacement(j, map, roues, Direction.EST));
		j++;
		getSimulateur().ajouteEvenement(new EvenementDeplacement(j, map, roues, Direction.EST));
		j++;


		if ((incendieAEteindre.getIntensite() - nbInterventionsRoues*roues.quantiteIntervention())/(roues.quantiteIntervention()) < nbInterventionsRoues) {
			nbInterventionsRoues = (incendieAEteindre.getIntensite() - nbInterventionsRoues*roues.quantiteIntervention())/(roues.quantiteIntervention());	// Si on ajoute +1, on s'aasure que le feu est éteint.
		}
		for (int i = 0; i < nbInterventionsRoues; i++) {
			getSimulateur().ajouteEvenement(new EvenementDeverserEau(j, roues, listeDIncendies, incendieAEteindre));
			j += roues.tempsIntervention();
		}

		getSimulateur().ajouteEvenement(new EvenementDeplacement(j, map, roues, Direction.SUD));
		j++;
	}
}