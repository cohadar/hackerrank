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
				if (i >= A[k]) {
					if (D[1 - k2][i - A[k]] != -1) {
						D[k2][i] = Math.max(D[k2][i], D[1 - k2][i - A[k]] + V[k]);
					}
				}	
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
		for (int i = 0; i < n; i++) {
			V[i] = scanner.nextInt();
			A[i] = scanner.nextInt();
		}
		return new DorseyThief(A, V, x);
	}	

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		DorseyThief o = DorseyThief.load(scanner);
		long temp = o.solve();
		System.out.println((temp >= 0) ? temp : "Got caught!");
	}

}
