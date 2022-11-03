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
	
	float Gen = 0; 	//similarit� g�n�tique au plus proche voisin
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
	
	float SG  = 0;	//cr�er pheromone
	float Res = 0;	//réactivité, caféine

	float Kil = 0;	//tuer les voisins

	//---- internes à la fourmi ----
	int nbNeurones     = 3;
	Genome genome      = null;
	int orientee       = 0;
	
	//---- pour toutes les fourmis ----
	static int sizeX   = 10;
	static int sizeY   = 10;
	static float maxmultiplier = 2.0f;

	//memoire courte garde autant de variable float que de neurones
	//est utilisé pour reproduire les valeurs calculées d'une étape sur l'autre
	float[] memoireCourte = null;

	//internes correspond aux valeurs conservées dans les neurones internes
	float[] internes = null;


	public Fourmi(int nbGenes,int largeur,int hauteur){
		this(nbGenes);
		sizeX=largeur;
		sizeY=hauteur;
	}
	
    public Fourmi(int nbGenes){
         this.genome = new Genome(nbGenes);
         this.memoireCourte = new float[nbGenes];
         for (int i=0;  i<nbGenes; i++){
             this.memoireCourte[i]=0.0f;
         }
         this.internes = new float[nbNeurones];
         for (int i=0; i<nbNeurones;i++){
             this.internes[i]= 0.0f;
         }
    }
    
    protected void setBrainSize(int nbNeurones) {
        this.nbNeurones=nbNeurones;
        this.internes = new float[nbNeurones];
        for (int i=0; i<nbNeurones;i++){
            this.internes[i]= 0.0f;
        }
    }
    protected void setMultiplier(float max) {
        this.maxmultiplier=max;
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
			value = (float) ((float)(this.genome.getGenes(i,'v')-127.5)*maxmultiplier/255.0);
			switch (input){
				case  0 : neural = this.BD;break;
				case  1 : neural = this.BDx;break;	
				case  2 : neural = this.BDy;break;
				case  3 : neural = this.Bfd;break;
				case  4 : neural = this.Blr;break;
				case  5 : neural = this.LBf;break;
				case  6 : neural = this.Gen;break;
				case  7 : neural = this.Age;break;
				case  8 : neural = this.LMx;break;
				case  9 : neural = this.LMy;break;
				case 10 : neural = this.Lx;break;
				case 11 : neural = this.Ly;break;	
				case 12 : neural = this.Plr;break;	
				case 13 : neural = this.Pfd;break;
				case 14 : neural = this.LPf;break;
				case 15 : neural = this.Pop;break;
				case 16 : neural = this.Rnd;break;
				case 17 : neural = this.Osc;break;
				case 18 : neural = this.Slr;break;
				case 19 : neural = this.Sfd;break;
				case 20 : neural = this.Sg ;break;
				//les autres cas hors des 21 entrées sont des outputs depuis les neurones internes
				default : neural = this.internes[i-nbEntrees];break;
			}
			this.memoireCourte[i]= (float)(neural * (value));
		}
		//une boucle pour appliquer le prochain état des neurones
		for (int i = 0; i<genome.length;i++){
			output = (int)(this.genome.getGenes(i,'o')*(nbSorties+this.nbNeurones+1))/255;
			neural = this.memoireCourte[i];
			switch(output){
				case  0 : this.MY  = neural;break;
				case  1 : this.MX  = neural;break;
				case  2 : this.MRL = neural;break;
				case  3 : this.Mrv = neural;break;
				case  4 : this.Mfd = neural;break;
				case  5 : this.Mrn = neural;break;
				case  6 : this.LPD = neural;break;
				case  7 : this.OSC = neural;break;
				case  8 : this.SG  = neural;break;
				case  9 : this.Res = neural;break;
				case 10 : this.Kil = neural;break;
				case 11 : break; //changer le nombre de neurones;
				//les autres cas hors des 11 entrées sont des inputs des neurones internes
				default : this.internes[i-nbSorties] = neural;break;
			}
		}
	}

	//mettre à jour les neurones input
	protected void UpdatePositions(int posX, int posY){
		this.Lx  = (float)(posX);
		this.Ly  = (float)(posY);
		this.BDx = (float)(sizeX-posX);
		this.BDy = (float)(sizeY-posY);
		this.BD  = (float)(Math.min(Math.min(posX,posY),Math.min(sizeX-posX,sizeY-posY)));
		this.Age++;
		this.Osc = (float)(Math.sin(this.Age));
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
			value = (float)((this.genome.getGenes(i,'v')-127.5)*100)/255;
			output = (int)(this.genome.getGenes(i,'o')*(nbSorties+this.nbNeurones+1))/255;
			System.out.printf("g"+ i + ": n" + input + " x%.1f -> n"+output+" / ",value);
			if ((i+1)%5==0)System.out.println();
		}
	}
	
	protected void showState() {
	    float neural = 0;
	    System.out.println("inputs :");
        for (int i =0; i<(nbEntrees);i++) {
            switch (i){
                case  0 : neural = this.BD;break;
                case  1 : neural = this.BDx;break;  
                case  2 : neural = this.BDy;break;
                case  3 : neural = this.Bfd;break;
                case  4 : neural = this.Blr;break;
                case  5 : neural = this.LBf;break;
                case  6 : neural = this.Gen;break;
                case  7 : neural = this.Age;break;
                case  8 : neural = this.LMx;break;
                case  9 : neural = this.LMy;break;
                case 10 : neural = this.Lx;break;
                case 11 : neural = this.Ly;break;   
                case 12 : neural = this.Plr;break;  
                case 13 : neural = this.Pfd;break;
                case 14 : neural = this.LPf;break;
                case 15 : neural = this.Pop;break;
                case 16 : neural = this.Rnd;break;
                case 17 : neural = this.Osc;break;
                case 18 : neural = this.Slr;break;
                case 19 : neural = this.Sfd;break;
                case 20 : neural = this.Sg ;break;
                //les autres cas hors des 21 entrées sont des outputs depuis les neurones internes
            }
            System.out.print(i+": "+neural+" - ");
            if ((i+1)%5==0)System.out.println();
        }
        System.out.println("\noutputs :");
        for (int i =0; i<(nbSorties+this.nbNeurones);i++) {
            switch(i){
                case  0 : neural =this.MY;break;
                case  1 : neural =this.MX ;break;
                case  2 : neural =this.MRL;break;
                case  3 : neural =this.Mrv;break;
                case  4 : neural =this.Mfd;break;
                case  5 : neural =this.Mrn;break;
                case  6 : neural =this.LPD;break;
                case  7 : neural =this.OSC;break;
                case  8 : neural =this.SG ;break;
                case  9 : neural =this.Res;break;
                case 10 : neural =this.Kil;break;
                //les autres cas hors des 11 entrées sont des inputs des neurones internes
                default : neural = this.internes[i-nbSorties]; break;
            }
            System.out.print(i+": "+neural+" - ");
            if ((i+1)%5==0)System.out.println();
        }
	}

}
