package AccionesSemanticas;

import principal.Token;

public class AS112 extends AccionSemantica {
	
	public void ejecutar(Character carActual, String tokenString, int cantLin ){ 
		
		boolean aux_rango = Long.parseLong(tokenString)>32768;
		//Valido rango
		if (aux_rango) {
			System.out.println("Warning("+cantLin+"): el identificador supera el rango 32768. Se procedera a descartarlo.");
			this.tokenString = "";
			darAltaTS = false;
			token = new Token(0, tokenString); 
		}else {
			
			this.tokenString = tokenString;
			//Alta en TS
			darAltaTS = true;
			
			inicializaPalabrasReservadas();
			//Enteros NORMARL = "ENO"
			if (palabrasReservadas.containsKey("ENO")) { 
				tokenInt = palabrasReservadas.get("ENO"); // guardo el valor numerico de la palabra reservada
				token = new Token(tokenInt, tokenString); // arma el token para devolver al parser
			}
		}
		
	}
}
