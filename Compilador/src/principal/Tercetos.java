package principal;

import java.util.ArrayList;
import java.util.Iterator;

public class Tercetos {
	private String[] terceto;
	private ArrayList<String[]> tercetosListado = new ArrayList<String[]>();

	public Tercetos(){
		tercetosListado.clear();
	}
	
	public ArrayList<String[]> getTercetosListado() {
		return tercetosListado;
	}

	public void setTercetosListado(ArrayList<String[]> tercetosListado) {
		this.tercetosListado = tercetosListado;
	}

	public int creaTerceto(String op1,String op2, String op3, String tipo) {
		int cantidadTercetos = tercetosListado.size();
		terceto  = new String[5];
		terceto[0] = op1;
		terceto[1] = op2;
		terceto[2] = op3;
		terceto[3] = (tipo == null?"":tipo);
		terceto[4] = "";
		// Guardo la variable donde se guardara el resultado de la operacion
		if (tipo != "") {
			terceto[4] = "@res"+Integer.toString(cantidadTercetos+1);
		} else {
			if(cantidadTercetos !=0) {
				String[] elementoUltimo = this.getTerceto(cantidadTercetos-1);
				if (op1.equals(elementoUltimo[0]) && op2.equals(elementoUltimo[1]) && op3.equals(elementoUltimo[2])) {
					return tercetosListado.size(); //retorna la posicion del terceto
				}
			}
		}		
		
		tercetosListado.add(terceto);
		return tercetosListado.size(); //retorna la posicion del terceto 
	}
	
	public void listaTercetos() {
		creaTerceto("EOF", "-", "-", "");
		Iterator<String[]> tercetoIterator = tercetosListado.iterator();
		System.out.println( "---------------------- Lista de Tercetos -------------------");
		int i = 1;
		while(tercetoIterator.hasNext()){
			String[] elemento = tercetoIterator.next(); // elemento es el terceto
			System.out.println(i+". ( "+elemento[0]+" , "+elemento[1]+" , "+elemento[2]+" ) "+elemento[3]+" "+elemento[4]);
			i++;
		}
	}
	
	public String[] getTerceto(int indice) { 
		return tercetosListado.get(indice); //el indice comienza en CERO OJO!!!!!
	}
	
	public int getCantidadTercetos() {
		return tercetosListado.size();
		
	}
	
	public void completaTerceto (int nroTercetoCambiar, int valorOperador) {
		String[] tercetoModificar;
		// Recupero el terceto a modificar
		tercetoModificar = this.getTerceto(nroTercetoCambiar-1);
		String guion = "-";
		
		// busco el guion y modifico el elemento del terceto que lo posea por valorOperador
		if (tercetoModificar[0] == guion) {
			tercetoModificar[0] =  "["+Integer.toString(valorOperador)+"]";
		}else if (tercetoModificar[1] == guion) {
			tercetoModificar[1] =  "["+Integer.toString(valorOperador)+"]";
		}else if (tercetoModificar[2] == guion) {
			tercetoModificar[2] =  "["+Integer.toString(valorOperador)+"]";
		}
		
		//this.tercetosListado.add(nroTercetoCambiar, tercetoModificar);
	}
}
