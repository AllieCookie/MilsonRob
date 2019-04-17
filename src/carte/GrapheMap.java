package carte;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.lang.model.element.Element;

public class GrapheMap {
	
	private Carte carte;
	
	//Pour chaque sommet on associe un chemin pour acceder a l'element voisin
	private HashMap<Elements, HashMap<String, Elements>> graphe;
	
	public GrapheMap(Carte cr) {
		carte =  cr;
		initGraphe();
	}
	
	/* Creation du graphe qui associe pour chaque element de la carte un ensemble de voisin */
	private void initGraphe() {
		
		graphe = new HashMap<Elements, HashMap<String, Elements>>();
		int ordMax = carte.getOrdonnees();
		int absMax = carte.getAbscisses();
		HashMap<String, Elements> he = new HashMap<String, Elements>();
		Elements e;
		
		for(int i=0;i<absMax;i++) {
			for(int j=0;j<ordMax;j++) {
				
				e = carte.GetAt(i, j);
				if(e.getType() != TypeElement.Vide){
					he = createVoisin(i, j);
					graphe.put(e, he);
				}	
			}
		}
		
	}
	
	
	public Carte getCarte() {
		return carte;
	}
	
	public List<Elements> getAllElemments(){
		
		ArrayList<Elements> al = new ArrayList<Elements>();
		int ordMax = carte.getOrdonnees();
		int absMax = carte.getAbscisses();
		Elements e;
		
		for(int i=0;i<absMax;i++) {
			for(int j=0;j<ordMax;j++) {
				
				e = carte.GetAt(i, j);
				if(e.getType() != TypeElement.Vide){
					al.add(e);
				}	
			}
		}
		
		return al;
	}

	private HashMap<String, Elements> createVoisin(int abs,int ord) {
		HashMap<String, Elements> he = new HashMap<String, Elements>();
		Elements e;
		
		int absMax = carte.getAbscisses() - 1;
		int ordMax = carte.getOrdonnees() - 1;
		
		if (abs != 0 ) {
			e = carte.GetAt(abs - 1, ord);
			if(e.getType() != TypeElement.Vide){
				he.put("s", e);
				//le.add(e);	
			}
		}
		
		if (abs != absMax ) {
			e = carte.GetAt(abs + 1, ord);
			if(e.getType() != TypeElement.Vide){
				he.put("u", e);
				//le.add(e);	
			}

		}
		
		if(ord != 0) {
			e = carte.GetAt(abs, ord - 1);
			if(e.getType() != TypeElement.Vide){
				he.put("l", e);
				//le.add(e);	
			}

		}
		if(ord != ordMax) {
			e = carte.GetAt(abs, ord + 1);
			if(e.getType() != TypeElement.Vide){
				he.put("r", e);
				//le.add(e);	
			}

		}
		
		//System.out.println(abs+","+ord+":"+he);
		return he;
		
	}
	
	//Renvoie une hashmap qui associe pour chaque direction un voisin de e
	public HashMap<String, Elements> getVoisins(Elements e) {
		return graphe.get(e);
	}
	
	public ArrayList<Elements> getVoisinsArray(Elements e) {
		HashMap<String, Elements> he = getVoisins(e);
		ArrayList<Elements> al = new ArrayList<Elements>();
			
		Set<String> keyList = he.keySet();
		for(String s : keyList) {
			al.add(he.get(s));

		}
		return al;
	}
	
	//Renvoie une hashmap qui associe pour chaque direction un voisin de e orienter par rapport a la case ou se trouvait le robot avant (voisin de e)
	//Renvoie null en cas de pb
	public  HashMap<String, Elements> getVoisinsWithPred(Elements e,Elements pred){
		HashMap<String, Elements> he = graphe.get(e);
		if (pred == null) {
			return he;
		}
		
		//Recherche de l'emplacement de l'etat precedent
		Set<String> keyList = he.keySet();
		String instruPred = "";
		for(String s : keyList) {
			if(he.get(s)==pred) {
				instruPred = s;
			}
		}

		HashMap<String, Elements> newhe = new HashMap<String, Elements>();
		
		for(String s : keyList) {
			
			switch (instruPred) {
			
			case "r":
				switch (s) {
				case "r":	newhe.put("u", he.get("r")); break;
				case "l":	newhe.put("s", he.get("l")); break;				
				case "s":	newhe.put("r", he.get("s")); break;				
				case "u":	newhe.put("l", he.get("u")); break;			
				}
				break;
				
			case "l":
				switch (s) {
				case "r":	newhe.put("s", he.get("r")); break;
				case "l":	newhe.put("u", he.get("l")); break;				
				case "s":	newhe.put("l", he.get("s")); break;				
				case "u":	newhe.put("r", he.get("u")); break;			
				}	
				break;
			case "s":
				switch (s) {
				case "r":	newhe.put("l", he.get("r")); break;
				case "l":	newhe.put("r", he.get("l")); break;				
				case "s":	newhe.put("u", he.get("s")); break;				
				case "u":	newhe.put("s", he.get("u")); break;			
				}	
				break;
			case "u":
				newhe.put(s, he.get(s)); 
				break;
					
			}
		
		}
		//System.out.println("E: "+e+" Pred: "+pred+"He:"+he+" newHe: "+newhe);
		return newhe;
		
	}
	
	//Dit si le deplacement est possible, c'est a dire si aller dans tel direction est permis par le grahe
	//Utilisation pour le depart quand on ne connait pas de pred
	public boolean deplacementPossible(Elements depart,String direction) {
		return graphe.get(depart).containsKey(direction);
	}
	
	/**
	Dit si le deplacement est possible, c'est a dire si aller dans tel direction est permis par le grahe en prenant
	Un predeceseur afin d'orienter les choix possible, si pred = null -> deplacement possible
	Utilisation qui marche pour tout les cas, utilisation par défaut 
	**/
	public boolean deplacementPossibleWithPred(Elements depart,String direction,Elements pred) {
		if (pred == null) {
			return deplacementPossible(depart,direction);
		}
		return getVoisinsWithPred(depart, pred).containsKey(direction);
	}
	
	public Elements deplacement(Elements depart, String direction) {
		return getVoisins(depart).get(direction);
	}
	
	public Elements deplacementPred(Elements depart, String direction, Elements pred) {
		return getVoisinsWithPred(depart, pred).get(direction);
	}
	
	//Renvoie une hashmap qui associe pour chaque direction un voisin de e orienter par rapport a l'orientation du robot actuel (voisin de e)
	public  HashMap<String, Elements> getVoisinsWithOrient(Elements e,String st){
		HashMap<String, Elements> he = graphe.get(e);
		
		//Recherche de l'emplacement de l'etat precedent
		Set<String> keyList = he.keySet();
		String instruPred = st;


		HashMap<String, Elements> newhe = new HashMap<String, Elements>();
		
		for(String s : keyList) {
			
			switch (instruPred) {
			
			case "l":
				switch (s) {
				case "r":	newhe.put("u", he.get("r")); break;
				case "l":	newhe.put("s", he.get("l")); break;				
				case "s":	newhe.put("r", he.get("s")); break;				
				case "u":	newhe.put("l", he.get("u")); break;			
				}
				break;
				
			case "r":
				switch (s) {
				case "r":	newhe.put("s", he.get("r")); break;
				case "l":	newhe.put("u", he.get("l")); break;				
				case "s":	newhe.put("l", he.get("s")); break;				
				case "u":	newhe.put("r", he.get("u")); break;			
				}	
				break;
			case "u":
				switch (s) {
				case "r":	newhe.put("l", he.get("r")); break;
				case "l":	newhe.put("r", he.get("l")); break;				
				case "s":	newhe.put("u", he.get("s")); break;				
				case "u":	newhe.put("s", he.get("u")); break;			
				}	
				break;
			case "s":
				newhe.put(s, he.get(s)); 
				break;
					
			}
		
		}
		//System.out.println("E: "+e+" Orient: "+st+" He:"+he+" newHe: "+newhe);
		return newhe;
		
	}
	
	/**
	Dit si le deplacement est possible, c'est a dire si aller dans tel direction est permis par le grahe en prenant
	Une orientation du robot afin d'orienter les choix possible,
	Utilisation qui marche pour tout les cas, utilisation par défaut quand on connait pas la case precedente ou si on souhaite parcourir dans un chemin 
	**/
	public boolean deplacementPossibleWithOrient(Elements depart,String direction,String orientation) {
		return getVoisinsWithOrient(depart, orientation).containsKey(direction);
	}
	
	public Elements deplacementOrient(Elements depart, String direction, String orientation) {
		return getVoisinsWithOrient(depart, orientation).get(direction);
	}
	
	public String getOrientFromPred(Elements actuel, Elements pred) {
		HashMap<String, Elements> gv = getVoisins(actuel);
		Set<String> ensemble = gv.keySet();
		String st ="";
		String r = "";
		for(String s: ensemble) {
			if(gv.get(s) == pred) {
				st = s;
			}
		}
		switch (st) {
		
		case "s":
			r = "u";
			break;
		case "u":
			r = "s";
			break;
		case "r":
			r = "l";
			break;
		case "l":
			r = "r";
			break;
		default:
			System.err.println("GetOrientFromPred: Erreur pred");
			break;
		}

		return r;
	}
	


}
