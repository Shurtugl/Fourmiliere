package fr.panceaux.brain;


public class Chromosome {
	//objet contenant un descriptif binaire
	//n bits pour le neurone Source
	//n bits pour le neurone Cible
	//y bits pour le poids de la liaison -dont un pour n�gatif-
	//on fixe arbitrairement n=8, y=8
	
	final int complexity=8+8+8;
	int allele = 0;
	int mutation = 0;

	int[] gene = new int[complexity];
	
	
	public Chromosome() {
		for (int i=0;i<complexity;i++) {
			allele=(int)(Math.random()*2);
			this.gene[i]=allele;
		}
	}

	public void setGene(int[] input){
		this.gene=input;
	}
	
	public void imprimer(){
		for (int i=0;i<complexity;i++){
			System.out.print(gene[i]+",");
		}
	}
	
	public int[] reproduire(int chanceDeMuter) {
		int[] geneTemp= new int [complexity];
		int vamuter =(int)(Math.random()*chanceDeMuter)/100;
		if vamuter
		geneTemp = this.gene;
		
		mutation=(int)(Math.random()*complexity)/complexity*chanceDeMuter/100;
		System.out.println("mutation aléatoire à l'allele"+mutation);
		if (mutation<=complexity) {
			allele=(int)(Math.random()*2)/2;
			System.out.println("par une allele set aléatoirement à"+allele);
			geneTemp[mutation]=allele;
		}
		return geneTemp;
	}
	
	
}
