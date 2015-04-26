package controladores;


import java.util.ArrayList;

import modelos.Equipo;
import modelos.Jugador;
import modelos.ModeloTablaJugadores;

public class EquipoController {
	
	Equipo equipo;
	
	public EquipoController(Equipo equipoCon) {
		equipo=equipoCon;
	}
	
	public void setEquipo(Equipo equipoSel){
		this.equipo=equipoSel;
	}

	public void cargaJugador(String nombre, int dorsal, String demarcacion, String segundaDemarcacion, String nacimiento){
		Jugador jugador=new Jugador();
		jugador.setNombre(nombre);
		jugador.setDemarcacion(demarcacion);
		jugador.setDorsal(dorsal);
		jugador.setSegundaDemarcacion(segundaDemarcacion);
		jugador.setFechaNacimiento(nacimiento);
		jugador.setId(0);
		this.equipo.addJugador(jugador);
	}
	
	public void eliminarJugador(int row) {
		equipo.removeJugador(row);
		
	}
}
