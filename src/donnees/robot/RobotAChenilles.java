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

    public RobotAChenilles(Case depart){
		super(depart);
		capacite = 2000;
		vitesse = 60;
	}

	public RobotType getType(){
		return RobotType.CHENILLES;
	}

	public double getVitesse(NatureTerrain natureDuTerrain){
		if (natureDuTerrain.equals(NatureTerrain.EAU) || natureDuTerrain.equals(NatureTerrain.ROCHE)) {
			return 0;
		}
		else if (natureDuTerrain.equals(NatureTerrain.FORET)) {
			return vitesse / 2; 	// 0.5*60
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

	public void deplacer(Carte carte, Direction dir){
		super.setPosition(carte.getVoisin(this.position, dir));
	}
}
