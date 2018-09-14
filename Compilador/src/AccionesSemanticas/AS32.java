package AccionesSemanticas;

public class AS32 extends AccionSemantica {
//Consume salto de linea o retorno de carro, que es cuando finalizan los comentarios porque son de una sola linea, como este, por ejemplo	
	public void ejecutar(Character carActual, String tokenString, int cantLin ){ 
		this.tokenString = "";
		devuelveChar = false;
	}

}
