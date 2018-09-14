package AccionesSemanticas;

import principal.Token;

public class AS40 extends AccionSemantica {

	public void ejecutar(Character carActual, String tokenString, int cantLin){
		
		devuelveChar = true;
		//Alta en TS
		darAltaTS = true;
		this.tokenString = tokenString;
	
		inicializaPalabrasReservadas();
		if (palabrasReservadas.containsKey("-")) { 
			tokenInt = palabrasReservadas.get("-"); // guardo el valor numerico de la palabra reservada
			token = new Token(tokenInt, tokenString); // arma el token para devolver al parser
		}
	}
	
}
