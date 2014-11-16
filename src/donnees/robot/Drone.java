package donnees.robot;
import donnees.*;
public class Drone extends Robot/* implements Voler*/ {
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
	int neuf = 0;	// S'assure d'obtenir des valeurs correctes malgré la division par 3

	public Drone(Case depart){
		super(depart);
		this.capacite = 10000;
		this.vitesse = 100;
	}

	public Drone(Case pos, int vitesse) {
		this(pos);
		if(vitesse > 0) {
			if (vitesse >= 150) {
				this.vitesse = 150;
			}
			else {
				this.vitesse = vitesse;
			}
		}
	}


    

/*  POUR TESTER SI UN ROBOT EST UN DRONE ON PEUT FAIRE : if(robot instanceof Drone) {...}
	public RobotType getType(){
		return RobotType.DRONE;
	}*/

	public double getVitesse(NatureTerrain natureDuTerrain){
		return vitesse;
	}


	public int getCapaciteMax(){
		return 10000;
	}

	public void remplirReservoir(Carte carte){
		if (position.getNature().equals(NatureTerrain.EAU)) {
			if (neuf == 8) {
				super.capacite += 2;
				neuf = 0;
			}
			else {
				super.capacite += 6;
				neuf++;
			}

			if (super.capacite > 10000){
				super.capacite = 10000;
			}
		}
		else {
			System.out.println("Le drône ne remplit son réservoir qu'au-dessus de l'eau !");
		}
	}


	public int tempsPourRemplir(){
		return 1800;
	}

	public int tempsIntervention(){
		return 30;
	}

	public int quantiteIntervention(){
		return 10000;
	}
}
