package carte;

public class Elements {

	private TypeElement type ; // Donne la configuration de la case Element
	private boolean robotInThere ; // Nous informe sur la presence ou non d'un robot 
	private boolean victimInThere ; // Nous informe sur la présence d'une victime
	
	public Elements(TypeElement typeIn ) {
		type = typeIn ;
		robotInThere = false ;
		victimInThere = false ;
	}
	

	
	// Getters and setters 
	
	
	
	public boolean isRobotInThere() {
		return robotInThere;
	}


	public boolean isVictimInThere() {
		return victimInThere;
	}



	public void setVictimInThere(boolean victimInThere) {
		this.victimInThere = victimInThere;
	}



	public void setRobotInThere(boolean robotInThere) {
		this.robotInThere = robotInThere;
	}


	public TypeElement getType() {
		return type;
	}

	public void setType(TypeElement type) {
		this.type = type;
	}



	@Override
	public String toString() {
		return   type + "";
	}
	
	
	
}
