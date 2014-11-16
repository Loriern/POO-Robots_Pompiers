package donnees;

// Penser à modifier pour prendre les vitesses des robots à la place du nombre de lignes/colonnes
public class PathNode {
	private Case parent;
	private Case node;
	private double pertinence;  // pertinence = g + h

	private double g;  // g : distance depuis la source
    private double h;  // distance entre ce noeud et l'objectif

    public PathNode(Case ceNode){
		this.node = ceNode;
		this.g = 0;
    }

    public PathNode(Case ceNode, Case nodeParent){
		this.node = ceNode;
		this.parent = nodeParent;
		this.calcG();
    }




    private void calcG(){
		this.g = manhattanDistance(this.parent, this.node);
    }

	// Manhattan Distance
    private void calcH(Case objectif){
		this.h = this.manhattanDistance(this.node, objectif);
    }

    private void calcPertinence(){
		this.pertinence = g + h;
    }

    private double manhattanDistance(Case depart, Case arrivee){
		double x = abs(arrivee.getLigne() - depart.getLigne());
		double y = abs(arrivee.getColonne() - depart.getColonne());
		return x+y;
    }
}