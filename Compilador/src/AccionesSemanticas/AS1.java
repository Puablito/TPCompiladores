package AccionesSemanticas;

public class AS1 extends AccionSemantica {
	
	public void ejecutar(Character carActual, String tokenString ){
		this.tokenString = Character.toString(carActual);
		devuelveChar = false;
	}
}
