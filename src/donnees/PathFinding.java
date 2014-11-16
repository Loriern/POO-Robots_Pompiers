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
		this.listeOuverte.add(new PathNode(this.robot.getPosition()));
		this.listeFermee = new PriorityQueue<PathNode>();
	}

// 	public Case suivant(Case source, Case destination){
// 		return carte.getVoisin(source, source.getOrientation(destination));
// 	}

	// Remplissage Liste Ouverte
	private void remplirListeOuverte(Case centre){
		if (centre.getOrientation() != Direction.ID) {	// Si on n'est pas à l'objectif
			if (carte.voisinExiste(centre, Direction.NORD) && robot.getVitesse(carte.getVoisin(centre, Direction.NORD)) > 0)
				this.listeOuverte.add(new PathNode(centre, carte.getVoisin(centre, Direction.NORD)));
			if (carte.voisinExiste(centre, Direction.SUD) && robot.getVitesse(carte.getVoisin(centre, Direction.SUD)) > 0)
				this.listeOuverte.add(new PathNode(centre, carte.getVoisin(centre, Direction.SUD)));
			if (carte.voisinExiste(centre, Direction.EST) && robot.getVitesse(carte.getVoisin(centre, Direction.EST)) > 0)
				this.listeOuverte.add(new PathNode(centre, carte.getVoisin(centre, Direction.EST)));
			if (carte.voisinExiste(centre, Direction.OUEST) && robot.getVitesse(carte.getVoisin(centre, Direction.OUEST)) > 0)
				this.listeOuverte.add(new PathNode(centre, carte.getVoisin(centre, Direction.OUEST)));
		} else {
			System.out.println("On a déjà atteint l'objectif : (" + objectif.getLigne() + ", " + objectif.getColonne() + ")");
		}
	}

	private void etudieEntourage(){

	}




	// Une fois le Pathfinding fini, il faut générer les événements (getDate())
}
