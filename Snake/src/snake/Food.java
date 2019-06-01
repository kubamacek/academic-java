package snake;

public class Food extends Segment{
	private int value = 0;
	private boolean eaten = false;
	
	public void setEaten() {
		eaten = true;
	}
	
	public boolean isEasten() {
		return eaten;
	}
}
