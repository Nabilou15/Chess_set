import java.util.*;

public class Cavalier extends Piece
{

	private NOM Nom ;
	
	public Cavalier(NOM nom, COULEUR couleur) 
	{
		super(couleur) ;
		this.Nom = nom ; 
	}
	
	/*
	* ACCESSEUR
	*/
	
	/**
	*
	*/
	public NOM get_Nom()
	{
		return this.Nom ;
	}
	
	Coups mouvement_valide(Case[][] plateau, int i, int j)
	{

		if( (!plateau[i][j].is_empty()) && super.get_Couleur() == plateau[i][j].get_Couleur_Piece() ) //on ne peut pas tuer ses propres pieces !!!
			return null ;				

		if(2 == distance_case_abs(i) && 1 == distance_case_ord(j))
		{
			return new Coups() ;
		}

		if(1 == distance_case_abs(i) && 2 == distance_case_ord(j) )
		{
			return new Coups() ;
		}

		return null ;
	}

	public int distance_case_abs(int i)
	{
		int[] pos = super.get_Position() ;
		
		int distance = pos[0] - i ;

		if(distance <= 0)
			return (distance = -distance) ;

		return distance ;
	}

	public int distance_case_ord(int j)
	{
			int[] pos = super.get_Position() ;
		
		int distance = pos[1] - j ;

		if(distance <= 0)
			return (distance = -distance) ;

		return distance ;
	}
}	 
