package AccionesSemanticas;

import principal.Token;

public class AS11 extends AccionSemantica {
	
	public void ejecutar(Character carActual, String tokenString, int cantLin){
		
		if (tokenString.length()>15) {
			System.out.println("Warning("+cantLin+"): el identificador supera los 15 caracteres. Se procedera a truncarlo.");
			this.tokenString = tokenString.substring(0, 14);
		}else {
			this.tokenString = tokenString;
		}
		devuelveChar = true;
		//Alta en TS
		darAltaTS = true;
		
		inicializaPalabrasReservadas();
		if (palabrasReservadas.containsKey("ID")) { 
			tokenInt = palabrasReservadas.get("ID"); // guardo el valor numerico de la palabra reservada
			token = new Token(tokenInt, tokenString); // arma el token para devolver al parser
		}
	}

}
