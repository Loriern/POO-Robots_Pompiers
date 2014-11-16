package donnees;

import java.util.*;

public class PathFinding {
	private Carte carte;
	private Robot robot;
	private Case objectif;

	private LinkedList<PathNode> listeOuverte;
	private PriorityQueue<PathNode> listeFermee;	// Contient la solution, gardez pour le moment ce nom svp, il est utilisé dans toutes les docs

	public PathFinding(Carte map, Robot robotAPlanifier, Case destinationFinale){
		this.carte = map;
		this.robot = robotAPlanifier;
		this.objectif = destinationFinale;
		this.listeOuverte = new LinkedList<PathNode>();
		this.listeOuverte.add(this.robot.getPosition());
		this.listeFermee = new PriorityQueue<PathNode>();
	}

	public Case suivant(Case source, Case destination){
		return carte.getVoisin(source, source.getOrientation(destination));
	}

	// Il faudra vérifier que Robot.getVitesse(NatureTerrain) ne renvoie pas 0

	// Une fois le Pathfinding fini, il faut générer les événements (getDate())
}
