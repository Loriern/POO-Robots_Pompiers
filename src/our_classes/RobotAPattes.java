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
