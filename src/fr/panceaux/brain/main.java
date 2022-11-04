package fr.panceaux.brain;

import java.util.Scanner;

public class main {

    static Fourmi[] nuee = null;
    static int[][] presence = null;
    static float[][] odeur = null;
    static int Gridlargeur = 100;
    static int Gridhauteur = 100;
    static int quantite = 1000;
    static int complexity = 20;
    static int nbNeurones = 3;
    static int tour = 100;
    static int txDeMuta = 15;
    static int nbDeGeneration = 10;
    static boolean initiated = false;
    static boolean verbose = false;
    static boolean console = false;


    static Scanner sc = new Scanner(System.in);
    public static void main(String args[]) {

        //créer une instance de "frame" JAVA AWT
        if (!console) {
        Affichage frame = new Affichage();
        }else {
                //MENU 
            int saisie;
            System.out.println("\n*** FOURMIS : UN GENERATEUR DE RESEAUX NEURONAUX BIOSIMULES ***");
           
            do{
                //propositions des options, secu sur la saisie
                do {
                    System.out.println("\n  -- menu principal --");
                    System.out.println(" 0 - QUITTER");
                    System.out.println(" 1 - Paramètres");
                    System.out.println(" 2 - Initialiser la population");
                    System.out.println(" 3 - Montrer les génomes");
                    System.out.println(" 4 - Lancer la Simulation");
                    System.out.println(" 5 - Analyse d'un schéma neuronal");
                    System.out.print(" 6 - Analyse de mouvement ..");
                    saisie = sc.nextInt();sc.nextLine();
                }while (saisie <0 || saisie>6);
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
                            montrerGenomes();
                        }else {
                            System.out.println("Attention, la population n'a pas encore été initialisée");
                        }
                        break;
                    case 4 : //lancer la simulation avec affichage step by step 
                        if (initiated) {
                            if (verbose) montrerGenomes();
                            generation();
                            if (verbose) montrerGenomes();
                        }else {
                            System.out.println("Attention, la population n'a pas encore été initialisée");
                        }break;
                    case 5 :   //afficher les infos d'une fourmi + son état avant/après 
                               //appliqué à un clone sujet pour ne pas interférer
                        if(initiated) {
                           System.out.println("Quelle fourmi parmi les "+quantite+"souhaitez vous analyser ?");
                           saisie = sc.nextInt();sc.nextLine();
                           montrerDetails(saisie);
                        }else {
                           System.out.println("Attention, la population n'a pas encore été initialisée");
                        }
                    case 6 : //affichage d'une génération intégrale
                        if(initiated) {
                            System.out.println("On souhaite voir une étape sur combien ?");
                            saisie = sc.nextInt();sc.nextLine();
                            step(saisie);
                            
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
    }

    protected static void montrerDetails(int id) {
        if (id<0 || id>quantite)id = 0;
        Fourmi sujet = nuee[id];
        System.out.println("***Fourmi "+(id+1)+" avant le premier step : ");
        sujet.showState();
        System.out.println("\n\n***Fourmi "+id+" après "+tour+" steps  :");
        for (int i = 0; i<tour;i++) {sujet.Pense();} 
        sujet.showState();
        sujet.showGenome();
        
    }

    protected static void step(int forEvery) {
        System.out.println("étapes : ");
        for (int tr=0;tr<tour;tr++){
            for (int i=0;i<quantite;i++){
                nuee[i].Pense();
            }
            Animer(tr);
            System.out.print(" "+ tr);
            if (tr%forEvery==0)Afficher();
            
        }
    }
    protected static void generation() {
        for (int gen = 0; gen<nbDeGeneration; gen++){
            System.out.println("\n** GENERATION "+gen+" **");

            step(1);

            Darwin();
        }
    }

    protected static void montrerGenomes() {

            for(int i=0;i<quantite;i++){
                System.out.println("\n fourmi "+(1+i)+ " en "+(int)(nuee[i].Lx)+","+(int)(nuee[i].Ly));
                nuee[i].showGenome();
            }
    }

    //montrer un tableau ascii avec les Id de chaque fourmi
    protected static void Afficher(){
        String chaine="";
        if (!console){
            //tire une ligne horizontale
            System.out.println();
            for (int i =0; i<Gridlargeur+2;i++){
                System.out.print("__");
            }
            System.out.println();
        }
        
        //parcours la zone
        for (int j = 0; j<Gridhauteur; j++){
            if(!console)System.out.print("| ");
            for (int i = 0; i<Gridlargeur; i++){
                if ((presence[i][j])>0){
                    //affiche l'ID de la fourmi
                    if (verbose)System.out.print((presence[i][j]));
                    else System.out.print("* ");
                } else {
                    System.out.print("  ");
                }
            }
            //tire une ligne verticale
            System.out.println(" |");
        }
    }

    protected static void Param() {
        int saisie;
        boolean modified = false;
        do{
            System.out.println("\n  -- paramètres --");
            System.out.println(" 1 - Zone de jeu : L "+Gridlargeur+" x H "+Gridhauteur+" .");
            System.out.println(" 2 - Fourmis : "+quantite+" ayant chacune "+nbNeurones+" neurones internes.");
            System.out.println(" 3 - Génétique : "+txDeMuta+"/100 chance de muter sur "+complexity+" genes(=synapses).");
            System.out.println(" 4 - Simulation : "+nbDeGeneration+" generation de "+tour+" steps.");
            System.out.print(" 5 - Verbose : ");if (verbose)System.out.println(" affichage complet.");else System.out.println("affichage simple.");;
            System.out.print(" 6 - Revenir au menu principal  ..");
            saisie = sc.nextInt();sc.nextLine();
            switch (saisie){
                case 1 :
                    System.out.print("Spécifiez la zone de jeu : (largeur hauteur)..");
                    Gridlargeur = sc.nextInt(); Gridhauteur=sc.nextInt(); modified = true;break;
                case 2 :
                    System.out.print("Spécifiez les détails de fourmis : (nombre nombre_neurones)..");
                    quantite = sc.nextInt(); nbNeurones = sc.nextInt(); modified = true;break;
                case 3 :
                    System.out.print("Implément de mutation aléatoire : (%)..");
                    txDeMuta = sc.nextInt(); complexity=sc.nextInt();
                    if (txDeMuta >100) txDeMuta=100; if (txDeMuta<0) txDeMuta=0;modified = true;break;
                case 4 : 
                    System.out.print("Spécifiez la longueur de la simulation : (generation steps)..");
                    nbDeGeneration = sc.nextInt(); tour = sc.nextInt();modified = true;break;              
                case 5 :
                    System.out.print("Détailler l'affichage ? 1 / 0 ..");
                    verbose = (sc.nextInt()==1) ;break;
            }
            sc.nextLine();   
        }while (saisie != 6);
        if (modified) {
            Init();
            }
    }
    
    protected static void Init(){
        if (console)
            Affichage.text.setText("Initialisation de "+quantite+" fourmis, génération 0");
        else
            System.out.println("Initialisation de "+quantite+" fourmis, génération 0");
        //variables de fonctionnement
        
        int toX; int toY;
        
        //on crée et init à 0 une table de présence
        //
        presence = new int[Gridlargeur][Gridhauteur];
        for (int i = 0; i<Gridlargeur; i++){
            for (int j = 0; j<Gridhauteur; j++){
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
            }while (presence[toX][toY]!=0);
            //presence prend l'id de la fourmi
            nuee[i]= new Fourmi(complexity,Gridlargeur,Gridhauteur);
            nuee[i].setBrainSize(nbNeurones);
            nuee[i].UpdatePositions(toX, toY);
            presence[toX][toY]=i+1;   
        }
        initiated = true;
    }

    //modifier les positions selon les neurones output
    protected static void Animer(int step){
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
            presence[x][y]=0;
            nuee[i].UpdatePositions(newx, newy);
            presence[newx][newy]=(i+1);
        }
    }

    protected static void Darwin(){
        //on tue les fourmis ne répondant pas à un critère
        //puis on reproduit autant de fourmi que manquantes
        //en copiant un génome aléatoire
        int nbMorts=0;
        if (verbose)System.out.println("décès naturels :");
        for (int i=0; i<quantite; i++){
          //reset des presences dans l'espace
            presence[(int)(nuee[i].Lx)][(int)(nuee[i].Ly)]=0;
          //on Ã©limine les fourmis sur la moitié du terrain
            if (nuee[i].Lx <Gridlargeur/2){
                nuee[i]=null;  
                if (verbose)System.out.print((i+1)+" ");
                nbMorts++;
            }
        }
        System.out.println();
        int alea; int toX; int toY;
        
        //on recrée des fourmis en copiant des survivantes
        for (int i=0; i<quantite; i++){
            
            if(nuee[i]==null){
                //on crée une nouvelle Fourmi dans la case vide
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
                if (verbose) System.out.print("("+(alea+1)+")->"+(i+1)+" ");
            }
            
            
            //pour toutes fourmis survivantes ou nouvelles-nées, on spawn à une nouvelle position
            do {
                toX = (int)(Math.random()*Gridlargeur);
                toY = (int)(Math.random()*Gridhauteur);
            }while (presence[toX][toY]>=1);
            nuee[i].UpdatePositions(toX, toY);
            //set de presence
            presence[toX][toY]=(i+1);
            
        }
         System.out.println("                                         Nombre de tués remplacés : "+nbMorts);
    }

}
