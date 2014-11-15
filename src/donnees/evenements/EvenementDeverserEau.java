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
		if (incendie.getIntensite() > 0) {
			switch (robot.getType()) {
				case DRONE:
						deverserDrone(5000);
						break;
				case CHENILLES:
						deverserChenilles(5000);
				case PATTES:
						deverserPattes(5000);
						break;
				case ROUES:
						deverserRoues(5000);
						break;
			}
			incendie.extinction(5000);
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