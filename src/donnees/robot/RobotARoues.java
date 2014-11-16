package donnees.robot;
import donnees.*;
public class RobotARoues extends Robot{
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
	int trois = 0;	// S'assure d'obtenir des valeurs correctes malgré la division par 3

    public RobotARoues(Case depart){
		super(depart);
		this.capacite = 5000;
		this.vitesse = 80;
	}

	public RobotARoues(Case pos, int vitesse) {
		this(pos);
		this.vitesse = vitesse;
	}


	public double getVitesse(NatureTerrain natureDuTerrain){
		if (natureDuTerrain.equals(NatureTerrain.HABITAT) || natureDuTerrain.equals(NatureTerrain.TERRAIN_LIBRE)) {
			return 80;
		}
		else {
			return 0;
		}
	}


	public int getCapaciteMax(){
		return 5000;
	}

	public void remplirReservoir(Carte carte){
		if (carte.nextToWater(this.position)) {
			if (trois == 2) {
				super.capacite += 9;
				trois = 0;
			}
			else {
				super.capacite += 8;
				trois++;
			}

			if (super.capacite > 5000){
				super.capacite = 5000;
			}
		}
	}


	public int tempsPourRemplir(){
		return 600;
	}

	public int tempsIntervention(){
		return 5;
	}

	public int quantiteIntervention(){
		return 100;
	}
}
