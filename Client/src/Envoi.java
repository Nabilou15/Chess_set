import java.io.ObjectOutputStream;
import java.net.Socket;
import java.io.IOException;

/**
 * Classe permettant d'envoyer les Coordonnées
 * 
 * @author matthieu
 */
public class Envoi 
{
	private Socket Socket ;
	private ObjectOutputStream Out ;
	
	public Envoi (Socket socket)
	{
		Socket = socket ;
		try
		{
			//System.out.println("Socket : "+Socket) ;
			Out = new ObjectOutputStream(Socket.getOutputStream()) ;
		}
		catch (IOException e)
		{
			System.out.println("Erreur lors de la création de la sortie") ;
		}
		System.out.println("Flux de sortie OK") ;
	}
	
	public int run(int[][] coord)
	{
		try
		{
			Out.writeObject(coord) ;
			Out.flush() ;
		}
		catch (IOException e)
		{
			System.out.println("Erreur lors de l'envois") ;
			return -1 ;
		}
		
		return 0 ;
	}
}