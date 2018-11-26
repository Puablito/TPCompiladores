package principal;

import java.util.Iterator;
import java.util.ArrayList;

public class Registros {
	private String[] registro;
	private ArrayList<String[]> tablaRegistros = new ArrayList<String[]>();
	
	public Registros() {
		this.iniciaTabla();
	}
	
	public void iniciaTabla() {
		registro  = new String[2];
		registro[0] = "0";//Disponibilidad: 1 es libre, 0 ocupado 
		registro[1] = "EAX";//EAX , EBX , ECX , EDX
		tablaRegistros.add(registro);
		
		registro  = new String[2];
		registro[0] = "0"; 
		registro[1] = "EBX";
		tablaRegistros.add(registro);
		
		registro  = new String[2];
		registro[0] = "1"; 
		registro[1] = "ECX";
		tablaRegistros.add(registro);
		
		registro  = new String[2];
		registro[0] = "1"; 
		registro[1] = "EDX";
		tablaRegistros.add(registro);
		}


	public String tomaRegistro() {
		Iterator<String[]> tablaIterator = tablaRegistros.iterator();
		while(tablaIterator.hasNext() ){
			String[] registroActual = tablaIterator.next();
			if (registroActual[0]!="0") {
				registroActual[0]="0"; //Si el registro es distinto a --- entonces lo pone en cero para marcar que el registro esta ocupado
				return String.valueOf(registroActual[1]);
			}
		}
		return "---";
	}
	
	public void liberaRegistro(String nombreregistro) {
		Iterator<String[]> tablaIterator = tablaRegistros.iterator();
		while(tablaIterator.hasNext()) {
			String[] registroActual = tablaIterator.next();
			if (registroActual[1] == nombreregistro) {
				registroActual[0]="1"; //Se asigna 1 para volver a marcar como disponible el registro
				break;
			}
		}
	}
}