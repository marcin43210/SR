package com.srir;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
///Klasa zawieraj�ca funkcjonalno�� klienta
	/**W klasie tej znajduje si� po��czenie klienta z serwerem (localhost b�d� odpowiednie ip), implementacja RMI (pobranie rejestru i interfejsu od serwera, wywoa�enie ��danych funkcji serwera). */
public class Client {
	
	private Client(){};

	public static void main(String[] args) {
		String host = (args.length<1) ? null : args[0];
		try
		{
			Registry registry = LocateRegistry.getRegistry(host);
			ServerInt stub = (ServerInt) registry.lookup("ServerInt");
			String response = stub.pokazUchwale();
			
			System.out.println("Uchwala: " + response);
			System.out.println("Jezeli chcesz zaglosowac za - wybierz 1, przeciw - wybierz 2, nie masz zdania - wybierz 3");
			
			Scanner input = new Scanner(System.in);
			int i=input.nextInt();
			/** Wyb�r g�osu przez klienta oraz wywo�anie funkcji serwera */
			switch (i) {
			  case 1:
			  { 
			    String info = stub.glosZa();
			  System.out.println(info);
			  }
			  case 2:
			  {
				  String info = stub.glosPrzeciw();
				  System.out.println(info);
			  }
			  case 3:
			  {
				  String info = stub.glosBz();
				  System.out.println(info);
			  }
			    
			}
			String wyniki = stub.pokazGlosy();
			System.out.println(wyniki);
			
		}catch (Exception e)
		{
			System.err.println("Wyjatek klienta: " + e.toString());
			e.printStackTrace();
		}
		

	}

}
