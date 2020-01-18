package server.remote;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import server.data.UsuarioPaypal;

public class ServidorPago extends UnicastRemoteObject implements IServidorPago{

	private static final long serialVersionUID = 1L;
	private ArrayList<UsuarioPaypal> usuarios = new ArrayList<UsuarioPaypal>();

	protected ServidorPago() throws RemoteException {
		super();
		UsuarioPaypal u1 = new UsuarioPaypal();
		u1.setEmail("j.uraga@opendeusto.es"); u1.setContrasenya("12345"); u1.setMonedero(1000);
		UsuarioPaypal u2 = new UsuarioPaypal();
		u2.setEmail("gorka.garate@opendeusto.es"); u2.setContrasenya("abcd"); u2.setMonedero(1000);
		UsuarioPaypal u3 = new UsuarioPaypal();
		u3.setEmail("ibai.guillen@opendeusto.es"); u3.setContrasenya("12345"); u3.setMonedero(1000);
		UsuarioPaypal u4 = new UsuarioPaypal();
		u4.setEmail("yeray.bellanco@opendeusto.es"); u4.setContrasenya("abcd"); u4.setMonedero(1000);
		
		usuarios.add(u1);
		usuarios.add(u2);
		usuarios.add(u3);
		usuarios.add(u4);
	}

	

	@Override
	public boolean realizarPago(int precio, String email, String contrasenya) {
		System.out.println("..");
		System.out.println("..Mail: "+email );
		System.out.println("..Pass: "+ contrasenya);
		System.out.println("..Precio: "+ precio);
		for(UsuarioPaypal u : usuarios) {
			if (u.getEmail().equals(email) && u.getContrasenya().equals(contrasenya)) {
				if(u.pagar(precio) == true) {
					System.out.println("Pago realizado");
					System.out.println("..");
					return true;
				}
			}
		}
		System.out.println("Pago cancelado");
		System.out.println("..");
		return false;
	}
	
	@Override
	public boolean tieneFondos(int precio, String email, String contrasenya) throws RemoteException {
		System.out.println("..");
		System.out.println("..Mail: "+email );
		System.out.println("..Pass: "+ contrasenya);
		System.out.println("..Precio: "+ precio);
		for(UsuarioPaypal u : usuarios) {
			if (u.getEmail().equals(email) && u.getContrasenya().equals(contrasenya)) {
				if(u.getMonedero() >= precio) {
					System.out.println("Tiene fondos suficientes");
					System.out.println("..");
					return true;
				}
			}
		}
		System.out.println("No tiene fondos");
		System.out.println("..");
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
