package comm;

public class Comm implements Commande {
	BTcommunication bt;
	
	public void on(String nom) throws BTcommConnectException {
		bt=new BTcommunication(nom);
	}

	@Override
	public void off() throws BTcommConnectException {
		bt.deconnexion();
	}

	@Override
	public void avancer() throws BTcommConnectException{
		bt.envoie("s\n");
	}

	@Override
	public void gauche() throws BTcommConnectException {
		bt.envoie("l\n");
	}

	@Override
	public void droite() throws BTcommConnectException{
		bt.envoie("r\n");
	}

	@Override
	public void demitour() throws BTcommConnectException{
		bt.envoie("u\n");
	}

	@Override
	public void ramasser() throws BTcommConnectException{
		bt.envoie(" t\n");
	}

	@Override
	public void deposer() throws BTcommConnectException{
		bt.envoie("d\n");
	}


	
}