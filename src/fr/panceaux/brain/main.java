package fr.panceaux.brain;

import java.util.Scanner;

public class main {

    static Fourmi[] nuee = null;
    static float[][] presence = null;
    static int Gridlargeur = 30;
    static int Gridhauteur = 30;
    static int quantite = 10;
    static int complexity = 5;
    static int tour = 100;

    public static void main(String args[]) {
        

        //crÃ©er une instance de "frame" JAVA AWT
        //Affichage frame = new Affichage();

        //MENU 
        Scanner sc = new Scanner(System.in);
        int saisie;
        do{
            System.out.println("\n*** FOURMIS ***");
            System.out.println(" 0 - QUITTER");
            System.out.println(" 1 - ParamÃ¨tres");
            System.out.println(" 2 - Lancer la Simulation");
            System.out.println(" 3 - Montrer les gÃ©nomes");
            saisie = sc.nextInt();
            switch (saisie){
                case 0 : //quit
                    break;
                case 1 : //parametres
                    break;
                case 2 : //lancer pour x tours
                    Init();
                    for (int tr=0;tr<tour;tr++){
                        Afficher();
                        Animer(tr);
                        //effacer la console
                        System.out.print("\033[H\033[2J");
                        System.out.flush();
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
        for (int i = 0; i<Gridhauteur; i++){
            for (int j = 0; j<Gridlargeur; j++){
                if ((int)(presence[i][j])>1.0){
                    System.out.print((int)(presence[i][j]));
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
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
            newx=(int)(x+nuee[i].MX);
            newy=(int)(y+nuee[i].MY);
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

    /* clear console
     * 
     */
}
