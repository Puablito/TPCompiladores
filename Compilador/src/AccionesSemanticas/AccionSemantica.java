package AccionesSemanticas;
//es solo de definici�n casa AS sobreescribe el metodo
public abstract class AccionSemantica {
	
	Character caracterActual;
	String tokenString;
	
	public void ejecutar(Character carActual, String tokenString ){
		
		}
	
	public String concatenarCadena(String tokenString, Character caracterActual) {
		tokenString = tokenString + caracterActual;
		return tokenString;
	}

	public String getTokenString() {
		return tokenString;
	}

	public void setTokenString(String tokenString) {
		this.tokenString = tokenString;
	}
}
