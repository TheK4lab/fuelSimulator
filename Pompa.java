package fuelSimulator;

public class Pompa {
	String nomePompa;
	String[] nomeCarburante = {"Benzina", "Diesel"};
	double[] capacitaCarburante = {1000, 1000};
	double[] prezzoCarburante = {1.99, 0.99};
	int incasso;
	
	public Pompa (String nomePompa) {
		this.nomePompa = nomePompa;
	}
}

