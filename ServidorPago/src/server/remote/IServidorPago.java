package server.remote;

import java.rmi.Remote;

public interface IServidorPago extends Remote{
	
	public boolean realizarPago(int precio, String email); 

}
