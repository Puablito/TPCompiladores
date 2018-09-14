package AccionesSemanticas;

import principal.Token;

//es solo de definici�n casa AS sobreescribe el metodo
public abstract class AccionSemantica {
	
	protected int tokenInt;
	protected String tokenString;
	protected boolean devuelveChar;
	protected Token token;

	public boolean isDevuelveChar() {
		return devuelveChar;
	}

	public void setDevuelveChar(boolean devuelveChar) {
		this.devuelveChar = devuelveChar;
	}

	public String getTokenString() {
		return tokenString;
	}

	public void setTokenString(String tokenString) {
		this.tokenString = tokenString;
	}
	
	public void ejecutar(Character carActual, String tokenString ){}
	
	public Token armaToken() {
		return token;
	}
}
