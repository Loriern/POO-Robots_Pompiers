// Ensimag 2014-15 - 2A POO

import java.io.*;
import java.util.*;
import donnees.*;
import donnees.robot.*;


/**
 * Lecteur de cartes au format spectifie dans le sujet.
 * Les donnees sur les cases, robots puis incendies sont lues dans le fichier,
 * puis simplement affichees.
 * A noter: pas de verification semantique sur les valeurs numeriques lues.
 *
 * IMPORTANT:
 *
 * Cette classe ne fait que LIRE les infos et les afficher.
 * A vous de modifier ou d'ajouter des methodes, inspirees de celles presentes
 * (ou non), qui CREENT les objets au moment adequat pour construire une
 * instance de la classe DonneesSimulation a partir d'un fichier.
 *
 * Vous pouvez par exemple ajouter une methode qui cree et retourne un objet
 * contenant toutes les donnees lues:
 *    public static DonneesSimulation creeDonnees(String fichierDonnees);
 * Et faire des méthode creeCase(), creeRobot(), ... qui liset les données,
 * créent les objets adéquats et les ajoutent ds l'instance de DonneesSimulation.
 *
 */
public class LecteurDonnees {


	/**
	 * Lit et affiche le contenu d'un fichier de donnees (cases,
	 * robots et incendies).
	 * Methode de classe, utilisation: LecteurDonnees.lire(fichierDonnees)
	 * @param fichierDonnees nom du fichier a lire
	 */
	public static void lire(String fichierDonnees)
			throws FileNotFoundException, ExceptionFormatDonnees {
		System.out.println("\n == Lecture du fichier" + fichierDonnees);
		LecteurDonnees lecteur = new LecteurDonnees(fichierDonnees);
		lecteur.lireCarte();
		lecteur.lireIncendies();
		lecteur.lireRobots();
		scanner.close();
		System.out.println("\n == Lecture terminee");
	}


	public static DonneesSimulation initData(String fichierDonnees)
			throws FileNotFoundException, ExceptionFormatDonnees {
				lire(fichierDonnees);
				return donneesSim;
	}

    public DonneesSimulation getDonneesSimulation(){
        return this.donneesSim;
    }

	// Tout le reste de la classe est prive!

	private static Scanner scanner;
    private static DonneesSimulation donneesSim;

	/**
	 * Constructeur prive; impossible d'instancier la classe depuis l'exterieur
	 * @param fichierDonnees nom du fichier a lire
	 */
	private LecteurDonnees(String fichierDonnees)
			throws FileNotFoundException {
		scanner = new Scanner(new File(fichierDonnees));
		scanner.useLocale(Locale.US);
	}

	/**
	 * Lit et affiche les donnees de la carte.
	 * @throws ExceptionFormatDonnees
	 */
	private void lireCarte() throws ExceptionFormatDonnees {
		ignorerCommentaires();
		try {
			int nbLignes = scanner.nextInt();
			int nbColonnes = scanner.nextInt();
			int tailleCases = scanner.nextInt();
            donneesSim = new DonneesSimulation(nbLignes, nbColonnes, tailleCases);
			System.out.println("Carte " + nbLignes + "x" + nbColonnes
					+ "; taille des cases = " + tailleCases);

			for (int lig = 0; lig < nbLignes; lig++) {
				for (int col = 0; col < nbColonnes; col++) {
					lireCase(lig, col);
				}
			}

		} catch (NoSuchElementException e) {
			throw new ExceptionFormatDonnees("Format invalide. "
					+ "Attendu: nbLignes nbColonnes tailleCases");
		}
		// une ExceptionFormat levee depuis lireCase est remontee telle quelle
	}




	/**
	 * Lit et affiche les donnees d'une case.
	 */
	private void lireCase(int lig, int col) throws ExceptionFormatDonnees {
		ignorerCommentaires();
		System.out.print("Case (" + lig + "," + col + "): ");
		String chaineNature = new String();
		//NatureTerrain nature;

		try {
			chaineNature = scanner.next();
			// si NatureTerrain est un Enum, vous pouvez recuperer la valeur
			// de l'enum a partir d'une String avec:
			//NatureTerrain nature = NatureTerrain.valueOf(chaineNature);
            donneesSim.getCarte().initCase(lig, col, NatureTerrain.valueOf(chaineNature));
			verifieLigneTerminee();

			System.out.print("nature = " + chaineNature);

		} catch (NoSuchElementException e) {
			throw new ExceptionFormatDonnees("format de case invalide. "
					+ "Attendu: nature altitude [valeur_specifique]");
		}

		System.out.println();
	}


	/**
	 * Lit et affiche les donnees des incendies.
	 */
	private void lireIncendies() throws ExceptionFormatDonnees {
		ignorerCommentaires();
		try {
			int nbIncendies = scanner.nextInt();
            donneesSim.initListInc();
			System.out.println("Nb d'incendies = " + nbIncendies);
			for (int i = 0; i < nbIncendies; i++) {
				lireIncendie(i);
			}

		} catch (NoSuchElementException e) {
			throw new ExceptionFormatDonnees("Format invalide. "
					+ "Attendu: nbIncendies");
		}
	}


	/**
	 * Lit et affiche les donnees du i-eme incendie.
	 * @param i
	 */
	private void lireIncendie(int i) throws ExceptionFormatDonnees {
		ignorerCommentaires();
		System.out.print("Incendie " + i + ": ");

		try {
			int lig = scanner.nextInt();
			int col = scanner.nextInt();
			int intensite = scanner.nextInt();
			if (intensite <= 0) {
				throw new ExceptionFormatDonnees("incendie " + i
						+ "nb litres pour eteindre doit etre > 0");
			}
			verifieLigneTerminee();

			System.out.println("position = (" + lig + "," + col
					+ ");\t intensite = " + intensite);

			donneesSim.getIncendies().add(new Incendie(donneesSim.getCarte().getCase(lig, col), intensite));

		} catch (NoSuchElementException e) {
			throw new ExceptionFormatDonnees("format d'incendie invalide. "
					+ "Attendu: ligne colonne intensite");
		}
	}


	/**
	 * Lit et affiche les donnees des robots.
	 */
	private void lireRobots() throws ExceptionFormatDonnees {
		ignorerCommentaires();
		try {
			int nbRobots = scanner.nextInt();
			System.out.println("Nb de robots = " + nbRobots);
			donneesSim.initRobotTab(nbRobots);
			for (int i = 0; i < nbRobots; i++) {
				lireRobot(i);
			}

		} catch (NoSuchElementException e) {
			throw new ExceptionFormatDonnees("Format invalide. "
					+ "Attendu: nbRobots");
		}
	}


	/**
	 * Lit et affiche les donnees du i-eme robot.
	 * @param i
	 */
	private void lireRobot(int i) throws ExceptionFormatDonnees {
		ignorerCommentaires();
		System.out.print("Robot " + i + ": ");

		try {
			int lig = scanner.nextInt();
			int col = scanner.nextInt();
			System.out.print("position = (" + lig + "," + col + ");");
			String type = scanner.next();

			System.out.print("\t type = " + type);


			// lecture eventuelle d'une vitesse du robot (entier)
			System.out.print("; \t vitesse = ");
			String s = scanner.findInLine("(\\d+)");	// 1 or more digit(s) ?
			// pour lire un flottant:    ("(\\d+(\\.\\d+)?)");

			if (s == null) {
				System.out.print("valeur par defaut");
			} else {
				int vitesse = Integer.parseInt(s);
				System.out.print(vitesse);
			}
			verifieLigneTerminee();

			//RobotType robotType = RobotType.valueOf(type);
			donneesSim.initRobot(i, donneesSim.getCarte().getCase(lig, col), type);

			System.out.println();

		} catch (NoSuchElementException e) {
			throw new ExceptionFormatDonnees("format de robot invalide. "
					+ "Attendu: ligne colonne type [valeur_specifique]");
		}
	}




	/** Ignore toute (fin de) ligne commencant par '#' */
	private void ignorerCommentaires() {
		while(scanner.hasNext("#.*")) {
				scanner.nextLine();
		}
	}

	/**
	 * Verifie qu'il n'y a plus rien a lire sur cette ligne (int ou float).
	 * @throws ExceptionFormatDonnees
	 */
	private void verifieLigneTerminee() throws ExceptionFormatDonnees {
		if (scanner.findInLine("(\\d+)") != null) {
			throw new ExceptionFormatDonnees("format invalide, donnees en trop.");
		}
	}
}

