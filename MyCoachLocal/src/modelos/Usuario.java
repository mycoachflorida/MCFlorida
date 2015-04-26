package modelos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Usuario {
	private int id;
	private ArrayList<Equipo> equipos = new ArrayList<Equipo>();
	private String nombre;
	private String apellidos;
	private String correo;
	
	public Usuario(){
		
	}
	
	public static Usuario load(int id){
		MySql mysql = MySql.getInstance();
		ResultSet rs = mysql.query("SELECT nombre, apellidos, correo FROM usuarios WHERE id_usuario='" +id+ "'");
		Usuario usuario = new Usuario();
		
		try {
			while(rs.next()){
				usuario.setId(id);
				usuario.setNombre((String) rs.getObject("nombre"));
				usuario.setApellidos((String) rs.getObject("apellidos"));
				usuario.setCorreo((String) rs.getObject("correo"));
				Equipo.load(usuario);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return usuario;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Equipo> getEquipos() {
		
		return equipos;
	}

	public void setEquipos(ArrayList<Equipo> equipos) {
		this.equipos = equipos;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}






	
	
}
