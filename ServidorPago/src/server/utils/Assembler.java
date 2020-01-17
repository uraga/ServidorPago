package server.utils;

import server.data.Pago;
import server.dto.PagoDTO;

public class Assembler {
	
	private static Assembler instance;
	
	private Assembler() { }
	
	public static Assembler getInstance() {
		if (instance == null) {
			instance = new Assembler();
		}
		return instance;
	}
	
	public PagoDTO assemble(Pago p) {
		PagoDTO dto = new PagoDTO();
		
		dto.setEmail(p.getEmail());
		dto.setPrecio(p.getPrecio());
		
		System.out.println("Pago correctly assembled");
		return dto;
	}
	
	public Pago disassemble(PagoDTO dto) {
		Pago p = new Pago();
		
		p.setEmail(dto.getEmail());
		p.setPrecio(dto.getPrecio());
		
		System.out.println("Pago correctly disassembled");
		return p;
	}

}
