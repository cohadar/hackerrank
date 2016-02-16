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
	final int[][] L;
	final int[][] D;
	
	public TwoRobots(int m, int n, int[] A, int[] B) {
		this.m = m;
		this.n = n;
		this.A = A;
		this.B = B;
		this.L = new int[n][m];
		this.D = new int[n][m];
	}

	public void initD() {
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < m; i++) {
				L[k][i] = INF;
				D[k][i] = INF;
			}
		}
	}

	public int min() {
		int min = INF;
		for (int i = 0; i < m; i++) {
			min = Math.min(min, L[n-1][i]);
			min = Math.min(min, D[n-1][i]);
		}
		return min;
	}

	public void solve0() {
		int k = 0;
		for (int i = 0; i < m; i++) {
			L[k][i] = Math.min(L[k][i], Math.abs(A[k] - B[k]));
			D[k][i] = Math.min(D[k][i], Math.abs(A[k] - B[k]));
		}
	}

	public void solveL(int k, int l) {
		for (int r = 0; r < m; r++) {
			L[k][r] = Math.min(L[k][r], L[k-1][r] + Math.abs(l - A[k]) + Math.abs(A[k] - B[k]));
			D[k][l] = Math.min(D[k][l], L[k-1][r] + Math.abs(r - A[k]) + Math.abs(A[k] - B[k]));
		}
	}

	public void solveR(int k, int r) {
		for (int l = 0; l < m; l++) {
			L[k][r] = Math.min(L[k][r], D[k-1][l] + Math.abs(l - A[k]) + Math.abs(A[k] - B[k]));
			D[k][l] = Math.min(D[k][l], D[k-1][l] + Math.abs(r - A[k]) + Math.abs(A[k] - B[k]));
		}
	}

	public int solve() {
		initD();
		solve0();
		for (int k = 1; k < n; k++) {
			solveL(k, B[k - 1]);
			solveR(k, B[k - 1]);
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
