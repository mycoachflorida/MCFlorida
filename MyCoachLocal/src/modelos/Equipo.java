package modelos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controladores.IdiomaController;

public class Equipo extends AbstractTableModel  {
	
	private int id;
	private String nombre;
	private int disciplina;
	private ArrayList<Jugador> jugadores;
	private ArrayList<Tactica> tacticas;
	private boolean modificado;
	private boolean borradoJugadores;
	private static Object[][] jugadoresTabla=new Object[0][6];
	private static String columnas[] = new String[]{"Nombre","Posicion","Segunda posicion","Dorsal","Fecha Nacimiento"};
	private IdiomaController idioma= IdiomaController.getInstance("es");
	
	public Equipo() {
		
		modificado=false;
		borradoJugadores=false;
		jugadores=new ArrayList<Jugador>();
		
		
	}
	
	public void setBorradoJugadores(boolean mod) {
		this.borradoJugadores=mod;
	}
	
	public boolean getBorradoJugadores() {
		return this.borradoJugadores;
	}
	
	public void setModificado(boolean mod) {
		this.modificado=mod;
	}
	
	public boolean getModificado() {
		return this.modificado;
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

	public int getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(int disciplina) {
		this.disciplina = disciplina;
	}

	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(ArrayList<Jugador> jugadores) {
		this.jugadores = jugadores;
		
	}

	public ArrayList<Tactica> getTacticas() {
		return tacticas;
	}

	public void setTacticas(ArrayList<Tactica> tacticas) {
		this.tacticas = tacticas;
	}
	
	public static ArrayList<Equipo> load(Usuario usuario){
		MySql conexion = MySql.getInstance();
		int idUser = usuario.getId();
		ResultSet resultado = conexion.query("select * from equipos where id_usuario = "+idUser);
		
		ArrayList<Equipo> equipos = new ArrayList<Equipo>();
		
		try {
			while (resultado.next()){
				try {
					//RECOGIDA DE DATOS DE LA BBDD (TABLA EQUIPOS)
					
					Equipo nuevoEquipo = new Equipo();
					nuevoEquipo.setId((int)resultado.getObject("id_equipo"));
					nuevoEquipo.setNombre((String)resultado.getObject("nombre"));
					nuevoEquipo.setDisciplina((int)resultado.getObject("id_disciplina"));
					Jugador.load(nuevoEquipo);
					
					
					//AÃ‘ADIMOS EL NUEVO EQUIPO RECOGIDO DE LA BBDD EN EL ARRAYLIST
					equipos.add(nuevoEquipo);
					
				} catch (SQLException errorObtenerDatos) {
					//CAPTURA DE EXCEPCION EN CASO DE FALLO
					errorObtenerDatos.printStackTrace();
				}
			}
		} catch (SQLException e) {
			//CAPTURA DE EXCEPCION EN CASO DE FALLO (resultado.next())
			e.printStackTrace();
		}
		
		//ASIGNAMOS EL EQUIPO AL CORRESPONDIENTE USUARIO SEGUN SU ID
		usuario.setEquipos(equipos);
		
		return equipos;
	}
	
	public boolean save(){
		
		MySql conexion=MySql.getInstance();
		
		if(this.getId()==0) {
			conexion.modifyQuery("INSERT INTO equipos set nombre="+this.getNombre()+", set id_disciplina=1, id_usuario="+Autentificacion.getId());
			ResultSet rs=conexion.query("SELECT LAST_INSERT_ID() as last_id");
			try {
				if(rs.next()) {
					this.setId((int) rs.getObject("last_id"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				
			}
			for(int i=0; i<this.jugadores.size(); i++) {
				jugadores.get(i).save();
			}
		}
		else if(this.getModificado()) {
			conexion.modifyQuery("UPDATE equipos set nombre="+this.getNombre()+" WHERE id_equipo="+this.getId());
		}
		
		if(this.getBorradoJugadores()) {
			ResultSet rs=conexion.query("SELECT id_jugador from jugadores where id_equipo="+this.getId());
			try {
				
				while(rs.next()) {
					int id_jugador_bbdd=(int)rs.getObject("id_jugador");
					boolean borrarJugador=true;
					for(int i=0; i<jugadores.size(); i++) {
						if(jugadores.get(i).getId()==id_jugador_bbdd) {
							borrarJugador=false;
							break;
					}
					if(borrarJugador) {
						conexion.modifyQuery("DELETE from jugadores  WHERE id_jugador="+id_jugador_bbdd);

					}
				}
					
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		return true;
		
		
	}
	
	public String toString(){
		return this.nombre;
	}
	
	public void addJugador(Jugador jugador) {
		
		jugadores.add(jugador);
		
		
		
	}
	
	public Jugador getJugador(int row) {
		return jugadores.get(row);
	}
	
	

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return jugadores.size();
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		
		 Object value = "??";
	     Jugador jugador = jugadores.get(rowIndex);
	     
	     switch (columnIndex) {
         case 0:
             value = jugador.getNombre();
             break;
         case 1:
             value = idioma.getTraduccion(jugador.getDemarcacion());
             break;
         case 2:
        	 value = idioma.getTraduccion(jugador.getSegundaDemarcacion());
             break;
         case 3:
             value = jugador.getDorsal();
             break;
         case 4:
             value = jugador.getFechaNacimiento();
             break;
       
	     }

	     return value;
	     
		
	}
	
	@Override
	public String getColumnName(int columnIndex) {
	    
	    return columnas[columnIndex];
	}

	public void removeJugador(int row) {
		jugadores.remove(row);
	}
	

}


