import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class MaximizeSum {

	static long solve(long m, long[] A) {
		long max = A[0];
		long cumul = 0;
		TreeSet<Long> S = new TreeSet<>();
		for (int i = 0; i < A.length; i++) {
			cumul = (cumul + A[i]) % m;
			if (cumul > max) {
				max = cumul;
			}
			S.add(cumul);
			Long h = S.higher(cumul);
			if (h != null) {
				long d = (m - h) + cumul;
				if (d > max) {
					max = d;
				}
			}
		}
		return max;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		while (t-->0) {
			int n = scanner.nextInt();
			assert 2 <= n && n <= 1e5 : "out of range, n: " + n;			
			long m = scanner.nextLong();
			assert 1 <= m && m <= 1e14 : "out of range, m: " + m;
			long[] A = scanArray(scanner, n, m);
			System.out.println(solve(m, A));
		}
	}

	static long[] scanArray(Scanner scanner, int n, long m) {
		long[] A = new long[n];
		for (int i = 0; i < n; i++) {
			A[i] = scanner.nextLong() % m;
		}
		return A;
	}

}
