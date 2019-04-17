package carte;

public class Carte {
	
	
	private int abscisses ;
	private int ordonnees ;
	private Elements coordonees[][] ;
	
	public Carte(int absc , int ord ) {
		abscisses = absc ;
		ordonnees = ord ;
		coordonees =  new Elements[abscisses][ordonnees] ;
	}
	
	
	public int getAbscisses() {
		return abscisses;
	}


	public int getOrdonnees() {
		return ordonnees;
	}

	

	public void SetAt (int absc , int ord , Elements e) {
		coordonees[absc][ord] = e ;
	}
	
	public Elements GetAt (int absc , int ord ) {
		return coordonees[absc][ord] ;
	}
}
