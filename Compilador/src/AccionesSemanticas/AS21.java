package AccionesSemanticas;

import principal.*;


public class AS21 extends AccionSemantica {
	
	public AS21() {
		inicializaPalabrasReservadas();
	}
	
	
	public void ejecutar(Character carActual, String tokenString, int cantLin){
		devuelveChar = true;

		if (palabrasReservadas.containsKey(tokenString)) { 
			tokenInt = palabrasReservadas.get(tokenString); // guardo el valor numerico de la palabra reservada
			token = new Token(tokenInt, tokenString); // arma el token para devolver al parser
        }else {
        	token = new Token(0, tokenString);
        }	
	}

}
