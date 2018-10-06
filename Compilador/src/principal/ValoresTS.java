package principal;

public class ValoresTS {
	
	public  String tokenTipo;
	public  int tokenID;

	public void Values( String tokenTipo,  int tokenID) {
		this.tokenTipo = tokenTipo;
	    this.tokenID = tokenID;
	}
	  
	public String getTokenTipo() {
		return tokenTipo;
	}

	public void setTokenTipo(String tokenTipo) {
		this.tokenTipo = tokenTipo;
	}

	public int getTokenID() {
		return tokenID;
	}

	public void setTokenID(int tokenID) {
		this.tokenID = tokenID;
	}

}
