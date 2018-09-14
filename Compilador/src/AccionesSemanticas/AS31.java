package AccionesSemanticas;

public class AS31 extends AccionSemantica {
//Agrega un caracter que forma parte del comentario, luego esto sera descartado	
	public void ejecutar(Character carActual, String tokenString, int cantLin ){
		this.tokenString = tokenString + Character.toString(carActual);
		devuelveChar = false;
	}

}
