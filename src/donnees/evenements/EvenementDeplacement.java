package donnees.evenements;
import donnees.*;

public class EvenementDeplacement extends Evenement {
	private Carte carte;
	private Robot robot;
	private Direction direction;

	public EvenementDeplacement(long date, Carte map, Robot robotADeplacer, Direction dir){
		super(date);
		this.carte = map;
		this.robot = robotADeplacer;
		this.direction = dir;
	}

	public void execute(){
	// On check que le déplaceent puisse se faire
		Case voisin =  carte.getVoisin(robot.getPosition(), direction);
		if (voisin != robot.getPosition()) {
			this.robot.deplacer(voisin);
		}
		else {
			System.out.println("Le robot ne peut continuer à aller " + direction.toString() + "!");
			return;
		}
	}
}