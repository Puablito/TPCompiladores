package principal;

public class principalPruebas {

	public static void main(String[] args) {
		Tercetos tercetos = new Tercetos();
		String[] tercetoAux;
		int x;
		
		x = tercetos.creaTerceto("/", "X", "2");
		System.out.println("Terceto Nro: "+x);
		
		x = tercetos.creaTerceto("+", "5", "8");
		System.out.println("Terceto Nro: "+x);
		
		x = tercetos.creaTerceto("==", "D", "A");
		System.out.println("Terceto Nro: "+x);
		
		tercetoAux = tercetos.getTerceto(1); //el indice comienza en CERO OJO!!!!!
		System.out.println("Terceto AUX: "+tercetoAux[0]+" "+tercetoAux[1]+" "+tercetoAux[2]);
		
		tercetos.listaTercetos();
	}

}
