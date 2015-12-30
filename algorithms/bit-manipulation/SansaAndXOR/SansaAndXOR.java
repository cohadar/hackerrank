import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class SansaAndXOR {

	static boolean isUsed(int n, int k) {
		boolean oddBinomial = (k & (n - k)) == 0;
		return (n % 2 == 0) ? oddBinomial : !oddBinomial;
	}

	static int solve(int[] A) {
		int cumul = 0;
		System.out.println("==");
		for (int i = 0; i < A.length; i++) {
			if (isUsed(A.length - 1, i)) {
				//System.out.println("i = " + i);
				cumul ^= A[i];
			}
		}
		return cumul;
	}

	static int brute(int[] A, int len, int start) {
		int cumul = 0;
		for (int i = 0; i < len; i++) {
			cumul ^= A[start + i];
		}
		return cumul;
	}

	static int brute(int[] A, int len) {
		int current = 0;
		for (int i = 0; i < len; i++) {
			current ^= A[i];
		}
		int cumul = current;
		for (int i = 0; i < A.length - len; i++) {
			current = current ^ A[i] ^ A[i + len];
			cumul ^= current;
		}
		return cumul;
	}

	static int brute(int[] A) {
		int cumul = 0;
		for (int i = 0; i < A.length; i++) {
			cumul ^= brute(A, i + 1);
		}
		return cumul;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		while (t-->0) {
			int n = scanner.nextInt();
			int[] A = scanArray(scanner, n);
			System.out.println(brute(A));
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

