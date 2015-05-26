/**
 * Classe definissant l'utilisateur
 * 
 * @author matthieu
 *
 */
public class Joueur 
{
	private String Pseudo ;
	private UTILISATEUR Mode ;
	private int Id_joueur ;
	private COULEUR Couleur ;
	
	/**
	* Constructeur joueur 
	*
	*	@param pseudo //pseudo de l'utilisateur
	*	@param Mode //Joueur couleur blanch ou noir
	*	@param Id_joueur //
	*/
	public Joueur (int id_utilisateur, String pseudo)
	{
			this.Pseudo = pseudo ;
			this.Mode = UTILISATEUR.SPECTATEUR ;
			this.Id_joueur = id_utilisateur ;
	}
	
	public Joueur (UTILISATEUR mode, COULEUR couleur,int id_utilisateur, String pseudo)
	{
			this.Pseudo = pseudo ;
			this.Mode = mode ;
			this.Couleur = couleur ;
			this.Id_joueur = id_utilisateur ;
	}
	
	/**
	*	Accesseur Pseudo
	*
	* @return 
	*/
	public String get_Pseudo()
	{
		return this.Pseudo ;
	}
	
	/**
	*	Accesseur Mode
	*
	*/
	
	public UTILISATEUR get_Mode ()
	{
		return Mode ;
	}
	
	/**
	*
	*/
	public COULEUR get_Couleur()
	{
		return Couleur ;
	}
	
	public int get_Id_joueur ()
	{
		return Id_joueur ;
	}
	
}
