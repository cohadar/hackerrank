import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class DorseyThief {

	final int n;
	final int[] A;
	final int[] V;
	final int x;
	
	DorseyThief(int[] A, int[] V, int x) {
		this.n = A.length;
		this.A = A;
		this.V = V;
		this.x = x;
	}
	
	long solve() {
		long[][] D = new long[2][1 + x];
		Arrays.fill(D[0], -1);
		Arrays.fill(D[1], -1);
		D[0][0] = 0;
		D[1][0] = 0;
		for (int k = 0; k < n; k++) {
			int k2 = k % 2;
			for (int i = 0; i <= x; i++) {
				D[k2][i] = D[1 - k2][i];
			}
			for (int i = A[k]; i <= x; i++) {
				long max = D[1 - k2][i];
				long temp = D[1 - k2][i - A[k]];
				if (temp != -1) {
					max = Math.max(max, temp + V[k]);
				}
				D[k2][i] = max;
			}
		}
		return D[(n - 1) % 2][x];
	}

	static DorseyThief load(Scanner scanner) {
		int n = scanner.nextInt();
		int x = scanner.nextInt();
		assert 1 <= n && n <= 1e6 : "out of range, n: " + n;
		assert 1 <= x && x <= 5000 : "out of range, x: " + x;
		int[] A = new int[n];
		int[] V = new int[n];
		int j = 0;
		for (int i = 0; i < n; i++) {
			V[j] = scanner.nextInt();
			A[j] = scanner.nextInt();
			if (A[j] <= x) {
				j++;
			}
		}
		return new DorseyThief(Arrays.copyOf(A, j), Arrays.copyOf(V, j), x);
	}	

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		DorseyThief o = DorseyThief.load(scanner);
		long temp = o.solve();
		System.out.println((temp >= 0) ? temp : "Got caught!");
	}

}
