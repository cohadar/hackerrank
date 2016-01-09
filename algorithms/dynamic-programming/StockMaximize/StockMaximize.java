import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class StockMaximize {

	final int n;
	final int[] P;
	final long[][] V;
	
	StockMaximize(int[] P) {
		this.n = P.length;
		this.P = P;
		this.V = new long[2][n + 2];
	}

	void solve(int t) {
		int it = t % 2;
		for (int s = 0; s <= t + 1; s++) {
			for (int a = -1; a <= s; a++) {
				V[it][s] = Math.max(V[it][s], a * P[t] + V[1 - it][s - a]);
			}
		}
	}

	long solve() {
		for (int t = n - 1; t >= 0; t--) {
			solve(t);
		}
		return V[0][0];
	}
	

	static StockMaximize load(Scanner scanner) {
		int n = scanner.nextInt();
		assert 1 <= n && n <= 5e4 : "out of range, n: " + n;
		int[] P = scanArray(scanner, n);
		return new StockMaximize(P);
	}	

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = Integer.valueOf(scanner.nextLine());
		assert 1 <= t && t <= 10 : "out of range, t: " + t;
		while (t-->0) {
			StockMaximize o = StockMaximize.load(scanner);
			System.out.println(o.solve());
		}
	}

	static int[] scanArray(Scanner scanner, int n) {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = scanner.nextInt();
		}
		return A;
	}
}
