package principal;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class generadorAsembler {
	public ArrayList<String> cabecera;
	public ArrayList<String> data;
	public ArrayList<String> codigo;
	public Map<String, ValoresTS> TSMap;
	public Map<String, ValoresTS> TVariables = new Hashtable<String, ValoresTS>();
	public ArrayList<String[]> tercetos;
	public Registros registros;
	
	//Constructor
	public generadorAsembler(Map<String, ValoresTS> tablaSimbolosMap, ArrayList<String[]> tercetosListado) {
		TSMap = tablaSimbolosMap;
		tercetos = tercetosListado;
		registros = new Registros();
		creaCabecera(); // Crea el listado de valores de la cabecera
		generaData(); 
		creaCodigo(); // Crea el listado de valores del codigo assembler
		creaData(); // Crea el listado de variables usadas en el codigo assembler
	}
	
	// Genera todo el codigo que se encuetra hasta la etiqueta start (codigo asembler)
	// Carga todas las variables declaradas que se encuentran en la Tabla de Simbolos
	public void creaCabecera() { 
		cabecera = new ArrayList<String>();
		cabecera.add(".386");
		cabecera.add(".model flat, stdcall");
		cabecera.add("option casemap :none");
		cabecera.add("include \\masm32\\include\\windows.inc");
		cabecera.add("include \\masm32\\include\\kernel32.inc");
		cabecera.add("include \\masm32\\include\\user32.inc");
		cabecera.add("includelib \\masm32\\lib\\kernel32.lib");
		cabecera.add("includelib \\masm32\\lib\\user32.lib");
		cabecera.add(".data");
	}
	
	// Genera la Tabla con todas las variables que se van a declarar en assembler 
	public void generaData() {  
		//Recorro tabla de Simbolos y Guardo en la Tabla de Variables (DATA) 
		Set<String> mapKeys = TSMap.keySet(); // Obtenemos todas las llaves del mapa.
 
        // Recorremos el mapa por sus llaves e imprimimos sus valores.
        for (String key : mapKeys) {
            // Obtenemos el value.
            ValoresTS vTS = TSMap.get(key);
            if (vTS.getTokenTipo() != null) { 	// el token es un identificador o constante ya que posee tipo
            	// Solo guardo los identificadores y descarto las constantes
            	if ((key.indexOf("_i") == -1) && (key.indexOf("_ul") == -1)){ 
            		this.agergaVariable(key, vTS.getTokenTipo());
            	}
            }	
        }
		
	}
	
	// Agerga una variable a la tabla TVariables
	public void agergaVariable(String nombre, String tipo) {
		ValoresTS val = new ValoresTS();
		if (tipo != "") {
			val.setTokenID(0);
			val.setTokenTipo(tipo);
		}
		TVariables.put(nombre, val);
	}
	
	
	public boolean existeVariable(String variable) {
		if (TVariables.containsKey(variable)) {
			return true;
		}else {
			return false;
		}
	}
	
	public String getTipoVariable(String variable) {
		if (TVariables.containsKey(variable)) { // verifica las variables e identificadores
			ValoresTS vTS = TVariables.get(variable);
			return vTS.getTokenTipo();
		}else if (TSMap.containsKey(variable)){ // verifica las constantes
			ValoresTS vTS = TSMap.get(variable);
			return vTS.getTokenTipo();
		}
		return"";
	}
	
	public void creaData() {
		data = new ArrayList<String>();		
		//Recorro tabla de Simbolos y Guardo en la Tabla de Variables (DATA) 
		Set<String> TvarKeys = TVariables.keySet(); // Obtenemos todas las llaves del mapa (Tabla de variables).
 
        // Recorremos el mapa por sus llaves e imprimimos sus valores.
        for (String key : TvarKeys) {
            // Obtenemos el value.
            ValoresTS vTS = TVariables.get(key);
            if (vTS.getTokenTipo() != null) { 	// el token es un identificador o constante ya que posee tipo
            	String tipo = vTS.getTokenTipo();
            	
            	if (tipo == "INT") {
            		data.add("	"+key +" dw 0");		// ver si los tipos ASSEMBLER estan correctos!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            	}else if (tipo == "ULONG") {
            		data.add("	"+key +" dd 0");		// ver si los tipos ASSEMBLER estan correctos!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            	}
            }	
        }
		
		
	}
	
	public String getNewVariable(int indice,String tipo) {
		String varAux = "@var"+indice;
		boolean existeVar = this.existeVariable(varAux);
		if (existeVar == false) {
			this.agergaVariable(varAux,tipo);
		}
		return varAux;
	}
	// Genera el bloque code de asembler, usar metodo agregaVariable para declarar variables en la seccion data
	public void creaCodigo() {
		codigo = new ArrayList<String>();
		codigo.add(".code");
		codigo.add("start:");
		
		// Recorro la lista de tercetos
		Iterator<String[]> tercetoIterator = tercetos.iterator();
		int i=0;
		while(tercetoIterator.hasNext()){
			String[] elemento = tercetoIterator.next(); // elemento es el terceto
			i++;							//indica el numero del terceto actual
			String varAux;
			String operacion = elemento[0]; // Operacion
			String op1 = elemento[1];		// Operador 1
			String op2 = elemento[2];		// Operador 2
			
			
			// Verifico si es una referencia a otro terceto y recupero la variable donde se guarda el resultado
			if ('[' == op1.charAt(0)) { 
				String nroTerceto = op1.substring(1,op1.length()-1);
				op1 = "@var"+nroTerceto;
			}
			if ('[' == op2.charAt(0)) { 
				String nroTerceto = op2.substring(1,op2.length()-1);
				op2 = "@var"+nroTerceto;
			}
			
			// Genero codigo para las operaciones
			
			//Falta agregar las variables a la parte de data
			//falta agergar un "_" a los identificadores
			//falta sacar los sufijos a las constantes
			//falta el while
			//falta el if
			String reg=registros.tomaRegistro();
			if (operacion.equals("+")) {
				codigo.add("	MOV "+reg+", "+op1);
				codigo.add("	ADD "+reg+", "+op2);
				varAux = this.getNewVariable(i,this.getTipoVariable(op1));
				codigo.add("	MOV "+varAux+","+reg);
			}else if (operacion.equals("-")) {
				codigo.add("	MOV "+reg+", "+op1);
				codigo.add("	SUB "+reg+", "+op2);
				varAux = this.getNewVariable(i,this.getTipoVariable(op1));
				codigo.add("	MOV "+varAux+","+reg);
			}else if (operacion.equals("*")) {
				codigo.add("	MOV "+reg+", "+op1);
				codigo.add("	MUL "+reg+", "+op2);
				varAux = this.getNewVariable(i,this.getTipoVariable(op1));
				codigo.add("	MOV "+varAux+","+reg);
			}else if (operacion.equals("/")) {
				codigo.add("	MOV "+reg+", "+op1);
				codigo.add("	DIV "+reg+", "+op2);
				varAux = this.getNewVariable(i,this.getTipoVariable(op1));
				codigo.add("	MOV "+varAux+","+reg);
			}else if (operacion.equals(":=")) {
				codigo.add("	MOV "+reg+", "+op2);
				codigo.add("	MOV "+op1+","+reg);
			}
			registros.liberaRegistro(reg);
		
		
		
		}
		
		codigo.add("	invoke ExitProcess, 0");
		codigo.add("end start");
		codigo.add("");
	}
	

	
	public void imprimeCodigoPantalla() {
		System.out.println( "---------------------- Codigo Assembler -------------------");
		
		//Recorro la cabecera
		for (Iterator<String> iterator = cabecera.iterator(); iterator.hasNext();) {
			String string = iterator.next();
			System.out.println(string);
		}
		
		//Recorro el data
		for (Iterator<String> iterator = data.iterator(); iterator.hasNext();) {
			String string = iterator.next();
			System.out.println(string);
		}
		
		//Recorro el c�digo
		for (Iterator<String> iterator = codigo.iterator(); iterator.hasNext();) {
			String string = iterator.next();
			System.out.println(string);
		}
	}
	
	// Genera el archivo .asm
	public void imprimeCodigoArchivo(String nombreArchivo) {
		FileWriter archivo = null;
        PrintWriter pw = null;
        try {
        	nombreArchivo = nombreArchivo.replace("txt", "asm"); // reemplazo la extension del archivo
        	archivo = new FileWriter(nombreArchivo);
            pw = new PrintWriter(archivo);

            // Recorro y grabo la cabecera
    		for (Iterator<String> iterator = cabecera.iterator(); iterator.hasNext();) {
    			String string = iterator.next();
    			pw.println(string);
    		}
    		
    		// Recorro y grabo el .data
    		for (Iterator<String> iterator = data.iterator(); iterator.hasNext();) {
    			String string = iterator.next();
    			pw.println(string);
    		}
    		
    		// Recorro y grabo el codigo
    		for (Iterator<String> iterator = codigo.iterator(); iterator.hasNext();) {
    			String string = iterator.next();
    			pw.println(string);
    		}

        } catch (Exception e) {
            System.out.println("Error al intentar crear el archivo "+nombreArchivo);
        } finally {
           try {
        	   // Aprovechamos el finally para asegurarnos que se cierra el fichero.
        	   if (null != archivo) {
        		   archivo.close();
        	   }
           } catch (Exception e2) {
        	   System.out.println("Error al intentar cerrar el archivo "+nombreArchivo);
           }
        }
	}
}
