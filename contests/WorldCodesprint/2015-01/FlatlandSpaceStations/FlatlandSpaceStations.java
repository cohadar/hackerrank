import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class FlatlandSpaceStations {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		assert 1 <= n && n <= 1e5 : "out of range, n: " + n;
		assert 1 <= m && m <= n : "out of range, m: " + m;
		int[] A = scanArray(scanner, m);
		Arrays.sort(A);
		debug(A);
		long max = 0;
		max = Math.max(max, A[0] - 0);
		max = Math.max(max, (n - 1) - A[A.length - 1]);
		for (int i = 1; i < A.length; i++) {
			int temp = (A[i] - A[i - 1]) / 2;
			max = Math.max(max, temp);
		}
		System.out.println(max);
	}

	static int[] scanArray(Scanner scanner, int n) {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = scanner.nextInt();
		}
		return A;
	}

	static boolean DEBUG = false;
	
	static void debug(Object...os) {
		if (!DEBUG) { return; }
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}

