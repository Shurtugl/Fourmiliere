package fr.panceaux.brain;

import java.util.Scanner;

public class main {

    static Fourmi[] nuee = null;
    static int[][] presence = null;
    static int Gridlargeur = 30;
    static int Gridhauteur = 30;
    static int quantite = 10;
    static int complexity = 5;
    static int tour = 50;
    static int txDeMuta = 0;
    static int nbDeGeneration = 10;
    static boolean initiated = false;

    static Scanner sc = new Scanner(System.in);
    public static void main(String args[]) {
        

        //créer une instance de "frame" JAVA AWT
        //Affichage frame = new Affichage();
        int saisie;
        //MENU 
        do{
            

            System.out.println("\n*** FOURMIS ***");
            System.out.println(" 0 - QUITTER");
            System.out.println(" 1 - Paramètres");
            System.out.println(" 2 - Initialiser la population");
            System.out.println(" 3 - Montrer les génomes");
            System.out.println(" 4 - Lancer la Simulation");
            System.out.println(" 5 - Analyse d'un schéma neuronal");
            saisie = sc.nextInt();sc.nextLine();
            switch (saisie){
                case 0 : //quit
                    break;
                case 1 : //acceder aux modifications de parametres (methode)
                    Param();
                    break;
                case 2 : //appeler l'initialisation en suivant les paramètres
                    Init();
                    break;
                case 3 : //montrer le genome de toutes les fourmis
                    if (initiated) {
                        for(int i=0;i<quantite;i++){
                            System.out.println("\n fourmi "+(1+i)+ " en "+(int)(nuee[i].Lx)+","+(int)(nuee[i].Ly));
                            nuee[i].showGenome();
                        }
                    }else {
                        System.out.println("Attention, la population n'a pas encore été initialisée");
                    }
                    break;
                case 4 : //lancer la simulation avec affichage step by step 
                    if (initiated) {
                        for(int i=0;i<quantite;i++){
                            System.out.println("\n fourmi "+i+ " en "+(int)(nuee[i].Lx)+","+(int)(nuee[i].Ly));
                            nuee[i].showGenome();
                        }
                        for (int gen = 0; gen<nbDeGeneration; gen++){
                            System.out.println("\n** GENERATION "+gen+" **");
                            Afficher();
                            System.out.println("Ã©tapes : ");
                            for (int tr=0;tr<tour;tr++){
                                for (int i=0;i<quantite;i++){
                                    nuee[i].Pense();
                                }
                                Animer(tr);
                                System.out.print(" "+ tr);
                            }
                            Afficher();
                            Darwin();
                        }
                        for(int i=0;i<quantite;i++){
                            System.out.println("\n fourmi "+(1+i)+ " en "+(int)(nuee[i].Lx)+","+(int)(nuee[i].Ly));
                            nuee[i].showGenome();
                        }
                    }else {
                        System.out.println("Attention, la population n'a pas encore été initialisée");
                    }break;
                case 5 :   //afficher les infos d'une fourmi + son état avant/après 
                           //appliqué à un clone sujet pour ne pas interférer
                    if(initiated) {
                        System.out.println("Quelle fourmi parmi les "+quantite+"souhaitez vous analyser ?");
                        saisie = sc.nextInt();sc.nextLine();
                        if (saisie<0 || saisie>quantite)saisie = 0;
                        Fourmi sujet = nuee[saisie];
                        
                        System.out.println("***Fourmi "+saisie+" avant le premier step : ");
                        sujet.showState();
                        System.out.println("\n\n***Fourmi "+saisie+" après "+tour+" steps  :");
                        for (int i = 0; i<tour;i++) {sujet.Pense();} 
                        sujet.showState();
                        sujet.showGenome();
                    }else {
                        System.out.println("Attention, la population n'a pas encore été initialisée");
                    }
            }
            
        }while(saisie != 0);

       
    System.out.println("*** Merci d'avoir joué avec nous! ***");
    System.out.println("***                               ***");
    System.out.println("*************************************\n\n");
    sc.close();
    }

    //montrer un tableau ascii avec les Id de chaque fourmi
    public static void Afficher(){
        System.out.println();
        //tire une ligne horizontale
        for (int i =0; i<Gridlargeur+2;i++){
            System.out.print("__");
            
        }
        System.out.println();
        //parcours la zone
        for (int i = 0; i<Gridhauteur; i++){
            System.out.print("| ");
            for (int j = 0; j<Gridlargeur; j++){
                if ((presence[i][j])>0){
                    //affiche l'ID de la fourmi
                    System.out.print((presence[i][j]));
                } else {
                    System.out.print("  ");
                }
            }
            //tire une ligne verticale
            System.out.println(" |");
        }
    }

    public static void Param() {
        int saisie;
        boolean modified = false;
        do{
        System.out.println("1 - Zone de jeu : L "+Gridlargeur+", H "+Gridhauteur);
        System.out.println("2 - Fourmis : "+quantite+" ayant chacune "+complexity+" neurones.");
        System.out.println("3 - Chances de mutation : "+txDeMuta);
        System.out.println("4 - Simulation : "+nbDeGeneration+" generation de "+tour+" steps.");
        System.out.print("5 - Revenir au menu principal.");
        saisie = sc.nextInt();sc.nextLine();
        switch (saisie){
            case 1 :
                System.out.println("Spécifiez la zone de jeu : (largeur hauteur)");
                Gridlargeur = sc.nextInt(); Gridhauteur=sc.nextInt(); modified = true;break;
            case 2 :
                System.out.println("Spécifiez les détails de fourmis : (nombre nombre_de_neurone)");
                quantite = sc.nextInt(); complexity = sc.nextInt(); modified = true;break;
            case 3 :
                System.out.println("Implément de mutation aléatoire (%) :");
                txDeMuta = sc.nextInt();
                if (txDeMuta >100) txDeMuta=100; if (txDeMuta<0) txDeMuta=0;modified = true;break;
            case 4 : 
                System.out.println("Spécifiez la longueur de la simulation : (generation steps)");
                nbDeGeneration = sc.nextInt(); tour = sc.nextInt();modified = true;break;              
            }
        }while (saisie != 5);
        if (modified) {
            Init();
            }
    }
    
    public static void Init(){
        
        System.out.println("Initialisation de "+quantite+" fourmis, génération 0");
        //variables de fonctionnement
        
        int toX; int toY;
        
        //on crée et init à 0f une table de présence
        //
        presence = new int[Gridlargeur][Gridhauteur];
        for (int i = 0; i<Gridhauteur; i++){
            for (int j = 0; j<Gridlargeur; j++){
                presence[i][j]=0;
            }
        }

        //on instancie les fourmis
        //on leur affecte une position en Ã©vitant le recouvrement
        nuee = new Fourmi[quantite];      
        for(int i=0;i<quantite;i++){
            do {
                toX = (int)(Math.random()*Gridlargeur);
                toY = (int)(Math.random()*Gridhauteur);
            }while (presence[toX][toY]>=1);
            //presence prend l'id de la fourmi
            nuee[i]= new Fourmi(complexity,Gridlargeur,Gridhauteur);
            nuee[i].UpdatePositions(toX, toY, 0);
            presence[toX][toY]=presence[toX][toY]+i;   
        }
        initiated = true;
    }

    //modifier les positions selon les neurones output
    public static void Animer(int step){
        int x;
        int y;
        int newx;
        int newy;
        for (int i=0; i<quantite; i++){
            //lire la position et Ã©valuer la prochaine
            x=(int)(nuee[i].Lx);
            y=(int)(nuee[i].Ly);

            //on teste si on dÃ©passe le dÃ©clencheur 0.5 sur MX ou MY
            //si oui on dÃ©cale de 1 dans la direction add hoc
            if      (nuee[i].MX<-.5) { newx=x-1;}
            else if (nuee[i].MX>0.5) { newx=x+1;}
            else                     { newx=x;}

            if      (nuee[i].MY<-0.5){ newy=y-1;}
            else if (nuee[i].MY>0.5) { newy=y+1;}
            else                     { newy=y;}

            //on s'assure de ne pas sortir de la zone de jeu
            if (newx<0||newx>=Gridlargeur){ newx=x;}
            if (newy<0||newy>=Gridhauteur){ newy=y;}

            //si la future case est occupÃ©e, on ne bouge pas
            if (presence[newx][newy]>=1){
                newx=x;
                newy=y;
            }

            //mÃ j la table des prÃ©sences
            presence[x][y]-=i;
            nuee[i].UpdatePositions(x, y, step);
            presence[newx][newy]+=i;
        }
    }

    static void Darwin(){
        //on tue les fourmis ne répondant pas à un critère
        //puis on reproduit autant de fourmi que manquantes
        //en choisissant un gÃ©nome alÃ©atoire

        //on Ã©limine les fourmis sur la moitié du terrain
        System.out.println("décès des fourmis no : ");
        for (int i=0; i<quantite; i++){
            if (nuee[i].Lx <Gridlargeur/2){
                presence[(int)(nuee[i].Lx)][(int)(nuee[i].Ly)]-=i;
                nuee[i]=null;
                System.out.print((1+i)+" ");
                
            }
        }

        int alea;
        int toX; int toY;
        //on recrée des fourmis en copiant des survivantes
        for (int i=0; i<quantite; i++){
            if (nuee[i]==null){

                //on crée une nouvelle fourmi dans une case vide
                do {
                    toX = (int)(Math.random()*Gridlargeur);
                    toY = (int)(Math.random()*Gridhauteur);
                }while (presence[toX][toY]>=1);
                //presence prend l'id de la fourmi
                nuee[i]= new Fourmi(complexity,Gridlargeur,Gridhauteur);
                nuee[i].UpdatePositions(toX, toY, 0);
                presence[toX][toY]=presence[toX][toY]+i;

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
