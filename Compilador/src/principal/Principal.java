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
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
public class Principal extends JFrame {

	private JPanel contentPane;
	private JTextField txtPath;
	public FileReader fr; 
	private BufferedReader bf;

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
				
				String cPath = txtPath.getText();
				if (cPath.isEmpty()){
					JOptionPane.showMessageDialog(null, "Debe ingresar una ruta de archivo!");
				}else {
					// Abro el archivo que contiene el c�digo fuente
					File file = new File (cPath);
					try {
						//fr = new FileReader(file)
						//FileReader fr = new FileReader(cPath); comento para pruebas
						FileReader fr = new FileReader("C:\\cf.txt");
						bf = new BufferedReader (fr);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (Exception e) {
						e.printStackTrace();
					}
					
					// Creo el analizador lexico y se lo paso como parametro al parser
					Lexico analizadorLexico = new Lexico(bf); 
					Parser analizadorSintactico = new Parser(analizadorLexico);
				}
			}
		});
		btnIniciarParser.setBounds(10, 57, 106, 23);
		panel.add(btnIniciarParser);
	}
}
