package AccionesSemanticas;

import principal.Token;

public class AS90 extends AccionSemantica {

	public void ejecutar(Character carActual, String tokenString, int cantLin){
		
		//Alta en TS
		darAltaTS = true;
		
		if (tokenString.isEmpty()) {
			this.tokenString = tokenString + carActual ;
		}else {
			this.tokenString = tokenString;
		}
		inicializaPalabrasReservadas();
		if (palabrasReservadas.containsKey(this.tokenString)) { 
			tokenInt = palabrasReservadas.get(this.tokenString); // guardo el valor numerico de la palabra reservada
			token = new Token(tokenInt, this.tokenString); // arma el token para devolver al parser
		}
	}
	
}