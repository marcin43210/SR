package com.srir;

import java.net.InetAddress;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

/// Klasa impementuj¹ca funkcjonalnoœæ serwea
	/** W klasie tej znajduj¹ siê implementacje funkcji 
	 * interfesju serwera, które s¹ udostêpniane dla klienta oraz cia³o (main) serwera aplikacji */

public class Server implements ServerInt { 
	public Server()								
	{}
	MySQLTest baza = new MySQLTest();
	
	/// Funkcja zwracaj¹ca treœæ uchwa³y
		/** Funkcja wywo³uje obiekt klast MySQLTest, pobiera, za pomoc¹ jego funkcji, treœæ uchwa³y. */
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
	/// Funkcja zwracaj¹ca g³soy uchwa³y
			/** Funkcja wywo³uje obiekt klast MySQLTest, pobiera, za pomoc¹ jego funkcji, iloœci g³osów dla danej uchwa³y. */
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
	/// Funkcja g³osuj¹ca za
	/** Funkcja wywo³uje obiekt klast MySQLTest, za pomoc¹ jego funkcji g³osuje za uchwa³¹. */
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
/// Funkcja g³osuj¹ca przeciw
	/** Funkcja wywo³uje obiekt klast MySQLTest, za pomoc¹ jego funkcji g³osuje za uchwa³¹. */
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
	/// Funkcja wstrzymuj¹ca od g³osu
		/** Funkcja wywo³uje obiekt klast MySQLTest, za pomoc¹ jego funkcji wstrzymuje siê od g³osu. */
	public String glosBz()
	{	
		
		try
		{
			
			baza.updateBz();
			System.out.println("Wstrzymano siê od g³osu");
			
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return "Wstrzymales sie od glosu!";
	}
	/// Main serwera
		/**zawarta jest tutaj funkjonalnoœæ serwera, obs³uga RMI (tworzenie rejestru, bindowanie i udostêpnianie) */
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
