package fr.panceaux.brain;
//modif
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Affichage extends Frame{
      
    //Initialiser par constructor
    Affichage() {  
        //variables d'affichage
        int Gridlargeur = 300;
        int Gridhauteur = 300;

        int btnW = 110;
        int btnH = 30;
        int spc = 15;
        int haut = 3*Gridhauteur+3*spc+btnH;
        int large = 3*Gridlargeur+2*spc;
        

        //Ajouter les 4 boutons, situ�s en bas � droite
        Button btn_OK = new Button("OK");
        Button btn_A = new Button("Bouger la nuee");
        Button btn_B = new Button("Afficher la nuee");
        Button btn_C = new Button("Créer une nuee");
        btn_OK.setBounds(large-(btnW+spc),haut-btnH-spc,btnW,btnH); 
        btn_A.setBounds(large-2*(btnW+spc),haut-btnH-spc,btnW,btnH); 
        btn_B.setBounds(large-3*(btnW+spc),haut-btnH-spc,btnW,btnH); 
        btn_C.setBounds(large-4*(btnW+spc),haut-btnH-spc,btnW,btnH); 
        add(btn_OK);
        add(btn_A);
        add(btn_B);
        add(btn_C);
        
        //Ajouter une toile de peinture
        MyCanvas canv = new MyCanvas();
        canv.setBounds(spc,spc,large-2*spc,haut-3*spc-btnH);
        add(canv);

        
        //Ajouter un champ de texte
        TextField text = new TextField();
        text.setBounds(spc,haut-btnH-spc,btnW*2,btnH);
        add(text);
               
        //paramètres de la fenêtre
        setSize(large,haut);  
        setTitle("Fenêtre de prototype");
        setLayout(null);    
        setVisible(true); 
        
        
        //permettre de refermer par la croix rouge
        addWindowListener (new WindowAdapter() {    
            public void windowClosing (WindowEvent e) {    
                dispose();    
            }    
        });
        
        /* BOUTONS
        //affectation des effets des boutons
        btn_OK.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                    text.setText("Bravo ! Tu as cliqu� sur OK");
            }  
        }); 
        btn_A.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                                        ;
            }  
        }); 
        btn_B.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                    ;canv.repaint();
            }  
        }); 
        btn_C.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                    ;canv.repaint();
            }  
        });    */
            
    }
    
    //créer une class qui aura des attributs de toile de peintre
    class MyCanvas extends Canvas {   
        
        //idem class constructor  
        public MyCanvas() {    
            setBackground (Color.GRAY);    
            setSize(100, 100);    
        }    
        
        public int x = 0;
        public int y = 0;
        public int r = 3;
            
        public void setPos(int setX,int setY){
            this.x =setX;
            this.y =setY;
        }    


        //on crée un objet point
        class Cercle {
            
            public void paint(Graphics g){ 
                g.setColor(Color.red);
                //dessin d'un rond rayon r
                g.fillOval(x,y,r,r);
            }
        }
        
                    
    }   
    
    
     
}
