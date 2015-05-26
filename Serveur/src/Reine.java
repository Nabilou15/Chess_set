import java.util.*;

public class Reine extends Piece
{

	private NOM Nom ;
	
	public Reine(NOM nom, COULEUR couleur) 
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
		
		if( test1 == 0 && test2 == 0)
			return null ;

		if(test1 ==0  ^ test2 == 0) //on detecte un deplacement vertical ou horizontal
			return deplacement (plateau, i, j, calcul_direction(ipiece, i), calcul_direction(jpiece, j), 1) ;

		if((test1)*(test1) == (test2)*(test2))//valeur absolue !!! on detecte un mouvement en diagonale
			return deplacement (plateau, i, j, calcul_direction(ipiece, i), calcul_direction(jpiece, j), 1) ;
		
						
		return null ;				
	}

	/**
	*	calcul de la direction
	*/
	public int calcul_direction(int pos_piece, int pos)
	{
		int k = pos_piece - pos ;
		
		if (k>0)
			return -1 ;
		
		if (k<0)
			return 1 ;		
		
		return 0 ;
	}
	
	/**
	*	mouvement de façon recursive
	*/
	public Coups deplacement (Case[][] plateau, int iarrive, int jarrive, int ipas, int jpas, int cpt_pas)
	{
		int[] pos = super.get_Position() ;	
		int ipiece = pos[0] ;
		int jpiece = pos[1] ;

		if((ipiece+(ipas*cpt_pas) == iarrive) && (jpiece+(jpas*cpt_pas) == jarrive))// on a deja testé si on peut aller sur la case de destination
		{
			System.out.println("deplacement accepté") ;
			return new Coups() ;
		}

		if( !plateau[ipiece+(ipas*cpt_pas)][jpiece+(jpas*cpt_pas)].is_empty() ) // on trouve un obstacle
		{
			return null ;
		}
		

		if( null == deplacement(plateau, iarrive, jarrive, ipas, jpas, cpt_pas+1)) // on incremente le compteur de pas. 
			return null ; 
		else
			return new Coups() ;
	}

	/**
	*	teste si la reine a un mouvement possible
	*/
	boolean peut_bouger(Case[][] plateau, ArrayList<Piece> pieces_adverses, Roi roi)
	{

		int[] ancienne_pos = super.get_Position() ; // on stock l'ancienne position du roi
		int ipiece = ancienne_pos[0] ;
		int jpiece = ancienne_pos[1] ;

		if (tester_deplacement(plateau, pieces_adverses, roi, ipiece+1, jpiece+1, 1))// on teste la diagonale
		{
			plateau[ipiece][jpiece].set_Piece(this, ipiece, jpiece) ;// on remet la piece à sa place initiale
			return true ;
		}
	
		plateau[ipiece][jpiece].set_Piece(this, ipiece, jpiece) ;
		return false ;
	}
	
	/**
	*
	*/
	public boolean tester_deplacement (Case[][] plateau, ArrayList<Piece> pieces_adverses, Roi roi, int ipas, int jpas,int cpt_pas)
	{
		int[] ancienne_pos = super.get_Position() ; // on stock l'ancienne position du roi
		int ipiece = ancienne_pos[0] ;
		int jpiece = ancienne_pos[1] ;
		
		if( !fin_deplacement(plateau, ipiece+ipas, jpiece+jpas) )
			return false ;
		
		plateau[ipiece][jpiece].vider_case() ; // on enleve la piece de la case precedente
				
		if(!plateau[ipiece+(cpt_pas*ipas)][jpiece+(jpas*cpt_pas)].is_empty())
			return test_case_non_vide(plateau, pieces_adverses, roi, ipiece+(cpt_pas*ipas), jpiece+(cpt_pas*jpas)) ;
		
		else
			plateau[ipiece+(cpt_pas*ipas)][jpiece+(cpt_pas*jpas)].set_Piece(this, ipiece+(cpt_pas*ipas), (jpiece+cpt_pas*jpas)) ;// on deplace la piece
		
		if(!roi.est_echec(pieces_adverses, plateau))
		{
		 plateau[ipiece+(cpt_pas*ipas)][jpiece+(cpt_pas*jpas)].vider_case() ; //on remettra la piece a sa place aprés.
		 return true ;
		}

		else
			return tester_deplacement (plateau, pieces_adverses, roi, ipas, jpas, cpt_pas++) ;//on applique un pas a jpas et ipas.	
	}
		
	/**
	*	test si la piece peut se deplacer vers la case
	*/
	public boolean fin_deplacement(Case[][] plateau, int i, int j)
	{

		if( (!plateau[i][j].is_empty()) && super.get_Couleur() == plateau[i][j].get_Couleur_Piece() ) //on ne peut pas tuer ses propres pieces !!!
			return false ;		

		if(i==0 || i==7) // on verifie si i sort pas du plateau
			return false ;
		
		if(j==0 || j==7) // on verifie si j sort pas du plateau	
			return false ;
		
		return true ;
	}
	
	/**
	*	teste si l'obstacle est prenable et si ça mets fin a l'echec.
	*/
	public boolean test_case_non_vide(Case[][] plateau, ArrayList<Piece> pieces_adverses, Roi roi, int i, int j )
	{
		if(super.get_Couleur() == plateau[i][j].get_Couleur_Piece())
			return false ;

		Piece piece_precedente = plateau[i][j].get_Piece() ; //on stock la piece precedente pour la remettre a sa place.
		plateau[i][j].set_Piece(this, i, j) ; // on bouge la piece.
		
		if(roi.est_echec(pieces_adverses, plateau))
		{
			plateau[i][j].set_Piece(piece_precedente, i, j) ; // on remet les pieces à leurs places
			return false ; // on retourne false et on pourra pas aller plus loin.
		}
		else
		{
			plateau[i][j].set_Piece(piece_precedente, i, j) ; // on remet les pieces à leurs places
			return true ;
		}
	}

}
	
 
