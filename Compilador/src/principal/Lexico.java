package principal;

import java.io.BufferedReader;
import java.util.Enumeration;
import java.util.Hashtable;
import AccionesSemanticas.*;

public class Lexico {
	private int matrizEstados[][];
	private AccionSemantica matrizAS[][]; // Cada acción tiene una Accion Semantica dentro
	private AccionSemantica as;
	//Hashtable<String,Simbolo> tablaSimbolos = new Hashtable...;
	BufferedReader codigoFuente;
	private int estadoActual;
	private int codigoCaracterActual;
	private int cantidadLineas;
	private int tokenId=0;
	private Character caracterActual;
	
	// Contructor - Se usa en "Principal.java"
	public Lexico(BufferedReader fileR) {
		this.codigoFuente = fileR;
		inicializarMatrizAS();
		inicializarMatrizEstados();
	}
	
	public int getToken() {
		estadoActual = 0;
		codigoCaracterActual = 0;
		try {
			cantidadLineas = 0;
			while (codigoCaracterActual != -1){ 				// el fin de archivo devuelve -1
				codigoFuente.mark(1); 							// marco el archivo para poder devolver el caracter si es necesario
				codigoCaracterActual = codigoFuente.read(); 	// leo codigo ascii del caracter
				caracterActual = (char) codigoCaracterActual; 	// se castea para recuperar el caracter del codigo ascii leido
				
				System.out.print(caracterActual);
				as = matrizAS[estadoActual][caracterActual];	// En as se asigna la accion semantica que se encuentra en la posicion de la matriz
				//tokenId = as.ejecutar(...);
				//estadoActual = matrizEstados[estadoActual][caracterActual];
			}
		}catch (Exception e) {
			e.printStackTrace();
		}	
	return 1;// tokenId;
	}
	
	public void inicializarMatrizAS() {
		AccionSemantica matrizAS[][] = 	{  /*Col  0*/  /*Col  1*/  /*Col  2*/  /*Col  3*/  /*Col  4*/  /*Col  5*/  /*Col  6*/  /*Col  7*/  /*Col  8*/  /*Col  9*/  /*Col 10*/  /*Col 11*/  /*Col 12*/  /*Col 13*/  /*Col 14*/  /*Col 15*/  /*Col 16*/  /*Col 17*/  /*Col 18*/  /*Col 19*/
		/* fila  0*/         			{  new AS1(),  new AS1(),  new AS1(),  new AS1(),  new AS0(),  new AS1(),  new AS1(),  new AS1(), new AS90(),  new AS1(), new AS90(), new AS90(), new AS90(), new AS90(), new AS90(),  new AS1(),  new AS1(),  new AS1(),  new AS1(),new ASERR()},
		/* fila  1*/					{ new AS11(), new AS10(), new AS10(), new AS10(), new AS11(), new AS11(), new AS11(), new AS11(), new AS11(), new AS11(), new AS11(), new AS11(), new AS11(), new AS11(), new AS11(), new AS11(), new AS10(), new AS10(), new AS10(), new AS11()},
		/* fila  2*/					{ new AS20(), new AS21(), new AS21(), new AS21(), new AS21(), new AS21(), new AS21(), new AS21(), new AS21(), new AS21(), new AS21(), new AS21(), new AS21(), new AS21(), new AS21(), new AS21(), new AS21(), new AS21(), new AS21(), new AS21()},
		/* fila  3*/					{ new AS40(), new AS40(), new AS40(), new AS40(), new AS40(), new AS40(), new AS40(), new AS40(), new AS40(), new AS30(), new AS40(), new AS40(), new AS40(), new AS40(), new AS40(), new AS40(), new AS40(), new AS40(), new AS40(), new AS40()},
		/* fila  4*/					{ new AS31(), new AS31(), new AS31(), new AS31(), new AS32(), new AS31(), new AS31(), new AS31(), new AS31(), new AS31(), new AS31(), new AS31(), new AS31(), new AS31(), new AS31(), new AS31(), new AS31(), new AS31(), new AS31(), new AS31()},
		/* fila  5*/					{ new AS50(), new AS50(), new AS50(), new AS50(), new AS50(), new AS50(), new AS60(), new AS60(), new AS50(), new AS50(), new AS50(), new AS50(), new AS50(), new AS50(), new AS50(), new AS50(), new AS50(), new AS50(), new AS50(), new AS50()},
		/* fila  6*/					{ new AS80(), new AS80(), new AS80(), new AS80(), new AS80(), new AS80(), new AS80(), new AS70(), new AS80(), new AS80(), new AS80(), new AS80(), new AS80(), new AS80(), new AS80(), new AS80(), new AS80(), new AS80(), new AS80(), new AS80()},
		/* fila  7*/					{new AS100(),new AS100(),new AS100(),new AS100(),new AS100(),new AS100(),new AS100(),new AS100(),new AS100(),new AS100(),new AS100(),new AS100(),new AS100(),new AS100(),new AS100(),new AS100(),new AS100(),new AS100(),new AS100(),new AS100()},
		/* fila  8*/         			{new AS101(),new AS101(),new AS101(),new AS101(),new AS101(),new AS101(),new AS101(),new AS101(),new AS101(),new AS101(),new AS101(),new AS101(),new AS101(),new AS101(),new AS101(),new AS102(),new AS101(),new AS101(),new AS101(),new AS101()},
		/* fila  9*/					{new ASERR(),new ASERR(),new AS110(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new AS111()},
		/* fila 10*/					{new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new AS112(),new AS120(),new ASERR(),new ASERR()},
		/* fila 11*/					{new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new ASERR(),new AS121(),new ASERR()},
									   	};
		
	}
	
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
	
	public void listarMmatrizSimbolos(){
	/*	Enumeration<Variable> e = matrizSimbolos.keys();
		Object simbolo;
		while( e.hasMoreElements() ){
		  simbolo = e.nextElement();
		  System.out.println( "Simbolo: " + simbolo );
		}*/
	}
}
