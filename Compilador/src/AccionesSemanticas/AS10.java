package AccionesSemanticas;

public class AS10 extends AccionSemantica {
	
	public void ejecutar(Character carActual, String tokenString, int cantLin ){
		this.tokenString = tokenString + Character.toString(carActual);
		devuelveChar = false;
	}

}
