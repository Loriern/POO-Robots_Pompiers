package donnees;

// Penser à modifier pour prendre les vitesses des robots à la place du nombre de lignes/colonnes
public class PathNode implements Comparable<PathNode> {
	private PathNode parent;
	private Case node;

	private double pertinence;  // pertinence = g + h

	private double g;  // g : distance depuis la source
    private double h;  // distance entre ce noeud et l'objectif

    public PathNode(Case ceNode, Case destination){	// Première case, sans parent
		this.node = ceNode;
		this.g = 0;
		this.calcH(destination);
		this.calcPertinence();
    }

    public PathNode(/*Case*/PathNode nodeParent, Case ceNode, Case destination){
		this.node = ceNode;
		this.parent = nodeParent;
		this.calcG();
		this.calcH(destination);
		this.calcPertinence();
    }

    public double getPertinence(){
		return this.pertinence;
    }

    public double getG(){
		return this.g;
    }

    public Case getCase(){
		return this.node;
    }

    public PathNode getParent(){
		return this.parent;
    }


	public int compareTo(PathNode p){
 		if (this.g > p.getG()) {
 			return 1;
 		} else if (this.g == p.getG()) {
 			return 0;
 		} else {
 			return -1;
 		}
	}

	public boolean memeNoeud(PathNode p){
		return this.node.equalsTile(p.getCase());
	}

	public boolean memeNoeud(Case c){
		return this.node.equalsTile(c);
	}




    private void calcG(){
		this.g = this.parent.getG() + 1;
    }

    private void calcH(Case objectif){
		this.h = this.manhattanDistance(this.node, objectif);
    }

    private void calcPertinence(){
		this.pertinence = g + h;
    }

    private double manhattanDistance(Case depart, Case arrivee){
		double x = Math.abs(arrivee.getLigne() - depart.getLigne());
		double y = Math.abs(arrivee.getColonne() - depart.getColonne());
		return x+y;
    }
}