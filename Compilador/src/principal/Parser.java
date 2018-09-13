package principal;

public class Parser {
	
	private Lexico aLexico;
	private Token token; // preguntar si el token que devolvevmos es el numero o la palabra, me parece que era el numero

	public Parser(Lexico analizadorLexico) {
		this.aLexico = analizadorLexico;
		
		token = aLexico.getToken();
		while (token != null){ // recorre hasta que no haya mas tokens
			System.out.println(token.getId());
			System.out.println(token.getDato());
			token = aLexico.getToken();
		}
	aLexico.mostrarListaSimbolos();
	}

}