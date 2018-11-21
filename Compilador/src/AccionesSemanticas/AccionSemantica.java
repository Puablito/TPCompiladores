package AccionesSemanticas;

import java.util.Hashtable;
import java.util.Map;
import principal.Token;
import principal.ValoresTS;

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
	
	public void DarAltaTS(String tokenString, int tokenID, String tokenTipoS, Hashtable<String,Integer> tablaSimbolosLex, Map<String, ValoresTS> tablaSimbolosMapLex) {
		tablaSimbolosLex.put(tokenString, tokenID);
	
		ValoresTS v = new ValoresTS();
		if (tokenTipoS != "") {
		v.setTokenID(tokenID);
		v.setTokenTipo(tokenTipoS);
		}
		tablaSimbolosMapLex.put(tokenString, v);
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
		palabrasReservadas.put("<=",269);
		palabrasReservadas.put("<>",270);
		palabrasReservadas.put("==",271);
		palabrasReservadas.put(">=",272);
		palabrasReservadas.put("ENO",273);
		palabrasReservadas.put("ELA",274);
		palabrasReservadas.put("CAD",275);
		palabrasReservadas.put("(",40);
		palabrasReservadas.put(")",41);
		palabrasReservadas.put("*",42);
		palabrasReservadas.put("+",43);
		palabrasReservadas.put(",",44);
		palabrasReservadas.put("-",45);
		palabrasReservadas.put("/",47);
		palabrasReservadas.put(";",59);
		palabrasReservadas.put("<",60);
		palabrasReservadas.put("=",61);
		palabrasReservadas.put(">",62);	
		}
}
