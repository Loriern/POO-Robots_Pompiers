package donnees;
public enum RobotType {
	DRONE,
	ROUES,
	CHENILLES,
	PATTES;

	public String toString(){
		String s = new String("");

		switch(this) {
			case DRONE:
					s += "drone";
					break;
			case ROUES:
					s += "robot à roues";
					break;
			case CHENILLES:
					s += "robot à chenilles";
					break;
			case PATTES:
					s += "robot à pattes";
					break;
		}

		return s;
	}
}