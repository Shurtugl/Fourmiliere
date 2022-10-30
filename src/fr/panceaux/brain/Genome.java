package fr.panceaux.brain;

public class Genome {
    //objet contenant une liste de 3 gènes sous forme d'allele binaire
    //le nombre d'allele est une variable définie exterieurement
    public Chromosome[] chrom;
    public int length;

    //constructor à longueur variable
    public Genome(int nbGenes){
        this.chrom = new Chromosome[nbGenes];
        for (int i =0; i<nbGenes;i++){
            this.chrom[i]=new Chromosome();
        }
        this.length= nbGenes;
    }
    
    //fournir la valeur des gènes input, output ou value
    protected int getGenes(int nbGene, char type){
        int gene=0;
        int start=0;
        int end=0;
        //affectation de start et end en fonction du gene recherché
        switch (type){
            case 'i' :              end=start+8; break;
            case 'o' : start=8;     end=start+8; break;
            case 'v' : start=2*8;   end=start+8; break;
            default : System.out.println("ERREUR : mauvaise définition du gene au chromosome "+chrom);
        }
        for (int i = start; i<end; i++){
                //le gene prend directement la valeur decimal à partir du binaire
                gene+=(Math.pow(i-start, 2))*this.chrom[nbGene].getgene(i);
            }
        
        return gene;
    }

}
