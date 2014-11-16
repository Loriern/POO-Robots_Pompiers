package evenements;
import java.io.*;
import java.util.*;
import donnees.*;
import donnees.robot.*;

public class EvenementDeverserEau extends Evenement {
	private Robot robot;
	private LinkedList<Incendie> listeIncendies;
	private Incendie incendie;

	public EvenementDeverserEau(long date, Robot robotAVider, LinkedList<Incendie> listeDIncendies, Incendie incendieAEteindre){
		super(date);
		this.robot = robotAVider;
		this.listeIncendies = listeDIncendies;
		this.incendie = incendieAEteindre;
	}

	public void execute(){
		int eau = this.robot.quantiteIntervention();
		if (incendie.getIntensite() > 0) {
            if ( robot.getClass() == Drone.class ){
                deverserDrone(eau);
            } else if ( robot.getClass() == RobotAChenilles.class ){
                deverserChenilles(eau);
            } else if ( robot.getClass() == RobotARoues.class ){
                deverserRoues(eau);
            } else if ( robot.getClass() == RobotAPattes.class ){
                deverserPattes(eau);
            }
			incendie.extinction(eau);
		}
		else {
			System.out.println(robot.getClass() + " est vide !");
		}
		if (incendie.getIntensite() <= 0) {
			listeIncendies.remove(incendie);
		}
	}

	private void deverserDrone(int vol){
		this.robot.deverserEau(vol);
	}

	private void deverserChenilles(int vol){
		this.robot.deverserEau(vol);
	}

	private void deverserPattes(int vol){
		this.robot.deverserEau(vol);
	}

	private void deverserRoues(int vol){
		this.robot.deverserEau(vol);
	}
}
