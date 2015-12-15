import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class XoringNinja {

	static final int PRIME = (int)1e9 + 7;

	// my solution, correct but a bit complicated
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

	// solution from editorial
	static long solve2(int[] A) {
		int orr = 0;
		for (int ia = 0; ia < A.length; ia++) {
			orr |= A[ia];
		}		
		return (orr * powmod(A.length - 1)) % PRIME;
	}	

	static long njak(int zeros, int ones) {
		long zsum = powmod(zeros);
		long osum = powmod(ones - 1);
		return (osum * zsum) % PRIME;
	}

	static long powmod(int n) {
		if (n < 0) {
			return 0;
		}
		long sum = 1;
		for (int i = 0; i < n; i++) {
			sum *= 2;
			sum %= PRIME;
		}
		return sum;
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

}

