import java.io.IOException;
import java.io.DataOutputStream ;
import java.net.ServerSocket;
import java.net.Socket;

public class Connexions //extends Thread
{
	private Partie Partie ;
	private int Joueurs_connectes ;
	private int Observateurs_connectes ;
	private ServerSocket Serveur_socket = null ;
	private Socket Socket = null ;
	
	/**
	 * Constructeur Connexions
	 * A completer !
	 */
	public Connexions(ServerSocket serveur_socket, Partie partie)
	{
		this.Serveur_socket = serveur_socket ;
		this.Partie = partie ;
		Joueurs_connectes = 0 ;
		Observateurs_connectes = 2 ;
		this.run() ; /* Pour lancer la méthode run*/
	}
	
	/***********************************************/
	/**************** ACCESSEURS ******************/
	/*********************************************/
	
	/**
	 * Permet de récupéré le socket ou s'est connecté le joueur
	 * 
	 * @return Socket
	 */
	public Socket get_Socket()
	{
		return Socket ;
	}
	
	/***********************************************/
	/**************** MODIFIEUR  ******************/
	/*********************************************/
	
	public void run()
	{
		try
		{

			while (true)
			{
				Socket = Serveur_socket.accept() ;
				System.out.println("Un joueur s'est connecté") ;
				//System.out.println("Socket créé : " +Socket) ;
				DataOutputStream out = new DataOutputStream(Socket.getOutputStream()) ;

				if(2 > Joueurs_connectes)
				{
					out.writeInt(Joueurs_connectes) ;
					out.flush() ;
					Thread t_partie = new ThreadPartie(Partie, Socket, Joueurs_connectes) ;
					Joueurs_connectes++ ;
					t_partie.start() ;
				}

				else
				{

					out.writeInt(Observateurs_connectes) ;
					out.flush() ;
					Thread t_partie = new ThreadPartie(Partie, Socket, Observateurs_connectes) ;
					Observateurs_connectes++ ;
					t_partie.start();
				}

				if(Observateurs_connectes == 12)
				{
					System.out.println("Quota de joueurs atteint") ;
					break ;
				}

			}
		}
		
		catch (IOException e)
		{
			System.out.println("Erreur lors de la création du socket") ;
		}
	}
	
	
	public void close()
	{
		try
		{
			Serveur_socket.close() ;
			Socket.close() ;
			System.out.println("Un joueur s'est deconnecté") ;
		}
		catch(IOException e)
		{
			System.out.println("Erreur lors de la fermeture du socket") ;
		}
	}
}
