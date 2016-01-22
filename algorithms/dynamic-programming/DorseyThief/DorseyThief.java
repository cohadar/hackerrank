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
		long[] D = new long[1 + x];
		long[] B = new long[1 + x];
		Arrays.fill(D, -1);
		D[0] = 0;
		for (int k = 0; k < n; k++) {
			B = Arrays.copyOf(D, x - A[k] + 1);
			for (int i = A[k]; i <= x; i++) {
				long temp = B[i - A[k]];
				if (temp != -1) {
					D[i] = Math.max(D[i], temp + V[k]);
				}
			}
		}
		return D[x];
	}

	static DorseyThief load(Scanner scanner) {
		String[] _nx = scanner.nextLine().split(" ");
		int n = Integer.valueOf(_nx[0]);
		int x = Integer.valueOf(_nx[1]);
		assert 1 <= n && n <= 1e6 : "out of range, n: " + n;
		assert 1 <= x && x <= 5000 : "out of range, x: " + x;
		int[] A = new int[n];
		int[] V = new int[n];
		int j = 0;
		for (int i = 0; i < n; i++) {
			String[] _va = scanner.nextLine().split(" ");
			V[j] = Integer.valueOf(_va[0]);
			A[j] = Integer.valueOf(_va[1]);
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
