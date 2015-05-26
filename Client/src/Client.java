import java.io.BufferedReader;

import java.io.IOException;

import java.io.InputStreamReader;

import java.io.PrintWriter;

import java.net.InetAddress;

import java.net.Socket;

import java.net.UnknownHostException;


/**
 * Classe Client.
 * Cette classe représente les Clients. C'est ici que le programme se lance.
 * On créé l'Interface depuis cette classe et on la lance. 
 * 
 * @author Legros Matthieu
 * @version 0.0
 */

public class Client 
{	
	/**
	 * Classe main qui fera un appel à interface.
	 * 
	 * @param args
	 */
	public static void main (String[] args)
	{
		Interface new_interface = new Interface() ;//on crée une nouvelle Interface.
		new_interface.run() ; //on lance l'interace.  
	}
}

