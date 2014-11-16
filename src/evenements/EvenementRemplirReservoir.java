package evenements;
import donnees.*;
import donnees.robot.*;

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
            if ( robot.getClass() == Drone.class ){
                remplirDrone();
            } else if ( robot.getClass() == RobotAChenilles.class ){
                remplirChenilles();
            } else if ( robot.getClass() == RobotARoues.class ){
                remplirRoues();
            } else if ( robot.getClass() == RobotAPattes.class ){
                remplirPattes();
            }
        }

		else {
			System.out.println(robot.getClass() + "est plein !");
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
