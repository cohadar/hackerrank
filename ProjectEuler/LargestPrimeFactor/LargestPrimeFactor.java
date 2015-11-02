import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class LargestPrimeFactor {

	static int[] sieveOfEratosthenes(int n) {
		int[] P = new int[n];
		for (int i = 0; i < P.length; i++) { P[i] = i; }
		for (int p = 2; p * p < n; p++) {
			while (P[p] == 0) { p++; }
			for (int k = p * p; k < P.length; k += p) { P[k] = 0; }
		}
		return P;
	}

	static long largestPrime(int[] P, long n) {
		long lp = 1;
		for (int i = 2; i < P.length; i++) {
			if (P[i] != 0) {
				while (n % P[i] == 0) {
					lp = P[i];
					n /= lp;
				};
			}	
		}
		return (lp == 1) ? n : Math.max(lp, n); // <---<< this max was hard to find
	}

	public static void main(String[] args) {
		int[] P = sieveOfEratosthenes(1000000);
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		while (t-->0) {
			long n = scanner.nextLong();
			System.out.println(largestPrime(P, n));
		}
	}

}
