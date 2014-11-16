package donnees;

public class PathFinding {
	private Carte carte;

	public PathFinding(Carte map){
		this.carte = map;
	}

	public Case suivant(Case source, Case destination){
		switch(source.getOrientation(destination)) {
			case NORD:
					return source;
					break;
			case SUD:
					s += "au Sud";
					break;
			case EST:
					s += "à l'Est";
					break;
			case OUEST:
					s += "à l'Ouest";
					break;
			case NORDEST:
					s += "au Nord-Est";
					break;
			case SUDEST:
					s += "au Sud-Est";
					break;
			case NORDOUEST:
					s += "au Nord-Ouest";
					break;
			case SUDOUEST:
					s += "au Sud-Ouest";
					break;
			case ID:
					s += "sur la même case";
					break;
		}
	}
}
