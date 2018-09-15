package AccionesSemanticas;

public class AS101 extends AccionSemantica {

	public void ejecutar(Character carActual, String tokenString, int cantLin ){ 
		
		if (carActual.equals('\n') || carActual.equals('\r')){
			this.tokenString = tokenString + " ";
		}else {
			this.tokenString = tokenString + Character.toString(carActual);
		}
		devuelveChar = false;
	}

}
