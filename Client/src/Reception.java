import java.io.ObjectInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Classe permettant de recevoir les Coordonnées
 * 
 * @author matthieu
 */
public class Reception
{
	private Socket Socket ;
	private ObjectInputStream In ;
	
	public Reception (Socket socket)
	{
		Socket = socket ;
		//System.out.println("Socket : "+Socket) ;
		try
		{
			In = new ObjectInputStream(Socket.getInputStream()) ;
			//System.out.println("In : "+In) ;
		}
		catch (IOException e)
		{
			System.out.println("Erreur lors de la création de l'entré") ;
		}
		
		System.out.println("Flux d'entré OK") ;
	}
	
	public int[][] run()
	{
		try
		{
			Object objet_recu = In.readObject() ;
			int[][] coord = (int[][]) objet_recu ;
			return coord ;
		}
		catch (IOException e)
		{
			//System.out.println("Erreur lors de l'envois") ;
			return null ;
		}
		catch (ClassNotFoundException e)
		{
			//System.out.println("Erreur lors de l'envois") ;
			return null ;
		}
		
	}
}
