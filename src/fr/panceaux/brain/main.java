package fr.panceaux.brain;


public class main {

    static Fourmi[] nuee = null;
    static float[][] presence = null;
    static int Gridlargeur = 90;
    static int Gridhauteur = 90;
    static int quantite = 100;
    static int complexity = 5;

    public static void main(String args[]) {
        
    
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

            nuee[i]= new Fourmi(complexity);
            nuee[i].UpdatePositions(Gridlargeur, Gridhauteur, toX, toY, 0);
            presence[toX][toY]=presence[toX][toY]+1.0f;   
        }

        //créer une instance de "frame" JAVA AWT
        //Affichage frame = new Affichage();


        

        for(int i=0;i<quantite;i++){
            System.out.println("\n fourmi "+i+ " en "+nuee[i].getX()+","+nuee[i].getY());
            nuee[i].showGenome();
        }
        
       
    }

    public static void Afficher(float[][] tableau,int larg,int haut){
        for (int i = 0; i<haut; i++){
            for (int j = 0; j<larg; j++){
                if ((int)(tableau[i][j])==0){
                    System.out.print(" ");
                } else if ((int)(tableau[i][j])==1){
                    System.out.print("#");
                }else {
                    System.out.print("?");
                    
                } 
            }
            System.out.println();
        }
    }


    /* clear console
     * System.out.print("\033[H\033[2J");
        System.out.flush();
     */
}
