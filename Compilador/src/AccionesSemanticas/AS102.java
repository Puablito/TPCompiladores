package AccionesSemanticas;

import principal.Token;

public class AS102  extends AccionSemantica {

	public void ejecutar(Character carActual, String tokenString, int cantLin ){ 
		this.tokenString = tokenString + Character.toString(carActual);
		devuelveChar = false;
		darAltaTS = true;
		
		inicializaPalabrasReservadas();
		if (palabrasReservadas.containsKey("CAD")) { 
			tokenInt = palabrasReservadas.get("CAD"); // guardo el valor numerico de la palabra reservada
			token = new Token(tokenInt, this.tokenString); // arma el token para devolver al parser
			token.setTokenTipo("STRING");
		}
	}

}