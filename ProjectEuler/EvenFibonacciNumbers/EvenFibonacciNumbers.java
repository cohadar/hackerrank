import java.util.*;
import java.io.*;
import java.math.*;

public class EvenFibonacciNumbers {

	static final int FIBS_IN_LONG = 91;
	static long[] L = new long[FIBS_IN_LONG];
	static BigInteger[] B = new BigInteger[FIBS_IN_LONG];

	static void precompute() {
		L[0] = 1;
		L[1] = 2;
		B[0] = BigInteger.ONE;
		B[1] = BigInteger.ONE.add(BigInteger.ONE);
		long a = L[0];
		long b = L[1]; 
		for (int i = 2; i < L.length; i++) {
			long tmp = b;
			b += a;
			a = tmp;
			L[i] = b;
			B[i] = new BigInteger(String.valueOf(L[i]));
		}
	}

	static BigInteger solve(long n) {
		BigInteger sum = BigInteger.ZERO;
		for (int i = 0; i < L.length; i++) {
			if (L[i] <= n) {
				if (L[i] % 2 == 0) {
					sum = sum.add(B[i]);
				}
			} else {
				break;
			}
		}
		return sum;
	}

	public static void main(String[] args) {
		precompute();
		Scanner scanner = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int t = scanner.nextInt();
		while (t-->0) {
			long n = scanner.nextLong();
			sb.append(solve(n));
			sb.append('\n');
		}
		System.out.println(sb);
	}

	static boolean DEBUG = true;

	static void debug(Object...os) {
		if (!DEBUG) { return; }
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}

