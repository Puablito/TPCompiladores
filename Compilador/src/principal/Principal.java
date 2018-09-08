package principal;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class Principal extends JFrame {

	private JPanel contentPane;
	private JTextField txtPath;
	private FileReader fr; 

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
			public void actionPerformed(ActionEvent arg0) {
				File file = new File (txtPath.getText());
				try {
					fr = new FileReader(file);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			// completar llamando al parser y puede ser que tenga que llamar al lexico
			}
		});
		btnIniciarParser.setBounds(10, 57, 106, 23);
		panel.add(btnIniciarParser);
	}
}
