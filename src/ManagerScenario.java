import donnees.*;

public class ManagerScenario extends Manager {

	public ManagerScenario(Simulateur simulateurToConnect){
		super(simulateurToConnect);
	}

	public void manage(){
		Robot drone = super.getSimulateur().getDonneesSimulation().getRobots()[0];
// 		super.getSimulateur().ajouteEvenement(new EvenementMouvement(i, NORD));	// i dépendant de la vitesse
	}
}