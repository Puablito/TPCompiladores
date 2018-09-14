package AccionesSemanticas;

import principal.Token;

public class AS121 extends AccionSemantica {
	
	public void ejecutar(Character carActual, String tokenString, int cantLin ){ 
		
		boolean aux_rango = Long.parseLong(tokenString)>4294967295L;
		//Valido rango
		if (aux_rango) {
			System.out.println("Warning("+cantLin+"): el identificador supera el rango 4294967295. Se procedera a descartarlo.");
			this.tokenString = "";
			darAltaTS = false;
			token = new Token(0, tokenString); 
		}else {
			
			this.tokenString = tokenString;
			//Alta en TS
			darAltaTS = true;
			
			inicializaPalabrasReservadas();
			//Enteros LARGOS = "ELA"
			if (palabrasReservadas.containsKey("ELA")) { 
				tokenInt = palabrasReservadas.get("ELA"); // guardo el valor numerico de la palabra reservada
				token = new Token(tokenInt, tokenString); // arma el token para devolver al parser
			}
		}
		
	}
}
