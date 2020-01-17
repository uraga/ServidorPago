package server.data;

public class UsuarioPaypal {
	
	int monedero;
	String email;
	String contrasenya;
	
	public int getMonedero() {
		return monedero;
	}

	public void setMonedero(int monedero) {
		this.monedero = monedero;
	}

	public String getContrasenya() {
		return contrasenya;
	}

	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean pagar(int cantidad) {
		if (monedero >= cantidad) {
			this.monedero -= cantidad;
			return true;
		}
		return false;
	}
	
	
	
	
	

}
