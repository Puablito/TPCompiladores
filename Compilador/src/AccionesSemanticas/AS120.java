package AccionesSemanticas;

public class AS120 extends AccionSemantica {
	//Agrega el caracter u, parte del sufijo de enteros largos	
	public void ejecutar(Character carActual, String tokenString, int cantLin ){ 
		this.tokenString = tokenString + Character.toString(carActual);
		devuelveChar = false;
	}

}
