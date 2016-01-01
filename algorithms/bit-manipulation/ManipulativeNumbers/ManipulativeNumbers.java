import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class ManipulativeNumbers {

	static int countOnes(int[] A, int mask) {
		int count = 0;
		for (int i = 0; i < A.length; i++) {
			if ((A[i] & mask) != 0) {
				count++;
			}
		}
		return count;
	}

	static int[] extractZeros(int[] A, int mask, int zeros) {
		int[] B = new int[zeros];
		for (int i = 0, j = 0; i < A.length; i++) {
			if ((A[i] & mask) == 0) {
				B[j++] = A[i];
			}
		}
		return B;
	}

	static int[] extractOnes(int[] A, int mask, int ones) {
		int[] B = new int[ones];
		for (int i = 0, j = 0; i < A.length; i++) {
			if ((A[i] & mask) != 0) {
				B[j++] = A[i];
			}
		}
		return B;
	}	

	static int numberOfPairs(int[] A, int k) {
		if (k > 31) {
			return 0;
		}
		int ones = countOnes(A, 1 << k);
		int zeros = A.length - ones;
		if (ones == zeros) {
			return ones * 2;
		} else if (ones < zeros) {
			int[] B = extractZeros(A, 1 << k, zeros);
			int max = A.length - ones * 2;
			return ones * 2 + Math.min(numberOfPairs(B, k + 1), max);
		} else {
			int[] B = extractOnes(A, 1 << k, ones);
			int max = A.length - zeros * 2;
			return zeros * 2 + Math.min(numberOfPairs(B, k + 1), max);
		}
	}

	static int solve(int[] A) {
		for (int k = 31; k >= 0; k--) {
			int np = numberOfPairs(A, k);
			if (np == A.length) {
				return k;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int[] A = scanArray(scanner, n);
		System.out.println(solve(A));
	}

	static int[] scanArray(Scanner scanner, int n) {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = scanner.nextInt();
		}
		return A;
	}

}

