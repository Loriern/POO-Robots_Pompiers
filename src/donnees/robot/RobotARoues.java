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

    public RobotARoues(Case depart){
		super(depart);
		capacite = 5000;
		vitesse = 80;
	}

	public RobotType getType(){
		return RobotType.ROUES;
	}

	public double getVitesse(NatureTerrain natureDuTerrain){
		if (natureDuTerrain.equals(NatureTerrain.HABITAT) || natureDuTerrain.equals(NatureTerrain.TERRAIN_LIBRE)) {
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

	public void deplacer(Carte carte, Direction dir){
		super.setPosition(carte.getVoisin(this.position, dir));
	}
}
