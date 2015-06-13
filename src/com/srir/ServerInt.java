package com.srir;

import java.rmi.Remote;
import java.rmi.RemoteException;
/// Interfejs udostepniany klientowi
	/**Interfejs zawieraj¹cy spis funkcji, które klient mo¿e zdalnie wywo³aæ na serwerze. Wszystkie te funkcje zaimplementowane s¹ w klasie "Server". */
public interface ServerInt extends Remote {
	String pokazUchwale() throws RemoteException;
	String glosZa() throws RemoteException;
	String glosPrzeciw() throws RemoteException;
	String glosBz() throws RemoteException;
	String pokazGlosy() throws RemoteException;
	

}
