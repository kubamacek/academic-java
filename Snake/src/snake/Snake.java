package snake;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Snake {
	private Segment head = new Segment();
	
	public void draw(JPanel pane) {
		head.draw(pane);
	}
	
	public Point getLocation() {
		return head.getLocation();
	}
	
	public Dimension getSize() {
		return head.getSize();
	}
	
	public void setLocation(int x, int y) {
		head.setLocation(x, y);
	}
}
