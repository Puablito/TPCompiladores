package principal;

import java.io.BufferedReader;
import java.util.Enumeration;
import java.util.Hashtable;
import AccionesSemanticas.*;

public class Lexico {
	private int matrizEstados[][];
	private AccionSemantica matrizAS[][]; // Cada acción tiene una Accion Semantica dentro
	private AccionSemantica as;
	Hashtable<String,String> tablaSimbolos = new Hashtable<String,String>(); //no se cuantos campos tendrá ni el tipo de datos, genericamente la arme con 2 campos string 
	Hashtable<Character,Integer> tablaConversion = new Hashtable<Character,Integer>(); // para saber a que columna de la matriz de estado pertenece cada caracter
	BufferedReader codigoFuente;
	private int estadoActual;
	private int codigoCaracterActual;
	private int cantidadLineas=1 ;
	private int tokenId=0;
	private int fila=0;
	private int columna;
	private String tokenString;
	private Character caracterActual;
	
	// Contructor - Se usa en "Principal.java"
	public Lexico(BufferedReader fileR) {
		this.codigoFuente = fileR;
//		inicializarMatrizAS(); descomentar cuando se descomente el metodo asi no da error
		inicializarMatrizEstados();
		inicializarListaConversion();
	}
	
	public int getToken() {
		
		columna = 0;
		codigoCaracterActual = 0;
		tokenString="";
		try {
			while (fila != -1){ 								// Fin del token = -1
				codigoFuente.mark(1); 							// marco el archivo para poder devolver el caracter si es necesario
				codigoCaracterActual = codigoFuente.read(); 	// leo codigo ascii del caracter
				caracterActual = (char) codigoCaracterActual; 	// se castea para recuperar el caracter del codigo ascii leido
				if (caracterActual.equals('\n')){
					cantidadLineas++;
				}
				
				//si no esta en la tabla el get falla y da nullpointerexeption
				// por eso el if, pero que pasa si viene un caracter que no esta en la tabla?
				// ver que hacer en ese caso, que es raro que suceda
				if (tablaSimbolos.containsKey(tokenString)) { // si el caracter está devuelve el dato 
					columna = tablaConversion.get(caracterActual); // devuelve la columna a la que pertenece el caracter leido en la matriz de estados
				}
				
				// las 2 lineas siguientes se pueden hacer juntas pero me parece mas entendible separadas
				as = matrizAS[fila][columna];	// En as se asigna la accion semantica que se encuentra en la posicion de la matriz
				
				//tokenString => se va armando el token en esta variable 
				tokenString = as.ejecutar(); // pasar el caracter actual, el tokenString y lo que sea necesario y que devuelva el tokenString con la agregacion realizada del caracter 
				
				//Recupero de la matriz de estados el estado siguiente del automata (en la matriz es la fila) 
				fila = matrizEstados[fila][columna];
				
				if (fila != -2){
					// realiza gestion de errores
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		// Verifica si el token ya esta en la tabla
		if (!tablaSimbolos.containsKey(tokenString)) { 
            //No esta y lo agrego
			tablaSimbolos.put(tokenString, "ver que poner aca"); // guardo el tokenString con sus datos adicionales
        }
		
		//con el tokenString armo el tokenId y lo devuelvo
		return tokenId;
	}

// LO COMENTO PORQUE ME MOLESTAN LOS ERRORES, PORQUE NO EXISTEN LAS CLASES AS (de hincha bolas :))	- Pablo
//	public void inicializarMatrizAS() {
//		AccionSemantica matrizAS[][] = 	{  /*Col  0*/  /*Col  1*/  /*Col  2*/  /*Col  3*/  /*Col  4*/  /*Col  5*/  /*Col  6*/  /*Col  7*/  /*Col  8*/  /*Col  9*/  /*Col 10*/  /*Col 11*/  /*Col 12*/  /*Col 13*/  /*Col 14*/  /*Col 15*/  /*Col 16*/  /*Col 17*/  /*Col 18*/  /*Col 19*/
//		/* fila  0*/         			{  new AS1(),  new AS1(),  new AS1(),  new AS1(),  new AS0(),  new AS1(),  new AS1(),  new AS1(), new AS90(),  new AS1(), new AS90(), new AS90(), new AS90(), new AS90(), new AS90(),  new AS1(),  new AS1(),  new AS1(),  new AS1(),new ASERR()},
//		/* fila  1*/					{ new AS11(), new AS10(), new AS10(), new AS10(), new AS11(), new AS11(), new AS11(), new AS11(), new AS11(), new AS11(), new AS11(), new AS11(), new AS11(), new AS11(), new AS11(), new AS11(), new AS10(), new AS10(), new AS10(), new AS11()},
//		/* fila  2*/					{ new AS20(), new AS21(), new AS21(), new AS21(), new AS21(), new AS21(), new AS21(), new AS21(), new AS21(), new AS21(), new AS21(), new AS21(), new AS21(), new AS21(), new AS21(), new AS21(), new AS21(), new AS21(), new AS21(), new AS21()},
//		/* fila  3*/					{ new AS40(), new AS40(), new AS40(), new AS40(), new AS40(), new AS40(), new AS40(), new AS40(), new AS40(), new AS30(), new AS40(), new AS40(), new AS40(), new AS40(), new AS40(), new AS40(), new AS40(), new AS40(), new AS40(), new AS40()},
//		/* fila  4*/					{ new AS31(), new AS31(), new AS31(), new AS31(), new AS32(), new AS31(), new AS31(), new AS31(), new AS31(), new AS31(), new AS31(), new AS31(), new AS31(), new AS31(), new AS31(), new AS31(), new AS31(), new AS31(), new AS31(), new AS31()},
//		/* fila  5*/					{ new AS50(), new AS50(), new AS50(), new AS50(), new AS50(), new AS50(), new AS60(), new AS60(), new AS50(), new AS50(), new AS50(), new AS50(), new AS50(), new AS50(), new AS50(), new AS50(), new AS50(), new AS50(), new AS50(), new AS50()},
//		/* fila  6*/					{ new AS80(), new AS80(), new AS80(), new AS80(), new AS80(), new AS80(), new AS80(), new AS70(), new AS80(), new AS80(), new AS80(), new AS80(), new AS80(), new AS80(), new AS80(), new AS80(), new AS80(), new AS80(), new AS80(), new AS80()},
//		/* fila  7*/					{new AS100(),new AS100(),new AS100(),new AS100(),new AS100(),new AS100(),new AS100(),new AS100(),new AS100(),new AS100(),new AS100(),new AS100(),new AS100(),new AS100(),new AS100(),new AS100(),new AS100(),new AS100(),new AS100(),new AS100()},
//		/* fila  8*/         			{new AS101(),new AS101(),new AS101(),new AS101(),new AS101(),new AS101(),new AS101(),new AS101(),new AS101(),new AS101(),new AS101(),new AS101(),new AS101(),new AS101(),new AS101(),new AS102(),new AS101(),new AS101(),new AS101(),new AS101()},
//		/* fila  9*/					{new ASERR(),new ASERR(),new AS110(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new AS111()},
//		/* fila 10*/					{new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new AS112(),new AS120(),new ASERR(),new ASERR()},
//		/* fila 11*/					{new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new AS121(),new ASERR()},
//									   	};
//	}
	
	public void inicializarMatrizEstados() {
		int matrizEstados[][] = {	/* 20 Columnas*/
		/* fila 0*/				{ 2, 1, 9, 1, 0, 5, 6, 6,-1, 3,-1,-1,-1,-1,-1, 7, 1, 1, 1,-2}, // -1 = Final y -2 = Error (no se si esta bien)
		/* fila 1*/				{-1, 1, 1, 1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 1, 1, 1,-1},
		/* fila 2*/				{ 2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		/* fila 3*/				{-1,-1,-1,-1,-1,-1,-1,-1,-1, 4,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		/* fila 4*/				{ 4, 4, 4, 4,-1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4},
		/* fila 5*/				{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		/* fila 6*/				{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		/* fila 7*/				{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8},
		/* fila 8*/				{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,-1, 8, 8, 8, 8},
		/* fila 9*/				{-2,-2, 9,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,10},
		/* fila 10*/			{-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-1,11,-2,-2},
		/* fila 11*/			{-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-1,-2},
								};
		
	}
	
	
	
	public void mostrarListaSimbolos(){ //Lista las claves de la tabla de simbolos
		  System.out.println( "Simbolos: " + tablaSimbolos.keys() );
	}

	private void inicializarListaConversion(){
		//1er campo va el caracter, 2do la columna a la que pertenece en la matriz de estados 
//(COMPLETAR COLUMNA 2 CON NUESTROS DATOS - YO SOLO COPIE Y PEGUE)- Pablo
//NO SE PORQUE USAN EL CHARAT(0) PARA MI NO VA - Pablo
		tablaConversion.put("a".charAt(0), 0); 
		tablaConversion.put("b".charAt(0), 0);
		tablaConversion.put("c".charAt(0), 0);
		tablaConversion.put("d".charAt(0), 0);
		tablaConversion.put("e".charAt(0), 0);
		tablaConversion.put("f".charAt(0), 0);
		tablaConversion.put("g".charAt(0), 0);
		tablaConversion.put("h".charAt(0), 0);
		tablaConversion.put("i".charAt(0), 8);
		tablaConversion.put("j".charAt(0), 0);
		tablaConversion.put("k".charAt(0), 0);
		tablaConversion.put("l".charAt(0), 7);
		tablaConversion.put("m".charAt(0), 0);
		tablaConversion.put("n".charAt(0), 0);
		tablaConversion.put("o".charAt(0), 0);
		tablaConversion.put("p".charAt(0), 0);
		tablaConversion.put("q".charAt(0), 0);
		tablaConversion.put("r".charAt(0), 0);
		tablaConversion.put("s".charAt(0), 0);
		tablaConversion.put("t".charAt(0), 0);
		tablaConversion.put("u".charAt(0), 6);
		tablaConversion.put("v".charAt(0), 0);
		tablaConversion.put("w".charAt(0), 0);
		tablaConversion.put("x".charAt(0), 0);
		tablaConversion.put("y".charAt(0), 0);
		tablaConversion.put("z".charAt(0), 0);
		
		tablaConversion.put("A".charAt(0), 0);
		tablaConversion.put("B".charAt(0), 0);
		tablaConversion.put("C".charAt(0), 0);
		tablaConversion.put("D".charAt(0), 0);
		tablaConversion.put("E".charAt(0), 0);
		tablaConversion.put("F".charAt(0), 0);
		tablaConversion.put("G".charAt(0), 0);
		tablaConversion.put("H".charAt(0), 0);
		tablaConversion.put("I".charAt(0), 0);
		tablaConversion.put("J".charAt(0), 0);
		tablaConversion.put("K".charAt(0), 0);
		tablaConversion.put("L".charAt(0), 0);
		tablaConversion.put("M".charAt(0), 0);
		tablaConversion.put("N".charAt(0), 0);
		tablaConversion.put("O".charAt(0), 0);
		tablaConversion.put("P".charAt(0), 0);
		tablaConversion.put("Q".charAt(0), 0);
		tablaConversion.put("R".charAt(0), 0);
		tablaConversion.put("S".charAt(0), 0);
		tablaConversion.put("T".charAt(0), 0);
		tablaConversion.put("U".charAt(0), 0);
		tablaConversion.put("V".charAt(0), 0);
		tablaConversion.put("W".charAt(0), 0);
		tablaConversion.put("X".charAt(0), 0);
		tablaConversion.put("Y".charAt(0), 0);
		tablaConversion.put("Z".charAt(0), 0);
		
		tablaConversion.put("0".charAt(0), 1);
		tablaConversion.put("1".charAt(0), 1);
		tablaConversion.put("2".charAt(0), 1);
		tablaConversion.put("3".charAt(0), 1);
		tablaConversion.put("4".charAt(0), 1);
		tablaConversion.put("5".charAt(0), 1);
		tablaConversion.put("6".charAt(0), 1);
		tablaConversion.put("7".charAt(0), 1);
		tablaConversion.put("8".charAt(0), 1);
		tablaConversion.put("9".charAt(0), 1);
		
		tablaConversion.put("/".charAt(0), 2);
		
		tablaConversion.put("_".charAt(0), 3);
		
		tablaConversion.put("-".charAt(0), 4);
		
		tablaConversion.put("#".charAt(0), 5);
		
		tablaConversion.put("=".charAt(0), 9);
		
		tablaConversion.put(":".charAt(0), 10);
		
		tablaConversion.put("<".charAt(0), 11);
		
		tablaConversion.put(">".charAt(0), 12);
		
		tablaConversion.put("!".charAt(0), 13);
		
		tablaConversion.put("{".charAt(0), 14);
		
		tablaConversion.put("}".charAt(0), 23);
		
		tablaConversion.put("(".charAt(0), 21);
		
		tablaConversion.put(")".charAt(0), 22);
		
		tablaConversion.put(",".charAt(0), 15);
		
		tablaConversion.put(";".charAt(0), 16);
		
		tablaConversion.put("'".charAt(0), 17);
		
		tablaConversion.put(" ".charAt(0), 18);
		
		tablaConversion.put('\t', 18);
		
		tablaConversion.put('\n', 19);	
		
		tablaConversion.put('\r', 19);
			
	}

}
