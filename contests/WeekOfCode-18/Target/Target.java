import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class Target {

	// W and Z are a really bad names
	static long solve(long[] W, long[] Z) {
		reverse(W);
		Arrays.sort(Z);
		debug('W', W);
		debug('Z', Z);
		int w = 0;
		int z = 0;
		long sum = 0;
		while (z < Z.length && w < W.length) {
			if (Z[z] <= W[w]) {
				sum += (W.length - w);	
				z++;
			} else {
				w++;
			}
		}
		return sum;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int k = scanner.nextInt();
		assert 1 <= k && k <= 1e4 : "out of range, k: " + k;
		int n = scanner.nextInt();
		assert 1 <= n && n <= 5e5 : "out of range, n: " + n;
		long[] W = scanW(scanner, k); // squares of radii
		long[] Z = scanZ(scanner, n); // squares of distances from center
		System.out.println(solve(W, Z));
	}

	static long[] scanW(Scanner scanner, int n) {
		long[] W = new long[n];
		for (int i = 0; i < n; i++) {
			W[i] = scanner.nextLong();
			W[i] *= W[i];
		}
		return W;
	}

	static long[] scanZ(Scanner scanner, int n) {
		long[] Z = new long[n];
		for (int i = 0; i < n; i++) {
			long x = scanner.nextLong();
			long y = scanner.nextLong();
			Z[i] = x * x + y * y;
		}
		return Z;
	}

	static void swap(long[] A, int i, int j) {
		long t = A[i];
		A[i] = A[j];
		A[j] = t;
	}

	static void reverse(long[] A) {
		for (int i = 0; i < A.length / 2; i++) {
			swap(A, i, A.length - i - 1);
		}
	}

	static boolean DEBUG = false;
	
	static void debug(Object...os) {
		if (!DEBUG) { return; }
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}

