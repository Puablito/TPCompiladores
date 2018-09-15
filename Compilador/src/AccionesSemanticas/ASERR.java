package AccionesSemanticas;

public class ASERR extends AccionSemantica {
	
	public void ejecutar(Character carActual, String tokenString, int cantLin ){
		this.tokenString = Character.toString(carActual);
		devuelveChar = false;
	}

}
