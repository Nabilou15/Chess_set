import java.net.Socket;

public class ThreadPartie extends Thread
{
	private Partie Partie ;
	private Joueur Joueur ;
	
	public ThreadPartie(Partie partie, Socket socket, int id_joueur)
	{
		Partie = partie ;
		
		if (id_joueur == 0)
		{
			this.Joueur = new Joueur (UTILISATEUR.BLANC, COULEUR.BLANC ,id_joueur, "") ;
		}
		
	  else if (id_joueur == 1)
	  {
	  	this.Joueur = new Joueur (UTILISATEUR.NOIR, COULEUR.NOIR, id_joueur, "") ;
	  }
	  
	  else 
	  {
	  	this.Joueur = new Joueur (id_joueur, "") ;
		}
  
		Partie.set_Socket(socket) ;
	}
	
	
	public void run()
	{
	  if(Joueur== null){
	  System.out.println("ERREUR JOUEUR NULL");
	  }
		
		if (Joueur.get_Id_joueur() <2 )
		{
			Partie.jouer(this.Joueur) ;
		}

		else
			Partie.observer(this.Joueur) ;	
	}
}
