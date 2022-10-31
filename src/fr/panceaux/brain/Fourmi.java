package fr.panceaux.brain;

public class Fourmi {

	/* Objet possédant un micro réseau neuronal,
	 * pour le déplacer au sein d'une simulation.
	 * Tient compte de multiple paramètre selon un
	 * génome qui défini ses connexions nerveuses
	 */

	//---- 21 entrées (-1.0,1.0)f : ----
	final int nbEntrees=21;
	float BD  = 0; 	//plus proche frontière
	float BDx = 0; 	//distance à la frontière X
	float BDy = 0; 	//distance à la frontière Y
	
	float Bfd = 0; 	//bloquage par autrui sur l'avant
	float Blr = 0; 	//bloquage par autrui sur les cotés
	float LBf = 0; 	//bloquage total sur l'avant (longue**)
	
	float Gen = 0; 	//similarité génétique au plus proche voisin
	float Age = 0; 	//age au sein de cette simulation

	float LMx = 0; 	//dernier mouvement sur l'axe X
	float LMy = 0; 	//dernier mouvement sur l'axe Y
	float Lx  = 0; 	//position sur l'axe X
	float Ly  = 0; 	//position sur l'axe Y
	
	float Plr = 0; 	//population sur les cotés (variation)
	float Pfd = 0; 	//population sur l'avant (variation)
	float LPf = 0; 	//population totale sur l'avant (longue**)
	float Pop = 0; 	//densité de population toute la map
	
	float Rnd = 0;  //entree aléatoire
	float Osc = 0;	//entree oscillante
	
	float Slr = 0;	//pheromone sur les cotés (variation)
	float Sfd = 0;	//pheromone sur l'avant (variation)
	float Sg  = 0;	//pheromone totale
	
	//---- 11 sorties (-1.0,1.0)f : ---- 
	final int nbSorties=11;
	float MY  = 0; 	//mouvement sur l'axe X
	float MX  = 0;	//mouvement sur l'axe Y
	float MRL = 0;	//mouvement vers un coté
	float Mrv = 0;	//mouvement vers l'arriere
	float Mfd = 0;	//mouvement vers l'avant
	float Mrn = 0;	//mouvement aléatoire
	
	float LPD = 0;	//distance des longues entrees **
	float OSC = 0;	//periode d'oscillation
	
	float SG  = 0;	//créer pheromone
	float Res = 0;	//réactivité, caféine

	float Kil = 0;	//tuer les voisins

	//---- internes à la fourmi ----
	int nbNeurones=3;
	Genome genome = null;
	int sizeX = 10;
	int sizeY = 10;

	//memoire courte garde autant de variable float que de neurones
	//est utilisé pour reproduire les valeurs calculées d'une étape sur l'autre
	float[] memoireCourte = null;

	//internes correspond aux valeurs conservées dans les neurones internes
	float[] internes = null;


	public Fourmi(int complexity,int largeur,int hauteur){
		this.genome = new Genome(complexity);
		this.memoireCourte = new float[complexity];
		for (int i=0;  i<complexity; i++){
			this.memoireCourte[i]=0.0f;
		}
		this.internes = new float[nbNeurones];
		for (int i=0; i<nbNeurones;i++){
			this.internes[i]= 0.0f;
		}
		this.sizeX=largeur;
		this.sizeY=hauteur;
	}
	
	protected void Pense(){
		float neural =0.0f;
		int input;
		int output;
		float value;
		//une boucle pour calculer le prochain état des neurones
		for (int i = 0; i<this.genome.length;i++){
			//getGenes sort forcément un chiffre sur 8 bit, donc {0, 255}.
			//on ramène sur une échelle {0,x} pour chaque paramètre
			//on cast en int ou en float selon le besoin
			input = (int)(this.genome.getGenes(i,'i')*(nbEntrees+this.nbNeurones))/255;
			value = (float)(this.genome.getGenes(i,'v'))/255;
			switch (input){
				case  1 : neural = this.BD;break;	
				case  2 : neural = this.BDx;break;
				case  3 : neural = this.BDy;break;
				case  4 : neural = this.Bfd;break;
				case  5 : neural = this.Blr;break;
				case  6 : neural = this.LBf;break;
				case  7 : neural = this.Gen;break;
				case  8 : neural = this.Age;break;
				case  9 : neural = this.LMx;break;
				case 10 : neural = this.LMy;break;
				case 11 : neural = this.Lx;break;	
				case 12 : neural = this.Ly;break;	
				case 13 : neural = this.Plr;break;
				case 14 : neural = this.Pfd;break;
				case 15 : neural = this.LPf;break;
				case 16 : neural = this.Pop;break;
				case 17 : neural = this.Rnd;break;
				case 18 : neural = this.Osc;break;
				case 19 : neural = this.Slr;break;
				case 20 : neural = this.Sfd;break;
				case 21 : neural = this.Sg ;break;
				//les autres cas hors des 21 entrées sont des outputs depuis les neurones internes
				default : neural = this.internes[i-nbEntrees];break;
			}
			this.memoireCourte[i]= neural * value;
		}
		//une boucle pour appliquer le prochain état des neurones
		for (int i = 0; i<genome.length;i++){
			output = (int)(this.genome.getGenes(i,'o')*(nbSorties+this.nbNeurones+1))/255;
			neural = this.memoireCourte[i];
			switch(output){
				case  1 : this.MY  = neural;break;
				case  2 : this.MX  = neural;break;
				case  3 : this.MRL = neural;break;
				case  4 : this.Mrv = neural;break;
				case  5 : this.Mfd = neural;break;
				case  6 : this.Mrn = neural;break;
				case  7 : this.LPD = neural;break;
				case  8 : this.OSC = neural;break;
				case  9 : this.SG  = neural;break;
				case 10 : this.Res = neural;break;
				case 11 : this.Kil = neural;break;
				case 12 : //changer le nombre de neurones;break;
				//les autres cas hors des 11 entrées sont des inputs des neurones internes
				default : this.internes[i-nbNeurones] = neural;break;
			}
		}
	}

	//mettre à jour les neurones input
	protected void UpdatePositions(int posX, int posY, int step){
		this.Lx  = (float)(posX);
		this.Ly  = (float)(posY);
		this.BDx = (float)(sizeY-posX);
		this.BDy = (float)(sizeX-posY);
		this.BD  = (float)(Math.min(Math.min(posX,posY),Math.min(sizeX-posX,sizeY-posY)));
		this.Age = (float)(step);
		this.Osc = (float)(Math.sin(step*1.0));
		this.Rnd = (float)(Math.random());
	}

	protected void SetBlocage(int avant, int cotes, int longue){
		this.Bfd=avant;
		this.Blr=cotes;
		this.LBf=longue;
	}
	
	protected void showGenome(){
		int input; int output;
		float value;
		for (int i = 0; i<genome.length;i++){
			input = (int)(this.genome.getGenes(i,'i')*(nbEntrees+this.nbNeurones))/255;
			value = (float)(this.genome.getGenes(i,'v')*100)/255;
			output = (int)(this.genome.getGenes(i,'o')*(nbSorties+this.nbNeurones+1))/255;
			System.out.printf(" g " + i + " : " + input + " x%.1f ->"+output+"/",value);
		}
	}

}
