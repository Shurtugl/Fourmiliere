package fr.panceaux.brain;

public class Genome {
    //objet contenant une liste de gènes
    //le nombre de gènes est une variable définie exterieurement
    public Chromosome[] genome;
    
    //constructor à longueur variable
    public Genome(int nbchro){
        genome= new Chromosome[nbchro];
    }
    

}
