import java.util.*;

public class Fou extends Piece
{

	private NOM Nom ;
	
	public Fou(NOM nom, COULEUR couleur) 
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
			int ipiece = pos[0] ;
			int jpiece = pos[1];
			int test1 = ipiece-i;
			int test2 = jpiece-j;
			
			System.out.println(test1+";"+test2) ;
			
			if((test1)*(test1)!=(test2)*(test2))//valeur absolue !!!
				return null ;
					
			if( (!plateau[i][j].is_empty()) && super.get_Couleur() == plateau[i][j].get_Couleur_Piece() ) //on ne peut pas tuer ses propres pieces !!!
				return null ;
				
			if(ipiece-i < 0 && jpiece-j < 0 )
				return diago_sud_est(plateau, i, j) ;
				
			if(ipiece-i > 0 && jpiece-j > 0 )
				return diago_nord_ouest(plateau, i, j) ;
				
			if(ipiece-i < 0 && jpiece-j > 0 )
				return diago_sud_ouest(plateau, i, j) ;
				
			if(ipiece-i > 0 && jpiece-j < 0 )
				return diago_nord_est(plateau, i, j) ;	
				
				
		 return null ;				
	}


	Coups diago_sud_est (Case[][] plateau, int i, int j)
	{
			int[] pos = super.get_Position() ;
			
			int ipiece = pos[0] ;
			int jpiece = pos[1];
	
			System.out.println("passage diago sud est") ;
			
			for(int pas = 1 ; pas<i-ipiece ; pas++)
			{
				if( !plateau[ipiece+pas][jpiece+pas].is_empty() )
				{
					System.out.println("valeur du pas : "+pas) ;
					return null ;
				}
			}
		
			return new Coups() ;
	}
	
	Coups diago_nord_ouest (Case[][] plateau, int i, int j)
	{
			int[] pos = super.get_Position() ;	
			int ipiece = pos[0] ;
			int jpiece = pos[1];

			System.out.println("passage diago nord ouest ") ;
			
			for(int pas = 1 ; pas<ipiece-i ; pas++) // on rejoind les lignes 0.0
			{
				if( !plateau[ipiece-pas][jpiece-pas].is_empty() )
				{
					System.out.println("valeur du pas : "+pas) ;
					return null;
				}
			}
			
			return new Coups() ;
	}

	
		Coups diago_sud_ouest (Case[][] plateau, int i, int j)
	  {
			int[] pos = super.get_Position() ;	
			int ipiece = pos[0] ;
			int jpiece = pos[1];
			
			System.out.println("passage diago sud ouest") ;
			for(int pas = 1 ; pas<i-ipiece ; pas++)
			{
				if( !plateau[ipiece+pas][jpiece-pas].is_empty() )
				{
					System.out.println("valeur du pas : "+pas) ;
					return null ;
				}
			}
			return new Coups() ;
		}	
	
	Coups diago_nord_est (Case[][] plateau, int i, int j)
	{
			int[] pos = super.get_Position() ;	
			int ipiece = pos[0] ;
			int jpiece = pos[1];

			System.out.println("passage diago nord est") ;
			
			for(int pas = 1 ; pas<ipiece-i ; pas++)
			{
				if( !plateau[ipiece-pas][jpiece+pas].is_empty() )
				{
					System.out.println("valeur du pas : "+pas) ;
					return null ;
				}
			}
			return new Coups() ;	
	}
	
}
