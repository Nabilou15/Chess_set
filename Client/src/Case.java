import java.awt.* ;
import javax.swing.*;

/**
  * Classe Case
  * représente une case du Plateau.
  * Une Case peut avoir une piece ou non.
*/

public class Case extends JPanel 
{
	private int Indice_case ;
	private int i ;
	private int j ;
	private Piece Piece ;
	private JPanel Cadre ;

	/**
	 * Constructeur de Case
	 * on récupére l'indice de la case pour determiner sa couleur (noir ou blanc).
	 * 
	 * @param i //indice abscisses
	 * @param j	//indice ordonnes
	 */
	public Case( int i, int j)
	{
		Indice_case = i+j ;
		this.i = i ;
		this.j = j ;
		Cadre = new JPanel() ;
		Cadre.setPreferredSize(new Dimension(10, 10)) ;
	}
	
	/**
	 * Méthode pour dessiner la Case. 
	 * en fonction de l'indice on affichera une couleur noir ou blanc
	 */
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);	
		if(Indice_case % 2 == 1)
		{
			this.setBackground(Color.GRAY) ;
		}
		
		else
		{
			this.setBackground(Color.LIGHT_GRAY) ;
		}
		
		//this.setLayout(new BorderLayout()) ;
		//JLabel label = new JLabel(Integer.toString(i)+ ',' + Integer.toString(j)) ; 
		//this.add(label, BorderLayout.NORTH) ;
		this.update_affichage() ;
	}

/*	******************************
 	********* Accesseurs *********
 	******************************	*/	
	
	/**
	 * Retourne la piece sur la case
	 * 
	 * @return Piece
	 */
	public Piece get_Piece()
	{
		return Piece ;
	}
	
	/**
	 * Retourne si la case est vide ou non
	 * 
	 * @return true //si case est vide
	 * 		   false // sinon
	 */
	public boolean is_empty()
	{
		if(Piece==null)
			return true ;
		else
			return false ;
	}	

/*	******************************
 	********* Modifieur  *********
 	******************************	*/
	
	/**
	 * ajoute une Piece a la case.
	 * si Piece est null alors la Case se vide.
	 *
	 * @param piece 
	 */
	public void set_Piece(Piece piece)
	{
		this.Piece = piece ;
		
		if(Piece != null)
		{
			this.setLayout(null) ;
			this.setLayout(new BorderLayout()) ;
			JLabel label_piece = new JLabel() ;
			label_piece.setIcon(Piece.get_Icone()) ;
			this.add(label_piece,BorderLayout.CENTER) ;
		}
		else
		{
			//this.setLayout(null) ;
			this.removeAll() ;
		}
			
		this.update_affichage() ;
		this.repaint() ;
	}
	
	public void selection()
	{
		this.removeAll() ;
		this.setLayout(null) ;
		this.setLayout(new BorderLayout()) ;
		Cadre.setBackground(Color.RED) ;
		JLabel label_piece = new JLabel() ;
		label_piece.setIcon(Piece.get_Icone()) ;
		this.add(label_piece,BorderLayout.CENTER) ;
		//this.add(Cadre, BorderLayout.NORTH) ;
		//this.add(Cadre, BorderLayout.EAST) ;
		this.add(Cadre, BorderLayout.SOUTH) ;
		//this.add(Cadre, BorderLayout.WEST) ;
		revalidate() ;
		repaint() ;
	}
	
	/**
	 * Met à jours l'affichage de la case.
	 */
	public void update_affichage()
	{
		this.revalidate() ;
		//this.repaint() ;
	}
}
