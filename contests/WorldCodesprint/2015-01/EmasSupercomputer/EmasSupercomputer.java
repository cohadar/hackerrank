import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class EmasSupercomputer {

	static class Point {
		final int y;
		final int x;
		Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
		public String toString() {
			return String.format("(y=%d, x=%d)", y, x);
		}	
	}

	final int n;
	final int m;
	final boolean[][] G;
	final Point[][] P;
	
	EmasSupercomputer(int n, int m, boolean[][] G) {
		this.n = n;
		this.m = m;
		this.G = G;
		this.P = new Point[n][m];
		for (int y = 0; y < n; y++) {
			for (int x = 0; x < m; x++) {
				P[y][x] = new Point(y, x);
			}
		}
	}
	
	int area(int length) {
		return (length - 1) * 4 + 1;
 	}

 	boolean g(int y, int x) {
 		if (y < 0 || y >= n) {
 			return false;
 		}
 		if (x < 0 || x >= m) {
 			return false;
 		}
 		return G[y][x];
 	}

 	int solveB(int y, int x) {
		List<Point> B = new ArrayList<>();
		int max = 1;
		G[y][x] = false;
		B.add(P[y][x]);
		for (int i = 1; i < 15; i++) {
			if (g(y + i, x) && g(y - i, x) && g(y, x + i) && g(y, x - i)) {
				G[y + i][x] = false;
				B.add(P[y + i][x]);
				G[y - i][x] = false;
				B.add(P[y - i][x]);
				G[y][x + i] = false;
				B.add(P[y][x + i]);
				G[y][x - i] = false;
				B.add(P[y][x - i]);
				max = Math.max(max, area(i + 1));	
			} else {
				break;
			}
		}
		for (Point p : B) {
			G[p.y][p.x] = true;
		}
		return max;
 	}

 	int solveB() {
 		int max = 0;
 		for (int y = 0; y < n; y++) {
 			for (int x = 0; x < m; x++) {
 				if (G[y][x]) {
 					max = Math.max(max, solveB(y, x));
 				}
 			}
 		}
		return max;
 	}

	int solveA(int y, int x) {
		List<Point> A = new ArrayList<>();
		int max = 0;
		G[y][x] = false;
		A.add(P[y][x]);
		max = Math.max(max, 1 * solveB());
		for (int i = 1; i < 15; i++) {
			if (g(y + i, x) && g(y - i, x) && g(y, x + i) && g(y, x - i)) {
				G[y + i][x] = false;
				A.add(P[y + i][x]);
				G[y - i][x] = false;
				A.add(P[y - i][x]);
				G[y][x + i] = false;
				A.add(P[y][x + i]);
				G[y][x - i] = false;
				A.add(P[y][x - i]);
				max = Math.max(max, area(i + 1) * solveB());	
			} else {
				break;
			}
		}
		for (Point p : A) {
			G[p.y][p.x] = true;
		}
		return max;
	}

	int solve() {
		int max = 0;
		for (int y = 0; y < n; y++) {
			for (int x = 0; x < m; x++) {
				if (G[y][x]) {
					max = Math.max(max, solveA(y, x));
				}
			}
		}
		return max;
	}

	static EmasSupercomputer load(Scanner scanner) {
		String[] _nm = scanner.nextLine().split(" ");
		int n = Integer.valueOf(_nm[0]);
		int m = Integer.valueOf(_nm[1]);
		assert 2 <= n && n <= 15 : "out of range, n: " + n;
		assert 2 <= m && m <= 15 : "out of range, m: " + m;
		boolean[][] G = new boolean[n][m];
		for (int y = 0; y < n; y++) {
			char[] C = scanner.nextLine().toCharArray();
			for (int x = 0; x < m; x++) {
				G[y][x] = (C[x] == 'G');
			}
		}
		return new EmasSupercomputer(n, m, G);
	}	

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		EmasSupercomputer o = EmasSupercomputer.load(scanner);
		System.out.println(o.solve());
	}

}
