package principal;

public class Parser {
	
private Lexico aLexico;
	
	private int token; // preguntar si el token que devolvevmos es el numero o la palabra

	public Parser(Lexico analizadorLexico) {
		this.aLexico = analizadorLexico;
		
		int token = this.aLexico.getToken();
		while (token != -1){
			System.out.println(token);
			token = this.aLexico.getToken();
		}
	//this.aLexico.mostrarTablaSimbolos();
	}

}
