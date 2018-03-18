import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.util.ArrayList;

public class Point {
	int x, y, z, w;
	
	public Point(int a, int b, int c, int d) {
		x = a; y = b; z = c; w = d;
	}
}

class Line {
	Point a;
	Point b;
	
	public Line(Point one, Point two) {
		a = one;
		b = two;
	}
	
	public void drawLine(double V, double H, Graphics g, double distance) {
		int x1;
		int y1;
		int x2;
		int y2;
		x1 = (int)(Math.round(((double)a.x * Math.cos(H)) + ((double)a.z * Math.sin(H))) * distance/(distance + (double)a.w));
		x2 = (int)(Math.round(((double)b.x * Math.cos(H)) + ((double)b.z * Math.sin(H))) * distance/(distance + (double)b.w));
		y1 = (int)(Math.round(((double)a.y * Math.cos(V)) - ((double)a.x * Math.sin(V) * Math.sin(H)) + ((double)a.z * Math.sin(V)
				* Math.cos(H))) * distance/(distance + (double)a.w));
		y2 = (int)(Math.round(((double)b.y * Math.cos(V)) - ((double)b.x * Math.sin(V) * Math.sin(H)) + ((double)b.z * Math.sin(V)
				* Math.cos(H))) * distance/(distance + (double)b.w));
		g.drawLine(x1 + 400, y1 + 300, x2 + 400, y2 + 300);
	}
	
	public int averageW() {
		int averageW = (a.w + b.w) / 2;
		return averageW;
	}
}

class Face {
	ArrayList<Point> points;
	
	public Face() {
		points = new ArrayList<Point>();
	}
	
	public Face(ArrayList<Point> p) {
		points = p;
	}
	
	public void drawFace(double V, double H, Graphics g, double distance) {
		int[] x = new int[4];
		int[] y = new int[4];
		Polygon p = new Polygon();
		for (int k = 0; k < 4; k++) {
			x[k] = (int)(Math.round(((double)points.get(k).x * Math.cos(H)) + ((double)points.get(k).z * Math.sin(H)))
					* distance/(distance + (double)points.get(k).w));
			y[k] = (int)(Math.round(((double)points.get(k).y * Math.cos(V)) - ((double)points.get(k).x * Math.sin(V)
					* Math.sin(H)) + ((double)points.get(k).z * Math.sin(V) * Math.cos(H))) * distance/(distance + (double)points.get(k).w));
			p.addPoint(x[k] + 400, y[k] + 300);
		}
		g.setColor(Color.WHITE);
		g.fillPolygon(p);
		g.drawPolygon(p);
		g.setColor(Color.BLACK);
	}
	
	public int averageW() {
		int totalW = 0;
		for (int k = 0; k < points.size(); k++) {
			totalW += points.get(k).w;
		}
		totalW /= points.size();
		return totalW;
	}
}

class Polytope {
	ArrayList<Point> points;
	ArrayList<Line> lines;
	ArrayList<Face> faces;
	
	public Polytope() {
		points = new ArrayList<Point>();
		lines = new ArrayList<Line>();
		faces = new ArrayList<Face>();
	}
	
	public Polytope(ArrayList<Point> p, ArrayList<Line> l, ArrayList<Face> f) {
		points = p;
		lines = l;
		faces = f;
	}
}

class Tesseract extends Polytope {
	
	public Tesseract(int sidelength, Point center) {
		super();
		int x; int y; int z; int w;
		for (int k = 0; k < 16; k++) {
			if (k % 2 == 1)
				x = center.x + (sidelength / 2);
			else
				x = center.x - (sidelength / 2);
			if ((k / 2) % 2 == 1)
				y = center.y + (sidelength / 2);
			else
				y = center.y - (sidelength / 2);
			if ((k / 4) % 2 == 1)
				z = center.z + (sidelength / 2);
			else
				z = center.z - (sidelength / 2);
			if ((k / 8) % 2 == 1)
				w = center.w + (sidelength / 2);
			else
				w = center.w - (sidelength / 2);
			points.add(new Point(x,y,z,w));
		}
		for (int k = 0; k < 4; k++) {
			lines.add(new Line(points.get(0 + (4 * k)), points.get(1 + (4 * k))));
			lines.add(new Line(points.get(0 + (4 * k)), points.get(2 + (4 * k))));
			lines.add(new Line(points.get(1 + (4 * k)), points.get(3 + (4 * k))));
			lines.add(new Line(points.get(2 + (4 * k)), points.get(3 + (4 * k))));
		}
		for (int k = 0; k < 2; k++) {
			lines.add(new Line(points.get(0 + (8 * k)), points.get(4 + (8 * k))));
			lines.add(new Line(points.get(1 + (8 * k)), points.get(5 + (8 * k))));
			lines.add(new Line(points.get(2 + (8 * k)), points.get(6 + (8 * k))));
			lines.add(new Line(points.get(3 + (8 * k)), points.get(7 + (8 * k))));
		}
		lines.add(new Line(points.get(0), points.get(8)));
		lines.add(new Line(points.get(1), points.get(9)));
		lines.add(new Line(points.get(2), points.get(10)));
		lines.add(new Line(points.get(3), points.get(11)));
		lines.add(new Line(points.get(4), points.get(12)));
		lines.add(new Line(points.get(5), points.get(13)));
		lines.add(new Line(points.get(6), points.get(14)));
		lines.add(new Line(points.get(7), points.get(15)));
		for (int k = 0; k < 4; k++) {
			ArrayList<Point> temp = new ArrayList<Point>();
			temp.add(points.get(0 + (4 * k)));
			temp.add(points.get(1 + (4 * k)));
			temp.add(points.get(3 + (4 * k)));
			temp.add(points.get(2 + (4 * k)));
			faces.add(new Face(temp));
		}
		for (int k = 0; k < 2; k++) {
			ArrayList<Point> temp = new ArrayList<Point>();
			temp.add(points.get(0 + (8 * k)));
			temp.add(points.get(1 + (8 * k)));
			temp.add(points.get(5 + (8 * k)));
			temp.add(points.get(4 + (8 * k)));
			faces.add(new Face(temp));
			temp = new ArrayList<Point>();
			temp.add(points.get(0 + (8 * k)));
			temp.add(points.get(2 + (8 * k)));
			temp.add(points.get(6 + (8 * k)));
			temp.add(points.get(4 + (8 * k)));
			faces.add(new Face(temp));
			temp = new ArrayList<Point>();
			temp.add(points.get(1 + (8 * k)));
			temp.add(points.get(3 + (8 * k)));
			temp.add(points.get(7 + (8 * k)));
			temp.add(points.get(5 + (8 * k)));
			faces.add(new Face(temp));
			temp = new ArrayList<Point>();
			temp.add(points.get(2 + (8 * k)));
			temp.add(points.get(3 + (8 * k)));
			temp.add(points.get(7 + (8 * k)));
			temp.add(points.get(6 + (8 * k)));
			faces.add(new Face(temp));
		}
		for (int k = 0; k < 2; k++) {
			ArrayList<Point> temp = new ArrayList<Point>();
			temp.add(points.get(0 + (4 * k)));
			temp.add(points.get(1 + (4 * k)));
			temp.add(points.get(9 + (4 * k)));
			temp.add(points.get(8 + (4 * k)));
			faces.add(new Face(temp));
			temp = new ArrayList<Point>();
			temp.add(points.get(0 + (4 * k)));
			temp.add(points.get(2 + (4 * k)));
			temp.add(points.get(10 + (4 * k)));
			temp.add(points.get(8 + (4 * k)));
			faces.add(new Face(temp));
			temp = new ArrayList<Point>();
			temp.add(points.get(1 + (4 * k)));
			temp.add(points.get(3 + (4 * k)));
			temp.add(points.get(11 + (4 * k)));
			temp.add(points.get(9 + (4 * k)));
			faces.add(new Face(temp));
			temp = new ArrayList<Point>();
			temp.add(points.get(2 + (4 * k)));
			temp.add(points.get(3 + (4 * k)));
			temp.add(points.get(11 + (4 * k)));
			temp.add(points.get(10 + (4 * k)));
			faces.add(new Face(temp));
		}
		for (int k = 0; k < 4; k++) {
			ArrayList<Point> temp = new ArrayList<Point>();
			temp.add(points.get(0 + k));
			temp.add(points.get(4 + k));
			temp.add(points.get(12 + k));
			temp.add(points.get(8 + k));
			faces.add(new Face(temp));
		}
	}
	
	public void shiftTesseract(int x, int y, int z) {
		for (int k = 0; k < 16; k++) {
			points.get(k).x += x;
			points.get(k).y += y;
			points.get(k).z += z;
		}
	}
	
	public void drawTesseract(double V, double H, Graphics g, double distance) {
		int faceindex;
		int lineindex;
		int biggestW = 0;
		Face temp;
		int tempindex = 0;
		Line temp2;
		for (int j = faces.size() - 1; j > 0; j--) {
			for (int k = 0; k < j; k++) {
				biggestW = 0;
				tempindex = 0;
				if (faces.get(k).averageW() >= biggestW) {
					biggestW = faces.get(k).averageW();
					tempindex = k;
				}
			}
			temp = faces.get(tempindex);
			faces.set(tempindex, faces.get(j));
			faces.set(j, temp);
		}
		for (int j = lines.size() - 1; j > 0; j--) {
			for (int k = 0; k < j; k++) {
				biggestW = 0;
				tempindex = 0;
				if (lines.get(k).averageW() >= biggestW) {
					biggestW = lines.get(k).averageW();
					tempindex = k;
				}
			}
			temp2 = lines.get(tempindex);
			lines.set(tempindex, lines.get(j));
			lines.set(j, temp2);
		}
		faceindex = faces.size() - 1;
		lineindex = lines.size() - 1;
		do {
			if (faceindex >= 0 && lineindex >= 0) {
				if (faces.get(faceindex).averageW() >= lines.get(lineindex).averageW()) {
					faces.get(faceindex).drawFace(V, H, g, distance);
					faceindex--;
				}
				else {
					lines.get(lineindex).drawLine(V, H, g, distance);
					lineindex--;
				}
			}
			else if (faceindex >= 0) {
				faces.get(faceindex).drawFace(V, H, g, distance);
				faceindex--;
			}
			else {
				lines.get(lineindex).drawLine(V, H, g, distance);
				lineindex--;
			}
		} while (faceindex >= 0 || lineindex >= 0);
		//for (int k = 0; k < 32; k++) {
			//lines.get(k).drawLine(V,H,g,distance);
		//}
	}
}