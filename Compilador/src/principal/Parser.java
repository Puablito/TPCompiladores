package principal;

public class Parser {
	
	private Lexico aLexico;
	private int token; // preguntar si el token que devolvevmos es el numero o la palabra, me parece que era el numero

	public Parser(Lexico analizadorLexico) {
		this.aLexico = analizadorLexico;
		
		token = this.aLexico.getToken();
		while (token != -1){ // recorre hasta que no haya mas tokens
			System.out.println(token);
			token = this.aLexico.getToken();
		}
	aLexico.mostrarListaSimbolos();
	}

}
