package donnees;

public class PathFinding {
	private Carte carte;

	public PathFinding(Carte map){
		this.carte = map;
	}

	public Case suivant(Case source, Case destination){
		switch(source.getOrientation(destination)) {

		}
	}
}
