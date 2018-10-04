package principal;

import java.io.BufferedReader;
import java.util.Enumeration;
import java.util.Hashtable;


import AccionesSemanticas.*;

public class Lexico {
	
	private int matrizEstados[][];
	private int codigoCaracterActual;
	private int cantidadLineas=1;
	private int fila;
	private int columna;
	
	private String tokenString;
	private Character caracterActual;
	private boolean devuelveChar;
	private boolean isFinArchivo;
	
	public boolean isFinArchivo() {
		return isFinArchivo;
	}

	public void setFinArchivo(boolean isFinArchivo) {
		this.isFinArchivo = isFinArchivo;
	}


	private AccionSemantica matrizAS[][]; // Cada acci�n tiene una Accion Semantica dentro
	private AccionSemantica as;
	Hashtable<String,Integer> tablaSimbolos = new Hashtable<String,Integer>(); //no se cuantos campos tendr� ni el tipo de datos, genericamente la arme con 2 campos string 
	Hashtable<Character,Integer> tablaConversion = new Hashtable<Character,Integer>(); // para saber a que columna de la matriz de estado pertenece cada caracter
	BufferedReader codigoFuente;
	
	// Contructor - Se usa en "Principal.java"
	public Lexico(BufferedReader fileR) {
		this.codigoFuente = fileR;
		inicializarMatrizEstados();
		inicializarMatrizAS();
		inicializarListaConversion();
	}
	
	public Token getToken() {
		Token token;
		columna = 0;
		codigoCaracterActual = 0;
		fila = 0;
		this.setFinArchivo(false);
		tokenString="";
		try {
			while (fila != -1){ 								// Fin del token = -1
				
				codigoFuente.mark(1); 							// marco el archivo para poder devolver el caracter si es necesario
				codigoCaracterActual = codigoFuente.read(); 	// leo codigo ascii del caracter
				caracterActual = (char) codigoCaracterActual; 	// se castea para recuperar el caracter del codigo ascii leido
				
				if (caracterActual.equals('\n')){
					cantidadLineas = cantidadLineas+1;
				}
				
				if (codigoCaracterActual == -1) {
					this.setFinArchivo(true);
					columna = 20;
				}else {
					if (tablaConversion.containsKey(caracterActual)) { // si el caracter esta devuelve booleano
						columna = tablaConversion.get(caracterActual); // devuelve la columna a la que pertenece el caracter leido en la matriz de estados
					}else {
						columna = 21;
					}
				}
				
				as = matrizAS[fila][columna];	// En as se asigna la accion semantica que se encuentra en la posicion de la matriz
				
				//tokenString => se va armando el token en esta variable 
				// pasar el caracter actual, el tokenString y lo que sea necesario y que devuelva el tokenString con la agregacion realizada del caracter 
				as.ejecutar(caracterActual, tokenString, cantidadLineas);
				tokenString = as.getTokenString();
				
				devuelveChar = as.isDevuelveChar();
				if (devuelveChar) {
					codigoFuente.reset(); // vuelve a la ultima marca realizada en el archivo
				}
				
				//Recupero de la matriz de estados el estado siguiente del automata (en la matriz es la fila) 
				fila = matrizEstados[fila][columna];
				
				if (fila == -2){
					// realiza gestion de errores
					System.out.println("Error("+cantidadLineas+"): Caracter "+caracterActual +" no esperado.");
					fila = -1;
				}
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		token = as.armaToken();
		
		//Alta TS
		if (as.isDarAltaTS() && !tablaSimbolos.containsKey(tokenString)) { 
			tablaSimbolos.put(tokenString, token.getId());
        }
		
		// Devuelve un objeto tipo token
		return token; 	
	}

// LO COMENTO PORQUE ME MOLESTAN LOS ERRORES, PORQUE NO EXISTEN LAS CLASES AS (de hincha bolas :))	- Pablo
	public void inicializarMatrizAS() {
		matrizAS = new AccionSemantica[][]{/*Col  0*/  /*Col  1*/  /*Col  2*/  /*Col  3*/ /*Col  4*/  /*Col  5*/  /*Col  6*/  /*Col  7*/  /*Col  8*/  /*Col  9*/  /*Col 10*/  /*Col 11*/  /*Col 12*/  /*Col 13*/  /*Col 14*/  /*Col 15*/  /*Col 16*/  /*Col 17*/  /*Col 18*/  /*Col 19*/  /*Col 20*/  /*Col 21*/    /*Col 22*/    /*Col 23*/
		/* fila  0*/         			{  new AS1(),  new AS1(),  new AS1(),  new AS1(),  new AS0(),  new AS1(),  new AS1(),  new AS1(), new AS90(),  new AS1(), new AS90(), new AS90(), new AS90(), new AS90(), new AS90(),  new AS1(),  new AS1(),  new AS1(),  new AS1(),new ASERR(), new ASEOF(), new ASERR(),   new AS0(),  new AS90()},
		/* fila  1*/					{ new AS11(), new AS10(), new AS10(), new AS10(), new AS11(), new AS11(), new AS11(), new AS11(), new AS11(), new AS11(), new AS11(), new AS11(), new AS11(), new AS11(), new AS11(), new AS11(), new AS10(), new AS10(), new AS10(), new AS11(), new ASEOF(), new ASERR(),  new AS11(),  new AS11()},
		/* fila  2*/					{ new AS20(), new AS21(), new AS21(), new AS21(), new AS21(), new AS21(), new AS21(), new AS21(), new AS21(), new AS21(), new AS21(), new AS21(), new AS21(), new AS21(), new AS21(), new AS21(), new AS21(), new AS21(), new AS21(), new AS21(), new ASEOF(), new ASERR(),  new AS21(),  new AS21()},
		/* fila  3*/					{ new AS40(), new AS40(), new AS40(), new AS40(), new AS40(), new AS40(), new AS40(), new AS40(), new AS40(), new AS30(), new AS40(), new AS40(), new AS40(), new AS40(), new AS40(), new AS40(), new AS40(), new AS40(), new AS40(), new AS40(), new ASEOF(), new ASERR(), new ASEOF(),  new AS40()},
		/* fila  4*/					{ new AS31(), new AS31(), new AS31(), new AS31(), new AS32(), new AS31(), new AS31(), new AS31(), new AS31(), new AS31(), new AS31(), new AS31(), new AS31(), new AS31(), new AS31(), new AS31(), new AS31(), new AS31(), new AS31(), new AS31(), new ASEOF(),  new AS31(),  new AS31(),  new AS31()},
		/* fila  5*/					{ new AS50(), new AS50(), new AS50(), new AS50(), new AS50(), new AS50(), new AS60(), new AS60(), new AS50(), new AS50(), new AS50(), new AS50(), new AS50(), new AS50(), new AS50(), new AS50(), new AS50(), new AS50(), new AS50(), new AS50(), new ASEOF(), new ASERR(), new ASEOF(),  new AS50()},
		/* fila  6*/					{ new AS80(), new AS80(), new AS80(), new AS80(), new AS80(), new AS80(), new AS80(), new AS70(), new AS80(), new AS80(), new AS80(), new AS80(), new AS80(), new AS80(), new AS80(), new AS80(), new AS80(), new AS80(), new AS80(), new AS80(), new ASEOF(), new ASERR(), new ASEOF(),  new AS80()},
		/* fila  7*/					{new AS100(),new AS100(),new AS100(),new AS100(),new AS100(),new AS100(),new AS100(),new AS100(),new AS100(),new AS100(),new AS100(),new AS100(),new AS100(),new AS100(),new AS100(),new AS102(),new AS100(),new AS100(),new AS100(),new AS100(), new ASEOF(), new AS100(), new AS100(), new AS100()},
		/* fila  8*/         			{new AS101(),new AS101(),new AS101(),new AS101(),new AS101(),new AS101(),new AS101(),new AS101(),new AS101(),new AS101(),new AS101(),new AS101(),new AS101(),new AS101(),new AS101(),new AS102(),new AS101(),new AS101(),new AS101(),new AS101(), new ASEOF(), new AS101(), new AS101(), new AS101()},
		/* fila  9*/					{new ASERR(),new ASERR(),new AS110(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new AS111(), new ASEOF(), new ASERR(), new ASERR(), new ASERR()},
		/* fila 10*/					{new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new AS112(),new AS120(),new ASERR(),new ASERR(), new ASEOF(), new ASERR(), new ASERR(), new ASERR()},
		/* fila 11*/					{new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new AS121(),new ASERR(), new ASEOF(), new ASERR(), new ASERR(), new ASERR()},
									   	};
	}
	
	public void inicializarMatrizEstados() {
		 // Final = -1
		 // Error = -2
		//test
		 matrizEstados = new int[][] {/*2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23*/
		/* fila 0*/				{ 2, 1, 9, 1, 0, 5, 6, 6,-1, 3,-1,-1,-1,-1,-1, 7, 1, 1, 1,-2,-1,-2, 0,-1},
		/* fila 1*/				{-1, 1, 1, 1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1, 1, 1, 1,-1,-1,-2,-1,-1},
		/* fila 2*/				{ 2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-2,-1,-1},
		/* fila 3*/				{-1,-1,-1,-1,-1,-1,-1,-1,-1, 4,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-2,-1,-1},
		/* fila 4*/				{ 4, 4, 4, 4,-1, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,-1, 4, 4, 4},
		/* fila 5*/				{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-2,-1,-1},
		/* fila 6*/				{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-2,-1,-1},
		/* fila 7*/				{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,-1, 8, 8, 8, 8,-1, 8, 8, 8},
		/* fila 8*/				{ 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8,-1, 8, 8, 8, 8,-1, 8, 8, 8},
		/* fila 9*/				{-2,-2, 9,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,10,-1,-2,-2,-2},
		/* fila 10*/			{-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-1,11,-2,-2,-1,-2,-2,-2},
		/* fila 11*/			{-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-1,-2,-1,-2,-2,-2},
								};
		
	}
	
	
	public void mostrarListaSimbolos(){ //Lista las claves de la tabla de simbolos
		Enumeration<String> e = tablaSimbolos.keys();
		Object s;
		while( e.hasMoreElements() ){
		 s = e.nextElement();
		 System.out.println( "Simbolo: " + s );
		}
	}

	private void inicializarListaConversion(){
		//Campo1: caracter
		//Campo2: columna a la que pertenece en la matriz de estados 
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
		
		tablaConversion.put("a".charAt(0), 1); 
		tablaConversion.put("b".charAt(0), 1);
		tablaConversion.put("c".charAt(0), 1);
		tablaConversion.put("d".charAt(0), 1);
		tablaConversion.put("e".charAt(0), 1);
		tablaConversion.put("f".charAt(0), 1);
		tablaConversion.put("g".charAt(0), 1);
		tablaConversion.put("h".charAt(0), 1);
		tablaConversion.put("i".charAt(0), 1);
		tablaConversion.put("j".charAt(0), 1);
		tablaConversion.put("k".charAt(0), 1);
		tablaConversion.put("l".charAt(0), 1);
		tablaConversion.put("m".charAt(0), 1);
		tablaConversion.put("n".charAt(0), 1);
		tablaConversion.put("o".charAt(0), 1);
		tablaConversion.put("p".charAt(0), 1);
		tablaConversion.put("q".charAt(0), 1);
		tablaConversion.put("r".charAt(0), 1);
		tablaConversion.put("s".charAt(0), 1);
		tablaConversion.put("t".charAt(0), 1);
		tablaConversion.put("u".charAt(0), 1);
		tablaConversion.put("v".charAt(0), 1);
		tablaConversion.put("w".charAt(0), 1);
		tablaConversion.put("x".charAt(0), 1);
		tablaConversion.put("y".charAt(0), 1);
		tablaConversion.put("z".charAt(0), 1);
		
		tablaConversion.put("0".charAt(0), 2);
		tablaConversion.put("1".charAt(0), 2);
		tablaConversion.put("2".charAt(0), 2);
		tablaConversion.put("3".charAt(0), 2);
		tablaConversion.put("4".charAt(0), 2);
		tablaConversion.put("5".charAt(0), 2);
		tablaConversion.put("6".charAt(0), 2);
		tablaConversion.put("7".charAt(0), 2);
		tablaConversion.put("8".charAt(0), 2);
		tablaConversion.put("9".charAt(0), 2);
		
		tablaConversion.put("@".charAt(0), 3);
		
		tablaConversion.put('\n', 4); //salto de linea
		
		tablaConversion.put('\r', 4); //retorno de carro		
		
		tablaConversion.put("<".charAt(0), 5);
		
		tablaConversion.put(">".charAt(0), 6);
		
		tablaConversion.put("=".charAt(0), 7);
		
		tablaConversion.put("+".charAt(0), 8);
		
		tablaConversion.put("-".charAt(0), 9);
		
		tablaConversion.put("/".charAt(0), 10);
		
		tablaConversion.put("(".charAt(0), 11);
		
		tablaConversion.put(")".charAt(0), 12);
		
		tablaConversion.put(",".charAt(0), 13);
		
		tablaConversion.put(";".charAt(0), 14);
		
		tablaConversion.put("'".charAt(0), 15);
		
		tablaConversion.put("i".charAt(0), 16);
		
		tablaConversion.put("u".charAt(0), 17);
		
		tablaConversion.put("l".charAt(0), 18);
		
		tablaConversion.put("_".charAt(0), 19);
		
		tablaConversion.put('\t', 22); // tabulacion
		
		tablaConversion.put(" ".charAt(0), 22);
		
		tablaConversion.put("*".charAt(0), 23);
		}

	
}
