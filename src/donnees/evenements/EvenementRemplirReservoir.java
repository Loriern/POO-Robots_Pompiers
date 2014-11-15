package donnees.evenements;
import donnees.*;

public class EvenementRemplirReservoir extends Evenement {
	private Robot robot;

	public EvenementRemplirReservoir(long date, Robot robotARemplir){
		super(date);
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
			System.out.println("Il n'y a plus besoin de vider le " + robot.getType().toString() + "!");
			return;
		}
	}

	private void remplirDrone(){
		this.robot.remplirReservoir();
	}

	private void remplirChenilles(){
		this.robot.remplirReservoir();
	}

	private void remplirPattes(){
		this.robot.remplirReservoir();
	}

	private void remplirRoues(){
		this.robot.remplirReservoir();
	}
}