/**
 * Classe Case définie une case de l'echiquier.
 * 
 * @author matthieu
 *
 */
public class Case 
{

	private Piece Piece ;

	
	/**
	 * Constructeur d'une case
	 * 
	 * @param piece
	 */
	public Case (Piece piece)
	{
		if(this.Piece != null)
			this.Piece.detruire() ;			

		Piece = piece ;
	}
	
	/**********************************************/
	/**************   ACCESSEURS  *****************/
	/**********************************************/
	
	/**
	 * retourne si la case est vide
	 * 
	 * @return true si Piece est null
	 * 		   false sinon
	 */
	public boolean is_empty ()
	{
		if(Piece == null)
			return true ;
		
		else
			return false ;
	}
	
	/**
	 * retourne la piece sur la case
	 * 
	 * @return Piece
	 */
	public Piece get_Piece()
	{
		return Piece ;
	}

	/**
	* retourne la couleur de la piece sur la case
	*
	*	@return piece.get_Couleur()
	*/
	public COULEUR get_Couleur_Piece()
	{
		return Piece.get_Couleur() ;
	}
	
	/**********************************************/
	/**************   Modifieur   *****************/
	/**********************************************/
	
	/**
	 * Ajoute la piece a la case
	 * 
	 * @param piece //Attention si null
	 */
	public void set_Piece(Piece piece, int i, int j)
	{

		if(piece == null)
    {
    	throw new NullPointerException("Attention Piece null !!!");
    }

		Piece = piece ;
		Piece.set_Position(i, j) ;			
	}

	public void set_Piece_initiale(Piece piece, int i, int j)
	{

		if(piece == null)
    	{
    		throw new NullPointerException("Attention Piece null !!!");
   		}

		Piece = piece ;
		Piece.set_Position_initiale(i, j) ;			
	}

	/**
	 * retire la piece de la case
	 * 
	 * @param piece 
	 */
	public void vider_case()
	{

		if(Piece == null)
    {
    	throw new NullPointerException("On ne peut pas retirer une piece deja null !!!");
    }
		Piece = null ;			
	}
}
