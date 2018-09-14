package AccionesSemanticas;

public class AS111 extends AccionSemantica {
// Agrega a la cadena el _ que va antes del sufijo del nro		
		public void ejecutar(Character carActual, String tokenString, int cantLin ){
			this.tokenString = tokenString + Character.toString(carActual);
			devuelveChar = false;
		}

}
