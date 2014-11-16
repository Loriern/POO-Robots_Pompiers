package donnees;

public class Case {
	private int ligne;
	private int colonne;
	private NatureTerrain nature;

	public Case(int numLigne, int numColonne, NatureTerrain natureCase){
		this.ligne = numLigne;
		this.colonne = numColonne;
		this.nature = natureCase;
	}

	public int getLigne(){
		return this.ligne;
	}

	public int getColonne(){
		return this.colonne;
	}

	public NatureTerrain getNature(){
		return this.nature;
	}

	public Direction getOrientation(Case c) {	// Les lignes s'incrémentent vers le bas, les colonnes vers la gauche
		if ((this.colonne == c.colonne) && (this.ligne > c.ligne))
			return Direction.NORD;
		else if ((this.colonne == c.colonne) && (this.ligne < c.ligne))
			return Direction.SUD;
		else if ((this.colonne < c.colonne) && (this.ligne == c.ligne))
			return Direction.EST;
		else if ((this.colonne > c.colonne) && (this.ligne == c.ligne))
			return Direction.OUEST;
		else if ((this.colonne < c.colonne) && (this.ligne > c.ligne))
			return Direction.NORDEST;
		else if ((this.colonne < c.colonne) && (this.ligne < c.ligne))
			return Direction.SUDEST;
		else if ((this.colonne > c.colonne) && (this.ligne > c.ligne))
			return Direction.NORDOUEST;
		else if ((this.colonne > c.colonne) && (this.ligne < c.ligne))
			return Direction.SUDOUEST;
		else
			return Direction.ID;
	}
}
