import java.util.*;
import java.io.*;

public class MaximizeSum {

	static long brute(long[] A, long m) {
		int n = A.length;
		long sum = 0;
		long max = 0;
		for (int l = 0; l < n; l++) {
			for (int r = l; r < n; r++) {
				for (int k = l; k <= r; k++) {
					sum += A[k];
					sum %= m;
					if (sum > max) {
						max = sum;
					}
				}
			}
		}
		return max;
	}

	static long solve(long[] A, long m) {
		int n = A.length;
		long sum = 0;
		long max[][] = new long[2][n + 1];
		for (int b = 0; b < n; b++) {
			int ib = b % 2;
			sum += A[b];
			sum %= m;
			max[ib][b] = sum;
			for (int a = b - 1; a >= 0; a--) {	
				long prev = max[1 - ib][a];
				max[ib][a] = Math.max(prev, (prev + A[b]) % m);
			}
		}
		return max[(n - 1) % 2][0];
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
		}
		long m = 1 + random.nextLong() % 100000000000000L;
		System.out.println(brute(a, m));
		System.out.println(solve(a, m));
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
