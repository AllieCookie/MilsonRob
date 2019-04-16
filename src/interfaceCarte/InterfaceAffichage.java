package interfaceCarte;
import carte.* ;

import java.awt.*;

import javax.swing.*;



public class InterfaceAffichage {
	
	private JFrame frame ;
	private GridLayout layout ;
	private JPanel monPannel ; 
	
	private void createAndShowCarte(CarteImagee carteIm) {
	frame = new JFrame("Carte en temps statique");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setSize(600,	600);
	frame.setLocationRelativeTo(null);
	
	layout = new GridLayout(carteIm.getOrdonnees(),carteIm.getAbscisses(),0,0);
	monPannel = new JPanel(layout) ;
	
	for(int i =0 ; i< carteIm.getAbscisses() ; ++i) {
		for(int j =0 ; j< carteIm.getOrdonnees() ; ++j) {
			ImageIcon im = carteIm.getImageIconAt(i, j);
			JLabel image = new JLabel (im);
			monPannel.add(image);
		}
	}
		
	frame.add(monPannel);
	frame.setVisible(true);

	
	}
	
	
	public void refresh(CarteImagee carteIm) {
			
		frame.remove(monPannel);
		frame.repaint();
		
		monPannel = new JPanel(layout) ;
		for(int i =0 ; i< carteIm.getAbscisses() ; ++i) {
			for(int j =0 ; j< carteIm.getOrdonnees() ; ++j) {
				ImageIcon im = carteIm.getImageIconAt(i, j);
				JLabel image = new JLabel (im);
				monPannel.add(image);
			}
		}
				
		frame.add(monPannel);
		monPannel.repaint();
		monPannel.revalidate();
		frame.setVisible(true);
		
	}
	
	public void erase() {
		frame.remove(monPannel);
		frame.setVisible(true);
		frame.repaint();
	}
	
	public void affichage(CarteImagee ci) {
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				createAndShowCarte(ci);
			}
		});
	}
	
	public static void main (String[] args) {
		int absc =  2 ;
		int ord = 2 ;
		Carte myMap = new Carte(absc ,ord) ;
		Elements virageAGauche = new Elements(TypeElement.VirageBG) ;
		Elements virageADroite = new Elements(TypeElement.VirageBD) ; 
		Elements virageBasADroite = new Elements(TypeElement.VirageHD) ;
		Elements virageBasAGauche = new Elements(TypeElement.VirageHG) ;
		myMap.SetAt(0, 0, virageADroite);
		myMap.SetAt(0, 1, virageAGauche);
		myMap.SetAt(1, 0, virageBasADroite);
		myMap.SetAt(1, 1, virageBasAGauche);
		//CarteImagee CarteIm = new CarteImagee(myMap);
		
		Carte myMap2 = new Carte (7,7) ;
		for(int i=0 ; i<7 ; ++i) {
			for(int j=0 ; j<7 ; ++j) {
				myMap2.SetAt(i, j, virageBasAGauche);
			}
		}
		CarteImagee CarteIm2 = new CarteImagee(myMap2) ;
		InterfaceAffichage af = new InterfaceAffichage();
		javax.swing.SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				af.createAndShowCarte(CarteIm2);
			}
		});

	}


}