package donnees;

public class Carte{
	private Case[][] grille;
	private int tailleCases;
	//private int altitude;

    public Carte(int nbLignes, int nbColonnes, int tailleCases){
        grille = new Case[nbLignes][nbColonnes];
		this.tailleCases = tailleCases;
        /*
   			for (int i = 0; i < this.grille.length; i++) {
				for (int j = 0; j < this.grille[i].length; j++) {
					this.grille[i][j] = new Case(i, j, TERRAIN_LIBRE);	// Default
				}
			}*/
	}

	public void Evenements(long date){

	}

    public void initCase(int lig, int col, NatureTerrain natureCase)
    {
        grille[lig][col] = new Case (lig, col, natureCase);
    }

	public int getNbLignes(){
		return grille.length;
	}

	public int getNbColonnes(){
		return grille[0].length;
	}

	public int getTailleCases(){
		return this.tailleCases;
	}

	public Case getCase(int lig, int col){
		return this.grille[lig][col];
	}

	public boolean voisinExiste(Case src, Direction dir){
		if(Direction.NORD.equals(dir)) {
			if ((src.getLigne() - 1 >= 0) && (src.getLigne() - 1) < getNbLignes()){
				return true;
			}
			else {
				return false;
			}
		}
		else if (Direction.SUD.equals(dir)) {
			if ((src.getLigne() + 1 < getNbLignes()) && (src.getLigne() + 1) >= 0 ){
				return true;
			}
			else {
				return false;
			}
		}
		else if (Direction.EST.equals(dir)) {
			if ((src.getColonne() + 1 < getNbColonnes()) && (src.getColonne() + 1 >= 0)){
				return true;
			}
			else {
				return false;
			}
		}
		else if (Direction.OUEST.equals(dir)) {
			if ((src.getColonne() - 1 < getNbColonnes()) && (src.getColonne() - 1 >= 0)){
				return true;
			}
			else {
				return false;
			}
		}
		else
			return false;
	}


	public Case getVoisin(Case src, Direction dir){
		if (this.voisinExiste(src, dir)) {
			if(Direction.NORD.equals(dir))
				return this.grille[src.getLigne()-1][src.getColonne()];
			else if(Direction.SUD.equals(dir))
				return this.grille[src.getLigne()+1][src.getColonne()];
			else if(Direction.EST.equals(dir))
				return this.grille[src.getLigne()][src.getColonne()+1];
			else if(Direction.OUEST.equals(dir))
				return this.grille[src.getLigne()][src.getColonne()-1];
			else
				return src;
		}
		else {
			System.out.println("La case (" + src.getLigne() + ";" + src.getColonne() + ") ne possède pas de voisin dans la direction " + dir + " !");
			return src;
		}
	}

	public boolean nextToWater(Case c) {
		if((this.voisinExiste(c, Direction.NORD) /*== true*/ && this.getVoisin(c, Direction.NORD).getNature() == NatureTerrain.EAU)
			|| (this.voisinExiste(c, Direction.SUD) /*== true*/ && this.getVoisin(c, Direction.SUD).getNature() == NatureTerrain.EAU)
			|| (this.voisinExiste(c, Direction.EST) /*== true*/ && this.getVoisin(c, Direction.EST).getNature() == NatureTerrain.EAU)
			|| (this.voisinExiste(c, Direction.OUEST) /*== true*/ && this.getVoisin(c, Direction.OUEST).getNature() == NatureTerrain.EAU)) {
				return true;
		}
		else {
			return false;
		}
	}
}
