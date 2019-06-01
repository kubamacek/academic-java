package snake;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Movement implements KeyListener {
	private StringBuilder direction = new StringBuilder("");
	
	public void setDirection(StringBuilder dir) {
		// to pass by reference
		direction = dir;
	}
	
	public void keyPressed(KeyEvent k) {
		System.out.println(k.getKeyText(k.getKeyCode()));
		final String key = k.getKeyText(k.getKeyCode());
		direction.setLength(0);
		switch (key) {
			case "Up": {
				direction.append("up");
			}break;
			case "Down": {
				direction.append("down");
			}break;
			case "Right": {
				direction.append("right");
			}break;
			case "Left": {
				direction.append("left");
			}break;
			case "Esc": {
				System.out.println("Escape");
			}break;
		}
	}
	
	public void keyTyped(KeyEvent k) {
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
	}
}
