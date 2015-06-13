package com.srir;

import java.net.InetAddress;
import java.rmi.RMISecurityManager;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;

public class Server implements ServerInt {
	
	public Server()
	{}
	MySQLTest baza = new MySQLTest();
	
	
	public String pokazUchwale()
	{
		String body = null;
		
		try
		{
			baza.databaseConnection=baza.getConnection();
			body = baza.viewTable();
			System.out.println("Wyslalem tresc uchwaly");
			
			
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return body;
	}
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
