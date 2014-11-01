/*Plusieurs robots peuvent se trouver
simultanément sur une même case, et les robots peuvent traverser des cases en feu*/

abstract class Robot {
	protected Case position;
	protected int capacite;
	protected int vitesse;
	protected Deplacement deplacement;

	public Case getPosition(){
		return this.position;
	}

	public void setPosition(Case xy){
		this.position = xy;
	}
	
	public int getCapacite(){
		return this.capacite;
	}


//			Reminder : pour plus haut
// Le temps nécessaire
// pour se rendre d’une case à l’autre sera la moyenne
// de la vitesse sur chacune des cases multipliée
// par la taille des cases.
	public abstract double getVitesse(NatureTerrain natureDuTerrain);
/*
	protected abstract void deplacer(Case arrivee){
	    while ((position.ligne != arrivee.ligner) && (position.colonne != arrivee.colonne)){
	        deplacement(arrivee);
	    }
	}
    
    protected void deplacement(Case arrivee);
    */
    
	public void deverserEau(int vol){
		if (capacite >= vol)
		    this.capacite -= vol;
		else {
		    capacite = 0;
		    remplirReservoir();
		}
	}

	// Quand mettre le while ?
	//	-> Pour l'instant, implémenté à la minute
	public abstract void remplirReservoir()
	// Rajouter vérification que l'on peut remplir (terrain)
}


//	Sous-classes		//

public class Drones{
// Propriétés de déplacement sur une case
//
// Vitesse par défault de 100 km/h, mais peut être lue dans le fichier de données
// (sans dépasser 150 km/h)
// Peut se déplacer sur toutes les cases, quelle que soit leur nature, à vitesse
// constante.

// Réservoir et remplissage
//
// Réservoir de 10000 litres.
// Remplissage complet en 30 minutes.
// Se remplit sur une case contenant de l’eau.

// Extinction
//
// Intervention unitaire : vide la totalité du réservoir en 30 secondes.


	public double getVitesse(NatureTerrain natureDuTerrain){
		return 100;
	}


	public void remplirReservoir(){
		if (super.position.getNature == EAU) {
			super.capacite += 333;
			if (super.capacite > 10000){
				super.capacite = 10000;
			}
		}
		else {
			System.out.println("Le drône ne stationne pas sur l'eau !");
		}
	}
}


public class Roues{
// Propriétés de déplacement sur une case
//
// Vitesse par défaut de 80 km/h, mais qui peut être lue dans le fichier.
// Ne peut se déplacer que sur du terrain libre ou habitat.


// Réservoir et remplissage
//
// Réservoir de 5000 litres.
// Remplissage complet en 10 minutes.
// Se remplit à côté d’une case contenant de l’eau.


// Extinction
//
// Intervention unitaire : 100 litres en 5 sec.


	public double getVitesse(NatureTerrain natureDuTerrain){
		if (natureDuTerrain == (HABITAT || TERRAIN_LIBRE)) {
			return 80;
		}
		else {
			return 0;
		}
	}


	public void remplirReservoir(){
		super.capacite += 500;
		if (super.capacite > 5000){
			super.capacite = 5000;
		}
	}
}

public class Chenilles{
// Propriétés de déplacement sur une case
//
// Vitesse par défaut de 60 km/h, mais qui peut être lue dans le fichier (sans
// dépasser 80 km/h)
// La vitesse est diminuée de 50% en forêt.
// Ne peut pas se rendre sur de l’eau ou du rocher.


// Réservoir et remplissage
//
// Réservoir de 2000 litres.
// Remplissage complet en 5 minutes.
// Se remplit à côté d’une case contenant de l’eau.


// Extinction
//
// Intervention unitaire : 100 litres en 8 sec.


	public double getVitesse(NatureTerrain natureDuTerrain){
		if (natureDuTerrain == (EAU || ROCHER)) {
			return 0;
		}
		else if (natureDuTerrain == FORET) {
			return 30; 	// 0.5*60
		}
		else {
			return 60;
		}
	}


	public void remplirReservoir(){
		super.capacite += 400;
		if (super.capacite > 2000){
			super.capacite = 2000;
		}
	}
}

public class Pattes{
// Propriétés de déplacement sur une case
//
// Vitesse de base de 30 km/h, réduite à 10 km/h sur du rocher.
// Ne peut pas se rendre sur de l’eau.


// Réservoir et remplissage
//
// Utilise de la poudre.
// Réservoir considéré infini à l’échelle de la simulation. Ne se remplit jamais.


// Extinction
//
// Intervention unitaire : 10 litres en 1 sec.


	public double getVitesse(NatureTerrain natureDuTerrain){
		if (natureDuTerrain == EAU) {
			return 0;
		}
		else if (natureDuTerrain == ROCHER) {
			return 10;
		}
		else {
			return 30;
		}
	}

	public void deverserEau(int vol){
		return;	// :)
	}

	public void remplirReservoir(){
		return;	// :)
	}
}
