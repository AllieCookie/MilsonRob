package Tests;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ElementVisitor;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.Name;
import javax.lang.model.type.TypeMirror;

import carte.*;
import interfaceCarte.InterfaceAffichage;

public class TestsCarte {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int absc =  2 ;
		int ord = 2 ;
		Carte myMap = new Carte(absc ,ord) ;
		Elements virageAGauche = new Elements(TypeElement.VirageBG) ;
		Elements virageADroite = new Elements(TypeElement.VirageBD) ; 
		Elements virageBasADroite = new Elements(TypeElement.VirageHD) ;
		Elements virageBasAGauche = new Elements(TypeElement.Vide) ;
		Elements test = null;
		myMap.SetAt(0, 0, virageADroite);
		myMap.SetAt(0, 1, virageAGauche);
		myMap.SetAt(1, 0, virageBasADroite);
		//myMap.SetAt(1, 1, test);

		myMap.SetAt(1, 1, virageBasAGauche);
		
		//InterfaceAffichage monInterface = new InterfaceAffichage(); 
		CarteImagee CarteIm = new CarteImagee(myMap);
		CarteIm.getAbscisses() ;
		
		System.out.println("Ta race");
		
		//InterfaceAffichage ia = new InterfaceAffichage();
		//ia.affichage(CarteIm);
		
		Elements e = myMap.GetAt(1, 1);
		System.out.println(e);
		
		GrapheMap gm =  new GrapheMap(myMap);
		
		TestsCarte ts = new TestsCarte();
		
		//ts.test3x3();
		//ts.testBoucleWithPred();
		ts.testBoucleWithOrient();
		
	}
	
	public void test3x3() {
		Carte myMap = carteType1();

		
		//InterfaceAffichage monInterface = new InterfaceAffichage(); 
		CarteImagee CarteIm = new CarteImagee(myMap);
		CarteIm.getAbscisses() ;
		
		
		InterfaceAffichage ia = new InterfaceAffichage();
	
		ia.affichage(CarteIm);
	
		
		Elements e = myMap.GetAt(1, 1);
		Elements j;
		System.out.println(e);
		
		GrapheMap gm =  new GrapheMap(myMap);
		
		/*
		System.out.println("Ensemble de test sur le graphe:");
		HashMap<String, Elements> le = gm.getVoisins(zerozero);
		System.out.println(le);
		
		e = le.get("r");
		System.out.println(e);
		le = gm.getVoisins(e);
		j = le.get("v");
		System.out.println(le);
		
		
		System.out.println("Test getvoisinsPred");
		gm.getVoisinsWithPred(j, e);*/
		/*
		e = le.get(1);
		System.out.println(e);
		System.out.println(e.equals(myMap.GetAt(0,2)));*/
		
		
	}
	
	public void testBoucleWithPred(){
		
		System.out.println("\n\n-- DEBUT TEST BOUCLE PRED --\n");

		Carte map = carteType1();
		GrapheMap gm = new GrapheMap(map);
		CarteImagee CarteIm = new CarteImagee(map);		
		InterfaceAffichage ia = new InterfaceAffichage();
		ia.affichage(CarteIm);
		
		Elements pred = null;
		Elements e = map.GetAt(0, 0);
		Elements olde;
		System.out.println("a"+gm.getVoisins(e));
		System.out.println("b"+gm.getVoisinsArray(e));
		for(int i=0;i<10;i++) {
			System.out.println("\nETAPE "+i+": Courant: "+e+" Pred:"+pred);
			
			if(gm.deplacementPossibleWithPred(e, "r", pred)) {
				olde = e;
				e = gm.deplacementPred(e, "r", pred);
				pred = olde;

			}
			else {
				System.out.println("ERREUR");
			}

		}
		e = map.GetAt(0, 0);

		System.out.println("-- FIN TEST BOUCLE PRED--\n");
		System.out.println("a"+gm.getVoisins(e));
		System.out.println("b"+gm.getVoisinsArray(e));
		
		
	}
	
	public void testBoucleWithOrient(){
		
		System.out.println("\n\n-- DEBUT TEST BOUCLE ORIENT --");

		Carte map = carteType1();
		GrapheMap gm = new GrapheMap(map);
		CarteImagee CarteIm = new CarteImagee(map);		
		InterfaceAffichage ia = new InterfaceAffichage();
		ia.affichage(CarteIm);
		
		Elements pred = null;
		Elements e = map.GetAt(0, 0);
		Elements olde;
		String s = "s";
		System.out.println("a"+gm.getVoisins(e));
		System.out.println("b"+gm.getVoisinsArray(e));
		for(int i=0;i<10;i++) {
			System.out.println("\nETAPE "+i+": Courant: "+e+" Orient:"+s+" Pred:"+pred);
			if(gm.deplacementPossibleWithOrient(e, "r", s)) {
				olde = e;
				e = gm.deplacementOrient(e, "r", s);
				pred = olde;
				s = gm.getOrientFromPred(e, pred);
			}

			else {
				System.out.println("ERREUR");
			}

		}
		
		System.out.println("-- FIN TEST BOUCLE ORIENT --\n");
		System.out.println(gm.getAllElemments());
		e = map.GetAt(0, 0);
		System.out.println("a"+gm.getVoisins(e));
		System.out.println("b"+gm.getVoisinsArray(e));


		
	}
	
	public Carte carteType1() {
		int absc =  3;
		int ord = 3;
		Carte myMap = new Carte(absc ,ord) ;
		Elements vide1 = new Elements(TypeElement.Vide);
		Elements vide2 = new Elements(TypeElement.Vide);
		Elements vide3 = new Elements(TypeElement.Vide);		
		Elements zerozero = new Elements(TypeElement.VirageBD);

		myMap.SetAt(0, 0, zerozero);
		myMap.SetAt(0, 1, new Elements(TypeElement.CroisementGDB));
		myMap.SetAt(1, 0, new Elements(TypeElement.CroisementDHB));
		myMap.SetAt(2, 0, new Elements(TypeElement.RouteHB));
		myMap.SetAt(1, 1, new Elements(TypeElement.VirageHG));
		myMap.SetAt(0, 2, new Elements(TypeElement.RouteHB));
		myMap.SetAt(2, 1, vide1);
		myMap.SetAt(2, 2, vide2);
		myMap.SetAt(1, 2, vide3);
		
		return myMap;
		
	}

}
