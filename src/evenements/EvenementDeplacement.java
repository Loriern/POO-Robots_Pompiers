package evenements;
import donnees.*;
import donnees.robot.*;

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
		Case voisin =  carte.getVoisin(robot.getPosition(), direction);
		if (voisin != robot.getPosition()) {
            if ( robot.getClass() == Drone.class ){
                deplaceDrone(voisin);
            } else if ( robot.getClass() == RobotAChenilles.class ){
                deplaceChenilles(voisin);
            } else if ( robot.getClass() == RobotAPattes.class ){
                deplacePattes(voisin);
            } else if ( robot.getClass() == RobotARoues.class ){
                deplaceRoues(voisin);
            }
		}
		else {
			System.out.println("Error : " + robot.getType() + " ne peut continuer à aller " + direction.toString() + "!");
			return;
		}
	}

	private void deplaceDrone(Case voisin){
	// On check que le déplacement puisse se faire
// 		Case voisin =  carte.getVoisin(robot.getPosition(), direction);
// 		if (voisin != robot.getPosition()) {
			this.robot.setPosition(voisin);
// 		}
// 		else {
// 			System.out.println("Le drone ne peut continuer à aller " + direction.toString() + "!");
// 			return;
// 		}
	}

	private void deplaceChenilles(Case voisin){
// 		Case voisin =  carte.getVoisin(robot.getPosition(), direction);
// 		if (voisin != robot.getPosition()) {
			if (voisin.getNature() != NatureTerrain.EAU && voisin.getNature() != NatureTerrain.ROCHE) {
				this.robot.setPosition(voisin);
			}
			else {
				System.out.println("Error : " + robot.getClass() + " ne peut aller dans " + voisin.getNature() + "!");
				return;
			}
// 		}
// 		else {
// 			System.out.println("Le robot à chenilles ne peut continuer à aller " + direction.toString() + "!");
			return;
// 		}
	}

	private void deplacePattes(Case voisin){
// 		Case voisin =  carte.getVoisin(robot.getPosition(), direction);
// 		if (voisin != robot.getPosition()) {
			if (voisin.getNature() != NatureTerrain.EAU) {
				this.robot.setPosition(voisin);
			}
			else {
				System.out.println("Error : " + robot.getClass() + " ne peut aller dans " + voisin.getNature() + "!");
				return;
			}
// 		}
// 		else {
// 			System.out.println("Le robot à chenilles ne peut continuer à aller " + direction.toString() + "!");
// 			return;
// 		}
	}

	private void deplaceRoues(Case voisin){
// 		Case voisin =  carte.getVoisin(robot.getPosition(), direction);
// 		if (voisin != robot.getPosition()) {
			if (voisin.getNature() == NatureTerrain.TERRAIN_LIBRE || voisin.getNature() == NatureTerrain.HABITAT) {
				this.robot.setPosition(voisin);
			}
			else {
				System.out.println("Error : " + robot.getClass() + " ne peut aller dans " + voisin.getNature() + "!");
				return;
			}
// 		}
// 		else {
// 			System.out.println("Le robot à roues ne peut continuer à aller " + direction.toString() + "!");
// 			return;
// 		}
	}
}
