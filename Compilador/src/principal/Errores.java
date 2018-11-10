package principal;

import java.util.ArrayList;
import java.util.Iterator;


public class Errores {
	private String[] error;
	private ArrayList<String[]> erroresListado = new ArrayList<String[]>();

	public Errores(){
		erroresListado.clear();
	}
	
	public void creaError(int linea,String descripcion) {
		error  = new String[2];
		error[0] = Integer.toString(linea);
		error[1] = descripcion;
		
		erroresListado.add(error); 
	}
	
	public void listarErrores() {
		Iterator<String[]> erroresIterator = erroresListado.iterator();
		System.out.println( "---------------------- Lista de Errores -------------------");
		while(erroresIterator.hasNext()){
			String[] errorActual = erroresIterator.next();
			System.out.println("Linea "+errorActual[0]+": ERROR - "+errorActual[1]+" ");
		}
		System.out.println("Total de errores: "+getTotalErrores()+". ");
	}
	
	public int getTotalErrores() {
		return erroresListado.size();
	}
}
