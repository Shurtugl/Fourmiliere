package fr.panceaux.brain;


import java.util.Scanner;

	/* un Cerveau est basiquement une liste de couches
	 * peut : 	-imprimer son contenu
	 * 			-constructor
	 * 
	 */
public class Brain {

	private static int nbNeurone = 5;
	private static int nbCouche = 3;
	public static Couche[] reseau;
	
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
		int saisie = 0;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n  --- MENU ---");
		System.out.println("1 - Cr�er un r�seau de "+nbCouche+" couches, chacune de "+nbNeurone+" neurones");
		System.out.println("2 - Afficher l'�tat du r�seau en cours");
		System.out.print("\n     Entrez votre choix : ");
		
		do {
			saisie = sc.nextInt();
		} while (saisie!=1 && saisie!=2);
		sc.close();
		
		switch (saisie){
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
		reseau = new Couche[nbCouche];
		System.out.println(" ** Initialisation d'un r�seau neuronal **");
		for (int i =0; i<nbCouche; i++) {
			reseau[i].constCouche();
		}
	}
	
	/* m�thode pour afficher le contenu du cerveau
	 * c�d pour chaque layer les neurones qu'il contient 
	 * pour chaque neurones les valeurs de Mem & Somme
	 */
	public static void impCerveau() {
		System.out.println(" ** Affichage du contenu du r�seau neuronal ** ");
		for (int i =0; i<nbCouche; i++) {
			System.out.println(" --Layer n�"+i+" : --");
			reseau[i].impCouche();
		}
	}
	
		
	
	// ----------- DECLARATION DES OBJETS ----------
	
	
	/*	une couche correspond � une liste de Neurones
	 *	peut : 	- obtenir la valeur somme du neurone X
	 *			- obtenir la table de coeff du neurone X
	 *			- forcer la somme du neurone X
	 *			- forcer la table de coeff du neurone X
	 *			- imrimer son contenu
	 */
	public class Couche{
		private Neurone[] couche;
		
		public Double setSomme_L(int nIndex) {
			if(0<=nIndex && nIndex<=nbNeurone) {
				return this.couche[nIndex].getSomme();
			}else {
				return null;
			}
		}
		
		public Double[] getMem_L(int nIndex) {
			if(0<=nIndex && nIndex<=nbNeurone) {
				return this.couche[nIndex].getMem();
			}else {
				return null;
			}
		}
		
		public void setSomme_L (Double toSomme, int nIndex) {
			couche[nIndex].setSomme(toSomme);
		}
		
		public void setMem_L (Double[] toMem, int nIndex) {
			couche[nIndex].setMem(toMem);
		}
		
		public void impCouche() {
			for (int i = 0; i<nbNeurone; i++){
				System.out.print("["+i+"]");
				this.couche[i].impNeurone();
			}
		}
		
		public void constCouche() {
			couche = new Neurone[nbNeurone];
			for (int i = 0; i<nbNeurone; i++){
				System.out.print("N"+i);
				this.couche[i].constNeurone();
			}
			System.out.print(" ");
		}
	//fin Couche
	}

	
	/* un Neurone est une m�moire de :
     *  le coeff de chaque connexion aux neurones pr�c�dents
	 *  la valeur somme de ces neurones par leur coeff
	 *  peut :	-forcer la valeur somme
	 *			-forcer les valeurs de coeff memoire
	 *			-obtenir la somme interne
	 *			-obtenir la table coeff memoire
	 *			-imprimer son contenu
	 *			-constructor
	 */
	public class Neurone {
		public Double[] mem;
		private Double somme = null;
		
		public void setSomme (Double valeur){
			this.somme=valeur;
		}
		
		public void setMem (Double[] tableCoeff) {
			this.mem=tableCoeff;
		}
		
		public Double getSomme(){
			return this.somme;
		}
		
		public Double[] getMem() {
			return this.mem;
		}
		
		public void impNeurone() {
			for (int i = 0; i<nbNeurone; i++) {
				System.out.print(this.mem[i]);
			}
			System.out.print(" "+this.somme+" /");		
		}
		
		public void constNeurone() {
			mem = new Double[nbNeurone];
			for (int i = 0; i<nbNeurone; i++) {
				this.mem[i]=Math.random();
			}
			this.somme=Math.random();
		}
	//fin Neurone	
	}
	
	

	
	
	

//fin programme Brain.java	
}
