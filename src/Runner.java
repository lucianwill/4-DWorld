import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Graphics;
import java.util.ArrayList;

public class Runner extends JPanel implements ActionListener, KeyListener{
	private static final long serialVersionUID = 1L;
	Timer tm = new Timer(5, this);
	int method = 0;
	double V = 0;
	double H = Math.PI/2;
	double zoom = 0;
	int turn = 0;
	int elevate = 0;
	int xOffset = 0;
	int xChange = 0;
	int yOffset = 0;
	int yChange = 0;
	int zOffset = 0;
	int zChange = 0;
	double distance = 100;
	double distanceChange;
	ArrayList<Point> points;
	ArrayList<Line> lines;
	Tesseract test;
	
	public Runner() {
		tm.start();
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		test = new Tesseract(100, new Point(0,0,0,50));
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		ArrayList<Point> points = new ArrayList<Point>();
		ArrayList<Line> lines = new ArrayList<Line>();
		/*points.add(new Point(0 + xOffset,0 + yOffset,0 + zOffset,0));
		points.add(new Point(100 + xOffset,0 + yOffset,0 + zOffset,0));
		points.add(new Point(0 + xOffset,100 + yOffset,0 + zOffset,0));
		points.add(new Point(100 + xOffset,100 + yOffset,0 + zOffset,0));
		points.add(new Point(0 + xOffset,0 + yOffset,100 + zOffset,0));
		points.add(new Point(100 + xOffset,0 + yOffset,100 + zOffset,0));
		points.add(new Point(0 + xOffset,100 + yOffset,100 + zOffset,0));
		points.add(new Point(100 + xOffset,100 + yOffset,100 + zOffset,0));
		points.add(new Point(0 + xOffset,0 + yOffset,0 + zOffset,100));
		points.add(new Point(100 + xOffset,0 + yOffset,0 + zOffset,100));
		points.add(new Point(0 + xOffset,100 + yOffset,0 + zOffset,100));
		points.add(new Point(100 + xOffset,100 + yOffset,0 + zOffset,100));
		points.add(new Point(0 + xOffset,0 + yOffset,100 + zOffset,100));
		points.add(new Point(100 + xOffset,0 + yOffset,100 + zOffset,100));
		points.add(new Point(0 + xOffset,100 + yOffset,100 + zOffset,100));
		points.add(new Point(100 + xOffset,100 + yOffset,100 + zOffset,100));
		lines.add(new Line(points.get(0), points.get(1)));
		lines.add(new Line(points.get(0), points.get(2)));
		lines.add(new Line(points.get(1), points.get(3)));
		lines.add(new Line(points.get(2), points.get(3)));
		lines.add(new Line(points.get(0), points.get(4)));
		lines.add(new Line(points.get(1), points.get(5)));
		lines.add(new Line(points.get(2), points.get(6)));
		lines.add(new Line(points.get(3), points.get(7)));
		lines.add(new Line(points.get(4), points.get(5)));
		lines.add(new Line(points.get(4), points.get(6)));
		lines.add(new Line(points.get(5), points.get(7)));
		lines.add(new Line(points.get(6), points.get(7)));
		lines.add(new Line(points.get(0), points.get(8)));
		lines.add(new Line(points.get(1), points.get(9)));
		lines.add(new Line(points.get(2), points.get(10)));
		lines.add(new Line(points.get(3), points.get(11)));
		lines.add(new Line(points.get(4), points.get(12)));
		lines.add(new Line(points.get(5), points.get(13)));
		lines.add(new Line(points.get(6), points.get(14)));
		lines.add(new Line(points.get(7), points.get(15)));
		lines.add(new Line(points.get(8), points.get(9)));
		lines.add(new Line(points.get(8), points.get(10)));
		lines.add(new Line(points.get(9), points.get(11)));
		lines.add(new Line(points.get(10), points.get(11)));
		lines.add(new Line(points.get(8), points.get(12)));
		lines.add(new Line(points.get(9), points.get(13)));
		lines.add(new Line(points.get(10), points.get(14)));
		lines.add(new Line(points.get(11), points.get(15)));
		lines.add(new Line(points.get(12), points.get(13)));
		lines.add(new Line(points.get(12), points.get(14)));
		//lines.add(new Line(points.get(13), points.get(15)));
		//lines.add(new Line(points.get(14), points.get(15)));*/
		test.drawTesseract(V,H,g,distance);
		//for (int k = 0; k < lines.size(); k++) {
			//lines.get(k).drawLine(V, H, g, distance);
		//}
	}
	
	public void actionPerformed(ActionEvent e) {
		H += (double)turn * tm.getDelay()/160;
		if (V + (double)elevate * tm.getDelay()/160 > Math.PI/2)
			V = Math.PI/2;
		else if (V + (double)elevate * tm.getDelay()/160 < -Math.PI/2)
			V = -Math.PI/2;
		else
			V += (double)elevate * tm.getDelay()/160;
		if (distance + distanceChange * tm.getDelay()/10 < 50)
			distance = 50;
		else
			distance += distanceChange * tm.getDelay()/10;
		test.shiftTesseract(xChange, yChange, zChange);
		repaint();
	}
	
	public void keyPressed(KeyEvent e) {
		int c = e.getKeyCode();
		if (c == KeyEvent.VK_LEFT) {
			turn = 1;
		}
		if (c == KeyEvent.VK_RIGHT) {
			turn = -1;
		}
		if (c == KeyEvent.VK_UP) {
			elevate = 1;
		}
		if (c == KeyEvent.VK_DOWN) {
			elevate = -1;
		}
		if (c == KeyEvent.VK_T) {
			distanceChange = 1;
		}
		if (c == KeyEvent.VK_G) {
			distanceChange = -1;
		}
		if (c == KeyEvent.VK_A) {
			xChange = 1;
		}
		if (c == KeyEvent.VK_D) {
			xChange = -1;
		}
		if (c == KeyEvent.VK_W) {
			yChange = 1;
		}
		if (c == KeyEvent.VK_S) {
			yChange = -1;
		}
		if (c == KeyEvent.VK_Q) {
			zChange = 1;
		}
		if (c == KeyEvent.VK_E) {
			zChange = -1;
		}
	}
	
	public void keyTyped(KeyEvent e) {}
	
	public void keyReleased(KeyEvent e) {
		turn = 0;
		elevate = 0;
		distanceChange = 0;
		xChange = 0;
		yChange = 0;
		zChange = 0;
	}
	
	public static void main(String[] args) {
		Runner p = new Runner();
		JFrame jf = new JFrame();
		jf.setTitle("4-D Explorer");
		jf.setSize(800,600);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jf.add(p);
	}
}
