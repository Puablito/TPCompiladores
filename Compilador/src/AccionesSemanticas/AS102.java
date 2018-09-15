package AccionesSemanticas;

import principal.Token;

public class AS102  extends AccionSemantica {

	public void ejecutar(Character carActual, String tokenString, int cantLin ){ 
		this.tokenString = tokenString + Character.toString(carActual);
		devuelveChar = false;
	}

}