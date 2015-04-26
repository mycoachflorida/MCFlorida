package ventanas;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class CampoFutbol extends JPanel {
	
	private ImageIcon campoFutbol;

	/**
	 * Create the panel.
	 */
	public CampoFutbol() {
		
		campoFutbol = new ImageIcon(getClass().getResource("/img/estadio3.jpg"));
		setSize(campoFutbol.getIconWidth(),campoFutbol.getIconHeight());
		
		//Añadimos listeners del mouse
		setLayout(null);

		Imagen img=new Imagen("/img/ficha-roja.png", 50, 50);
		this.add(img);
		this.repaint();
		addMouseListener(new MouseListener() {
		
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				//System.out.println(":MOUSE_CLICK_EVENT:");
				Point p=e.getPoint();
				System.out.println(":MOUSE_CLICK_EVENT  Position X(): "+p.getX()+" Position Y(): "+p.getY());
				img.cambiarPosicion((int)p.getX(), (int)p.getY());
				
			}
		});
		
	}
	
	

	@Override
	protected void paintComponent(Graphics g){
		
		Dimension d = getSize();
		g.drawImage(campoFutbol.getImage(), 0, 0, d.width, d.height, null);
		super.paintComponents(g);
		
	}
}
