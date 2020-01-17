package server.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServidorPago extends Remote{
	
	public boolean realizarPago(int precio, String email, String contrasenya) throws RemoteException; 

}
