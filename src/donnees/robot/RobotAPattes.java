package donnees.robot;
import donnees.*;
public class RobotAPattes extends Robot{
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

    public RobotAPattes(Case depart){
		super(depart);
		this.capacite = Integer.MAX_VALUE;
		this.vitesse = 30;
	}

	public double getVitesse(NatureTerrain natureDuTerrain){
		if (natureDuTerrain.equals(NatureTerrain.EAU)) {
			return 0;
		}
		else if (natureDuTerrain.equals(NatureTerrain.ROCHE)) {
			return 10;
		}
		else {
			return this.vitesse;
		}
	}

	public void deverserEau(int vol){
		return;	// :)
	}


	public int getCapaciteMax(){
		return Integer.MAX_VALUE;
	}

	public void remplirReservoir(Carte carte){
		return;	// :)
	}


	public int tempsPourRemplir(){
		return 0;
	}

	public int tempsIntervention(){
		return 1;
	}

	public int quantiteIntervention(){
		return 10;
	}
}
