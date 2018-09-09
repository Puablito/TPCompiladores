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
		AccionSemantica matrizAS[][] = 	{ /*Col 0*//*Col 1*/ /*Col 2*/ /*Col 3*/ /*Col 4*/
		/* fila 0*/         			{new AS0(),new AS0(),new AS0(),new AS0(),new AS0()},
		/* fila 1*/						{new AS0(),new AS0(),new AS0(),new AS0(),new AS0()},
		/* fila 2*/						{new AS0(),new AS0(),new AS0(),new AS0(),new AS0()},
		/* fila 3*/						{new AS0(),new AS0(),new AS0(),new AS0(),new AS0()},
		/* fila 4*/						{new AS0(),new AS0(),new AS0(),new AS0(),new AS0()},
		/* fila 5*/						{new AS0(),new AS0(),new AS0(),new AS0(),new AS0()},
		/* fila 6*/						{new AS0(),new AS0(),new AS0(),new AS0(),new AS0()},
		/* fila 7*/						{new AS0(),new AS0(),new AS0(),new AS0(),new AS0()}
									   	};
		
	}
	
	public void inicializarMatrizEstados() {
		int matrizEstados[][] = {	/* 19 Columnas*/
		/* fila 0*/				{2,1,9,1,0,5,6,6,-1,-1,-1,-1,-1,7,1,1,1,1,-2}, // -1 = Final y -2 = Error (no se si esta bien)
		/* fila 1*/				{-1,1,1,1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		/* fila 2*/				{2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		/* fila 3*/				{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		/* fila 4*/				{4,4,4,4,-1,4,4,4,4,4,4,4,4,4,4,4,4,4,4},
		/* fila 5*/				{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		/* fila 6*/				{-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
		/* fila 7*/				{8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8,8},
		/* fila 8*/				{8,8,8,8,8,8,8,8,8,8,8,8,8,8,-1,8,8,8,8},
		/* fila 9*/				{-2,-2,9,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,10},
		/* fila 10*/			{-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-1,11,-2,-2},
		/* fila 11*/			{-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-2,-1,-2},
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
