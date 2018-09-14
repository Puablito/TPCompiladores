package principal;

public class Parser {
	
	private Lexico aLexico;
	private Token token; // preguntar si el token que devolvevmos es el numero o la palabra, me parece que era el numero

	public Parser(Lexico analizadorLexico) {
		this.aLexico = analizadorLexico;
		
		token = aLexico.getToken();
		while (token.getId() != -1){ // recorre hasta que no haya mas tokens
			System.out.println(token.getId() +","+  token.getDato());
			token = aLexico.getToken();
		}
	//aLexico.mostrarListaSimbolos();
	}

}