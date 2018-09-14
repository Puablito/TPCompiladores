package AccionesSemanticas;

public class AS20 extends AccionSemantica {
	
	public void ejecutar(Character carActual, String tokenString ){
		this.tokenString = tokenString + Character.toString(carActual);
		devuelveChar = false;
	}
	
}
