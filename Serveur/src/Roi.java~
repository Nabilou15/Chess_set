import java.util.*;

public class Roi extends Piece
{

	private NOM Nom ;
	
	public Roi(NOM nom, COULEUR couleur) 
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
		int[] pos = super.get_Position() ;
		int ipiece = pos[0];
		int jpiece = pos[1];
		
		/*if(ipiece == i && jpiece == j)
			return false ;*/

		if( (!plateau[i][j].is_empty()) && super.get_Couleur() == plateau[i][j].get_Couleur_Piece() ) //on ne peut pas tuer ses propres pieces !!!
			return null ;
		
		if(case_adjacente(i,j))
			return new Coups() ;
			
		return null ;	
		
	}
	
	/**
	* Pour savoir si la position (i,j) est adjacente au roi.
	*/
	public boolean case_adjacente(int i, int j)
	{
	
		int[] pos = super.get_Position() ;
		int ipiece = pos[0] ;
		int jpiece = pos[1] ;
		
		for(int pasi = -1; pasi < 2; pasi++)
		{
			for(int pasj = -1; pasj < 2; pasj++)
			{
				if(ipiece == i && jpiece == j)// la piece ne peut pas faire du sur-place.
				{
					return false ;
				}
				
				if (ipiece + pasi == i && jpiece + pasj == j)
					return true ;
			}
		}
		
		return false ;
	}

	/**
	*	teste si une piece a accés à la position du roi
	*/
	public boolean est_echec (ArrayList<Piece> pieces_adverses, Case[][] plateau)
	{
		int pos[] = super.get_Position() ;
		int iroi = pos[0] ;
		int jroi = pos[1] ;

		for (int i = 0 ; i<16 ; i++)
		{
			if(!pieces_adverses.get(1).est_tue())
			{
				if(null != pieces_adverses.get(1).mouvement_valide(plateau, iroi, jroi))
				{
					return true ;
				}
			}
		}		

		return false ;
	}

	/**
	*	teste si la piece peut bouger sans que le roi soit en echec.
	*
	*/
	boolean peut_bouger(Case[][] plateau, ArrayList<Piece> pieces_adverses, Piece roi)
	{
		int[] ancienne_pos = super.get_Position() ; // on stock l'ancienne position du roi
		int ipiece = ancienne_pos[0] ;
		int jpiece = ancienne_pos[1] ;

		plateau[ipiece][jpiece].vider_case() ; // on enleve la piece de la case.

		for(int i = -1 ; i<2 ; i++)
		{
			for(int j = -1 ; i<2 ; j++)
			{
				if(null != this.mouvement_valide(plateau, ipiece+i, jpiece+j))
				{
					plateau[ipiece+i][jpiece+j].set_Piece(this,ipiece+i,jpiece+j) ; // on deplace la piece vers la position
					if(!this.est_echec(pieces_adverses,plateau)) // on vérifie qu'aprés ce deplacement le roi n'est plus en echec.
					{
						//plateau[ipiece+i][jpiece+j].vider_case() ;
						return true ;
					}
				}
 			}
		}

		//plateau[ipiece][jpiece].set_Piece(this,ipiece,jpiece) ;
		return false ;
	}
}	 
