import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class CoolguyAndTwoSubseq {

	static final int PRIME = (int)1e9 + 7;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		assert 1 <= n && n <= 2e5 : "out of range, n: " + n;
		int[] A = scanArray(scanner, n);
		
		int ans = 0;
		int count = 0;
		for (int a = 0; a < n; a++) {
			int l = A[a];
			for (int b = a; b < n; b++) {
				l = Math.min(l, A[b]);
				for (int c = b+1; c < n; c++) {
					int r = A[c];
					for (int d = c; d < n; d++) {
						r = Math.min(r, A[d]);
						ans += Math.min(l, r);
						ans %= PRIME;
						debug(a, b, c, d);
						count++;
					}
				}
			}
		}

		System.out.println(ans);
		debug(n, count);
	}

	static int[] scanArray(Scanner scanner, int n) {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = scanner.nextInt();
			assert 1 <= A[i] && A[i] <= 1e9 : "out of range, A[i]: " + A[i];
		}
		return A;
	}

	static boolean DEBUG = true;
	
	static void debug(Object...os) {
		if (!DEBUG) { return; }
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}

