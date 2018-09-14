package AccionesSemanticas;

import java.util.Hashtable;

import principal.Token;

//es solo de definici�n casa AS sobreescribe el metodo
public abstract class AccionSemantica {
	
	protected int tokenInt;
	protected String tokenString;
	protected boolean devuelveChar;
	protected Token token;
	protected boolean darAltaTS=false;
	protected Hashtable<String,Integer> palabrasReservadas = new Hashtable<String,Integer>();
	
	public boolean isDarAltaTS() {
		return darAltaTS;
	}

	public void setDarAltaTS(boolean darAltaTS) {
		this.darAltaTS = darAltaTS;
	}

	public boolean isDevuelveChar() {
		return devuelveChar;
	}

	public void setDevuelveChar(boolean devuelveChar) {
		this.devuelveChar = devuelveChar;
	}

	public String getTokenString() {
		return tokenString;
	}

	public void setTokenString(String tokenString) {
		this.tokenString = tokenString;
	}
	
	public void ejecutar(Character carActual, String tokenString, int cantLin){}
	
	public Token armaToken() {
		return token;
	}
	
	protected void inicializaPalabrasReservadas() {
		palabrasReservadas.put("IF",257);
		palabrasReservadas.put("THEN",258);
		palabrasReservadas.put("ELSE",259);
		palabrasReservadas.put("ENDIF",260);
		palabrasReservadas.put("PRINT",261);
		palabrasReservadas.put("BEGIN",262);
		palabrasReservadas.put("END",263);
		palabrasReservadas.put("INT",264);
		palabrasReservadas.put("ULONG",265);
		palabrasReservadas.put("WHILE",266);
		palabrasReservadas.put("DO",267);
		palabrasReservadas.put("ID",268);
		palabrasReservadas.put("CTE",269);
		
	}
}
