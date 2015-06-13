package com.srir;

import java.rmi.Remote;
import java.rmi.RemoteException;
/// Interfejs udostepniany klientowi
	/**Interfejs zawieraj�cy spis funkcji, kt�re klient mo�e zdalnie wywo�a� na serwerze. Wszystkie te funkcje zaimplementowane s� w klasie "Server". */
public interface ServerInt extends Remote {
	String pokazUchwale() throws RemoteException;
	String glosZa() throws RemoteException;
	String glosPrzeciw() throws RemoteException;
	String glosBz() throws RemoteException;
	String pokazGlosy() throws RemoteException;
	

}
