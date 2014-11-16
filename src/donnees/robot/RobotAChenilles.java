package donnees.robot;
import donnees.*;
public class RobotAChenilles extends Robot{
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
	int trois = 0;	// S'assure d'obtenir des valeurs correctes malgré la division par 3

    public RobotAChenilles(Case depart){
		super(depart);
		this.capacite = 2000;
		this.vitesse = 60;
	}

	public RobotAChenilles(Case pos, int vitesse) {
		this(pos);
		if(vitesse > 0) {
			if (vitesse >= 80) {
				this.vitesse = 80;
			}
			else {
				this.vitesse = vitesse;
			}
		}
	}



	public double getVitesse(NatureTerrain natureDuTerrain){
		if (natureDuTerrain.equals(NatureTerrain.EAU) || natureDuTerrain.equals(NatureTerrain.ROCHE)) {
			return 0;
		}
		else if (natureDuTerrain.equals(NatureTerrain.FORET)) {
			return (this.vitesse) / 2;
		}
		else {
			return this.vitesse;
		}
	}


	public int getCapaciteMax(){
		return 2000;
	}

	public void remplirReservoir(Carte carte){
		if (carte.nextToWater(this.position)) {
			if (trois == 2) {
				super.capacite += 6;
				trois = 0;
			}
			else {
				super.capacite += 7;
				trois++;
			}

			if (super.capacite > 2000){
				super.capacite = 2000;
			}
		}
	}


	public int tempsPourRemplir(){
		return 300;
	}

	public int tempsIntervention(){
		return 8;
	}

	public int quantiteIntervention(){
		return 100;
	}
}
