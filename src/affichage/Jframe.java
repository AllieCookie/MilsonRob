package affichage;

import javax.swing.*;
import java.awt.event.*;

public class Jframe extends JFrame {
	 public Jframe() {
		 super("titre de l'application");
		 WindowListener l = new WindowAdapter() {
		 public void windowClosing(WindowEvent e)
		 {System.exit(0);}};
		 addWindowListener(l);
		 ImageIcon img = new ImageIcon("tips.gif");
		 JButton bouton = new JButton("Mon bouton",img);
		 JPanel panneau = new JPanel();
		 panneau.add(bouton);
		 setContentPane(panneau);
		 setSize(200,100);
		 setVisible(true);
	 }
	 public static void main(String [] args){
	 JFrame frame = new Jframe();
	 }
}

 

