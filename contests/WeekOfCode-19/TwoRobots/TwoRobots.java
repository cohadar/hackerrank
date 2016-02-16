import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class TwoRobots {

	static final int INF = Integer.MAX_VALUE / 2;

	final int m;
	final int n;
	final int[] A;
	final int[] B;
	final int[][][] D;
	
	public TwoRobots(int m, int n, int[] A, int[] B) {
		this.m = m;
		this.n = n;
		this.A = A;
		this.B = B;
		this.D = new int[1 + n][m][m];
	}

	public void initD() {
		for (int k = 0; k <= n; k++) {
			for (int l = 0; l < m; l++) {
				Arrays.fill(D[k][l], INF);
			}
		}
		int k = 0;
		int dist_ab = Math.abs(A[k] - B[k]);
		for (int l = 0; l < m; l++) {
			for (int r = 0; r < m; r++) {
				D[1 + k][B[k]][r] = Math.min(D[1 + k][B[k]][r], Math.abs(l - A[k]) + dist_ab);
				D[1 + k][l][B[k]] = Math.min(D[1 + k][l][B[k]], Math.abs(r - A[k]) + dist_ab);
			}
		}
	}

	public int min() {
		int min = INF;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < m; j++) {
				min = Math.min(min, D[n][i][j]);
			}
		}
		return min;
	}

	public int solve() {
		initD();
		for (int k = 0; k < n; k++) {
			int dist_ab = Math.abs(A[k] - B[k]);
			for (int l = 0; l < m; l++) {
				for (int r = 0; r < m; r++) {
					D[1 + k][B[k]][r] = Math.min(D[1 + k][B[k]][r], D[1+k-1][l][r] + Math.abs(l - A[k]) + dist_ab);
					D[1 + k][l][B[k]] = Math.min(D[1 + k][l][B[k]], D[1+k-1][l][r] + Math.abs(r - A[k]) + dist_ab);
				}
			}
		}
		return min();
	}

	public static TwoRobots load(Scanner scanner) {
		int m = scanner.nextInt();
		int n = scanner.nextInt();
		assert 1 <= m && m <= 1000 : "out of range, m: " + m;
		assert 1 <= n && n <= 1000 : "out of range, n: " + n;
		int[] A = new int[n];
		int[] B = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = scanner.nextInt() - 1;
			B[i] = scanner.nextInt() - 1;
		}
		return new TwoRobots(m, n, A, B);
	}	

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		assert 1 <= t && t <= 50 : "out of range, t: " + t;
		while (t-->0) {
			TwoRobots o = TwoRobots.load(scanner);
			System.out.println(o.solve());
		}
	}

}
