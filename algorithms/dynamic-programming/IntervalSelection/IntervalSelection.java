import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class IntervalSelection {

	static class Point implements Comparable<Point> {
		final int a;
		final int b;
		Point(int a, int b) {
			this.a = a;
			this.b = b;
		}
		public int compareTo(Point that) {
			if (this.a == that.a) {
				return Integer.compare(this.b, that.b);
			} else {
				return Integer.compare(this.a, that.a);
			}
		}
		public boolean intersects(Point that) {
			return this.b >= that.a && this.a <= that.b;
		}
		public Point common(Point that) {
			return new Point(Math.max(this.a, that.a), Math.min(this.b, that.b));
		}
		public String toString() {
			return String.format("(a=%d, b=%d)", a, b);
		}	
	}

	final int n;
	final Point[] P;
	final Point[][] C;
	
	IntervalSelection(Point[] P) {
		this.n = P.length;
		this.P = P;
		this.C = new Point[n][n];
		for (int a = 0; a < n - 1; a++) {
			for (int b = a + 1; b < n; b++) {
				if (P[a].intersects(P[b])) {
					C[a][b] = P[a].common(P[b]);
				}
			}
		}
	}
	
	boolean ok(Point a, Point b, Point c) {
		if (a.intersects(b) == false || a.intersects(c) == false || b.intersects(c) == false) {
			return true;
		}
		return a.intersects(b.common(c)) == false;
	}

	void solve(List<Point> L, Point c) {
		int m = L.size();
		for (int ia = 0; ia < m - 1; ia++) {
			for (int ib = ia + 1; ib < m; ib++) {
				Point a = L.get(ia);
				Point b = L.get(ib);
				if (!ok(a, b, c)) {
					return;
				} 
			}
		}
		L.add(c);
	}

	int solve() {
		List<List<Point>> LL = new ArrayList<>();
		for (Point c : P) {
			for (List<Point> L : LL) {
				solve(L, c);	
			}
			List<Point> L = new ArrayList<>();
			L.add(c);
			LL.add(L);
		}
		int max = 0;
		for (List<Point> L : LL) {
			max = Math.max(max, L.size());
		}
		return max;
	}

	static IntervalSelection load(Scanner scanner) {
		int n = scanner.nextInt();
		assert 2 <= n && n <= 1000 : "out of range, n: " + n;
		Point[] P = scanPointArray(scanner, n);
		return new IntervalSelection(P);
	}	

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		assert 1 <= t && t <= 100 : "out of range, t: " + t;
		while (t-->0) {
			IntervalSelection o = IntervalSelection.load(scanner);
			System.out.println(o.solve());
		}
	}

	static Point[] scanPointArray(Scanner scanner, int n) {
		Point[] A = new Point[n];
		for (int i = 0; i < n; i++) {
			int x = scanner.nextInt();
			int y = scanner.nextInt();
			assert x <= y;
			A[i] = new Point(x, y);
		}
		return A;
	}

}
