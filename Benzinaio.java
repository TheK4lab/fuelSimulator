package fuelSimulator;

import java.util.Scanner;

public class Benzinaio {
	
	static Scanner scanner = new Scanner(System.in);
	static double rifornimento;
	
	static void rifornisci(Pompa pompa, Utente utente) {
		int carburante = checkCarburante(pompa, utente);
		riepilogo(pompa, utente, carburante);
		int risposta = checkRisposta();
		if (risposta == 1) {
			faiCarburante(pompa, utente, carburante);
		}
		else {
			System.out.println("Annullamento operazione...");
			rimborso(pompa, utente);
			return;
		}
	}

	private static void faiCarburante(Pompa pompa, Utente utente, int carburante) {
		rifornimento = utente.budget/pompa.prezzoCarburante[carburante-1];
		System.out.println("Rifornimento completato!"
				+ "\nCarburante rifornito --> " + rifornimento);
		pompa.capacitaCarburante[carburante-1] -= rifornimento;
		pompa.incasso += utente.budget;
		utente.budget = 0;
	}

	private static void rimborso(Pompa pompa, Utente utente) {
		System.out.println("Rimborso --> " + utente.budget);
		utente.budget = 0;
	}

	static void ricarica(Pompa pompa) {
		selezionaCarburante(pompa);
		int risposta = checkRisposta();
		
		if ((risposta == 1 || risposta == 2) && pompa.capacitaCarburante[risposta-1] < 1000) {
			System.out.println("Inserisci importo ricarica: ");
			rifornimento = checkRicarica(pompa,risposta);
			pompa.capacitaCarburante[risposta-1] += (double)rifornimento;
			System.out.println("Serbatoio rifornito di " + rifornimento + " litri."
					+ "\nCapacità attuale: " + pompa.capacitaCarburante[risposta-1] + " litri.");
		}
		else {
			System.out.println("Serbatoio pieno!");
			return;
		}
		return;
	}

	// Metodi stampa
	
	private static void riepilogo(Pompa pompa, Utente utente, int carburante) {
		System.out.println("Hai scelto:"
				+ "\n" + pompa.nomePompa
				+ "\nImporto: " + utente.budget
				+ "\nCarburante: " + pompa.nomeCarburante[carburante-1]
				+ "\nProseguire? (1.Si 2.No)");
	}
	
	private static void selezionaCarburante(Pompa pompa) {
		System.out.println("Seleziona il tipo di carburante:");
		for(int i = 0; i < pompa.nomeCarburante.length; i++) {
			System.out.println((i+1) + " " + pompa.nomeCarburante[i] + " " + pompa.prezzoCarburante[i] + "/l\n");
		}
	}
	
	// Metodi check
	
	private static int checkRisposta() {
		int risposta;
		do {
			risposta = scanner.nextInt();
			if(risposta < 1 || risposta > 2) {
				System.out.println("ERRORE! INSERISCI UN VALORE VALIDO!");
			}
		} while(risposta < 1 || risposta > 2);
		return risposta;
	}
	
	private static int checkCarburante(Pompa pompa, Utente utente) {
		int risposta;
		do {
			selezionaCarburante(pompa);
			risposta = checkRisposta();
			if((risposta == 1 || risposta == 2) && (pompa.capacitaCarburante[risposta-1] <= 0 || 
					(utente.budget/pompa.prezzoCarburante[risposta-1]) >= pompa.capacitaCarburante[risposta-1])) 
			{
				System.out.println("Impossibile completare l'operazione. "
							+ "Il carburante è esaurito o non è sufficiente per completare l'operazione");
				rimborso(pompa, utente);
				return 0;
			}
		} while (risposta < 1 || risposta > 2);
		return risposta;
	}
	
	private static double checkRicarica(Pompa pompa, int risposta) {
		double ricarica;
		do {
			ricarica = scanner.nextDouble();
			if(pompa.capacitaCarburante[risposta-1] + ricarica > 1000) {
				System.out.println("Impossibile completare l'operazione.");
			}
		} while(pompa.capacitaCarburante[risposta-1] + ricarica > 1000);
		return ricarica;
	}

}
