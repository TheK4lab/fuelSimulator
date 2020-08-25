package fuelSimulator;

import java.util.Arrays;
import java.util.Scanner;

public class Program {
	static int risposta;
	static int numeroPompa;
	static int arrayDim = 4;
	static int totale;
	static Scanner scanner = new Scanner(System.in);
	static Integer[] banconote = new Integer[] { 5,10,20,50,100 };
	static Pompa[] pompe = new Pompa[arrayDim];
	Utente utente = new Utente();

	public static void main(String[] args) {
		for(int i = 0; i < pompe.length; i++) {
			pompe[i] = new Pompa("Pompa " + (i+1));
		}
		Menu();
	}

	private static void Menu() {
		selezionaOperazione();
		risposta = scanner.nextInt();
		switch (risposta) {
			case 1:
				rifornisci(utente);
				break;
			case 2:
				stampaIncasso();
				break;
			case 3:
				ricaricaSerbatoio();
				break;
			case 4:
				stampaGiacenza();
				break;
			case 5:
				System.exit(0);
			default:
				System.out.println("RISPOSTA ERRATA!");
			}
		Menu();
	}

	private static void ricaricaSerbatoio() {
		numeroPompa = checkPompa();
		Benzinaio.ricarica(pompe[numeroPompa-1]);		
	}

	private static void rifornisci(Utente utente) {
		inserisciBanconote();
		if(utente.budget > 0) {
			numeroPompa = checkPompa();
			Benzinaio.rifornisci(pompe[numeroPompa-1], utente);
		}
		
	}	
	
	private static void inserisciBanconote(Utente utente) {
		int banconotaUtente;
		
		System.out.println("Inserisci banconota (tagli da 5 a 100 euro) >> ");
		banconotaUtente = scanner.nextInt();
		if(!Arrays.asList(banconote).contains(banconotaUtente)) {
			System.out.println("Banconota non accettata!");
		}
		else {
			utente.budget += banconotaUtente;
		}
		System.out.println("Vuoi inserire un'altra banconota? (1.Si 2.No)");
		int risposta = checkRisposta();
		if (risposta == 1) {
			inserisciBanconote();
		}
		else {
			return;
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

	private static int checkPompa() {
		int pompa;
		System.out.println("Inserisci numero pompa (1 - " + pompe.length + ") >> ");
		do {
			pompa =  scanner.nextInt();
			if(pompa < 1 || pompa > pompe.length) {
				System.out.println("ERRORE! Inserisci un valore compreso tra 1 e " + pompe.length + "\n");
			}
		} while(pompa < 1 || pompa > pompe.length);
		return pompa;
	}
	
	// Metodi di stampa
	
	private static void selezionaOperazione() {
		System.out.println("Seleziona operazione da eseguire"
				+ "\n1. Rifornisci"
				+ "\n2. Stampa incasso totale"
				+ "\n3. Ricarica un serbatoio"
				+ "\n4. Stampa giacenza di ogni pompa"
				+ "\n5. Esci");
	}
	
	private static void stampaIncasso() {
		totale = 0;
		for(int i = 0; i < pompe.length; i++) {
			System.out.println("Incasso pompa " + (i+1) + " " + pompe[i].incasso);
			totale += pompe[i].incasso;
		}
		System.out.println("\nTotale: " + " " + totale + "\n");
	}
	
	private static void stampaGiacenza() {	
		System.out.println("Giacenza pompe:");
		for(int i = 0; i < pompe.length; i++) {
			System.out.println(pompe[i].nomePompa); 
			for(int j = 0; j < pompe[i].capacitaCarburante.length; j++) {
				System.out.println("\n" + pompe[i].nomeCarburante[j] + " " + pompe[i].capacitaCarburante[j]);
			}
		}
	}
}
