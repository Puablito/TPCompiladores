package AccionesSemanticas;
//es solo de definici�n casa AS sobreescribe el metodo
public abstract class AccionSemantica {
	
	public String ejecutar() {
		return "";
		}
	
	public String concatenarCadena(String tokenString, Character caracterActual) {
		tokenString = tokenString + caracterActual;
		return tokenString;
	}
}
