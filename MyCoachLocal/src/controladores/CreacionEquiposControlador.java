package controladores;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import modelos.ModeloTablaJugadores;

public class CreacionEquiposControlador {


	private ModeloTablaJugadores mtj;
	private String datosInsercion[];
	private String jugadores[][];

	public CreacionEquiposControlador(){
		datosInsercion = new String[5];
	}
	
	public void volcarDatosTabla(String datoNombre, String datoPosicion, String datoSegundaPosicion, String datoDorsal, String datoEdad, int datoFila){
		//VOLCADO DE DATOS DE ENTRADA EN ARRAY
		datosInsercion[0] = datoNombre;
		datosInsercion[1] = datoPosicion;
		datosInsercion[2] = datoSegundaPosicion;
		datosInsercion[3] = datoDorsal; 
		datosInsercion[4] = datoEdad; 
		
		mtj = new ModeloTablaJugadores();
		jugadores = mtj.getJugadores(); 
		
		for(int x=0;x<datosInsercion.length;x++){
				jugadores[datoFila][x] = datosInsercion[x];	
		}
	}
	
	public static boolean esNumerico(String valor){
		try{
			Integer.parseInt(valor);
			return true;
		}
		catch(NumberFormatException nfe){
			return false;
		}
	}
	
	public static boolean compruebaCampos(String nombre, String posicion, String segundaPosicion, String dorsal, String edad){
		if(nombre.equals("") || posicion.equals("Sin asignar") || segundaPosicion.equals("Sin asignar") || dorsal.equals("") || edad.equals("")){
			JOptionPane.showMessageDialog(null, "Faltan campos por rellenar.");
			return false;
		}
		else{
			return true;
		}
	}
	
	//REVISAR METODOS EDITAR Y ELIMINAR JUGADOR (NO FUNCIONAN CORRECTAMENTE)
	public void editarJugador(int filaEdicion){
		jugadores = mtj.getJugadores();
	}
	
	public void eliminarTodo(){
		ModeloTablaJugadores mtj = new ModeloTablaJugadores();
		String jugadores[][] = mtj.getJugadores();
		//DECLARACION DE CONSTANTES
		final int COLUMNAS=5;
		final int FILAS=11;
		//RECORRIDO Y VACIADO DE MATRIZ
		for(int a=0;a<FILAS;a++){
			for(int b=0;b<COLUMNAS;b++){
				jugadores[a][b] = "";
			}
		}
	}
	
	public void eliminarJugador(int filaEliminacion){
		ModeloTablaJugadores mtj = new ModeloTablaJugadores();
		String jugadores[][] = mtj.getJugadores();
		final int COLUMNAS=5;
		
		if(jugadores[filaEliminacion][0] == ""){
				JOptionPane.showMessageDialog(null, "Seleccione un jugador para eliminar");
		}
		else{
			for(int a=0;a<COLUMNAS;a++){
				jugadores[filaEliminacion][a] = "";
			}
		}
	}
	
	public void registraEquipo(){
		
	}
		
}
