package snake;

import java.awt.BorderLayout;

import java.awt.EventQueue;
import java.awt.Point;

import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.TimerTask;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

public class Board extends JFrame {
	private boolean running = false;
	private int points = 0;
	
	private JButton btnStart = new JButton("Start");
	private Snake snake = new Snake();
	private ArrayList<Food> foods = new ArrayList<>();
	private JPanel contentPane = new JPanel();
	private static StringBuilder direction = new StringBuilder ();
	
	
	private Timer timer = new Timer();
	private TimerTask taskSnake = new TimerTask() {
		@Override
		public void run() {
			if (running) {
				move();
				checkCollision();
				System.out.println(points);
			}
		}
	};
	private TimerTask taskFood = new TimerTask() {
		@Override
		public void run() {
			if (running) spawn();
		}
	};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Board frame = new Board();
					frame.setVisible(true);
					Movement movement = new Movement();
					movement.setDirection(direction);
					frame.addKeyListener(movement);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Board() {
		
		timer.scheduleAtFixedRate(taskSnake, 1000, Config.fastSpeed);
		timer.scheduleAtFixedRate(taskFood, 3000, Config.speedFoodRespown);
		
		setContentPane();
		setStartButton();
		setFPSSelect();
		setSnake();
		
	}
	
	private void checkCollision() {
		if (checkCollisionWithFrame()
			|| checkCollisionWithWalls()) {
			btnStart.setVisible(true);
			direction.setLength(0);
			snake.draw(contentPane);
			running = false;
		}
		
		if (checkCollisionWithFood()) {
			points++;
		}
	}
	
	private boolean checkCollisionWithFood() {
		boolean reached = false;
		Point pointSnake = snake.getLocation();
		Dimension dimSnake= snake.getSize();
		
		for (Food food : foods) {
			Point loc = food.getLocation();
			Dimension dimFood = food.getSize();
			if (!food.isEasten() && (isInsideBox(pointSnake.x, pointSnake.y, loc, dimFood) ||
				isInsideBox(pointSnake.x + dimSnake.width, pointSnake.y, loc, dimFood) ||
				isInsideBox(pointSnake.x, pointSnake.y + dimSnake.height, loc, dimFood) || 
				isInsideBox(pointSnake.x + dimSnake.width, pointSnake.y + dimSnake.height, loc, dimFood))) {
				reached = true;
				food.setEaten();
				food.removeLabel(contentPane);
			}
		}
		
		return reached;
	}
	
	private boolean isInsideBox(int x, int y, Point loc, Dimension dim) {
		boolean isInside = false;
		if (x > loc.x && x < loc.x + dim.width &&
			y > loc.y && y < loc.y + dim.height) isInside = true;
		
		return isInside;
	}
	
	private boolean checkCollisionWithFrame() {
		Dimension dimFrame = contentPane.getSize();
		Point point = snake.getLocation();
		Dimension dimSnake = snake.getSize();
		boolean reset = false;
		
		if (point.x < 0 ||
			point.x + dimSnake.width > dimFrame.width ||
			point.y < 0 ||
			point.y + dimSnake.height > dimFrame.height) {
			reset = true;
		}
		
		return reset;
	}
	
	private boolean checkCollisionWithWalls() {
		return false;
	}

	private void move() {
		Point oldPos = snake.getLocation();
		
		switch (direction.toString()) {
			case "up": {
				snake.setLocation(oldPos.x, oldPos.y - Config.step);
			}break;
			case "down": {
				snake.setLocation(oldPos.x, oldPos.y + Config.step);
			}break;
			case "right": {
				snake.setLocation(oldPos.x + Config.step, oldPos.y);
			}break;
			case "left": {
				snake.setLocation(oldPos.x - Config.step, oldPos.y);
			}break;
		}
	}
	
	private void setStartButton() {
		btnStart.setBounds(160, 110, 89, 23);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnStart.setVisible(false);
				direction.append(Config.initDirection);
				running = true;
			}
		});
		contentPane.add(btnStart);
	}
	
	private void setSnake() {
		snake.draw(contentPane);
		
	}
	
	private void setContentPane() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	}
	
	private void spawn() {
		Food food = new Food();
		Random rand = new Random();
		System.out.println(contentPane.getSize().width);
		int x = rand.nextInt(contentPane.getSize().width - Config.segmentSize);
		int y = rand.nextInt(contentPane.getSize().height - Config.segmentSize);
		food.draw(contentPane, x, y);
		foods.add(food);
	}
	
	private void setFPSSelect() {
		
	}
}
