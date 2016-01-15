import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class Billboards {

	static long solve(int[] A, int k) {
		int n = A.length;
		long[][] P = new long [2][1 + n];
		for (int y = 0; y < n; y++) {
			int iy = y % 2;
			P[iy][0] = P[1 - iy][k];
			for (int x = 1; x <= k; x++) {
				P[iy][x] = P[1 - iy][k];
				if (P[iy][x] < A[y] + P[1 - iy][x - 1]) {
					P[iy][x] = A[y] + P[1 - iy][x - 1];
				}
			}
			debug(P[iy]);
		}
		return P[(n - 1) % 2][k];
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int k = scanner.nextInt();
		assert 1 <= n && n <= 1e5 : "out of range, n: " + n;
		assert 1 <= k && k <= n : "out of range, k: " + k;
		int[] A = scanArray(scanner, n);
		System.out.println(solve(A, k));
	}

	static int[] scanArray(Scanner scanner, int n) {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = scanner.nextInt();
		}
		return A;
	}	

	static boolean DEBUG = true;
	
	static void debug(Object...os) {
		if (!DEBUG) { return; }
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}
}

