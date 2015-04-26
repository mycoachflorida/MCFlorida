package modelos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Tactica {
	
	private int id;
	private String nombre;

	public Tactica() {
		// TODO Auto-generated constructor stub
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

	public static ArrayList<Tactica> load(Equipo equipo){
		MySql conexion = MySql.getInstance();
		int idEquipo = equipo.getId();
		ResultSet resultado = conexion.query("select id_tactica, nombre FROM tacticas WHERE id_equipo="+idEquipo);
		Tactica tactica = new Tactica();
		
		ArrayList<Tactica> tacticas = new ArrayList<Tactica>();
		try{
			while(resultado.next()){
				tactica.setId((int)resultado.getObject("id_tactica"));
				tactica.setNombre((String)resultado.getObject("nombre"));
				tacticas.add(tactica);
			}
		}
		catch(SQLException errorCarga){
			errorCarga.printStackTrace();
		}
		equipo.setTacticas(tacticas);
		return tacticas;
	}
}
