package AccionesSemanticas;

public class AS110 extends AccionSemantica {
// Agrega a la cadena un digito mas leido		
			public void ejecutar(Character carActual, String tokenString, int cantLin ){
				this.tokenString = tokenString + Character.toString(carActual);
				devuelveChar = false;
			}

}
