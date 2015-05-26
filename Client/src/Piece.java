import javax.swing.ImageIcon ;


/**
 * Classe Piece
 * Cette classe définit les pièces du jeu.
 * Elle a pour attributs :
 * Type Nom
 * BufferedImage Icone
 * 
 * @author matthieu
 *
 */
public class Piece 
{
	private NOM Nom ;
	private ImageIcon Icone ; //c'est ici que l'on stockera l'image de la piece.
	
/**
 * Constructeur de Piece
 * 
 * @param type //Nom de la pièce(tour, fou, Roi...)
 * @param chemin_image //chemin vers l'image dans le repertoire.
 */
	public Piece(NOM type, String chemin_vers_image)
	{
		this.Nom = type ;
		Icone = new ImageIcon(chemin_vers_image) ;		
	}
	
/*	******************************
 	********* Accesseurs *********
 	******************************	*/
	
/**
*	Return l'icone de la Piece
*
*	@return BufferedImage
*/
	public ImageIcon get_Icone()
	{
		return Icone ;
	}
	
/**
*	Return le Type de l'image
*
*	@return Type Nom 
*/
	public NOM get_Nom()
	{
		return Nom ;
	}
}
