import javax.swing.* ;
import java.awt.* ;
/*import java.awt.event.MouseEvent ;
import java.awt.event.MouseListener ;
import javax.swing.JFrame ;
import java.net.Socket ;
import java.io.IOException ;
import java.io.ObjectInputStream ;
import java.io.ObjectOutputStream ;*/


/**
*	Classe qui affiche l'ecran de lancement du jeu.
*	C'est ici que l'utilisateur choisira le serveur et le port où il veut se connecter.
*	Il pourra aussi choisir un pseudo.	
*
*/
public class Interface_connexion extends JPanel
{
	//Connexion Connexion ;
	String Pseudo ;
	JTextField champs_pseudo ;
	
	String Adresse_serveur ;
	JTextField champs_adresse_serveur ;
	
	String Port ;
	JTextField champs_Port ;
	
	JButton Connexion ;
	JButton Quitter ;
	
	public Interface_connexion()
	{
	
		Box b_label = Box.createHorizontalBox() ;		
		JLabel label_titre = new JLabel("Bienvenue sur le jeu d'echec en réseau") ;	
		b_label.add(label_titre) ;
		
		Box b_champs = Box.createVerticalBox() ;	
		champs_pseudo = new JTextField() ;
		b_champs.add(champs_pseudo) ;
		b_champs.add(Box.createRigidArea(new Dimension(0,30)));
		champs_adresse_serveur = new JTextField() ;
		b_champs.add(champs_adresse_serveur) ;
		b_champs.add(Box.createRigidArea(new Dimension(0,30)));
		champs_Port = new JTextField() ;
		b_champs.add(champs_Port) ;
		
		Box b_bouton = Box.createHorizontalBox() ;
		Connexion = new JButton("connexion") ;
		b_bouton.add(Connexion) ;
		b_bouton.add(Box.createRigidArea(new Dimension(50,0)));
		Quitter = new JButton("quitter") ;
		b_bouton.add(Quitter) ;
		
		Box box = Box.createVerticalBox() ;
		box.add(b_label) ;
		box.add(Box.createRigidArea(new Dimension(0,100)));
		box.add(b_champs) ;
		box.add(Box.createRigidArea(new Dimension(0,100)));
		box.add(b_bouton) ;
		this.add(box) ;		
	}

	public void paintComponent(Graphics g)
	{	
		super.paintComponent(g) ; //pour dessiner Plateau.
		this.setMinimumSize(new Dimension(400,600)) ; //taille minimum du plateau.
		Image background = (new ImageIcon("images/fond.jpeg")).getImage() ;
		g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);

		this.revalidate();
		this.repaint();
		//this.attente() ;
	}

	public void get_saisi_utilisateur()
	{
		Pseudo = champs_pseudo.getText() ;
		Adresse_serveur = champs_adresse_serveur.getText() ;
		Port = champs_Port.getText() ;
	}

	public void attente()
	{
		try 
		{
		  	Thread.sleep(5000) ;
		} 

		catch (InterruptedException e) 
		{
			//rien
		}
	}
}
