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
	
	/* Le Menu permet de lancer différentes actions
	 * il ne crée pas d'instance ni ne fait de calcul
	 * il n'existe que pour séparer l'interface du backend
	 * il ne s'utilise qu'en lançant directement brain.java
	 * c'est une méthode ammenée à disparaître
	 * lorsque l'objet Brain sera utilisé par un code maître
	 */
	public static void Menu() {
	
		int tx = 0;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n  --- MENU ---");
		System.out.println("1 - Créer un réseau de "+nbCouche+" couches, chacune de "+nbNeurone+" neurones");
		System.out.println("2 - Afficher l'état du réseau en cours");
		System.out.println("3 - ");
		System.out.println("4 - ");
		System.out.println("5 - Créer un neurone ayant "+nbNeurone+" coefficients");
		System.out.println("6 - Afficher l'état du neurone");
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
	
	/* Le constructor crée un agglomérat de couches
	 * chaque couche construit son agglomérat de neurones
	 * chaque neurone initialise ses valeurs aléatoirement
	 * 
	 */
	public static void constCerveau() {
		reseau = new ArrayList<Couche>();
		System.out.println(" ** Initialisation d'un réseau neuronal **");
		for (int i =0; i<nbCouche; i++) {

				reseau.add(new Couche(nbNeurone));

		}
	}
	
	/* méthode pour afficher le contenu du cerveau
	 * câd pour chaque layer les neurones qu'il contient 
	 * pour chaque neurones les valeurs de Mem & Somme
	 */
	public static void impCerveau() {
		System.out.println(" ** Affichage du contenu du réseau neuronal ** ");
		for (int i =0; i<nbCouche; i++) {
			System.out.println(" --Layer n°"+i+" : --");
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
	//fin Couche
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
		

		public void Neurone(int nbM) {
				for (int i = 0; i<nbM; i++) {
					mem.add(Math.random());
					};
			
			this.somme=Math.random();
		}
		

	//fin Neurone	
	}
	


//fin programme Brain.java	
}
