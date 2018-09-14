package AccionesSemanticas;

public class AS30 extends AccionSemantica {
//Consume el 2do guion que da inicio a un comentario dentro del codigo y como va a ser descartado se le asigna vacio.	
	public void ejecutar(Character carActual, String tokenString, int cantLin ){
		this.tokenString = "";
		devuelveChar = false;
	}
}
