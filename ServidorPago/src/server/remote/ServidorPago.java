package server.remote;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import server.data.Pago;

public class ServidorPago extends UnicastRemoteObject implements IServidorPago{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected ServidorPago() throws RemoteException {
		super();
	}

	private ArrayList<Pago> pagos = new ArrayList<Pago>();

	@Override
	public boolean realizarPago(int precio, String email) {
		Pago p = new Pago();
		p.setEmail(email);
		p.setPrecio(precio);
		pagos.add(p);
		return true;
	}
	
	public static void main(String[] args) {
		if (args.length != 3) {
			System.out.println("usage: java [policy] [codebase] server.Server [host] [port] [server]");
			System.exit(0);
		}
		
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		
		String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
		
		try {
			IServidorPago server = new ServidorPago();
			Naming.rebind(name, server);
			System.out.println("* Server '" + name + "' active and waiting...");
		} catch (Exception e) {
			System.err.println("- Exception running the server: " + e.getMessage());
			e.printStackTrace();
		}
	}

}
