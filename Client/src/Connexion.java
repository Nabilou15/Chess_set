import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException ;

public class Connexion 
{
	private Socket Socket ;
	private DataInputStream in ;
	private int Id_joueur ;
	
	public Connexion()
	{
		System.out.println("Demande de connexion") ;
		try
		{
			Socket = new Socket("127.0.0.1", 2009) ;
			System.out.println("Demande de connexion acceptée") ;
			/*in = new DataInputStream(Socket.getInputStream()) ;
			Id_joueur = in.readInt() ;
			System.out.println("id_joueur : "+ Id_joueur) ;
			if(Id_joueur == 0)
			{
			System.out.println("Vous avez les blancs") ;
			}
			
			else if(Id_joueur == 1)
			{
			System.out.println("Vous avez les noirs") ;
			}
			
			else
			{
			System.out.println("Vous êtes spectateur") ;
			}*/
		}
		
		catch (UnknownHostException e)
		{
			e.printStackTrace() ;
		}
		
	 	catch (IOException e)
	 	{ 
	 		e.printStackTrace();
	 	}
	}

	public int recevoir_Id_joueur()
	{
		System.out.println("Demande d'attribution d'un Id_joueur") ;
		try
		{
			in = new DataInputStream(Socket.getInputStream()) ;
			Id_joueur = in.readInt() ;
			System.out.println("id_joueur : "+ Id_joueur) ;
			if(Id_joueur == 0)
			{
			System.out.println("Vous avez les blancs") ;
			}
			
			else if(Id_joueur == 1)
			{
			System.out.println("Vous avez les noirs") ;
			}
			
			else
			{
			System.out.println("Vous êtes spectateur") ;
			}		
		}
		
		catch (UnknownHostException e)
		{
			e.printStackTrace() ;
		}
		
	 	catch (IOException e)
	 	{ 
	 		e.printStackTrace();
	 	}

		return Id_joueur ;
	}
	/**
	 * 
	 * @return
	 */
	public Socket get_Socket()
	{
		//System.out.println("Socket : "+Socket) ;
		return Socket ;
	}
	
	public int get_Id_joueur()
	{
		return Id_joueur ;
	}

}
