import java.io.*;
import donnees.*;
import donnees.evenements.*;

public class Simulation {
	public static void main(String[] args) {
		if (args.length < 1) {
			System.out.println("Syntaxe: java TestLecteurDonnees <nomDeFichier>");
			System.exit(1);
		}

		try {
			Simulateur simulation = new Simulateur(args[0]);

			for (int i = 2; i <= 10; i += 2) {
				simulation.ajouteEvenement(new EvenementMessage(i, " [PING]"));
			}
			for (int i = 3; i <= 9; i += 3) {
				simulation.ajouteEvenement(new EvenementMessage(i, " [PONG]"));
			}

		} catch (FileNotFoundException e) {
			System.out.println("fichier " + args[0] + " inconnu ou illisible");
		} catch (ExceptionFormatDonnees e) {
			System.out.println("\n\t**format du fichier " + args[0] + " invalide: " + e.getMessage());
		}
	}
}
