import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class BikeRacers {

	static final long INF = Long.MAX_VALUE / 2;

	static class Point {
		final int x;
		final int y;
		Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public long squareDistance(Point that) {
			long dy = (long)this.y - (long)that.y;
			long dx = (long)this.x - (long)that.x;
			return dy * dy + dx * dx;
		}
		public String toString() {
			return String.format("(x=%d, y=%d)", x, y);
		}	
	}

	static long solve(int k, Point[] N, Point[] M) {
		long[][] D = new long[M.length][N.length];
		debug(k, N, M);
		for (int m = 0; m < M.length; m++) {
			for (int n = 0; n < N.length; n++) {
				D[m][n] = N[n].squareDistance(M[m]);
			}
		}
		long minD = INF;
		for (int i = 0; i < k; i++) {
			minD = INF;
			int minN = -1;
			int minM = -1;
			for (int m = 0; m < M.length; m++) {
				for (int n = 0; n < N.length; n++) {
					if (minD > D[m][n]) {
						minD = D[m][n];
						minN = n;
						minM = m;
					}
				}
			}
			for (int m = 0; m < M.length; m++) {
				D[m][minN] = INF;
			}
			for (int n = 0; n < N.length; n++) {
				D[minM][n] = INF;
			}
		}
		return minD;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		int k = scanner.nextInt();
		assert 1 <= n && n <= 250 : "out of range, n: " + n;
		assert 1 <= m && m <= 250 : "out of range, m: " + m;
		assert 1 <= k && k <= Math.min(n, m) : "out of range, k: " + k;
		Point[] N = scanPointArray(scanner, n);
		Point[] M = scanPointArray(scanner, m);
		System.out.println(solve(k, N, M));
	}

	static Point[] scanPointArray(Scanner scanner, int n) {
		Point[] A = new Point[n];
		for (int i = 0; i < n; i++) {
			int x = scanner.nextInt();
			int y = scanner.nextInt();
			A[i] = new Point(x, y);
		}
		return A;
	}

	static boolean DEBUG = true;
	
	static void debug(Object...os) {
		if (!DEBUG) { return; }
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}
}

