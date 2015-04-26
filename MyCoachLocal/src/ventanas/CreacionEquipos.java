package ventanas;



import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;

import com.sun.glass.events.KeyEvent;

import controladores.CreacionEquiposControlador;
import controladores.EquipoController;
import modelos.Autentificacion;
import modelos.Demarcacion;
import modelos.Equipo;
import modelos.Jugador;
import modelos.ModeloTablaJugadores;
import modelos.MySql;

import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.ListSelectionModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ScrollPaneConstants;



public class CreacionEquipos extends JPanel {
	private static final int INICIO_FILA=0;
	private static final int FINAL_FILA=11;
	
	private JTextField nombreEquipo;
	private JTextField nombre;
	private JFormattedTextField dorsal;
	private JFormattedTextField edad;
	private JComboBox comboBoxPosicion, comboBox2Posicion;
	private ImageIcon fondoPanel;
	private JTable tablaJugadores;
	private int filaSeleccionada;
	private String posicion, segundaPosicion;
	private JTextField camposTexto[] = new JTextField[3];
	private Equipo equipoSeleccionado;
	private EquipoController equipoController;
	private boolean editingPlayer=false;
	Demarcacion demarcaciones[]={
			new Demarcacion("delantero"),
			new Demarcacion("defensa")
	                            
	};
	private int nFila=INICIO_FILA;

	/**
	 * Create the panel.
	 */
	public CreacionEquipos() {
		
		if(Autentificacion.isAutentificado()==true){
			setLayout(null);
			this.setBounds(0,0,1080,720);
			fondoPanel = new ImageIcon(getClass().getResource("/img/Fondodifuminado.jpg"));
			
			etiquetas();
			comboBox();
			jTextField();
			botones();
			tablaJugadores();
			equipoController=new EquipoController(equipoSeleccionado);
		}
		else{
			System.out.println("ERROR");
			//RETORNAR A LA VISTA DE LOGIN (EL USUARIO NO ESTÃ� AUTENTIFICADO)
		}
	}
	
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(fondoPanel.getImage(), 0, 0, null);
	}
	
	private void etiquetas(){
		JLabel lblNombreEquipo = new JLabel("Nombre equipo");
		lblNombreEquipo.setBounds(50, 53, 107, 27);
		add(lblNombreEquipo);
		
		JLabel lblFormacion = new JLabel("Formacion predefinida");
		lblFormacion.setBounds(398, 53, 155, 27);
		add(lblFormacion);
		
		JLabel lblEquipos = new JLabel("Equipos");
		lblEquipos.setBounds(487, 92, 66, 27);
		add(lblEquipos);
		
		JLabel lblTexto = new JLabel("INFORMACION DEL JUGADOR");
		lblTexto.setBounds(52, 171, 215, 16);
		add(lblTexto);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(90, 221, 55, 21);
		add(lblNombre);
		
		JLabel lblPosicion = new JLabel("Posicion");
		lblPosicion.setBounds(239, 221, 55, 21);
		add(lblPosicion);
		
		JLabel lblSegundaPosicion = new JLabel("2 Posicion");
		lblSegundaPosicion.setBounds(375, 221, 78, 21);
		add(lblSegundaPosicion);
		
		JLabel lblDorsal = new JLabel("Dorsal");
		lblDorsal.setBounds(537, 221, 55, 21);
		add(lblDorsal);
		
		JLabel lblEdad = new JLabel("Fecha Nacimiento");
		lblEdad.setBounds(643, 221, 134, 21);
		add(lblEdad);
	}
	
	private void comboBox(){
		JComboBox comboBoxFormacion = new JComboBox();
		comboBoxFormacion.setToolTipText("");
		comboBoxFormacion.setBounds(565, 54, 166, 27);
		add(comboBoxFormacion);

		comboBoxFormacion.addItem("Formacion 4-4-2");
		comboBoxFormacion.addItem("Formacion 3-4-3");
		comboBoxFormacion.addItem("Formacion 2-4-4");
		comboBoxFormacion.addItem("Formacion 4-2-4");
		
		//RECOGIENDO LOS EQUIPOS DEL USUARIO
		ArrayList<Equipo> equiposUsuario = new ArrayList<Equipo>();
		
		//CREACION PRIMER ITEM "NUEVO EQUIPO"
		Equipo equipoNuevo=new Equipo();
		equipoNuevo.setId(0);
		equipoNuevo.setNombre("Nuevo equipo");
		this.equipoSeleccionado=equipoNuevo;
		equiposUsuario.add(equipoNuevo);
		equiposUsuario.addAll(Autentificacion.getUsuario().getEquipos());
		
		
		JComboBox<Equipo> comboBoxEquipo = new JComboBox(equiposUsuario.toArray());
		
		comboBoxEquipo.setBounds(565, 93, 166, 27);
		comboBoxEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				equipoSeleccionado = (Equipo)comboBoxEquipo.getSelectedItem();
				equipoController.setEquipo(equipoSeleccionado);
				if(comboBoxEquipo.getSelectedIndex()==0){
					//CreacionEquiposControlador cec = new CreacionEquiposControlador();
					//cec.eliminarTodo();
					refrescarTabla();
				}
				else{
					//EquipoController ec = new EquipoController();
					//nFila=ec.cargaJugadores(equipoSeleccionado);
					refrescarTabla();
				}
			}
		});
		add(comboBoxEquipo);
	
		
		comboBoxPosicion = new JComboBox(demarcaciones);
		comboBoxPosicion.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				posicion = ((Demarcacion) comboBoxPosicion.getSelectedItem()).getIndex();
				
			}
		});
		comboBoxPosicion.setBounds(196, 243, 134, 28);
		add(comboBoxPosicion);
		
		
		
		
		comboBox2Posicion = new JComboBox(demarcaciones);
		comboBox2Posicion.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				segundaPosicion = ((Demarcacion) comboBox2Posicion.getSelectedItem()).getIndex();
				System.out.println(segundaPosicion);
			}
		});
		comboBox2Posicion.setBounds(341, 243, 134, 28);
		add(comboBox2Posicion);
		
		
	}
	
	private void jTextField(){
		nombreEquipo = new JTextField();
		nombreEquipo.setBounds(169, 52, 134, 28);
		add(nombreEquipo);
		nombreEquipo.setColumns(10);
		nombreEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				equipoSeleccionado.setNombre(nombreEquipo.getText());
				
			}
		});
		
		
		nombre = new JTextField();
		nombre.setBounds(50, 243, 134, 28);
		add(nombre);
		nombre.setColumns(10);
		camposTexto[0]=nombre;

		dorsal = new JFormattedTextField();
		dorsal.setBounds(487, 243, 134, 28);
		add(dorsal);
		dorsal.setColumns(10);
		camposTexto[1]=dorsal;
		
		edad = new JFormattedTextField();
		edad.setBounds(633, 243, 134, 28);
		add(edad);
		edad.setColumns(10);
		camposTexto[2]=edad;
	}
	
	private boolean limitarDorsalYedad(){
		if(dorsal.getText().length()>2){
			JOptionPane.showMessageDialog(null, "El dorsal solo deben tener 2 dÃ­gitos.");
			return false;
		}
		return true;
	}
	
	private void botones(){
		JButton btnAddJugador = new JButton("Crear Jugador ");
		btnAddJugador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accionBotonAddJugador(comboBoxPosicion,comboBox2Posicion);
			}
		});
		btnAddJugador.setBounds(779, 243, 134, 29);
		add(btnAddJugador);
		
		JButton btnEditarJugador = new JButton("Editar Jugador ");
		btnEditarJugador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accionBotonEditarJugador();
			}
		});
		btnEditarJugador.setBounds(779, 300, 135, 29);
		add(btnEditarJugador);
		
		JButton btnEliminarJugador = new JButton("Eliminar Jugador ");
		btnEliminarJugador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accionBotonEliminarJugador(); //ESTE METODO Y EVENTO DEL BOTON NO FUNCIONA CORRECTAMENTE (OJEAR).
			}
		});
		btnEliminarJugador.setBounds(779, 334, 135, 29);
		add(btnEliminarJugador);
		
		
		JButton btnRegistraEquipo = new JButton("Registrar Equipo");
		btnRegistraEquipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accionBotonRegistraEquipo();
			}
		});
		btnRegistraEquipo.setBounds(632, 520, 135, 29);
		add(btnRegistraEquipo);
		
		JButton btnEliminarTodo = new JButton("Eliminar todo");
		btnEliminarTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				accionBotonEliminarTodo();
			}
		});
		btnEliminarTodo.setBounds(486, 520, 135, 29);
		add(btnEliminarTodo);
		
		JButton btnIrATacticas = new JButton("Ir a Tacticas");
		btnIrATacticas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnIrATacticas.setBounds(331, 520, 144, 29);
		add(btnIrATacticas);
	}
	
	private void accionBotonAddJugador(JComboBox comboBoxPosicion, JComboBox comboBox2Posicion){
		
		//CreacionEquiposControlador cec = new CreacionEquiposControlador();
		if(CreacionEquiposControlador.compruebaCampos(nombre.getText(), ((Demarcacion)comboBoxPosicion.getSelectedItem()).getIndex() ,((Demarcacion)comboBox2Posicion.getSelectedItem()).getIndex(),dorsal.getText(),edad.getText())==true){
			if(CreacionEquiposControlador.esNumerico(dorsal.getText())==true /*&& CreacionEquiposControlador.esNumerico(edad.getText())==true*/){
				if(limitarDorsalYedad()==true){
					if(nFila != FINAL_FILA){
						
						//cec.volcarDatosTabla(nombre.getText(),posicion,segundaPosicion,dorsal.getText(),edad.getText(),nFila);

						equipoController.cargaJugador(
								nombre.getText(), 
								Integer.parseInt(dorsal.getText()), 
								((Demarcacion)comboBoxPosicion.getSelectedItem()).getIndex(), 
								((Demarcacion)comboBox2Posicion.getSelectedItem()).getIndex(),
								edad.getText()
						);
						refrescarTabla();
						vaciarJTextField();
						comboBoxPosicion.setSelectedItem("Sin asignar");
						comboBox2Posicion.setSelectedItem("Sin asignar");
						nFila++;	
					}
					else{
						JOptionPane.showMessageDialog(null, "No puedes registrar mï¿½s de 11 jugadores.");
					}
				}
				
			}
			else{
				JOptionPane.showMessageDialog(null, "Los campos dorsal y edad deben ser numï¿½ricos.");
			}
		}
		else{
			
		}
	}
	
	private void accionBotonEditarJugador(){
		if(tablaJugadores.getSelectedRow()>-1) {
			Jugador jugador=equipoSeleccionado.getJugador(tablaJugadores.getSelectedRow());
			nombre.setText(jugador.getNombre());
			int indexDemarcacion=0;
			for(int i=0; i<demarcaciones.length; i++) {
				if(demarcaciones[i].getIndex().equals(jugador.getDemarcacion())) {
					indexDemarcacion=i;
					break;
				}
			}
			
			comboBoxPosicion.setSelectedIndex(indexDemarcacion);
			
			indexDemarcacion=0;
			for(int i=0; i<demarcaciones.length; i++) {
				
				if(demarcaciones[i].getIndex().equals(jugador.getSegundaDemarcacion())) {
					indexDemarcacion=i;
					break;
				}
			}
			
			comboBox2Posicion.setSelectedIndex(indexDemarcacion);
			
			dorsal.setText(String.valueOf(jugador.getDorsal()));
			
			edad.setText(jugador.getFechaNacimiento());
			
			
		}
	}
	
	private void accionBotonEliminarJugador(){
		if(tablaJugadores.getSelectedRow()>-1) {
			equipoController.eliminarJugador(tablaJugadores.getSelectedRow());
			refrescarTabla();
		}
		
	}
	
	private void accionBotonRegistraEquipo(){
		CreacionEquiposControlador cec = new CreacionEquiposControlador();
		cec.registraEquipo();
	}
	
	private void accionBotonEliminarTodo(){
		CreacionEquiposControlador cec = new CreacionEquiposControlador();
		cec.eliminarTodo();
		refrescarTabla();
		nFila=INICIO_FILA;
	}
	
	private void tablaJugadores(){

		tablaJugadores = new JTable();
		
		tablaJugadores.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				filaSeleccionada = tablaJugadores.getSelectedRow();
				//System.out.println(filaSeleccionada);
			}
		});
		
		tablaJugadores.setGridColor(SystemColor.controlShadow);
		tablaJugadores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaJugadores.setFont(new Font("Tahoma", Font.BOLD, 11));
		tablaJugadores.setFillsViewportHeight(true);
		tablaJugadores.setColumnSelectionAllowed(true);
		tablaJugadores.setCellSelectionEnabled(true);
		tablaJugadores.setBackground(Color.WHITE);
		tablaJugadores.setBorder(new LineBorder(new Color(128, 128, 128)));
		tablaJugadores.setModel(equipoSeleccionado);
		tablaJugadores.getColumnModel().getColumn(0).setResizable(false);
		tablaJugadores.getColumnModel().getColumn(1).setResizable(false);
		tablaJugadores.getColumnModel().getColumn(2).setResizable(false);
		tablaJugadores.getColumnModel().getColumn(3).setResizable(false);
		tablaJugadores.getColumnModel().getColumn(4).setResizable(false);
		tablaJugadores.setBounds(50, 300, 715, 192);
		tablaJugadores.getTableHeader().setFont(new Font("Arial", Font.BOLD, 11));
		JScrollPane scrollPane = new JScrollPane(tablaJugadores);
		scrollPane.setBounds(50, 300, 715, 192);
		add(scrollPane);
		alineacionCentradaCeldas();
	}
	
	public JTable getTablaJugadores(){
		return tablaJugadores;
	} 
	
	public void alineacionCentradaCeldas(){
		DefaultTableCellRenderer AlinearTablaJugadores = new DefaultTableCellRenderer();
		AlinearTablaJugadores.setHorizontalAlignment(SwingConstants.CENTER);//ALINEACION CENTRADA
		tablaJugadores.getTableHeader().getColumnModel().getColumn(0).setCellRenderer(AlinearTablaJugadores);
		tablaJugadores.getTableHeader().getColumnModel().getColumn(1).setCellRenderer(AlinearTablaJugadores);
		tablaJugadores.getTableHeader().getColumnModel().getColumn(2).setCellRenderer(AlinearTablaJugadores);
		tablaJugadores.getTableHeader().getColumnModel().getColumn(3).setCellRenderer(AlinearTablaJugadores);
		tablaJugadores.getTableHeader().getColumnModel().getColumn(4).setCellRenderer(AlinearTablaJugadores);
		
	}
	
	
	private void refrescarTabla(){
		//REFRESCO DE DATOS EN TABLA
		tablaJugadores.setModel(equipoSeleccionado);
		tablaJugadores.repaint();
		alineacionCentradaCeldas();
	}
	
	
	public void vaciarJTextField(){
		//VACIADO DE JTEXTFIELD'S
		JTextField arrayJText[] = new JTextField[3];
		arrayJText[0] = nombre;
		arrayJText[1] = dorsal;
		arrayJText[2] = edad;
		
		for(int a=0;a<arrayJText.length;a++){
			arrayJText[a].setText("");
		}
	}
}