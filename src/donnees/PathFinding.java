package donnees;

import java.util.*;

public class PathFinding {
	private Carte carte;
	private Robot robot;
	private Case objectif;

	private LinkedList<PathNode> listeOuverte;
	private PriorityQueue<PathNode> listeFermee;	// Contient la solution, gardez pour le moment ce nom svp, il est utilisé dans toutes les docs

	private LinkedList<Case> solution;
	private double tempsSolution = 0;

	public PathFinding(Carte map, Robot robotAPlanifier, Case destinationFinale){
		this.carte = map;
		this.robot = robotAPlanifier;
		this.objectif = destinationFinale;
		this.listeOuverte = new LinkedList<PathNode>();
		this.listeOuverte.add(new PathNode(this.robot.getPosition(), this.objectif));
		this.listeFermee = new PriorityQueue<PathNode>();
		this.solution = new LinkedList<Case>();
	}

	public LinkedList<Case> getSolution(){
		algo();
		morphIntoSolution();
		System.out.println("On retourne la solution...");
		return solution;
	}

	private void algo(){
		PathNode noeudCourant = this.listeOuverte.removeFirst();
		this.listeFermee.add(noeudCourant);

		while (/*!isInClosedList(objectif)*/noeudCourant.getCase() != objectif) {
			remplirListeOuverte(noeudCourant/*.getCase()*/);
			noeudCourant = updateListeSolution();
// 			for (long j = 0; j < 100000000; j++) {;}
			System.out.println("On s'intéresse à la case : (" + noeudCourant.getCase().getLigne() + ", " + noeudCourant.getCase().getColonne() + ")");
			if (listeOuverte == null) {
				System.out.println("Il n'existe pas de solution.");
				return;
			}
		}
	}

	// Remplissage Liste Ouverte
	// Voir Nord pour un détail des if
	private void remplirListeOuverte(/*Case*/PathNode centre){
		if (centre.getCase().getOrientation(objectif) != Direction.ID) {	// Si on n'est pas à l'objectif
			addInOpenList(centre, Direction.NORD);
			addInOpenList(centre, Direction.SUD);
			addInOpenList(centre, Direction.EST);
			addInOpenList(centre, Direction.OUEST);
		} else {
			System.out.println("On a déjà atteint l'objectif : (" + objectif.getLigne() + ", " + objectif.getColonne() + ")");
		}
	}

	private void addInOpenList(/*Case*/PathNode centre, Direction dir){
		PathNode noeudAAjouter;

		if (carte.voisinExiste(centre.getCase(), dir) && robot.getVitesse(carte.getVoisin(centre.getCase(), dir).getNature()) > 0){
			if (!this.listeFermee.contains(carte.getVoisin(centre.getCase(), dir))){
				noeudAAjouter = new PathNode(centre, carte.getVoisin(centre.getCase(), dir), this.objectif);
				int nodePosition = isInOpenList(noeudAAjouter);
				if (nodePosition >= 0) {
					if (noeudAAjouter.getPertinence() < listeOuverte.get(nodePosition).getPertinence()) {
						listeOuverte.set(nodePosition, noeudAAjouter);
					}
				} else {
					this.listeOuverte.add(noeudAAjouter);
				}
			}
		}
	}


	private int isInOpenList(PathNode n){
		int positionInList = -1;
		boolean isHere = false;

		for (PathNode p : listeOuverte) {
			positionInList++;

			if (p.memeNoeud(n)) {
				isHere = true;
				break;
			}
		}

		if (isHere)
			return positionInList;
		else
			return -1;
	}

	private PathNode nextDestination(){
		PathNode realiseBestPertinence = this.listeOuverte.getFirst();
		double bestPertinenceOfList = realiseBestPertinence.getPertinence();


		for (PathNode p : listeOuverte) {
			if (bestPertinenceOfList > p.getPertinence()) {
				realiseBestPertinence = p;
				bestPertinenceOfList = realiseBestPertinence.getPertinence();
			}
		}

		return realiseBestPertinence;
	}

	private PathNode updateListeSolution(){
		PathNode noeudRetenu = this.nextDestination();
		this.listeFermee.add(noeudRetenu);
		this.listeOuverte.remove(noeudRetenu);
		return noeudRetenu;
	}

	private void morphIntoSolution(){
		System.out.println("On est dans morphIntoSolution()...");
		PathNode last = listeFermee.peek();
		Case buffer;
		for (PathNode p : listeFermee) {
			last = p;
		}

		this.tempsSolution = last.getG();

		this.listeOuverte.clear();

		buffer = last.getCase();
		while (buffer != robot.getPosition()) {
			this.solution.addFirst(buffer);
			buffer = last.getParent().getCase();
			last = last.getParent();
		}
	}
}
