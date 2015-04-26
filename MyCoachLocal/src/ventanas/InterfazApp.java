package ventanas;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class InterfazApp extends JPanel {

	
	private JComboBox comboBox;
	private ImageIcon fondoPanel;
	private JTable table;
	private JTable table_1;
	
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public InterfazApp() {
		setLayout(null);
		
		JComboBox cambiaEquipo = new JComboBox();
		cambiaEquipo.addItem("Cambia Equipo");
		cambiaEquipo.addItem("Ches.C.F");
		cambiaEquipo.addItem("Valencia C.F");
		cambiaEquipo.addItem("Elche C.F");
		cambiaEquipo.setFont(new Font("Tahoma", Font.PLAIN, 11));
		cambiaEquipo.setBackground(new Color(255, 255, 255));
		cambiaEquipo.setToolTipText("Cambiar Equipo");
		cambiaEquipo.setBounds(50, 17, 120, 35);
		this.add(cambiaEquipo);
		
		JComboBox cambiaFormacion = new JComboBox();
		cambiaFormacion.addItem("2-3-1");
		cambiaFormacion.addItem("3-2-1");
		cambiaFormacion.addItem("3-3");
		cambiaFormacion.setToolTipText("Elige Formacion");
		cambiaFormacion.setBounds(367, 24, 100, 20);
		this.add(cambiaFormacion);
		
		JButton botonGuardar = new JButton (new ImageIcon("C:\\Users\\dai\\Desktop\\button (2).png"));
		botonGuardar.setToolTipText("Guarda Formaci\u00F3n");
		botonGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		botonGuardar.setBounds(617, 21, 130, 31);
		this.add(botonGuardar);
		
		//añadimos campo
		
		CampoFutbol campo = new CampoFutbol();
		campo.setBorder(new LineBorder(new Color(0, 0, 0)));
		campo.setBounds(50, 150, 700, 450);
		this.add(campo);
		
		
		fondoPanel = new ImageIcon(getClass().getResource("/img/estadio.jpg"));
		setSize(fondoPanel.getIconWidth(),fondoPanel.getIconHeight());
		
		JLabel Formacion = new JLabel("Formacion:");
		Formacion.setForeground(Color.WHITE);
		Formacion.setFont(new Font("Georgia", Font.PLAIN, 12));
		Formacion.setBounds(280, 27, 77, 14);
		add(Formacion);
		
		table = new JTable();
		table.setCellSelectionEnabled(true);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
				{null},
			},
			new String[] {
				"New column"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(101);
		table.getColumnModel().getColumn(0).setMaxWidth(6666666);
		table.setBounds(803, 239, 210, 112);
		add(table);
		
		JLabel lblJugadoresTitulares = new JLabel("TITULARES");
		lblJugadoresTitulares.setForeground(Color.WHITE);
		lblJugadoresTitulares.setFont(new Font("Impact", Font.BOLD, 30));
		lblJugadoresTitulares.setBounds(835, 173, 142, 55);
		add(lblJugadoresTitulares);
		
		JLabel lblSuplentes = new JLabel("SUPLENTES");
		lblSuplentes.setFont(new Font("Impact", Font.PLAIN, 30));
		lblSuplentes.setForeground(Color.WHITE);
		lblSuplentes.setBounds(835, 455, 130, 49);
		add(lblSuplentes);
		
		table_1 = new JTable();
		table_1.setModel(new DefaultTableModel(
			new Object[][] {
				{null},
				{null},
				{null},
				{null},
			},
			new String[] {
				"New column"
			}
		));
		table_1.setBounds(803, 515, 215, 64);
		add(table_1);
		
}
	@Override
	protected void paintComponent(Graphics g){
		super.paintComponents(g);
		Dimension d = getSize();
		g.drawImage(fondoPanel.getImage(), 0, 0, d.width, d.height, null);
		
	}
}

