import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class StoneGame {

	static final int PRIME = (int)1e9 + 7;

	static long countEm(int[] A, int mask) {
		long n1 = 0;
		for (int i = 0; i < A.length; i++) {
			if ((A[i] & mask) != 0) {
				n1 = (n1 + 1) % PRIME;
			}
		}
		return n1 / 2 + 1;
	}

	static long countEm(int[] A) {
		long count = 1;
		for (int k = 0; k < 32; k++) {
			count = (count * countEm(A, 1 << k)) % PRIME;
		}
		return count;
	}

	static long countEmBrute(int[] A) {
		long count = 1;
		for (int k = 0; k < 32; k++) {
			count = (count * countEm(A, 1 << k)) % PRIME;
		}
		return count;
	}	

	static int[] dec(int[] A) {
		int[] B = new int[A.length];
		for (int i = 0; i < A.length; i++) {
			B[i] = A[i] - 1;
		}
		return B;
	}

	static long solve(int[] P) {
		long all = countEm(P);
		long allChanged = countEmBrute(dec(P));
		debug(all, allChanged);
		return (all - allChanged + PRIME) % PRIME;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		assert 3 <= n && n <= 100 : "out of range, n: " + n;
		int[] P = scanArray(scanner, n);
		System.out.println(solve(P));
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

