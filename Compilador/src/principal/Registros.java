package principal;

import java.util.Iterator;
import java.util.ArrayList;

public class Registros {
	private String[] registro;
	private ArrayList<String[]> tablaRegistros = new ArrayList<String[]>();

	public void iniciaTabla() {
		registro  = new String[2];
		registro[0] = "1";//Disponibilidad: 1 es libre, 0 ocupado 
		registro[1] = "EAX";//EAX , EBX , ECX , EDX
		tablaRegistros.add(registro);
		
		registro  = new String[2];
		registro[0] = "1"; 
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
		
		registro  = new String[2];
		registro[0] = "1"; 
		registro[1] = "---";  //Este registro es por si no hay alguno libre
		tablaRegistros.add(registro);
		}


	public String tomaRegistro() {
		Iterator<String[]> tablaIterator = tablaRegistros.iterator();
		String[] registroActual;
		do{
		registroActual = tablaIterator.next();
		}while(tablaIterator.hasNext() || registroActual[0]=="0" );
		if (registroActual[1]!="---") {
			registroActual[0]="0"; //Si el registro es distinto a --- entonces lo pone en cero para marcar que el registro esta ocupado 
		}
		return  String.valueOf(registroActual[1]);
	}
	
	public void liberaRegistro(String nombreregistro) {
		Iterator<String[]> tablaIterator = tablaRegistros.iterator();
		String[] registroActual;
		do{
		registroActual = tablaIterator.next();
		}while(registroActual[1]!=nombreregistro);
		registroActual[0]="1";//Se asigna 1 para volver a marcar como disponible el registro
	}
}
