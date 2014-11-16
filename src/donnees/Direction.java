package donnees;
public enum Direction {
	NORD,
	SUD,
	EST,
	OUEST,
// 	NORDEST,
// 	SUDEST,
// 	NORDOUEST,
// 	SUDOUEST,
	ID;

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
// 			case NORDEST:
// 					s += "au Nord-Est";
// 					break;
// 			case SUDEST:
// 					s += "au Sud-Est";
// 					break;
// 			case NORDOUEST:
// 					s += "au Nord-Ouest";
// 					break;
// 			case SUDOUEST:
// 					s += "au Sud-Ouest";
// 					break;
			case ID:
					s += "sur la même case";
					break;
		}

		return s;
	}
}
