package com.srir;

import java.net.InetAddress;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

/// Klasa impementuj�ca funkcjonalno�� serwea
	/** W klasie tej znajduj� si� implementacje funkcji 
	 * interfesju serwera, kt�re s� udost�pniane dla klienta oraz cia�o (main) serwera aplikacji */

public class Server implements ServerInt { 
	public Server()								
	{}
	MySQLTest baza = new MySQLTest();
	
	/// Funkcja zwracaj�ca tre�� uchwa�y
		/** Funkcja wywo�uje obiekt klast MySQLTest, pobiera, za pomoc� jego funkcji, tre�� uchwa�y. */
	public String pokazUchwale()

	{
		String body = null;
		
		try
		{
			baza.databaseConnection=baza.getConnection();
			body = baza.viewTable();
			System.out.println("Wyslalem tresc uchwaly.");
			
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return body;
	}
	/// Funkcja zwracaj�ca g�soy uchwa�y
			/** Funkcja wywo�uje obiekt klast MySQLTest, pobiera, za pomoc� jego funkcji, ilo�ci g�os�w dla danej uchwa�y. */
	public String pokazGlosy()
	{
		String wynik=null;
		try
		{
			baza.databaseConnection=baza.getConnection();
			wynik = baza.viewVotes();
			
			
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return wynik;
	}
	/// Funkcja g�osuj�ca za
	/** Funkcja wywo�uje obiekt klast MySQLTest, za pomoc� jego funkcji g�osuje za uchwa��. */
public String glosZa()
	{	
		
		try
		{
			
			baza.updateZa();
			System.out.println("Zaglosowano za uchwala");
			
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return "Zaglosowales za uchwala!";
	}
/// Funkcja g�osuj�ca przeciw
	/** Funkcja wywo�uje obiekt klast MySQLTest, za pomoc� jego funkcji g�osuje za uchwa��. */
	public String glosPrzeciw()
	{	
		
		try
		{
			
			baza.updatePrzeciw();
			System.out.println("Zaglosowano przeciw uchwale");
			
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return "Zaglosowales przeciw uchwale!";
	}
	/// Funkcja wstrzymuj�ca od g�osu
		/** Funkcja wywo�uje obiekt klast MySQLTest, za pomoc� jego funkcji wstrzymuje si� od g�osu. */
	public String glosBz()
	{	
		
		try
		{
			
			baza.updateBz();
			System.out.println("Wstrzymano si� od g�osu");
			
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return "Wstrzymales sie od glosu!";
	}
	/// Main serwera
		/**zawarta jest tutaj funkjonalno�� serwera, obs�uga RMI (tworzenie rejestru, bindowanie i udost�pnianie) */
	public static void main(String[] args) {
		
		
		try
		{
			System.setSecurityManager(new RMISecurityManager());	
			Server obj = new Server();
			ServerInt stub = (ServerInt) UnicastRemoteObject.exportObject(obj, 0);
			System.out.println(InetAddress.getLocalHost().getHostAddress());
			
			
			Registry registry = LocateRegistry.createRegistry(1099);
			registry.bind("ServerInt", stub);
			System.out.println("Serwer gotowy");
		}catch (Exception e)
		{
			System.err.println("Wyjatek przy odpalaniu serwera: " + e.toString());
			e.printStackTrace();
		}

	}

}
