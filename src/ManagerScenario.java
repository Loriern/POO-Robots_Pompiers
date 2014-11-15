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
		Incendie incendieAEteindre = super.getSimulateur().getDonneesSimulation().getIncendie(4);

		int nbInterventionsRoues = (roues.getCapaciteMax())/(roues.quantiteIntervention());

// 		super.getSimulateur().ajouteEvenement(new EvenementDeplacement(i, map, drone, NORD));	// i dépendant de la vitesse
		super.getSimulateur().ajouteEvenement(new EvenementDeplacement(1, map, drone, Direction.NORD));
		super.getSimulateur().ajouteEvenement(new EvenementDeplacement(2, map, drone, Direction.NORD));
		super.getSimulateur().ajouteEvenement(new EvenementDeplacement(3, map, drone, Direction.NORD));
		super.getSimulateur().ajouteEvenement(new EvenementDeplacement(4, map, drone, Direction.NORD));
/////////////////////////////////// - Fin Drône
		int j = 1;
		super.getSimulateur().ajouteEvenement(new EvenementDeplacement(j, map, roues, Direction.NORD));

		j++;
		// A automatiser
		if ((incendieAEteindre.getIntensite())/(roues.quantiteIntervention()) < nbInterventionsRoues) {
			nbInterventionsRoues = (incendieAEteindre.getIntensite())/(roues.quantiteIntervention());
		}
		for (int i = 0; i < nbInterventionsRoues; i++) {
			super.getSimulateur().ajouteEvenement(new EvenementDeverserEau(j, roues, listeDIncendies, incendieAEteindre));
			j++;
		}
		super.getSimulateur().ajouteEvenement(new EvenementDeplacement(j, map, roues, Direction.OUEST));
		j++;
		super.getSimulateur().ajouteEvenement(new EvenementDeplacement(j, map, roues, Direction.OUEST));
		j++;

		for (int i = 0; i < roues.tempsPourRemplir(); i++) {
			super.getSimulateur().ajouteEvenement(new EvenementRemplirReservoir(j, map, roues));
			j++;
		}

		super.getSimulateur().ajouteEvenement(new EvenementDeplacement(j, map, roues, Direction.EST));
		j++;
		super.getSimulateur().ajouteEvenement(new EvenementDeplacement(j, map, roues, Direction.EST));
		j++;


		if ((incendieAEteindre.getIntensite())/(roues.quantiteIntervention()) < nbInterventionsRoues) {
			nbInterventionsRoues = (incendieAEteindre.getIntensite())/(roues.quantiteIntervention());
		}
		for (int i = 0; i < nbInterventionsRoues; i++) {
			super.getSimulateur().ajouteEvenement(new EvenementDeverserEau(j, roues, listeDIncendies, incendieAEteindre));
			j++;
		}
	}
}