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
		this.listeOuverte.add(new PathNode(this.robot.getPosition(), this.objectif));
		this.listeFermee = new PriorityQueue<PathNode>();
	}

// 	public Case suivant(Case source, Case destination){
// 		return carte.getVoisin(source, source.getOrientation(destination));
// 	}

	public PriorityQueue<PathNode> getSolution(){
		algo();
		return listeFermee;
	}

	private void algo(){
// 		boolean algorithme_fini = false;
		PathNode noeudCourant = this.listeOuverte.getFirst();
		this.listeFermee.add(noeudCourant);	// Le premier noeud fait partie de la solution...
		this.listeOuverte.remove(noeudCourant);

		while (isInClosedList(objectif)) {
			remplirListeOuverte(noeudCourant.getCase());
			noeudCourant = updateListeSolution();
			if (listeOuverte == null) {
				System.out.println("Il n'existe pas de solution");
				return;
			}
		}
	}

	// Remplissage Liste Ouverte
	// Voir Nord pour un détail des if
	private void remplirListeOuverte(Case centre){
		PathNode noeudAAjouter;
		if (centre.getOrientation(objectif) != Direction.ID) {	// Si on n'est pas à l'objectif
			// 	On commence en vérifiant la direction "principale"
			if (carte.voisinExiste(centre, centre.getOrientation(objectif)) && robot.getVitesse(carte.getVoisin(centre, centre.getOrientation(objectif)).getNature()) > 0) {
				if (!this.listeFermee.contains(carte.getVoisin(centre, centre.getOrientation(objectif)))){
					noeudAAjouter = new PathNode(centre, carte.getVoisin(centre, centre.getOrientation(objectif)), this.objectif);
					if (isInOpenList(noeudAAjouter) >= 0) {
						if (noeudAAjouter.getPertinence() < listeOuverte.get(isInOpenList(noeudAAjouter)).getPertinence() ) {
							listeOuverte.set(isInOpenList(noeudAAjouter), noeudAAjouter);
						}
					} else {
						this.listeOuverte.addFirst(noeudAAjouter);
					}
				}
			}

			// On vérifie s'il y a un obstacle
/* Nord */	if (carte.voisinExiste(centre, Direction.NORD) && robot.getVitesse(carte.getVoisin(centre, Direction.NORD).getNature()) > 0){
				// On vérifie que le noeud n'est pas déjà retenu
				if (!this.listeFermee.contains(carte.getVoisin(centre, Direction.NORD))){
					noeudAAjouter = new PathNode(centre, carte.getVoisin(centre, Direction.NORD), this.objectif);
					// On vérifie s'il est dans la liste de ceux à étudier (liste ouverte)
					if (isInOpenList(noeudAAjouter) >= 0) {
						// On vérifie s'il vaut mieux le considérer avec ce nouveau parent (centre)
						if (noeudAAjouter.getPertinence() < listeOuverte.get(isInOpenList(noeudAAjouter)).getPertinence() ) {
							listeOuverte.set(isInOpenList(noeudAAjouter), noeudAAjouter);
						}
					} else {
						this.listeOuverte.addLast(noeudAAjouter);
					}
				}
			}

			if (carte.voisinExiste(centre, Direction.SUD) && robot.getVitesse(carte.getVoisin(centre, Direction.SUD).getNature()) > 0){
				if (!this.listeFermee.contains(carte.getVoisin(centre, Direction.SUD))){
					noeudAAjouter = new PathNode(centre, carte.getVoisin(centre, Direction.SUD), this.objectif);
					if (isInOpenList(noeudAAjouter) >= 0) {
						if (noeudAAjouter.getPertinence() < listeOuverte.get(isInOpenList(noeudAAjouter)).getPertinence() ) {
							listeOuverte.set(isInOpenList(noeudAAjouter), noeudAAjouter);
						}
					} else {
						this.listeOuverte.addLast(noeudAAjouter);
					}
				}
			}

			if (carte.voisinExiste(centre, Direction.EST) && robot.getVitesse(carte.getVoisin(centre, Direction.EST).getNature()) > 0){
				if (!this.listeFermee.contains(carte.getVoisin(centre, Direction.EST))){
					noeudAAjouter = new PathNode(centre, carte.getVoisin(centre, Direction.EST), this.objectif);
					if (isInOpenList(noeudAAjouter) >= 0) {
						if (noeudAAjouter.getPertinence() < listeOuverte.get(isInOpenList(noeudAAjouter)).getPertinence() ) {
							listeOuverte.set(isInOpenList(noeudAAjouter), noeudAAjouter);
						}
					} else {
						this.listeOuverte.addLast(noeudAAjouter);
					}
				}
			}

			if (carte.voisinExiste(centre, Direction.OUEST) && robot.getVitesse(carte.getVoisin(centre, Direction.OUEST).getNature()) > 0){
				if (!this.listeFermee.contains(carte.getVoisin(centre, Direction.OUEST))){
					noeudAAjouter = new PathNode(centre, carte.getVoisin(centre, Direction.OUEST), this.objectif);
					if (isInOpenList(noeudAAjouter) >= 0) {
						if (noeudAAjouter.getPertinence() < listeOuverte.get(isInOpenList(noeudAAjouter)).getPertinence() ) {
							listeOuverte.set(isInOpenList(noeudAAjouter), noeudAAjouter);
						}
					} else {
						this.listeOuverte.addLast(noeudAAjouter);
					}
				}
			}


		} else {
			System.out.println("On a déjà atteint l'objectif : (" + objectif.getLigne() + ", " + objectif.getColonne() + ")");
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

	private boolean isInClosedList(Case c){
		boolean isHere = false;

		for (PathNode p : listeFermee) {
			if (p.memeNoeud(c)) {
				isHere = true;
				break;
			}
		}

		return isHere;
	}

	private PathNode nextDestination(){
		int tailleListe = this.listeOuverte.size();

		PathNode realiseBestPertinence = this.listeOuverte.getFirst();
		double bestPertinenceOfList = realiseBestPertinence.getPertinence();


		for (int i = 1; i < tailleListe; i++) {
			if (bestPertinenceOfList > this.listeOuverte.get(i).getPertinence()) {
				realiseBestPertinence = this.listeOuverte.get(i);
				bestPertinenceofList = realiseBestPertinence.getPertinence();
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




	// Une fois le Pathfinding fini, il faut générer les événements (getDate())
}
