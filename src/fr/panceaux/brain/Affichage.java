package fr.panceaux.brain;
//modif
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JLabel;

public class Affichage extends Frame{
    static JLabel room = new JLabel("\n ***FOURMILIERE*** \n    BIENVENUE");
    static TextField text = new TextField();
    static Button btn_A = new Button("Paramètres");
    static Button btn_B = new Button("Créer");
    static Button btn_C = new Button("Génomes");
    static Button btn_D = new Button("Simuler");
    static Button btn_E = new Button("Details");
    static Button btn_F = new Button("Animation");
    
    //Initialiser par constructor
    Affichage() {  
        //variables d'affichage
        int Gridlargeur = 400;
        int Gridhauteur = 300;

        int btnW = 100;
        int btnH = 45;
        int spc = 15;
        int haut = 3*Gridhauteur+3*spc+btnH;
        int large = 3*Gridlargeur+2*spc;
        

        //Ajouter les boutons, situïés en bas à droite
        btn_A.setBounds(btnW*2+spc,btnH+spc,btnW,btnH); 
        btn_B.setBounds(btnW*3+spc,btnH+spc,btnW,btnH);
        btn_C.setBounds(btnW*4+spc,btnH+spc,btnW,btnH);
        btn_D.setBounds(btnW*5+spc,btnH+spc,btnW,btnH);
        btn_E.setBounds(btnW*6+spc,btnH+spc,btnW,btnH);
        btn_F.setBounds(btnW*7+spc,btnH+spc,btnW,btnH);
        add(btn_A);
        add(btn_B);
        add(btn_C);
        add(btn_D);
        add(btn_E);
        add(btn_F);
        
        /*Ajouter une toile de peinture
        MyCanvas canv = new MyCanvas();
        canv.setBounds(spc,spc,large-2*spc,haut-3*spc-btnH);
        add(canv);*/
        
        //ajoute un champ Label spécifique
        JLabel room = new JLabel("\n ***FOURMILIERE*** \n    BIENVENUE");
        room.setBounds(spc,spc*2+btnH,large-spc,haut-(2*spc+btnH)-spc);
        add(room);
        
        //Ajouter un champ de texte
        text.setBounds(spc,spc,btnW*2,btnH);
        add(text);
               
        //paramÃ¨tres de la fenÃªtre
        setSize(large,haut);  
        setTitle("FOURMILIERE par Shurtugl");
        setLayout(null);    
        setVisible(true); 
        
        
        //permettre de refermer par la croix rouge
        addWindowListener (new WindowAdapter() {    
            public void windowClosing (WindowEvent e) {    
                dispose();    
            }    
        });
        
         
        //affectation des effets des boutons
        btn_A.addActionListener(new ActionListener(){  //Paramètres
            public void actionPerformed(ActionEvent e){  
                Parametres frame = new Parametres();
            }  
        }); 
        btn_B.addActionListener(new ActionListener(){  //Créer
            public void actionPerformed(ActionEvent e){  
                main.Init();
            }  
        }); 
        btn_C.addActionListener(new ActionListener(){  //Génomes
            public void actionPerformed(ActionEvent e){  
                if (main.initiated) {
                    main.montrerGenomes();
                }else {
                    text.setText("Attention, la population n'a pas encore été initialisée");
                }
            }  
        });
        btn_D.addActionListener(new ActionListener(){  //Simuler
            public void actionPerformed(ActionEvent e){  
                if (main.initiated) {
                    main.generation();
                }else {
                    text.setText("Attention, la population n'a pas encore été initialisée");
                }
            }  
        });
        btn_E.addActionListener(new ActionListener(){  //Détails
            public void actionPerformed(ActionEvent e){  
                if(main.initiated) {
                    room.setText("Quelle fourmi parmi les "+main.quantite+"souhaitez vous analyser ?");
                    //saisie = sc.nextInt();sc.nextLine();
                    //main.montrerDetails(saisie);
                 }else {
                     text.setText("Attention, la population n'a pas encore été initialisée");
                 }
            }  
        });
        btn_F.addActionListener(new ActionListener(){  //Animer
            public void actionPerformed(ActionEvent e){  
                if(main.initiated) {
                    room.setText("On souhaite voir une étape sur combien ?");
                    //saisie = sc.nextInt();sc.nextLine();
                    //step(saisie);
                    
                }else {
                    text.setText("Attention, la population n'a pas encore été initialisée");
                }
            }  
        });
            
    }
    

    /*
    etiket = new JLabel("texte initial");
    16.              c.add(etiket);
    17.              setLocationRelativeTo(this.getParent()); 
    18.              bouton = new JButton("Modifier etiket");
    19.              c.add(bouton);
    
        public void actionPerformed(ActionEvent a) 
    25.          {
    26.               etiket.setText("texte final");
    27.                
    28.          }   
    */
    
    //créer une class qui aura des attributs de toile de peintre
    class MyCanvas extends Canvas {   
        //constructor  
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

        //on crÃ©e un objet point
        class Cercle {
            
            public void paint(Graphics g){ 
                g.setColor(Color.red);
                //dessin d'un rond rayon r
                g.fillOval(x,y,r,r);
            }
        }
    }
    
}
