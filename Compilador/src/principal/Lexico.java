package principal;

import java.io.BufferedReader;
import java.util.Hashtable;
import AccionesSemanticas.*;

public class Lexico {
	private int matrizEstados[][];
	private Accion matrizAS[][] = new Accion[15][15]; // Cada acción tiene una Accion Semantica dentro
	//Hashtable<String,Simbolo> tablaSimbolos = new Hashtable...;
	BufferedReader codigoFuente;
	private int estadoActual;
	private Character caracterActual;
	private int tokenId=0;
	
	// Contructor 
	public Lexico(String path) {
		
	}
	
	public int getToken() {
		estadoActual = 0;
		while (estadoActual != -1){
			//caracterActual = codigoFuente.read();
			//as = martizAS[estadoActual][caracterActual];
			//tokenId = as.ejecutar(...);
			estadoActual = matrizEstados[estadoActual][caracterActual];
		}
	return tokenId;
	}
}
