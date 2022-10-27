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
	
	//une liste d'items binaire (0, 1) stockés en int pour plus de souplesse avec les formules
	int[] gene = new int[complexity];
	
	//constructor, appelé pour '=new Chromosome()' qui initialise les valeurs
	public Chromosome() {
		for (int i=0;i<complexity;i++) {
			allele=(int)(Math.random()*2);
			this.gene[i]=allele;
		}
	}

	//modificateur, appelé de l'extérieur
	public void setGene(int[] input){
		this.gene=input;
	}
	
	//affichage du contenu, utilisé en debug
	public void imprimer(){
		for (int i=0;i<complexity;i++){
			System.out.print(gene[i]+",");
		}
	}
	
	//reproduction retourne le gene MAIS
	//celui ci *peut* avoir été modifié sur 1 allele:
	//selon le taux de mutation 
	public int[] reproduire(int tauxmute) {
		int[] geneTemp= new int [complexity];
		geneTemp = this.gene;
		//on appelle dans ce if la fonction Chance(n) qui retourne true n% du temps
		if (MyOwn.Chance(tauxmute)){
			//on selectionne alors une allele au hasard
			mutation=(int)(Math.random()*complexity)/complexity;
			//on inverse l'allele en question
			if (geneTemp[mutation]==1) geneTemp[mutation]=0;
			else geneTemp[mutation]=1;
		}
		return geneTemp;
	}
	
	
}
