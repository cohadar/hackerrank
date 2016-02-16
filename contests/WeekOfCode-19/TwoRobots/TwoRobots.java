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
	final int[][] D;
	
	public TwoRobots(int m, int n, int[] A, int[] B) {
		this.m = m;
		this.n = n;
		this.A = A;
		this.B = B;
		this.D = new int[n][m];
	}
	
	static int min(int[] A) {
		int m = INF;
		for (int i = 0; i < A.length; i++) {
			m = Math.min(m, A[i]);
		}
		return m;
	}

	public void initD() {
		for (int k = 0; k < n; k++) {
			Arrays.fill(D[k], INF);
		}
		int k = 0;
		for (int r = 0; r < m; r++) {
			D[k][r] = Math.abs(A[0] - B[0]);
		}
	}

	public int solve() {
		assert n > 0;
		initD();
		for (int k = 1; k < n; k++) {
			for (int r = 0; r < m; r++) {
				int val = D[k - 1][r] + Math.abs(B[k - 1] - A[k]) + Math.abs(A[k] - B[k]);
				D[k][r] = Math.min(D[k][r], val);
			}
			for (int l = 0; l < m; l++) {
				int val = D[k - 1][l] + Math.abs(l - A[k]) + Math.abs(A[k] - B[k]);
				D[k][B[k - 1]] = Math.min(D[k][B[k - 1]], val);
			}
		}
		return min(D[n - 1]);
	}

	public static TwoRobots load(Scanner scanner) {
		int m = scanner.nextInt();
		int n = scanner.nextInt();
		assert 1 <= m && m <= 1000 : "out of range, m: " + m;
		assert 1 <= n && n <= 1000 : "out of range, n: " + n;
		int[] A = new int[m];
		int[] B = new int[m];
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
