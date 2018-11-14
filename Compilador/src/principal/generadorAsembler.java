package principal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class generadorAsembler {
	public ArrayList<String> cabecera;
	public ArrayList<String> codigo;
	public Map<String, ValoresTS> TSMap;
	
	//Constructor
	public generadorAsembler(Map<String, ValoresTS> tablaSimbolosMap) {
		TSMap = tablaSimbolosMap;
		creaData();
		creaCodigo();
	}
	
	// genera todo el codigo que se encuetra hasta la etiqueta start (código asembler)
	public void creaData() { 
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
		
		//Recorro tabla de Simbolos
		
		Set<String> mapKeys = TSMap.keySet(); // Obtenemos todas las llaves del mapa.
 
        // Recorremos el mapa por sus llaves e imprimimos sus valores.
        for (String key : mapKeys) {
            // Obtenemos el value.
            ValoresTS vTS = TSMap.get(key);
            if (vTS.getTokenTipo() != null) { // el token es un identificador o constante ya que posee tipo
            	if (vTS.getTokenTipo() == "INT") {
            		cabecera.add("	"+key +" dw 0");			// ver si los tipos ASSEMBLER estan correctos!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            	}else if (vTS.getTokenTipo() == "ULONG") {
            		cabecera.add("	"+key +" dd 0");			// ver si los tipos ASSEMBLER estan correctos!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            	}
            }	
        }
		
	}
	
	// creaCodigo usará cabecera para agergar las variables nuevas que se vayan generando
	public void creaCodigo() {
		codigo = new ArrayList<String>();
		codigo.add(".code");
		codigo.add("start:");
		codigo.add("	invoke ExitProcess, 0");
		codigo.add("end start");
		codigo.add("");
	}
	
	public void muestraCodigo() {
		System.out.println( "---------------------- Codigo Assembler -------------------");
		
		//Recorro la cabecera
		for (Iterator<String> iterator = cabecera.iterator(); iterator.hasNext();) {
			String string = iterator.next();
			System.out.println(string);
		}
		
		//Recorro el código
		for (Iterator<String> iterator = codigo.iterator(); iterator.hasNext();) {
			String string = iterator.next();
			System.out.println(string);
		}
	}
	
	public void grabaArchivo() {
		
	}
}
