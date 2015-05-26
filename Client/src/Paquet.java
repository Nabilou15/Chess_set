/**
*	Class definissant les données que le serveur envoi au joueur
*	ou que le serveur reçoit.
*/

public class Paquet
{
	COUPS_SPECIAL Coups_special ;
	int[][] Coord ;
	ETAT_PARTIE Etat ;
	String Perdant ;
	String Gagnant ;

	/**
	*	Constructeurs
	* On renvoit un coup autorisé spécial ou non.
	*/
	public Paquet (COUPS_SPECIAL coups, int[][] coord, ETAT_PARTIE etat) 
	{
		this.Coups_special = coups ;
		this.Coord = coord ;
		this.Etat = etat ;
		this.Perdant = null ;
		this.Gagnant = null ;
	}

	/**
	* Le coup peut avoir été annulé car invalide ou parce que le joueur est en echec.
	*/
	public Paquet (ETAT_PARTIE etat) 
	{
		
		//On annule le coup du joueur en envoyant des coordonnées identique
		this.Coord[0][0] = 0 ;
		this.Coord[0][1] = 0 ;
		this.Coord[1][0] = 0 ;
		this.Coord[1][1] = 0 ;
		
		//Si le joueur est en echec.
		this.Etat = etat ;

		//pas de perdant ou de gagnant
		this.Perdant = null ;
		this.Gagnant = null ;
	}

	/**
	*	On a un perdant ou un gagnant.
	*/
	public Paquet (ETAT_PARTIE etat, String perdant, String gagnant) 
	{
		this.Etat = etat ;
		this.Perdant = perdant ;
		this.Gagnant = gagnant ;
	}

	/**
	*	Accesseurs
	*/

	public COUPS_SPECIAL get_Coups_special()
	{
		return this.Coups_special ;
	} 	

	public int[][] get_Coord()
	{
		return this.Coord ;
	} 

	public ETAT_PARTIE get_Etat()
	{
		return this.Etat ;
	}

	public String get_Perdant()
	{
		return this.Perdant ;
	} 	

	public String get_Gagnant()
	{
		return this.Gagnant ;
	} 	
}
