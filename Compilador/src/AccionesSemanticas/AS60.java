package AccionesSemanticas;

import principal.Token;

public class AS60 extends AccionSemantica {

	public void ejecutar(Character carActual, String tokenString, int cantLin){
		
		//Alta en TS
		darAltaTS = true;
		this.tokenString = tokenString + carActual;
	
		inicializaPalabrasReservadas();
		if (palabrasReservadas.containsKey(this.tokenString)) { 
			tokenInt = palabrasReservadas.get(this.tokenString); // guardo el valor numerico de la palabra reservada
			token = new Token(tokenInt,this.tokenString); // arma el token para devolver al parser
		}
	}
	
}

