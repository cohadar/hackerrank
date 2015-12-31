import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class SansaAndXOR {

	static int solve(int[] A) {
		if (A.length % 2 == 0) {
			return 0;
		}
		int cumul = 0;
		for (int i = 0; i < A.length; i++) {
			if (i % 2 == 0) {
				cumul ^= A[i];
			}
		}
		return cumul;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		while (t-->0) {
			int n = scanner.nextInt();
			int[] A = scanArray(scanner, n);
			System.out.println(solve(A));
		}
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
