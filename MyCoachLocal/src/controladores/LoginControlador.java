package controladores;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import modelos.Autentificacion;
import modelos.MySql;
import ventanas.CreacionEquipos;
import ventanas.Login;

public class LoginControlador {
	private String nombre;
	private char password[];
	ResultSet rs;
	
	public LoginControlador(String usuario, char[] password){
		this.nombre = usuario;
		this.password = password;
	}
	
	
	public boolean checkLogin(String user, char[] password){
		Login panel = new Login();
		this.nombre = user;
		//Como el campo JPassword me devuelve un char[], lo paso a un String para mostrarlo en el mensaje.
		String passwordString = String.valueOf(password);		
		//Conecto a la base de datos.
		MySql mysql = MySql.getInstance();

		rs = mysql.query("SELECT id_usuario FROM usuarios WHERE correo= '"+user+"' AND contrasenya= '"+passwordString+"'");
		try {
			if(rs.next()){
				int id = (int)rs.getObject("id_usuario");
				//JOptionPane.showMessageDialog(panel, "Tu id es: "+id, "Login Correcto", JOptionPane.INFORMATION_MESSAGE);
				//Le paso al modelo Autentificacion el id del usuario que he recogido de la BD.
				//Autentificacion autentificacion = new Autentificacion(id);
				//Modificaciones para gestionar el paso a la siguiente ventana o el error.
				new Autentificacion(id);
				//Autentificacion.setAutentificado(true);
				CreacionEquipos creacionEquipo = new CreacionEquipos();
				MainController controladorPrincipal = new MainController();
				controladorPrincipal.cambiaPanel(creacionEquipo);
			}else{
				JOptionPane.showMessageDialog(panel, "Usuario o contraseña incorrecta.", "Login Incorrecto", JOptionPane.INFORMATION_MESSAGE);	
				//muestraError();
				return false;
			}
			rs.close();
			return true;

		} catch (HeadlessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;

	}
	/*
	public void muestraError(){
		JLabel errorAut = new JLabel("Usuario o contraseña incorrectos");
		errorAut.setBounds(400, 475, 150, 100);
		errorAut.setForeground(Color.BLUE);
		errorAut.setFont(new Font("STHeiti", Font.PLAIN, 20));
		errorAut.setVisible(true);
		
	}
	*/
}
