package affichage;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import comm.BTcommConnectException;
import comm.Comm;

public class InferfacePrincipale {
	Comm com;
	String nomControlleur = "MilsonRob";
	Boolean on;
	
	//creation de la telecommande

	public void create(Comm c) {
		//init comm
		com = c;
		on = false; 
		
		//init fenetre
		JFrame frame = new JFrame("Controle");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(200, 400);

		//Partie telecommande;
		JPanel jmain = creationTelecommande();
		frame.add(jmain);
		
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		

	}
	
	
	private JPanel creationTelecommande() {
		// Création, paramétrage et positionnement des composants
		JPanel jp = new JPanel();
		jp.setBackground(Color.gray);
		JPanel jp2 = new JPanel();
		jp2.setBackground(Color.gray);
	
		JPanel jmain = new JPanel(new GridLayout(4, 1));
		jmain.setBackground(Color.gray);
		
		JButton buttonL = new JButton("l");
		JButton buttonS = new JButton("s");
		JButton buttonR = new JButton("r");
		JButton buttonV = new JButton("u");
		JButton buttonT = new JButton("t");
		JButton buttonD = new JButton("d");
		JButton buttonO = new JButton("o");
		
		buttonL.setBackground(Color.white);
		buttonS.setBackground(Color.white);
		buttonR.setBackground(Color.white);
		buttonV.setBackground(Color.white);
		buttonT.setBackground(Color.white);
		buttonD.setBackground(Color.white);
		buttonO.setBackground(Color.white);
	
	
		buttonL.addActionListener(new ButtonEvent());
		buttonS.addActionListener(new ButtonEvent());
		buttonR.addActionListener(new ButtonEvent());
		buttonV.addActionListener(new ButtonEvent());
		buttonT.addActionListener(new ButtonEvent());
		buttonD.addActionListener(new ButtonEvent());
		buttonO.addActionListener(new ButtonEvent());
	
	
		JLabel jl1 = new JLabel("Mouvement:");
		jl1.setForeground(Color.white);
		JLabel jl2 = new JLabel("Action:");
		jl2.setForeground(Color.white);
	
		
		Box line1=new Box(BoxLayout.X_AXIS);
		line1.add(Box.createHorizontalGlue());
		line1.add(buttonS);
		line1.add(Box.createHorizontalGlue());
		Box line2=new Box(BoxLayout.X_AXIS);
		line2.add(buttonL);
		line2.add(buttonO);
		line2.add(buttonR);
		Box line3=new Box(BoxLayout.X_AXIS);
		line3.add(Box.createHorizontalGlue());
		line3.add(buttonV);
		line3.add(Box.createHorizontalGlue());
	
		jp.setLayout(new BoxLayout(jp,BoxLayout.Y_AXIS));
		jp.add(line1);
		jp.add(line2);
		jp.add(line3);

		jp2.add(buttonD);
		jp2.add(buttonT);
		
		jmain.add(jl1);
		jmain.add(jp);
		jmain.add(jl2);
		jmain.add(jp2);
		
		return null;
	}
	
	 public static void main(String [] args){
		 InterfaceCommande ic = new InterfaceCommande();
		 //ic.create();
		 Comm com = new Comm();
		 javax.swing.SwingUtilities.invokeLater(new Runnable() {
			 public void run() {ic.create(com); } });
	 }
	 
	 
	 public class ButtonEvent implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent e) {
				String action = e.getActionCommand();
				System.out.println(action);
				
				try {
				switch (action) {
				case "s":
					com.avancer();
					break;
				case "u":
					com.demitour();
					break;
				case "r":
					com.droite();
					break;
				case "l":
					com.gauche();
					break;
				
				case "o":
					if(on) {
						com.off();
						on = false;
					}
					else {
						com.on(nomControlleur);
						on = true;
					}
					break;
				case "d":
					com.deposer();
					break;
				case "t":
					com.ramasser();
					break;
				}
				}
				
				catch(BTcommConnectException bt) {
					bt.printStackTrace();
				}
				
				
			}
			
			
		}
}
