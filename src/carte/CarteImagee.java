package carte;


import javax.swing.*;

public class CarteImagee {
	
	int absc ;
	int ord ;
	ImageIcon tabImages[][] ;
	
	public CarteImagee(Carte carte) {
		absc = carte.getAbscisses();
		ord = carte.getOrdonnees() ;
		tabImages = new ImageIcon[absc][ord] ;
		for(int i =0 ; i<absc ; ++i) {
			for(int j =0 ; j<ord ; ++j) {
				Elements e = carte.GetAt(i, j) ;
				switch (e.getType().toString()) {
					case "CroisementGDH" : tabImages[i][j] = new ImageIcon("./imagesCases/GDH.png"); ;
					break ;
					case "CroisementDHB" : tabImages[i][j] = new ImageIcon("./imagesCases/DHB.png"); ;
					break ;
					case "CroisementGDB" : tabImages[i][j] = new ImageIcon("./imagesCases/GDB.png"); ;
					break ;
					case "CroisementGHB" : tabImages[i][j] = new ImageIcon("./imagesCases/GHB.png"); ;
					break ;
					case "VirageBD" : tabImages[i][j] = new ImageIcon("./imagesCases/BD.png"); ;
					break ;
					case "VirageBG" : tabImages[i][j] = new ImageIcon("./imagesCases/BG.png"); ;
					break ;
					case "VirageHD" : tabImages[i][j] = new ImageIcon("./imagesCases/HD.png"); ;
					break ;
					case "VirageHG" : tabImages[i][j] = new ImageIcon("./imagesCases/HG.png"); ;
					break ;
					case "RouteDG" : tabImages[i][j] = new ImageIcon("./imagesCases/DG.png"); ;
					break ;
					case "RouteHB" : tabImages[i][j] = new ImageIcon("./imagesCases/HB.png"); ;
					break ;
				}
			}
		}
	}
	
	public ImageIcon getImageIconAt (int absc , int ord) {
		return tabImages[absc][ord] ;
	}
	
	public int getAbscisses() {
		return absc;
	}


	public int getOrdonnees() {
		return ord;
	}

	
}
