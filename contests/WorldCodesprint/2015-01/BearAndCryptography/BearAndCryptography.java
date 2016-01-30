import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class BearAndCryptography {

	final boolean[] B;
	final int[] P;
	
	BearAndCryptography(boolean[] B, int[] P) {
		this.B = B;
		this.P = P;
	}

	static boolean isPrime(int[] P, long x) {
		for (int p : P) {
			if (x % p == 0) {
				return false;
			}
		}
		return true;
	}

	int numDivisors(long x) {
		int count = 2;
		for (int p : P) {
			if (p > x) {
				break;
			}
			if (x % p == 0) {
				count++;
				while (x % p == 0) {
					x /= p;
				}
			}
		}
		return count;
	}

	long solve(long n, int k) {
		if (k == 1) { return 1; };
		if (k == 2) {
			for (long i = n; i >= 2; i--) {
				if (i < B.length) {
					if (B[(int)i]) { return i; };
				} else if (i % 2 != 0) {
					if (isPrime(P, i)) {
						return i;
					}
				}
			}
		}
		if (B[k]) {
			int root = (int)Math.pow(n, 1.0 / (k - 1));
			return (B[root]) ? (long)Math.pow(root, k - 1) : -1;
		}
		for (long i = n; i >= 2; i--) {
			debug(i, numDivisors(i), k);
			if (numDivisors(i) == k) {
				return i;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		boolean[] B = isPrime((int)1e6);
		int np = 0;
		for (int i = 2; i < B.length; i++) {
			if (B[i]) { np++; };
		}
		int[] P = new int[np];
		for (int i = 2, j = 0; i < B.length; i++) {
			if (B[i]) { P[j++] = i; };
		}		
		Scanner scanner = new Scanner(System.in);
		BearAndCryptography o = new BearAndCryptography(B, P);
		int t = scanner.nextInt();
		assert 1 <= t && t <= 50 : "out of range, t: " + t;
		while (t-->0) {
			long n = scanner.nextLong();		
			assert 1 <= n && n <= 1e12 : "out of range, n: " + n;
			int k = scanner.nextInt();
			assert 1 <= k && k <= 40 : "out of range, k: " + k;
			System.out.println(o.solve(n, k));
		}
	}

	static boolean[] isPrime(int N) {
		// initially assume all integers are prime
		boolean[] isPrime = new boolean[N + 1];
		for (int i = 2; i <= N; i++) {
			isPrime[i] = true;
		}

		// mark non-primes <= N using Sieve of Eratosthenes
		for (int i = 2; i*i <= N; i++) {
			// if i is prime, then mark multiples of i as nonprime
			// suffices to consider mutiples i, i+1, ..., N/i
			if (isPrime[i]) {
				for (int j = i; i*j <= N; j++) {
					isPrime[i*j] = false;
				}
			}
		}
		return isPrime;
	}

	static boolean DEBUG = true;
	
	static void debug(Object...os) {
		if (!DEBUG) { return; }
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}
