import java.util.*;

public class Pion extends Piece
{

	private NOM Nom ;
	private int premier_mouvement ;
	
	public Pion(NOM nom, COULEUR couleur) 
	{
		super(couleur) ;
		this.premier_mouvement = 0 ;
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
	

	/**
	* Attention les noirs se deplacent vers le bas depuis ligne 0 (+)
	* les blanc vers le haut ! depuis ligne 7 (-)
	* 
	*/
	Coups mouvement_valide(Case[][] plateau, int i, int j)
	{
		int[] pos = super.get_Position() ;

		//deplacement vers le bas
		if(deplacement_vers_avant(plateau, i, j))
		{
			return new Coups() ;
		}

		if(deplacement_diagonale(plateau, i, j))
		{
			return new Coups() ;
		}

		if(deplacement_initial(plateau, i, j))
		{
			return new Coups() ;
		} 

		return null ;
	}
	
	/**
	*
	*
	*/
	public boolean deplacement_vers_avant(Case[][] plateau, int i, int j)
	{
		int[] pos = super.get_Position() ;


		//Deplacement vers le BAS pour joueur NOIR
		if (super.get_Couleur() == COULEUR.NOIR)
		{
			if(i == pos[0]+1 && j == pos[1])//deplacement vers le bas
			{	
				if(i>7)			
				{
					return false ;
				}

				if(plateau[i][j].is_empty())
				{
					premier_mouvement = 2 ;
					return true ;
				}
			}
		}
			
		//Deplacement vers le HAUT joueur BLANC
		if(super.get_Couleur() == COULEUR.BLANC)
			{
				if(i == pos[0]-1 && j == pos[1])//deplacement vers le bas
				{
					if(i<0)			
					{
						return false ;
					}

					if(plateau[i][j].is_empty())
					{
						premier_mouvement = 2 ;
						return true ;
					}
				}
			}

		return false ;
	}

	public boolean deplacement_diagonale(Case[][] plateau, int i, int j)
	{
		int[] pos = super.get_Position() ;
		

		/*****************************************/
		//Deplacement vers le BAS pour joueur NOIR
		/****************************************/
		if (super.get_Couleur() == COULEUR.NOIR)
		{
			//deplacement en diagonale bas et droite
			if(i == pos[0]+1 && j == pos[1]+1)
			{
				if(i>7 || j>7)// on sort du plateau
					return false ;

				if(!plateau[i][j].is_empty() && COULEUR.BLANC == plateau[i][j].get_Piece().get_Couleur())
				{
					//plateau[i][j].vider_case() ;
					premier_mouvement = 2 ;
					return true ;
				}
			}

			//deplacement en diagonale bas et gauche
			if(i == pos[0]+1 && j == pos[1]-1)
			{
				if(i>7 || j<0)// on sort du plateau
					return false ;

				if(!plateau[i][j].is_empty() && COULEUR.BLANC == plateau[i][j].get_Piece().get_Couleur())
				{
					//plateau[i][j].vider_case() ;
					premier_mouvement = 2 ;
					return true ;
				}
			}
		}
		
		/**************************************/
		//Deplacement vers le HAUT joueur BLANC
		/**************************************/
		if(super.get_Couleur() == COULEUR.BLANC)
		{
			//deplacement en diagonale haut et droite
			if(i == pos[0]-1 && j == pos[1]+1)
			{
				if(i<0 || j>7)// on sort du plateau
					return false ;

				if(!plateau[i][j].is_empty() && COULEUR.NOIR == plateau[i][j].get_Piece().get_Couleur())
				{
					//plateau[i][j].vider_case() ;
					premier_mouvement = 2 ;
					return true ;
				}
			}
			
			//deplacement en diagonale haut et gauche
			if(i == pos[0]-1 && j == pos[1]-1)
			{
				if(i<0 || j<0)// on sort du plateau
					return false ;

				if(!plateau[i][j].is_empty() && COULEUR.NOIR == plateau[i][j].get_Piece().get_Couleur())
				{
					//plateau[i][j].vider_case() ;
					premier_mouvement = 2 ;
					return true ;
				}
			}
		}

		return false ;
	}

	public boolean deplacement_initial(Case[][] plateau,  int i, int j)
	{
		int[] pos = super.get_Position() ;

	
		if(0 != premier_mouvement)
		{
				return false ;
		}

		premier_mouvement = 1 ;
		//Deplacement vers le BAS pour joueur NOIR
		if (super.get_Couleur() == COULEUR.NOIR)
		{
			if(i == pos[0]+2 && j == pos[1])//deplacement vers le bas
			{
				if(i>7)			
				{
					return false ;
				}

				if(plateau[i][j].is_empty())
				{
					premier_mouvement = 2 ;
					return true ;
				}
			}
			
		}
			
		//Deplacement vers le HAUT joueur BLANC
		if(super.get_Couleur() == COULEUR.BLANC)
			{
				if(i == pos[0]-2 && j == pos[1])//deplacement vers le bas
				{
					if(i<0)			
					{
						return false ;
					}

					if(plateau[i][j].is_empty())
					{
						premier_mouvement = 2 ;
						return true ;
					}
				}
			}

		return false ;
	}
}	 
