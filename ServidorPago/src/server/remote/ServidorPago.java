package server.remote;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import server.data.UsuarioPaypal;

public class ServidorPago extends UnicastRemoteObject implements IServidorPago{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected ServidorPago() throws RemoteException {
		super();
	}

	private ArrayList<UsuarioPaypal> usuarios = new ArrayList<UsuarioPaypal>();
	static {
		UsuarioPaypal u1 = new UsuarioPaypal();
		u1.setEmail("j.uraga@opendeusto.es"); u1.setContrasenya("12345"); u1.setMonedero(1000);
		UsuarioPaypal u2 = new UsuarioPaypal();
		u2.setEmail("gorka.garate@opendeusto.es"); u2.setContrasenya("abcd"); u2.setMonedero(1000);
	}

	@Override
	public boolean realizarPago(int precio, String email, String contrasenya) {
		for(UsuarioPaypal u : usuarios) {
			if (u.getEmail().equals(email) && u.getContrasenya().equals(contrasenya)) {
				return u.pagar(precio);
			}
		}
		return false;
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
