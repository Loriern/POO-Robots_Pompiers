package donnees;
public enum NatureTerrain {
	EAU,
	FORET,
	HABITAT,
	ROCHE,
	TERRAIN_LIBRE;

	public String toString(){
		String s = new String("");

		switch(this) {
			case EAU:
					s += "l'eau";
					break;
			case FORET:
					s += "les forêts";
					break;
			case HABITAT:
					s += "l'habitat";
					break;
			case ROCHE:
					s += "les rochers";
					break;
			case TERRAIN_LIBRE:
					s += "les terrains libres";
					break;
		}

		return s;
	}
}
