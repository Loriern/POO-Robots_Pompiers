class Carte {
	// Tel qu'implémenté : n sont les lignes, m les colonnes
	private Case grille[n][m];
	private int tailleCases;
	//private int altitude;

	public Evenements(long date){

	}

	public int getNbLignes(){	// A mieux implémenter
		return n;
	}

	public int getNbColonnes(){	// A mieux implémenter
		return m;
	}

	public int TailleCases(){
		return this.tailleCases;
	}

	public Case getCase(int lig, int col){
		return this.grille[lig][col];
	}

	public boolean voisinExiste(Case src, Direction dir){
		switch (dir) {
			case NORD :
					if (src.getLigne()-1 >= 0){
						return true;
					}
					else {
						return false;
					}
					break;
			case SUD :
					if (src.getLigne()+1 < n){	// A mieux implémenter
						return true;
					}
					else {
						return false;
					}
					break;
			case EST :
					if (src.getColonne()+1 >= m){	// A mieux implémenter
						return true;
					}
					else {
						return false;
					}
					break;
			case OUEST :
					if (src.getColonne()-1 >= 0){
						return true;
					}
					else {
						return false;
					}
					break;
		}
	}

	public Case getVoisin(Case src, Direction dir){
		if (this.voisinExiste()) {
		switch (dir) {
			case NORD :
					return this.grille[src.getLigne()-1][src.getColonne()];
					break;
			case SUD :
					return this.grille[src.getLigne()+1][src.getColonne()];
					break;
			case EST :
					return this.grille[src.getLigne()][src.getColonne()+1];
					break;
			case OUEST :
					return this.grille[src.getLigne()][src.getColonne()-1];
					break;
		}
		}
		else {
			System.out.println("La case (" + src.getLigne() + "," + src.getColonne()
								+ ") ne possède pas de voisin dans la direction " + dir + " !");
			return src;
	}
}
