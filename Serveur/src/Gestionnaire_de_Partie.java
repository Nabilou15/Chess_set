import java.io.IOException;
import java.net.ServerSocket;



public class Gestionnaire_de_Partie
{
	
	private Thread T_connexion ;
	
	public Gestionnaire_de_Partie ()
	{
		try
		{
			ServerSocket Serveur_socket = new ServerSocket(2009) ;
			Partie partie = new Partie() ;
			new Connexions(Serveur_socket, partie) ; //accepte les connexions entrantes
		}
		
		catch (IOException e)
		{
			System.out.println("Erreur lors de la création du socket") ;
		}		
	}
}
