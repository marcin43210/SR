package com.srir;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServerInt extends Remote {
	String pokazUchwale() throws RemoteException;
	String glosZa() throws RemoteException;
	String glosPrzeciw() throws RemoteException;
	String glosBz() throws RemoteException;
	

}
