package AccionesSemanticas;

public class AS100 extends AccionSemantica {
//Comentario consume absolutamente TODO.	
	public void ejecutar(Character carActual, String tokenString, int cantLin ){ 
		
		if (carActual.equals('\n') || carActual.equals('\r')){
			this.tokenString = tokenString +"";
		}else {
			this.tokenString = tokenString + Character.toString(carActual);
		}
		devuelveChar = false;
	}

}