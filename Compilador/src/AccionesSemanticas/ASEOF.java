package AccionesSemanticas;

import principal.Token;

public class ASEOF  extends AccionSemantica {
	
	public ASEOF() {
		inicializaPalabrasReservadas();
	}
	
	public void ejecutar(Character carActual, String tokenString, int cantLin){
		devuelveChar = false;
		
		if (palabrasReservadas.containsKey(tokenString)) { 
			tokenInt = palabrasReservadas.get(tokenString); // guardo el valor numerico de la palabra reservada
			token = new Token(tokenInt, tokenString); // arma el token para devolver al parser
        }else if(true){
   
        	//Validar resto de opciones
        	token = new Token(0, tokenString);
        	System.out.println("Error en linea "+ cantLin +" Identificador: "+ tokenString);
   
        }
    
	}	
	
}
