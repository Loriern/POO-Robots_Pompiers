package donnees.evenements;
import java.io.*;
import java.util.*;
import donnees.*;

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
			switch (robot.getType()) {
				case DRONE:
						deverserDrone(eau);
						break;
				case CHENILLES:
						deverserChenilles(eau);
				case PATTES:
						deverserPattes(eau);
						break;
				case ROUES:
						deverserRoues(eau);
						break;
			}
			incendie.extinction(eau);
		}
		else {
			System.out.println("Il n'y a plus besoin de vider le " + robot.getType().toString() + "!");
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