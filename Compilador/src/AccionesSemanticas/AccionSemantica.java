package AccionesSemanticas;

import java.util.Hashtable;

import principal.Token;

//es solo de definici�n casa AS sobreescribe el metodo
public abstract class AccionSemantica {
	
	protected int tokenInt;
	protected String tokenString;
	protected boolean devuelveChar= false;
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
		palabrasReservadas.put("-",270);
		palabrasReservadas.put("<",271);
		palabrasReservadas.put("<=",272);
		palabrasReservadas.put("<>",273);
		palabrasReservadas.put("==",274);
		palabrasReservadas.put(">=",275);
		palabrasReservadas.put(">",276);
		palabrasReservadas.put("=",277);
		palabrasReservadas.put("+",278);
		palabrasReservadas.put("/",279);
		palabrasReservadas.put("(",280);
		palabrasReservadas.put(")",281);
		palabrasReservadas.put(",",282);
		palabrasReservadas.put(";",283);
		palabrasReservadas.put("ENO",284);
		palabrasReservadas.put("ELA",285);
		palabrasReservadas.put("CAD",286);
		palabrasReservadas.put("*",287);
		
	}
}
