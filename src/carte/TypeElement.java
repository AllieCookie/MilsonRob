package carte;

public enum TypeElement {
	VirageBD ,  // Virage qui part du Bas et qui va à gauche 
	VirageBG , // Virage qui part du Bas et qui va à droite 
	VirageHD , // Virage qui part du Haut et qui va à droite
	VirageHG , // Virage qui part du Haut et qui va à gauche
	RouteDG, // Route Horizontale
	RouteHB , // Route Verticale
	CroisementGDB , // Croisement sans possibilité de tourner en haut 
	CroisementGHB , // Croisement sans possibilité de tourner à droite
	CroisementGDH, // Croisement sans possibilité de tourner à gauche
	CroisementDHB , // Croisement sans possibilité de tourner en bas
	Vide; // Rien
}
