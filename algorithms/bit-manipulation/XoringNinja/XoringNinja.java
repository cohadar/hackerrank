import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class XoringNinja {

	static final int PRIME = (int)1e9 + 7;

	static long solve(int[] A) {
		long sum = 0;
		for (int i = 0; i < 31; i++) {
			int ones = 0;
			int zeros = 0;
			int MASK = 1 << i;
			for (int ia = 0; ia < A.length; ia++) {
				if ((A[ia] & MASK) == 0) {
					zeros++;
				} else {
					ones++;
				}
			}
			sum += (njak(zeros, ones) << i);
			sum %= PRIME;
		}
		return sum;
	}

	static long njak(int zeros, int ones) {
		// zsum == 2 ** zeros
		long zsum = 0;
		long zcumul = 1;
		for (int i = zeros; i >= 0; i--) {
			zsum += zcumul;
			zcumul *= i;
			zsum %= PRIME;
			zcumul %= PRIME;
		}
		long osum = 0;
		long ocumul = ones;
		int j = ones;
		while (ocumul > 0) {
			osum += ocumul;
			osum %= PRIME;
			ocumul *= (--j);
			ocumul %= PRIME;
			ocumul *= (--j);
			ocumul %= PRIME;
		}
		System.out.printf("osum=%d, zsum=%d\n", osum, zsum);
		return (osum * zsum) % PRIME;
	}

	static void test_njak() {
		for (int z = 0; z < 5; z++) {
			for (int o = 0; o < 5; o++) {
				System.out.printf("njak(%d, %d) == %d\n", z, o, njak(z, o));
			}
		}
	}

	public static void main(String[] args) {
		test_njak();
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

}

