package modelos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.sun.glass.ui.Pixels.Format;

public class Jugador {
	
	private int id;
	private String nombre;
	private int dorsal;
	private String demarcacion;
	private String segundaDemarcacion;
	private String fechaNacimiento;
	
	

	public Jugador() {
		
	}
	
	public Jugador(String nombre, int dorsal, String demarcacion, String segundaDemarcacion, String fechaNacimiento) {
		this.setNombre(nombre);
		this.setDorsal(dorsal);
		this.setSegundaDemarcacion(segundaDemarcacion);
		this.setFechaNacimiento(fechaNacimiento);
	}


	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public int getDorsal() {
		return dorsal;
	}



	public void setDorsal(int dorsal) {
		this.dorsal = dorsal;
	}



	public String getDemarcacion() {
		return demarcacion;
	}



	public void setDemarcacion(String demarcacion) {
		this.demarcacion = demarcacion;
	}



	public String getFechaNacimiento() {
		return fechaNacimiento;
	}



	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}



	public String getSegundaDemarcacion() {
		return segundaDemarcacion;
	}



	public void setSegundaDemarcacion(String segundaDemarcacion) {
		this.segundaDemarcacion = segundaDemarcacion;
	}
	
	public static ArrayList<Jugador> load(Equipo equipo){
		MySql conexion = MySql.getInstance();
		int idEquipo = equipo.getId();
		ResultSet resultado = conexion.query("select id_jugador, nombre, dorsal, demarcacion, segunda_demarcacion, fecha_nacimiento FROM jugadores WHERE id_equipo ="+idEquipo);
		
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		try{
			while(resultado.next()){
				Jugador jugador = new Jugador();
				jugador.setDemarcacion((String)resultado.getObject("demarcacion"));
				jugador.setDorsal((int)resultado.getObject("dorsal"));
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
				String fechaNacimientoString = formatter.format(resultado.getObject("fecha_nacimiento")); 
				jugador.setFechaNacimiento(fechaNacimientoString);
				jugador.setId((int)resultado.getObject("id_jugador"));
				jugador.setNombre((String)resultado.getObject("nombre"));
				jugador.setSegundaDemarcacion((String)resultado.getObject("segunda_demarcacion"));
				jugadores.add(jugador);
			}
		}
		catch(SQLException errorCargaDatos){
			errorCargaDatos.printStackTrace();
		}
		equipo.setJugadores(jugadores);
		return jugadores;
	}
	
	public void save() {
		
	}
	

}
