package ventanas;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Imagen extends JPanel{
	private String src;
	private int width, height;
	public Imagen(String src, int w, int h) {
		this.src=src;
		this.setPreferredSize(new Dimension(w, h));
		this.width=w;
		this.height=h;
		this.setBounds(200, 200, w, h);

	}
	
	public void cambiarPosicion(int x, int y){
		this.setBounds((int)(x-this.width/2), (int)(y-this.height/2), this.width, this.height);
		this.repaint();
	}
	
	public void paint(Graphics g) {
		Dimension dim = getSize();
		ImageIcon img = new ImageIcon(getClass().getResource(this.src));
		
		g.drawImage(img.getImage(), 0, 0, dim.width, dim.height, null);
		setOpaque(false);
		
		super.paintComponent(g);

	}
}
