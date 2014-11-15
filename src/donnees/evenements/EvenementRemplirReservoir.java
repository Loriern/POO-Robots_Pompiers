package donnees.evenements;
import donnees.*;

public class EvenementRemplirReservoir extends Evenement {
	private Carte carte;
	private Robot robot;

	public EvenementRemplirReservoir(long date, Carte map, Robot robotARemplir){
		super(date);
		this.carte = map;
		this.robot = robotARemplir;
	}

	public void execute(){
		if (robot.getCapacite() < robot.getCapaciteMax()) {
			switch (robot.getType()) {
				case DRONE:
						remplirDrone();
						break;
				case CHENILLES:
						remplirChenilles();
				case PATTES:
						remplirPattes();
						break;
				case ROUES:
						remplirRoues();
						break;
			}
		}
		else {
			System.out.println("Il n'y a plus besoin de remplir le " + robot.getType().toString() + "!");
			return;
		}
	}

	private void remplirDrone(){
		this.robot.remplirReservoir(carte);
	}

	private void remplirChenilles(){
		this.robot.remplirReservoir(carte);
	}

	private void remplirPattes(){
		this.robot.remplirReservoir(carte);
	}

	private void remplirRoues(){
		this.robot.remplirReservoir(carte);
	}
}