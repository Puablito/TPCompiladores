package AccionesSemanticas;

public class AS20 extends AccionSemantica {
	
	public void ejecutar(Character carActual, String tokenString ){
		
		this.caracterActual = carActual;
		
		tokenString = tokenString + Character.toString(caracterActual);
		
		}
	
}
