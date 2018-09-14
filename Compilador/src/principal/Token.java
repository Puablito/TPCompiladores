package principal;

public class Token {
	private String dato;
	private int id; 
	
	public Token(int nro, String cadena ) {
		this.dato=cadena;
		this.id=nro;
	}
	
	public void setDato(String dato) {
		this.dato = dato;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDato(){
		return dato;
	}
	
	public int getId(){
		return id;
	}
}