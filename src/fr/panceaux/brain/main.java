package fr.panceaux.brain;

import java.util.Scanner;

public class main {

    static Fourmi[] nuee = null;
    static float[][] presence = null;
    static int Gridlargeur = 30;
    static int Gridhauteur = 30;
    static int quantite = 10;
    static int complexity = 3;
    static int tour = 10;
    static int txDeMuta = 10;

    public static void main(String args[]) {
        

        //crÃƒÂ©er une instance de "frame" JAVA AWT
        //Affichage frame = new Affichage();
        final int every = 1;
        //MENU 
        Scanner sc = new Scanner(System.in);
        int saisie;
        do{
            System.out.println("\n*** FOURMIS ***");
            System.out.println(" 0 - QUITTER");
            System.out.println(" 1 - ParamÃƒÂ¨tres");
            System.out.println(" 2 - Lancer la Simulation");
            System.out.println(" 3 - Montrer les gÃƒÂ©nomes");
            saisie = sc.nextInt();
            switch (saisie){
                case 0 : //quit
                    break;
                case 1 : //parametres
                    break;
                case 2 : //lancer pour x tours
                    Init();
                    for (int tr=0;tr<tour;tr++){
                        if (tr%every==0){
                            Afficher();
                        }
                        for (int i=0;i<quantite;i++){
                            nuee[i].Pense();
                        }
                        Animer(tr);
                        System.out.println("étape no"+tr);
                    }
                    Afficher();
                    
                    break; 
                case 3 : //montrer le genome
                    for(int i=0;i<quantite;i++){
                        System.out.println("\n fourmi "+i+ " en "+(int)(nuee[i].Lx)+","+(int)(nuee[i].Ly));
                        nuee[i].showGenome();
                    }
                    break;
            }
        }while(saisie != 0);
        sc.close();
       
    System.out.println("*** Merci d'avoir joué avec nous! ***\n\n");
    }

    //montrer un tableau ascii avec les Id de chaque fourmi
    public static void Afficher(){
        //tire une ligne horizontale
        for (int i =0; i<Gridlargeur+2;i++){
            System.out.print("_");
            
        }
        System.out.println();
        //parcours la zone
        for (int i = 0; i<Gridhauteur; i++){
            System.out.print("|");
            for (int j = 0; j<Gridlargeur; j++){
                if ((int)(presence[i][j])>1.0){
                    //affiche l'ID de la fourmi
                    System.out.print((int)(presence[i][j]));
                } else {
                    System.out.print("  ");
                }
            }
            //tire une ligne verticale
            System.out.println("|");
        }
    }

    public static void Init(){

        //variables de fonctionnement
        
        int toX; int toY;
        
        //on créé et init à 0f une table de présence
        //les 1 ou 0 pour la présence d'une fourmi
        //les 0.xxxx pour le taux de pheromone
        presence = new float[Gridlargeur][Gridhauteur];
        for (int i = 0; i<Gridhauteur; i++){
            for (int j = 0; j<Gridlargeur; j++){
                presence[i][j]=0;
            }
        }

        //on instancie les fourmis
        //on leur affecte une position en évitant le recouvrement
        nuee = new Fourmi[quantite];      
        for(int i=0;i<quantite;i++){
            do {
                toX = (int)(Math.random()*Gridlargeur);
                toY = (int)(Math.random()*Gridhauteur);
            }while (presence[toX][toY]>=1.0f);
            //presence prend l'id de la fourmi
            nuee[i]= new Fourmi(complexity,Gridlargeur,Gridhauteur);
            nuee[i].UpdatePositions(toX, toY, 0);
            presence[toX][toY]=presence[toX][toY]+i;   
        }
    }

    //modifier les positions selon les neurones output
    public static void Animer(int step){
        int x;
        int y;
        int newx;
        int newy;
        for (int i=0; i<quantite; i++){
            //lire la position et évaluer la prochaine
            x=(int)(nuee[i].Lx);
            y=(int)(nuee[i].Ly);

            //on teste si on dépasse le déclencheur 0.5 sur MX ou MY
            //si oui on décale de 1 dans la direction add hoc
            if      (nuee[i].MX<-.5) { newx=x-1;}
            else if (nuee[i].MX>0.5) { newx=x+1;}
            else                     { newx=x;}

            if      (nuee[i].MY<-0.5){ newy=y-1;}
            else if (nuee[i].MY>0.5) { newy=y+1;}
            else                     { newy=y;}

            //on s'assure de ne pas sortir de la zone de jeu
            if (newx<0||newx>=Gridlargeur){ newx=x;}
            if (newy<0||newy>=Gridhauteur){ newy=y;}

            //si la future case est occupée, on ne bouge pas
            if (presence[newx][newy]>1.0f){
                newx=x;
                newy=y;
            }

            //màj la table des présences
            presence[x][y]-=i;
            nuee[i].UpdatePositions(x, y, step);
            presence[newx][newy]+=i;
        }
    }

    static void darwin(){
        //on tue les fourmis ne répondant pas à un critère
        //puis on reproduit autant de fourmi que manquantes
        //en choisissant un génome aléatoire

        //on élimine les fourmis sur la moitié du terrain
        for (int i=0; i<quantite; i++){
            if (nuee[i].Lx <Gridlargeur/2){
                nuee[i]=null;
            }
        }

        int alea;
        //on recrée des fourmis en copiant des survivantes
        for (int i=0; i<quantite; i++){
            if (nuee[i]==null){

                //on crée une nouvelle fourmi dans cette case
                nuee[i]= new Fourmi(complexity,Gridlargeur,Gridhauteur);

                //on choisi une donneuse parmi les existantes
                do{
                    alea=(int)(Math.random()*quantite);
                }while (nuee[alea]==null);
                
                //on clone la fourmi choisie
                //le getgene inclus la mutation possible
                for (int j=0;j<nuee[alea].genome.length;j++){
                    nuee[i].genome.chrom[j].setGene(nuee[alea].genome.chrom[j].reproduire(txDeMuta));
                }
            }
        }
    }

}
