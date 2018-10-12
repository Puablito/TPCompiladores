package AccionesSemanticas;

public class ASERR extends AccionSemantica {
	
	public void ejecutar(Character carActual, String tokenString, int cantLin ){
		this.tokenString = "";
		
		System.out.println("Error en linea "+cantLin+": Caracter \""+carActual +"\" no esperado.");
		devuelveChar = false;
	}

}
