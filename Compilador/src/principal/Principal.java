package principal;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import java.util.Map;
import java.util.Set;
import Parser.*;


public class Principal extends JFrame {
	
	private Lexico analizadorLexico;
	private JPanel contentPane;
	private JTextField txtPath;
	public FileReader fr; 
	private BufferedReader bf;
	public Hashtable<String,Integer> tablaSimbolos = new Hashtable<String,Integer>(); //no se cuantos campos tendr� ni el tipo de datos, genericamente la arme con 2 campos string 
	// HashMap<String, ValoresTS()> tablaSimbolosMap = new HashMap<String, ValoresTS()>;
	final Map<String, ValoresTS> tablaSimbolosMap = new Hashtable<String, ValoresTS>();
	final Iterator<ValoresTS> it = tablaSimbolosMap.values().iterator();
	private Tercetos tercetos;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principal frame = new Principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void mostrarListaSimbolos(){ //Lista las claves de la tabla de simbolos
		Enumeration<String> e = tablaSimbolos.keys();
		Object s;
		while( e.hasMoreElements() ){
		 s = e.nextElement();
		 System.out.println( "Simbolo: " + s );
		}
	}
	
	public void mostrarMapasimbolos() {
		 System.out.println( "---------------------- Comienzo de Tabla de Simbolos -------------------");
	
		 //Collection<ValoresTS> vts = tablaSimbolosMap.values();
		
		/* Enumeration<String> e = (Enumeration<String>) tablaSimbolosMap.keySet();
		 Object s;
		 while( e.hasMoreElements() ){
			 s = e.nextElement();
			 ValoresTS vTS = it.next();
			 System.out.println( "Simbolo: " + s + ", Tipo: " + vTS.getTokenID() );
		}
		*/
		 
		 // Obtenemos todas las llaves del mapa.
        Set<String> mapKeys = tablaSimbolosMap.keySet();
 
        // Recorremos el mapa por sus llaves e imprimimos sus valores.
        for (String key : mapKeys) {
            // Obtenemos el value.
            ValoresTS vTS = tablaSimbolosMap.get(key);
            System.out.println( "Simbolo: " + key + ", Tipo: " + vTS.getTokenTipo());
        }
        
	}
	
	/**
	 * Create the frame.
	 */
	public Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 151);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		txtPath = new JTextField();
		txtPath.setBounds(10, 30, 404, 20);
		panel.add(txtPath);
		txtPath.setColumns(10);
		
		JLabel lblPathDelCodigo = new JLabel("Path del C\u00F3digo Fuente:");
		lblPathDelCodigo.setBounds(10, 11, 153, 14);
		panel.add(lblPathDelCodigo);
		
		JButton btnIniciarParser = new JButton("Iniciar Parser");
		btnIniciarParser.addActionListener(new ActionListener() {
			// Acci�n del boton "Iniciar Parser"
			public void actionPerformed(ActionEvent arg0) {
				
				
				//txtPath.setText("c:\\codigo_fuente.txt");
				String cPath = txtPath.getText();
				if (cPath.isEmpty()){
					JOptionPane.showMessageDialog(null, "Debe ingresar una ruta de archivo!");
				}else{
					// Abro el archivo que contiene el c�digo fuente
					File file = new File (txtPath.getText());					
					try {
						//fr = new FileReader(file)
						//FileReader fr = new FileReader(cPath); comento para pruebas
						FileReader fr = new FileReader(file);
						bf = new BufferedReader (fr);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
					tercetos= new Tercetos();
					// Creo el analizador lexico y se lo paso como parametro al parser
					analizadorLexico = new Lexico(bf, tablaSimbolos, tablaSimbolosMap); 
					//ParserNuestro analizadorSintactico = new ParserNuestro(analizadorLexico); //parser del tp1
					Parser analizadorSintactico = new Parser(analizadorLexico, tercetos);
					analizadorSintactico.run();
					//mostrarListaSimbolos();
					mostrarMapasimbolos();
					tercetos.listaTercetos();
				}
			}
		});
		btnIniciarParser.setBounds(10, 57, 176, 23);
		panel.add(btnIniciarParser);
	}
}
