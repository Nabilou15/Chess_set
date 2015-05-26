import java.util.*;

public class Tour extends Piece
{

	private NOM Nom ;
	
	public Tour(NOM nom, COULEUR couleur) 
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
		int test1 = ipiece-i;
		int test2 = jpiece-j;
								
		if( (!plateau[i][j].is_empty()) && super.get_Couleur() == plateau[i][j].get_Couleur_Piece() ) //on ne peut pas tuer ses propres pieces !!!
			return null ;
		
		if(ipiece - i > 0 && test2 == 0)	
			return deplacement_nord(plateau, i, j) ;
		
		if(ipiece - i < 0 && test2 == 0)	
			return deplacement_sud(plateau, i, j) ;	
			
		if(jpiece - j < 0 && test1 == 0)	
			return deplacement_est(plateau, i, j) ;	
			
		if(jpiece - j > 0 && test1 == 0)	
			return deplacement_ouest(plateau, i, j) ;
			
		return null ;
	}
	
	
	
	Coups deplacement_nord(Case[][] plateau, int i, int j)
	{
		int[] pos = super.get_Position() ;
		int ipiece = pos[0] ;
		int jpiece = pos[1];
		
		
		for(int pas = 1; pas < ipiece - i; pas++)
		{
			if( !plateau[ipiece - pas][jpiece].is_empty() )
			{
				//System.out.println("valeur du pas : "+pas) ;
				return null ;
			}
			
		}
		
		return new Coups() ;
		
	
	}
	
	
	Coups deplacement_sud(Case[][] plateau, int i, int j)
	{
		int[] pos = super.get_Position() ;
		int ipiece = pos[0] ;
		int jpiece = pos[1];
		
		
		for(int pas = 1; pas < i - ipiece; pas++)
		{
			if( !plateau[ipiece + pas][jpiece].is_empty() )
			{
				return null ;
			}
			
		}
		
		return new Coups() ;
	
	}
	
	
	Coups deplacement_est(Case[][] plateau, int i, int j)
	{
		int[] pos = super.get_Position() ;
		int ipiece = pos[0] ;
		int jpiece = pos[1];
		
		
		for(int pas = 1; pas < j - jpiece; pas++)
		{
			if( !plateau[ipiece][jpiece + pas].is_empty() )
			{
				return null ;
			}
			
		}
		
		return new Coups() ;
	
	}
	
	Coups deplacement_ouest(Case[][] plateau, int i, int j)
	{
		int[] pos = super.get_Position() ;
		int ipiece = pos[0] ;
		int jpiece = pos[1];
		
		
		for(int pas = 1; pas < j - jpiece; pas++)
		{
			if( !plateau[ipiece][jpiece - pas].is_empty() )
			{
				return null ;
			}
			
		}
		
		return new Coups() ;
	
	}
	
	
	
}	 
