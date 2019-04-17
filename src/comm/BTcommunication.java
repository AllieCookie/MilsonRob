package comm;
/* imports exceptions */
import java.io.IOException;

/* imports pour la reception de donnees */
import java.io.InputStream;
import java.io.DataInputStream;

/* imports pour l'envoie de donnees */
import java.io.OutputStream;
import java.io.DataOutputStream;

/* import pour se connecter au robot par bluetooth */
import lejos.pc.comm.NXTConnector;

public class BTcommunication {
	/* ----- Attributs ----- */
	private static NXTConnector connex = new NXTConnector();
	private static boolean estConnect=false;
	private String nomRobot=new String();
	private String nomControleur = new String();

	/* ----- Methodes ----- */
	/* si pc est connecte avec robot */
	public boolean getEstConnect() {
		return estConnect;
	}

	/* nom du robot */
	public String getNomRobot() {
		return nomRobot;
	}
	
	/* nom de celui qui controle le robot */
	public String getNomControleur() {
		return nomControleur;
	}
	
	/* initialisation de la connexion */
	public BTcommunication(String nom) throws BTcommConnectException {
		connexion();
		this.nomRobot = this.reception();
		this.nomControleur=nom;
		envoie(nom); // /!\ ne pas oublier le "\n" /!\
	}


	/* connexion au robot par bluetooth */
	public void connexion () {
		if (estConnect=connex.connectTo()) {
			System.out.println("BTcommunication.connexion - Succeed :)");
		}else {
			System.err.println("BTcommunication.connexion - Fail :(");
		}

	}

	/* deconnexion au robot */
	public void deconnexion() throws BTcommConnectException {
		if (getEstConnect()) {
			try {
				connex.close();
			}catch(IOException e) {
				System.err.println("BTcommunication.deconnexion.");
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
		}else {
			throw new BTcommConnectException(getEstConnect());
		}
	}


	/* reception des donnees du robot */
	public String reception() throws BTcommConnectException {
		String mot = new String();
		if (getEstConnect()) {
			//ouverture flux
			InputStream recup=connex.getInputStream(); 
			DataInputStream drecup = new DataInputStream(recup); 

			char c;
			//recuperation du premier caractere pour initialiser mot
			try {
				mot=Character.toString(drecup.readChar());
				//recupere les donnees jusqu'a ce qu'il n'y ait plus rien a recuperer
				while ( recup.available() != 0 ) { 
					c = drecup.readChar();
					mot+=c; //remplissage du mot
				}

				//fermeture flux
				drecup.close();
				recup.close();
			} catch (IOException e) {
				System.err.println("BTcommunication.reception.");
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
		}else{
			throw new BTcommConnectException(getEstConnect());
		}

		return mot;
	}

	/* envoie de donnees au robot */
	public void envoie(String message) throws BTcommConnectException {
		if (getEstConnect()){
			//ouverture flux
			OutputStream envoie=connex.getOutputStream();
			DataOutputStream denvoie=new DataOutputStream(envoie);

			try {
				//envoie au robot 
				denvoie.writeUTF(message);

				//fermeture flux
				denvoie.close();
				envoie.close();

			} catch (IOException e) {
				System.err.println("BTcommunication.envoie.");
				System.err.println(e.getMessage());
				e.printStackTrace();
			}

		}else{
			throw new BTcommConnectException(getEstConnect());
		}
	}


	/* initialisation de la connexion 
	public void initCo() throws BTcommConnectException {
		connexion();
		this.nomRobot = this.reception();
		envoie("MilSon-Rob\n"); // /!\ ne pas oublier le "\n" /!\
	}*/


	/* ----- main ----- */
	public static void main(String[] args) throws Exception {
		BTcommunication bt = new BTcommunication("MilSon-Rob");

		/* tests */
		

		if ( bt.getEstConnect()) 
			bt.deconnexion();
	}

}
