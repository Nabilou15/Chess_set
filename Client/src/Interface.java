import javax.swing.* ;
import java.awt.* ;
import java.awt.event.MouseEvent ;
import java.awt.event.MouseListener ;
import javax.swing.JFrame ;
import java.net.Socket;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Classe Interface
 * Cette classe fera le lien avec toutes les autres classes.
 * Elle permettra l'affichage de la fenetre du jeu.
 * Demandera l'affichage du Plateau, du menu.
 * Fera l'appel de Envoi et Reception pour mettre a jour son interface.
 * Demandera à afficher les Erreurs (boites de dialogues).
 * Doit recupérer les Clics de l'utilisateur et les convertir en indice ([0;8][0;8]).
 * 
 * Interface a les arguments suivants :
 * Frame Fenetre
 * Plateau Plateau
 * Menu Menu
 * Piece Pieces
 * Erreur Erreur
 * Envoi Envoi
 * Reception Reception
 * Connexion Connexion
 * String Pseudo
 * String Adresse Serveur
 * String Port
 * 
 * @author Legros Matthieu
 * @version 0.0
 */
public class Interface extends JFrame implements MouseListener
{
	private int[][] Coord = new int[2][2] ; // Coord[2] -> les 2 positions && Coord[][2] -> [0]lignes et [1]colonnes
	private int Selections = 0 ; //nbr Coord selectionnées (1 ou 2)
	private boolean clicked ; //si le joueur a cliqué
	private Piece Piece = new Piece(NOM.PION, "") ;
	private String Pseudo ;
	private boolean Est_joueur ;
	private String Serveur ;
	private int Port ;
	private Plateau Plateau ;
	private Menu Menu ;
	private Piece Pieces ;
	private Erreur Erreur ;
	private Envoi Envoi ;
	private Reception Reception ;
	private Connexion Connexion ;
	private int Id_joueur ;
	private boolean tour ;
	/**
	 * Constructeur de l'Interface
	 * Il s'agit ici de créer et afficher une fenetre. Et mettre a jours les attributs.
	 * Attention ici on affichera pas tout de suite le plateau. C'est l'interface de connection.
	 */
	public Interface()
	{
		//System.out.println("En_cours") ;
		//Fenetre = new JFrame();
		super() ;
		this.setTitle("Jeu d'echec en reseau") ;
		this.setSize(400, 600) ;
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
		tour = false ;
		this.setVisible(true) ;
	}

	/**
	 * On lance l'interface de connexion on récuperera le pseudo de l'utilisateur.
	 * Une adresse de serveur si l'utilisateur ne veut pas utiliser celui par defaut (et un Port de connexion).
	 * On lancera ensuite l'interface de gestion des parties.
	 */
	public void run ()
	{
		//System.out.println("En_cours") ;
		Interface_connexion affichage = new Interface_connexion() ;
		this.setContentPane(affichage) ;
		Connexion = new Connexion() ;
		//System.out.println("Attention la connection est sous commentaire...") ;
		this.Interface_selection_partie() ;
		while(true)
		{
			int x = 1 ;
		}
	}
	
	/**
	 * Lance l'interface de gestion des partie. C'est ici que l'utilisateur pourra choisir sa partie. 
	 * Ou demander a en créer une nouvelle. Dès que l'utilisateur a choisi. On le connecte a la partie 
	 * désirée.
	 */
	public void Interface_selection_partie()
	{
		//this.setSize(1200, 900) ;
		//this.setContentPane(new JPanel()) ;
		//System.out.println("En_cours") ;
		Menu = new Menu() ;
		this.setJMenuBar(Menu);	
		this.revalidate() ;
		//this.Interface_partie() ;		
	}
	
	/**
	 * Interface d'une partie. 
	 * On créera le plateau. Boite de dialogue Prêt.
	 * On fera un appel de jeu() une fois le joueur prêt.
	 */
	public void Interface_partie() 
	{
		//System.out.println("En cours") ;
		JPanel conteneur = new JPanel() ;//conteneur de Plateau. ça nous permet de le placer.
		conteneur.setLayout(new GridBagLayout()) ; //pour placer de façon "manuelle le plateau" on doit enlever le placement par défaut	
		Plateau = new Plateau() ; //on crée le plateau.
		Plateau.setPreferredSize(new Dimension(800,800)) ; //on definit la taille voulue.
		conteneur.add(Plateau, new GridBagConstraints()) ; //pour centrer le plateau. 
		this.setContentPane(conteneur) ; //on ajoute le plateau dans le conteneur de la fenetre.
		Plateau.addMouseListener(this) ;
		this.creer_connection();
	}
	
	/**
	*
	*/
	public void creer_connection()
	{
		Id_joueur = Connexion.recevoir_Id_joueur() ;
		System.out.println("identifiant du joueur : "+Id_joueur) ; 
		Envoi = new Envoi(Connexion.get_Socket()) ;
		Reception = new Reception(Connexion.get_Socket()) ;

		if(Id_joueur == 1)
		{
			Plateau.remplir_Plateau_joueur_noir() ;
			tour = false ;
			jouer() ;
		}

		if (Id_joueur == 0)
		{
			Plateau.remplir_Plateau_joueur_blanc() ;
			tour = true ;
			jouer() ;
		}
	
		if (Id_joueur > 1)
		{
			Plateau.remplir_Plateau_joueur_blanc() ;
			tour = false ;
			observer() ;
		}

	}


	public void observer()
	{	
		revalidate() ;
		int i = 0 ;
		while (true)
		{
			System.out.println("Attente de reception des coordonnées") ;
			//Coord = null ;
			Coord = Reception.run() ;
			System.out.println("Coordonne reçues : "+"["+Coord[0][0]+";"+Coord[0][1]+"]"+" -> "+"["+Coord[1][0]+";"+Coord[1][1]+"]") ;
			if (Coord[0][0] != Coord[1][0] || Coord[0][1] != Coord[1][1])
			{
				Piece = Plateau.get_Case(Coord[0][0], Coord[0][1]).get_Piece() ;
				Plateau.vider_case(Coord[0][0], Coord[0][1]) ;
				Plateau.vider_case(Coord[1][0], Coord[1][1]) ;
				Plateau.ajouter_Piece(Coord[1][0], Coord[1][1], Piece) ;
				revalidate() ;
			}
			i++ ;
			System.out.println("Nbr de coordonnées reçu : "+i) ;
		}
	}

	/**
	 * On attendra que le l'adversaire sera prêt. Une fois prêt, La partie commence.
	 * On lira en permanence les clics de l'utilisateur.
	 * Appel de Envoi qui enverra ces clics au serveur (que si on est joueur).
	 * Appel de Reception pour recevoir l'etat du jeu (nouvelle coordonnées, etc...).
	 * Appel de update_PLateau pour mettre a jour l'affichage si nécéssaire.
	 * On affichera une erreur le cas échéant.
	 * Ne pas oublier la boucle infinie !
	 */
	public void jouer()
	{
		revalidate() ;

		while(true)
		{
			try 
			{
		  	Thread.sleep(1) ;
		  } 

		  catch (InterruptedException e) 
		  {
				//rien
			}
			
			//System.out.println("clicked : "+clicked) ; 
			if (tour)
			{
				if(clicked)
				{
					//System.out.println("clicked : "+clicked) ; //si on mets pas cette ligne cette partie ne s'execute jamais.
					if(Selections == 1)
					{
						convertir_pixels_to_coord() ;
						if (Plateau.get_Case(Coord[0][0], Coord[0][1]).is_empty())
						{
							Selections-- ;
						}
						
						else
						{
							Plateau.selection(Coord[0][0], Coord[0][1]) ;
							revalidate() ;
							repaint() ;
						}
						clicked = false ;
					}
					
					if (Selections == 2)
					{
						convertir_pixels_to_coord() ;
						if(Id_joueur == 1)
							this.inverser_Coord() ;
						System.out.println("Coordonne a envoyer : "+"["+Coord[0][0]+";"+Coord[0][1]+"]"+" -> "+"["+Coord[1][0]+";"+Coord[1][1]+"]") ;
						Envoi.run(Coord) ;
						System.out.println("envois des coordonnés") ;
						Coord = Reception.run() ;
						if(Id_joueur == 1)
							this.inverser_Coord() ;
						System.out.println("Coordonne reçues : "+"["+Coord[0][0]+";"+Coord[0][1]+"]"+" -> "+"["+Coord[1][0]+";"+Coord[1][1]+"]") ;
						Piece = Plateau.get_Case(Coord[0][0], Coord[0][1]).get_Piece() ;
						Plateau.vider_case(Coord[0][0], Coord[0][1]) ;
						Plateau.vider_case(Coord[1][0], Coord[1][1]) ;
						Plateau.ajouter_Piece(Coord[1][0], Coord[1][1], Piece) ;
						revalidate() ;
						Selections = 0 ;
						if (Coord[0][0] == Coord[1][0] && Coord[0][1] == Coord[1][1])
							tour = !tour ;
							
						tour = !tour ;
						clicked = false ;
					}		
				}
			}
			
			else
			{
				Coord = Reception.run() ;
				if(Id_joueur == 1)
					this.inverser_Coord() ;
				System.out.println("Coordonne reçues : "+"["+Coord[0][0]+";"+Coord[0][1]+"]"+" -> "+"["+Coord[1][0]+";"+Coord[1][1]+"]") ;
				Piece = Plateau.get_Case(Coord[0][0], Coord[0][1]).get_Piece() ;
				Plateau.vider_case(Coord[0][0], Coord[0][1]) ;
				Plateau.vider_case(Coord[1][0], Coord[1][1]) ;
				Plateau.ajouter_Piece(Coord[1][0], Coord[1][1], Piece) ;
				revalidate() ;
				tour = !tour ;
			}
		}
	}


	
	/**
	 * Sert a récupérer les Coordonnees du clics utilisateur en pixels.
	 * On les convertira en indice de case [1;8][1;8].
	 */
	public void convertir_pixels_to_coord() 
	{
    	
		if(Selections == 1)
		{
			Coord[0][0] = Coord[0][0]/100 ; // ligne du tableau
			Coord[0][1] = Coord[0][1]/100 ; // colonne
			//System.out.println("clic 1 : "+Coord[0][0]+";"+Coord[0][1]) ;
		}
		
		if(Selections == 2)
		{
			Coord[1][0] = Coord[1][0]/100 ;
			Coord[1][1] = Coord[1][1]/100 ;
			//System.out.println("clic 2 : "+Coord[1][0]+";"+Coord[1][1]) ;
		}	

		//on reconverti les coordonné si c'est c'est le joueur noir
	}

	/**
	*	on inverse les coordonnes du joueur
	*/
	public void inverser_Coord()
	{
		Coord[0][0] = 7-Coord[0][0] ;
		Coord[0][1] = 7-Coord[0][1] ;

		Coord[1][0] = 7-Coord[1][0] ;
		Coord[1][1] = 7-Coord[1][1] ;
	}
    /**
     *  Fonction appellée automatiquement lors d'un clic
	 *
     * @param m evenement souris
     */
	public void mouseClicked(MouseEvent m) 
  {
	if(!tour)
	{
		return ;
	}
    int x = m.getX() ;
    int y = m.getY() ;
    	
    	if(!clicked)
    	{
				Selections++ ;
		 	
				if(Selections == 1)
				{
					Coord[0][0] = y ; 
					Coord[0][1] = x ;
					clicked = true ;
				}
							
				if(Selections == 2)
				{
					Coord[1][0] = y ;
					Coord[1][1] = x ;
					clicked = true ;
				}
			}
   }
 
    public void mouseEntered(MouseEvent arg0) {}
    public void mouseExited(MouseEvent arg0) {}
    public void mousePressed(MouseEvent arg0) {}
    public void mouseReleased(MouseEvent arg0) {}
    
/*************************************************/	
	/**
	 *	On transmet les coordonnees au serveur.
	 *	On fera donc appel a envoi.	
	 *
	 *	@param Coord[][] //position de 2 case. Depart et arrive.
	 *
	 *	@throws (Coord[][0] > 0 && Coord[][0] < 9) && (Coord[][1] > 0 && Coord[][1] < 9)
	 */
	public void transmettre(int[][] Coord)
	{
		System.out.println("En_cours") ;
	}
	
	/**
	 *	On reçoit les coordonnees du serveur.
	 *	appel de Reception.	
	 *	On verifie si le coup etait permis ou non. Si les coordonnées etait identique,
	 *	le coups etait impossible.
	 *	On reçoit aussi le coups de l'autre joueur.	
	 *
	 *	@throws (Coord[0] > 0 && Coord[0] < 9) && (Coord[1] > 0 && Coord[1] < 9)
	 *
	 *	@return	Coor[][] //contient les coordonnes de depart et d'arrivé d'une piéce.
	 */
	public int[][] Receptionner()
	{
		System.out.println("En_cours") ;
		int[][] Coord = new int[2][2] ;
		return Coord ;
	}
}
