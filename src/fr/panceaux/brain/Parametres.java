package fr.panceaux.brain;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JLabel;

public class Parametres extends Frame{
    static JLabel room = new JLabel("\n ***FOURMILIERE*** \n    BIENVENUE");
    static TextField txt_largeur =          new TextField("Largeur du terrain");
    static TextField txt_hauteur =          new TextField("Hauteur du terrain");
    static TextField txt_quantite =         new TextField("Nombre de Fourmis");
    static TextField txt_nbNeurones =       new TextField("Nombre de neurones internes");
    static TextField txt_txDeMuta =         new TextField("Taux de mutation");
    
    static TextField txt_complexity =       new TextField("Nombre deènes");
    static TextField txt_nbDeGeneration =   new TextField("Limite de Génération");
    static TextField txt_tour =             new TextField("Nombre d'étapes par génération");
    static TextField txt_verbose =          new TextField("Affichage Simplifié");
    static Button btn_Valid =               new Button("Valider les changements");

    Parametres() {
        //variables d'affichage
       
        int width = 120;
        int height = 30;
        int margeW = 140;
        int margeH = 45;
        

        //Ajouter les boutons, situïés en bas à droite
        txt_largeur.setBounds   (15,margeH,width,height);
        txt_hauteur.setBounds   (15+margeW,margeH,width,height);
        txt_quantite.setBounds  (15+2*margeW,margeH,width,height);
        txt_nbNeurones.setBounds(15+3*margeW,margeH,width,height);
        txt_txDeMuta.setBounds  (15+4*margeW,margeH,width,height);
        
        txt_complexity.setBounds        (15,2*margeH,width,height);
        txt_nbDeGeneration.setBounds    (15+margeW,2*margeH,width,height);
        txt_tour.setBounds              (15+2*margeW,2*margeH,width,height);
        txt_verbose.setBounds           (15+3*margeW,2*margeH,width,height);
        
        btn_Valid.setBounds             (15+4*margeW,2*margeH,width,height);


        add(txt_largeur);add(txt_hauteur);add(txt_quantite);add(txt_nbNeurones);add(txt_txDeMuta);
        add(txt_complexity);add(txt_nbDeGeneration);add(txt_tour);add(txt_verbose);add(btn_Valid);
        
        
        //ajoute un champ Label spécifique
        JLabel paramAffich = new JLabel(" ** Paramètres ** ");
        paramAffich.setBounds(15,2*margeH,6*margeW,45);
        add(paramAffich);

               
        //paramÃ¨tres de la fenÃªtre
        setSize(5*margeW+30,2*margeH+45+30);  
        setTitle("FOURMILIERE par Shurtugl");
        setLayout(null);    
        setVisible(true);
        
      //permettre de refermer par la croix rouge
        addWindowListener (new WindowAdapter() {    
            public void windowClosing (WindowEvent e) {    
                dispose();    
            }    
        });
        
        btn_Valid.addActionListener(new ActionListener(){  //Paramètres
            public void actionPerformed(ActionEvent e){
                main.Gridlargeur =      Integer.parseInt(txt_largeur.getText());
                main.Gridhauteur =      Integer.parseInt(txt_hauteur.getText());
                main.quantite =         Integer.parseInt(txt_quantite.getText());
                main.nbNeurones =       Integer.parseInt(txt_nbNeurones.getText());
                main.txDeMuta =         Integer.parseInt(txt_txDeMuta.getText());
                
                main.complexity =       Integer.parseInt(txt_complexity.getText());
                main.nbDeGeneration =   Integer.parseInt(txt_nbDeGeneration.getText());
                main.tour =             Integer.parseInt(txt_tour.getText());
                main.verbose =  (1==Integer.parseInt(txt_verbose.getText()));
                
                
                /*
                 * txt_largeur = new TextField("Largeur du terrain");
    static TextField txt_hauteur = new TextField("Hauteur du terrain");
    static TextField txt_quantite = new TextField("Nombre de Fourmis");
    static TextField txt_nbNeurones = new TextField("Nombre de neurones internes");
    static TextField txt_txDeMuta = new TextField("Taux de mutation");
    static TextField txt_complexity = new TextField("Nombre deènes");
    static TextField txt_nbDeGeneration = new TextField("Limite de Génération");
    static TextField txt_tour = new TextField("Nombre d'étapes par génération");
    static TextField txt_verbose*/
            }  
        }); 
    }
}
