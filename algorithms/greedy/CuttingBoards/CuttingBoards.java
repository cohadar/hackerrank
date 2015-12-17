import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class CuttingBoards {

	static final int PRIME = (int)1e9 + 7;

	static long sum(long[] A) {
		long s = 0;
		for (int i = 0; i < A.length; i++) {
			s += A[i];
		}
		return s;
	}

	static long solve(long[] Y, long[] X) {
		Arrays.sort(Y);
		Arrays.sort(X);
		debug(Y);
		debug(X);
		long sumY = 0;		
		long sumX = 0;
		long price = 0;
		long segmentsY = Y.length + 1;
		long segmentsX = X.length + 1;
		int iy = 0;
		int ix = 0;
		// instead of cutting, we will "glue" (in reverse)
		while (iy < Y.length && ix < X.length) {
			if (sumY - X[ix] * segmentsY > sumX - Y[iy] * segmentsX) {
				price += X[ix] * segmentsY;
				price %= PRIME;
				segmentsX--;
				sumX += X[ix];
				ix++;
			} else {
				price += Y[iy] * segmentsX;
				price %= PRIME;
				segmentsY--;
				sumY += Y[iy];
				iy++;
			}
		}
		while (iy < Y.length) {
			price += Y[iy] * segmentsX;
			price %= PRIME;
			segmentsY--;
			sumY += Y[iy];
			iy++;
		}
		while (ix < X.length) {
			price += X[ix] * segmentsY;
			price %= PRIME;
			segmentsX--;
			sumX += X[ix];
			ix++;
		}				
		return price;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		assert 1 <= t && t <= 20 : "out of range, t: " + t;
		while (t-->0) {
			int m = scanner.nextInt();
			int n = scanner.nextInt();
			assert 2 <= m && m <= 1e6 : "out of range, m: " + m;
			assert 2 <= n && n <= 1e6 : "out of range, n: " + n;
			long[] Y = scanArray(scanner, m - 1);
			long[] X = scanArray(scanner, n - 1);
			System.out.println(solve(Y, X));
		}
	}

	static long[] scanArray(Scanner scanner, int n) {
		long[] A = new long[n];
		for (int i = 0; i < n; i++) {
			A[i] = scanner.nextInt();
			assert 0 <= A[i] && A[i] <= 1e9 : "out of range, A[i]: " + A[i];			
		}
		return A;
	}

	static boolean DEBUG = false;
	
	static void debug(Object...os) {
		if (!DEBUG) { return; }
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}
}