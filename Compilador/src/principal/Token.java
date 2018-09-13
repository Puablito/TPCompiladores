package principal;

public class Token {
	private String dato;
	private int id; 
	
	public void setToken(int nro, String cadena ) {
		this.dato=cadena;
		this.id=nro;
	}
	
	public void getDato(){
		return dato;
	}
	
	public void getId(){
		return id;
	}
}