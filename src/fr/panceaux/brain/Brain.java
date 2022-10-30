package fr.panceaux.brain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

	/* un Cerveau est basiquement une liste de couches
	 * peut : 	-imprimer son contenu
	 * 			-constructor
	 * 
	 */
public class Brain {

	private static int nbNeurone = 5;
	private static int nbCouche = 3;
	public static ArrayList<Couche> reseau;
	
	public static void main(String[] args) {
		
		System.out.println("****   RESEAU NEURONAL  ****");
		System.out.println("    Par Pierrick Anceaux    ");
		System.out.println();
		System.out.println("          Bienvenue         ");
		
		Menu();
	}
	
	// ----------- DECLARATION DES METHODES ----------
	
	/* Le Menu permet de lancer diff�rentes actions
	 * il ne cr�e pas d'instance ni ne fait de calcul
	 * il n'existe que pour s�parer l'interface du backend
	 * il ne s'utilise qu'en lan�ant directement brain.java
	 * c'est une m�thode ammen�e � dispara�tre
	 * lorsque l'objet Brain sera utilis� par un code ma�tre
	 */
	public static void Menu() {
	
		int tx = 0;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n  --- MENU ---");
		System.out.println("1 - Cr�er un r�seau de "+nbCouche+" couches, chacune de "+nbNeurone+" neurones");
		System.out.println("2 - Afficher l'�tat du r�seau en cours");
		System.out.println("3 - ");
		System.out.println("4 - ");
		System.out.println("5 - Cr�er un neurone ayant "+nbNeurone+" coefficients");
		System.out.println("6 - Afficher l'�tat du neurone");
		System.out.print("\n     Entrez votre choix : ");
		
		do {
			tx = sc.nextInt();
		} while (tx!=1);
				//&&tx!=2&&tx!=3&&tx!=4&&tx!=5&&tx!=6
		sc.close();
		
		switch (tx){
		case 1 : constCerveau(); break;
		case 2 : break;
		default : System.out.println("Oups une erreur a du arriver.");
		}	
	}
	
	/* Le constructor cr�e un agglom�rat de couches
	 * chaque couche construit son agglom�rat de neurones
	 * chaque neurone initialise ses valeurs al�atoirement
	 * 
	 */
	public static void constCerveau() {
		reseau = new ArrayList<Couche>();
		System.out.println(" ** Initialisation d'un réseau neuronal **");
		for (int i =0; i<nbCouche; i++) {

				//reseau.addAll(new Couche(nbNeurone).new Neurone(nbNeurone));
		}
	}
	
	/* m�thode pour afficher le contenu du cerveau
	 * c�d pour chaque layer les neurones qu'il contient 
	 * pour chaque neurones les valeurs de Mem & Somme
	 */
	public static void impCerveau() {
		System.out.println(" ** Affichage du contenu du réseau neuronal ** ");
		for (int i =0; i<nbCouche; i++) {
			System.out.println(" --Layer no"+i+" : --");
			reseau.get(i).impCouche();
		}
	}
	
	// ----------- DECLARATION DES OBJETS ----------
	
	
	/*	une couche correspond à une liste de Neurones
	 *	peut : 	- obtenir la valeur somme du neurone X
	 *			- obtenir la table de coeff du neurone X
	 *			- forcer la somme du neurone X
	 *			- forcer la table de coeff du neurone X
	 *			- imrimer son contenu
	 */
	public class Couche{
		private ArrayList<Neurone> couche = new ArrayList<>();
		
		public Double setSomme_L(int nIndex) {
			if(0<=nIndex && nIndex<=nbNeurone) {
				return this.couche.get(nIndex).getSomme();
			}else {
				return null;
			}
		}
		
		public ArrayList<Double> getMem_L(int nIndex) {
			if(0<=nIndex && nIndex<=nbNeurone) {
				return this.couche.get(nIndex).getMem();
			}else {
				return null;
			}
		}
		
		public void setSomme_L (Double toSomme, int nIndex) {
			couche.get(nIndex).setSomme(toSomme);
		}
		
		public void setMem_L (Double[] toMem, int nIndex) {
			couche.get(nIndex).setMem(toMem);
		}
		
		public void impCouche() {
			for (int i = 0; i<nbNeurone; i++){
				System.out.print("["+i+"]");
				this.couche.get(i).impNeurone();
			}
		}
		

		public Couche(int nbN) {
				for (int i = 0; i<nbN; i++){
					couche.add(new Neurone(nbN));
					System.out.print("N"+i+" ");
				};


		}
		
		/* un Neurone est une mémoire de :
	     *  le coeff de chaque connexion aux neurones précédents
		 *  la valeur somme de ces neurones par leur coeff
		 *  peut :	-forcer la valeur somme
		 *			-forcer les valeurs de coeff memoire
		 *			-obtenir la somme interne
		 *			-obtenir la table coeff memoire
		 *			-imprimer son contenu
		 *			-constructor
		 */
		public class Neurone {
			public ArrayList<Double> mem = new ArrayList<>();
			private Double somme = null;
			
			public void setSomme (Double valeur){
				this.somme=valeur;
			}
			
			public void setMem (Double[] tableCoeff) {
				this.mem=(ArrayList<Double>) Arrays.asList(tableCoeff);
			}
			
			public Double getSomme(){
				return this.somme;
			}
			
			public ArrayList<Double> getMem() {
				return this.mem;
			}
			
			public void impNeurone() {
				for (int i = 0; i<nbNeurone; i++) {
					System.out.print(this.mem);
				}
				System.out.print(" "+this.somme+" /");		
			}
			

			public Neurone(int nbM) {
					for (int i = 0; i<nbM; i++) {
						mem.add(Math.random());
						};
				
				this.somme=Math.random();
			}
			

		//fin Neurone	
		}
		
	//fin Couche
	}

	
	


//fin programme Brain.java	
}
