package AccionesSemanticas;

import java.util.Hashtable;

import principal.Token;

public class ASEOF  extends AccionSemantica {
	
private Hashtable<String,Integer> palabrasReservadas = new Hashtable<String,Integer>();
	
	public ASEOF() {
		inicializaPalabrasReservadas();
	}
	
	public void ejecutar(Character carActual, String tokenString ){
		devuelveChar = false;
		
		if (palabrasReservadas.containsKey(tokenString)) { 
			tokenInt = palabrasReservadas.get(tokenString); // guardo el valor numerico de la palabra reservada
			token = new Token(tokenInt, tokenString); // arma el token para devolver al parser
        }else if(true){
        	//Validar resto de opciones
        	token = new Token(0, tokenString);
        }
    
	}	
	
	
	private void inicializaPalabrasReservadas() {
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
		
	}
	
}
