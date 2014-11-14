abstract public class Manager {
	private Simulateur simulateur;

	public Manager(Simulateur simulateurToConnect){
		this.simulateur = simulateurToConnect;
	}

	public Simulateur getSimulateur(){
		return this.simulateur;
	}

	abstract public void manage();
}