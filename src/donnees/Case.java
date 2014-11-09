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
}
