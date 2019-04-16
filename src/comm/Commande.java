package comm;

public interface Commande {
	public void on(String nom) throws BTcommConnectException;
	abstract void off() throws BTcommConnectException;
	abstract void avancer() throws BTcommConnectException;
	abstract void gauche() throws BTcommConnectException;
	abstract void droite() throws BTcommConnectException;
	abstract void demitour() throws BTcommConnectException;
	abstract void ramasser() throws BTcommConnectException;
	abstract void deposer() throws BTcommConnectException;
}
