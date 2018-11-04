package principal;

import java.util.ArrayList;
import java.util.Iterator;

public class Tercetos {
	private String[] terceto;
	private ArrayList<String[]> tercetosListado = new ArrayList<String[]>();
	
	public Tercetos(){
		tercetosListado.clear();
	}
	
	public int creaTerceto(String op1,String op2, String op3) {
		terceto  = new String[3];
		terceto[0] = op1;
		terceto[1] = op2;
		terceto[2] = op3;
		
		tercetosListado.add(terceto);
		return tercetosListado.size(); //retorna la posicion del terceto 
	}
	
	public void listaTercetos() {
		Iterator<String[]> tercetoIterator = tercetosListado.iterator();
		int i = 1;
		while(tercetoIterator.hasNext()){
			String[] elemento = tercetoIterator.next(); // elemento es el terceto
			System.out.println(i+". ( "+elemento[0]+" , "+elemento[1]+" , "+elemento[2]+" ) ");
			i++;
		}
	}
	
	public String[] getTerceto(int indice) { 
		return tercetosListado.get(indice); //el indice comienza en CERO OJO!!!!!
	}
	
	public int getCantidadTercetos() {
		return tercetosListado.size();
		
	}
}
