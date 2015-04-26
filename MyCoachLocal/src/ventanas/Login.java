package ventanas;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controladores.LoginControlador;

public class Login extends JPanel {
	private JTextField textField;
	private ImageIcon fondoPanel;
	private JPasswordField passwordField;
	private URI url;
	
	/**
	 * Create the panel.
	 */
	public Login() {
		setBackground(Color.WHITE);
		setLayout(null);
		this.setBounds(0, 0, 1080, 720);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("STHeiti", Font.BOLD, 20));
		lblUsuario.setForeground(Color.BLUE);
		lblUsuario.setBounds(468, 280, 87, 16);
		add(lblUsuario);
		
		textField = new JTextField();
		textField.setBounds(578, 275, 134, 28);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setForeground(Color.BLUE);
		lblPassword.setFont(new Font("STHeiti", Font.BOLD, 20));
		lblPassword.setBounds(468, 320, 117, 16);
		add(lblPassword);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Llama al controlador para verificar usuario y contraseña.
				LoginControlador controlador = new LoginControlador(textField.getText(), passwordField.getPassword());
				//Booleano, comprobar!!!
				controlador.checkLogin(textField.getText(), passwordField.getPassword());
			}
		});
		btnLogin.setBounds(531, 360, 117, 29);
		add(btnLogin);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(578, 315, 134, 28);
		add(passwordField);
		
		//Intento poner un enlace para que vaya a la pagina de registro.
		JLabel registroLabel = new JLabel("Nuevo? Registrate aquí.");
		registroLabel.setForeground(Color.BLUE);
		registroLabel.setFont(new Font("STHeiti", Font.PLAIN, 20));
		registroLabel.setBounds(475, 400, 244, 28);
		registroLabel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent evnt){
				if(evnt.getClickCount()==1){
				    if (Desktop.isDesktopSupported()) {
				        try {
				          Desktop.getDesktop().browse(url);
				        } catch (IOException e) { /* TODO: error handling */ }
				      }
				    
				}
			}
		});
		add(registroLabel);
		
		try{
			url = new URI("www.superdeporte.es");
		}catch(Exception e){
			
		}
		
		fondoPanel = new ImageIcon(getClass().getResource("/img/fondoPrincipal.jpg"));
		
		/*
		if(checkLogin()==false){
			JLabel errorUsuario = new JLabel("Usuario o contraseña incorrectos.");
			errorUsuario.setForeground(Color.RED);
			errorUsuario.setFont(new Font("STHeiti", Font.PLAIN, 20));
			errorUsuario.setBounds(475, 440, 134, 28);
			add(errorUsuario);
			setVisible(true);
		}
		*/
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(fondoPanel.getImage(), 0, 0, null);
	}
	

}

	
	


	

