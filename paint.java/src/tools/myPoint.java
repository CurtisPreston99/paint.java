package tools;


public class myPoint implements Comparable<myPoint> {
	@Override
	public String toString() {
		return "myPoint [x=" + x + ", y=" + y + "]";
	}

	public int x;
	public int y;

	public myPoint(int a, int b) {
		x = a;
		y = b;
	}

	boolean equals(myPoint other) {
		return (x == other.x && y == other.y);
	}

	@Override
	public int compareTo(myPoint p) {
		if (p.x != this.x) {
			return p.x - x;
		}
		if (p.y != this.y) {
			return p.y - y;
		}

		return 0;
	}
}