package donnees;
public class Incendie {
	private Case position;
	private int intensite;	// ~Nb de litres d'eau nécessaires pour éteindre l'incendie

	public Incendie(Case pos, int intensity){
		this.position = pos;
		this.intensite = intensity;
	}

	public int getIntensite(){
		return this.intensite;
	}

	public Case getPosition(){
		return this.position;
	}

	public void extinction(int eauJeteeSurLeFeu){
		this.intensite -= eauJeteeSurLeFeu;
	}
}
