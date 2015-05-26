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
		try
		{
			In = new ObjectInputStream(Socket.getInputStream()) ;
		}
		catch (IOException e)
		{
			System.out.println("Erreur lors de la création de l'entré") ;
		}
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
			System.out.println("Erreur lors de la reception") ;
			return null ;
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("Erreur lors de la reception") ;
			return null ;
		}
		
	}
	
	public int[][] annule()
	{
		try
		{
			Object objet_recu = In.readObject() ;
			int[][] coord = (int[][]) objet_recu ;
			int[][] anciennes_coord = new int [2][2] ;
			anciennes_coord = coord ;
			anciennes_coord[1][0] = coord[0][0] ;
			anciennes_coord[1][1] = coord[0][1] ;
			return anciennes_coord ;
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
