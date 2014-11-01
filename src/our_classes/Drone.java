public class Drone extends Robot{
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
