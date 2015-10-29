import java.util.*;
import java.io.*;

public class MaximizeSum {

	static long msum(long[] A, int l, int r, long m) {
		long max = 0;
		long sum = 0;
		for (int k = l; k <= r; k++) {
			sum = (sum + A[k]) % m;
			assert sum > 0;
			if (sum > max) {
				max = sum;
			}
		}		
		return max;
	}

	static long brute(long[] A, long m) {
		int n = A.length;
		long max = 0;
		for (int l = 0; l < n; l++) {
			for (int r = l; r < n; r++) {
				long msum = msum(A, l, r, m);
				if (max < msum) max = msum;
			}
		}
		return max;
	}

	static long solve(long[] A, long m) {
		int n = A.length;
		long max = 0;
		for (int l = 0; l < n; l++) {
			long sum = A[l] % m;
			if (max < sum) max = sum;
			for (int r = l + 1; r < n; r++) {
				sum = (sum + A[r]) % m;
				if (max < sum) max = sum;
			}
		}
		return max;
	}

	static void scan(Scanner scanner) {
		int t = scanner.nextInt();
		for (int _t = 0; _t < t; _t++) {
			int n = scanner.nextInt();
			long m = scanner.nextLong();			
			long[] a = new long[n];
			for (int i = 0; i < n; i++) {
				a[i] = scanner.nextLong();
			}
			System.out.println(solve(a, m));
		}
	}

	static void test() {
		Random random = new Random();
		int n = 20 + random.nextInt(20);
		long[] a = new long[n];
		for (int i = 0; i < n; i++) {
			a[i] = 1 + random.nextLong() % 1000000000000000000L; 
			a[i] = Math.abs(a[i]);
		}
		long m = (1 + random.nextLong()) % 100000000000000L; 
		m = Math.abs(m);		
		System.out.println(m);
		System.out.println(brute(a, m));
		System.out.println(solve(a, m));
		assert brute(a, m) == solve(a, m);
	}

	public static void main(String[] args) throws Exception {
		test();
		// Scanner scanner = new Scanner(System.in);
		// if (args.length == 1 && "COHADAR".equals(args[0])) {
		// 	scanner = new Scanner(new File("MaximizeSum.in"));
		// }
		// scan(scanner);
	}

	static long[] scanArray(Scanner scanner, int n) {
		String line = scanner.nextLine();
		Scanner arrayScanner = new Scanner(line);
		long[] a = new long[n];
		for (int i = 0; i < n; i++) {
			a[i] = arrayScanner.nextLong();
		}
		return a;
	}

	static void debug(Object...os) {
		System.err.println(Arrays.deepToString(os));
	}

}
