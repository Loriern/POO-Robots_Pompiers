package donnees;
/*Plusieurs robots peuvent se trouver
simultanément sur une même case, et les robots peuvent traverser des cases en feu*/
abstract public class Robot{
	protected Case position;
	protected int capacite;/*Capacité du robot en litres*/
	protected int vitesse;
	//protected Deplacement deplacement;

	public Case getPosition(){
		return this.position;
	}

	public void setPosition(Case xy){
		this.position = xy;
	}

	public int getCapacite(){
		return this.capacite;
	}

	abstract public int getCapaciteMax();

	public Robot(Case depart){
		this.position = depart;
	}

	// public void setVitesse(int vitesse)

//			Reminder : pour plus haut
// Le temps nécessaire
// pour se rendre d’une case à l’autre sera la moyenne
// de la vitesse sur chacune des cases multipliée
// par la taille des cases.
	abstract public double getVitesse(NatureTerrain natureDuTerrain);
/*
	protected abstract void deplacer(Case arrivee){
	    while ((position.ligne != arrivee.ligner) && (position.colonne != arrivee.colonne)){
	        deplacement(arrivee);
	    }
	}

    protected void deplacement(Case arrivee);
    */

//     abstract public void deplacer(Case c);

	abstract public RobotType getType();


	public void deverserEau(int vol){
		if (capacite >= vol)
		    this.capacite -= vol;
		else {
		    capacite = 0;
		    System.out.println("Le " + this.getType() + " ne peut se vider de la quatité demandée!");
		    System.out.println("(Quantité d'eau: " + this.capacite + ")");
// 		    remplirReservoir();
		}
	}

	// Quand mettre le while ?
	//	-> Pour l'instant, implémenté à la minute
	abstract public void remplirReservoir();
	// Rajouter vérification que l'on peut remplir (terrain)
}
