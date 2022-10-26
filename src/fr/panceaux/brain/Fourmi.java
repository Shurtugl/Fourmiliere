package fr.panceaux.brain;

public class Fourmi {

	/* Objet poss�dant un micro r�seau neuronal,
	 * pour le d�placer au sein d'une simulation.
	 * Tient compte de multiple param�tre selon un
	 * g�nome qui d�fini ses connexions nerveuses
	 */

	//---- 21 entr�es (-1.0,1.0)f : ----

	float BD  = 0; 	//plus proche fronti�re
	float BDx = 0; 	//distance � la fronti�re X
	float BDy = 0; 	//distance � la fronti�re Y
	
	float Bfd = 0; 	//bloquage par autrui sur l'avant
	float Blr = 0; 	//bloquage par autrui sur les cot�s
	float LBf = 0; 	//bloquage total sur l'avant (longue**)
	
	float Gen = 0; 	//similarit� g�n�tique au plus proche voisin
	float Age = 0; 	//age au sein de cette simulation

	float LMx = 0; 	//dernier mouvement sur l'axe X
	float LMy = 0; 	//dernier mouvement sur l'axe Y
	float Lx  = 0; 	//position sur l'axe X
	float Ly  = 0; 	//position sur l'axe Y
	
	float Plr = 0; 	//population sur les cot�s (variation)
	float Pfd = 0; 	//population sur l'avant (variation)
	float LPf = 0; 	//population totale sur l'avant (longue**)
	float Pop = 0; 	//densit� de population toute la map
	
	float Rnd = 0;  //entree al�atoire
	float Osc = 0;	//entree oscillante
	
	float Slr = 0;	//pheromone sur les cot�s (variation)
	float Sfd = 0;	//pheromone sur l'avant (variation)
	float Sg  = 0;	//pheromone totale
	
	
	//---- 11 sorties (-1.0,1.0)f : ---- 
	
	float MY  = 0; 	//mouvement sur l'axe X
	float MX  = 0;	//mouvement sur l'axe Y
	float MRL = 0;	//mouvement vers un cot�
	float Mrv = 0;	//mouvement vers l'arriere
	float Mfd = 0;	//mouvement vers l'avant
	float Mrn = 0;	//mouvement al�atoire
	
	float LPD = 0;	//distance des longues entrees **
	float OSC = 0;	//periode d'oscillation
	
	float SG  = 0;	//cr�er pheromone
	float Res = 0;	//r�activit�, caf�ine

	float Kil = 0;	//tuer les voisins

	

}
