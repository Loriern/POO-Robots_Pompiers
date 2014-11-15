package donnees;
public enum Direction {
	NORD,
	SUD,
	EST,
	OUEST;

	public String toString(){
		String s = new String("");

		switch(this) {
			case NORD:
					s += "au Nord";
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
		}

		return s;
	}
}
