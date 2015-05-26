import java.util.*;

public abstract class Piece {

	private COULEUR Couleur ;
	private int[] Position ;
	private int[] Position_initiale ;
	private boolean Est_tue ;
	
	/**
	 * CONSTRUCTEUR
	 *
	 */
	  
	public Piece (COULEUR couleur)
	{
		this.Couleur = couleur ;
		this.Position = new int[2] ;
		this.Position_initiale = new int[2] ;
		this.Est_tue = false ;
	}
	
		
	/**
	 * ACCESSEUR
	 *
	 */
	
	public COULEUR get_Couleur ()
	{
		return Couleur ;
	}
	
	public int[] get_Position ()
	{
		return Position ;
	}

	public int[] get_Position_initiale ()
	{
		return Position_initiale ;
	}

	boolean est_tue ()
	{
		return Est_tue ;
	}
	
	/**
	 * MODIFIEUR
	 *
	 */
	 public void set_Position(int lignes, int colonnes)
	 {
	 		this.Position[0] = lignes ;
	 		this.Position[1] = colonnes ;
	 }

	public void set_Position_initiale(int lignes, int colonnes)
	 {
	 		this.Position[0] = lignes ;
	 		this.Position[1] = colonnes ;
			this.Position_initiale[0] = this.Position[0] ;
			this.Position_initiale[1] = this.Position[1] ;
	 }


	public void detruire()
	{
		this.Est_tue = true ;
	}
	/**
	* Retournes les cases a portée de la piece
	*
	*	@return boolean // true si mouvement valide
	*										 sinon false
	*/
	abstract Coups mouvement_valide(Case[][] plateau, int i, int j) ;

	/**
	*	verifie si la piece peut bouger sans que le roi soit en echec
	*	le plateau doit etre une copie du plateau de partie !!!!
	*/
	//abstract boolean peut_bouger(Case[][] plateau, ArrayList<Piece> liste_pieces, Piece roi) ;
}
