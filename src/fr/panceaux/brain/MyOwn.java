package fr.panceaux.brain;

public class MyOwn {
	
	//obtenir un aléatoire entre bornes
    public static float RandomFloat(float min, float max) {
        float a = (float) Math.random();
        float num = min + (float) Math.random() * (max - min);
        if(a < 0.5)
            return num;
        else
            return -num;
    }
    
    //fonction qui rend une valeur extremement 1 ou 0 ou gradient autour 0
    public static float Sigmoid(float x) {
        return (float) (1/(1+Math.pow(Math.E, -x)));
    }
    
    
}
