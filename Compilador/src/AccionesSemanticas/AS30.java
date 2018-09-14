package AccionesSemanticas;

public class AS30 extends AccionSemantica {
//Agrega el 2do guion que da inicio a un comentario dentro del codigo	
	public void ejecutar(Character carActual, String tokenString, int cantLin ){
		this.tokenString = tokenString + Character.toString(carActual);
		devuelveChar = false;
	}
}
