import java.util.*;
import java.io.*;

public class LargestPermutation {

	static void swap(int[] A, int i, int j) {
		int t = A[i];
		A[i] = A[j];
		A[j] = t;
	}

	static void reverse(int[] A) {
		for (int i = 0; i < A.length / 2; i++) {
			swap(A, i, A.length - i - 1);
		}
	}

	static int linearSearch(int[] A, int v) {
		for (int i = 0; i < A.length; i++) {
			if (A[i] == v) {
				return i;
			}
		}
		return -1;
	}

	static void optimalSwap(int[] A, int i, int v) {
		int j = linearSearch(A, v);
		swap(A, i, j);
	}

	static int[] solve(int[] A, int k) {
		int n = A.length;
		if (k >= A.length - 1) {
			Arrays.sort(A);
			reverse(A);
			return A;
		}
		int[] S = Arrays.copyOf(A, n);
		Arrays.sort(S);
		reverse(S);
		int i = 0;
		while (k-->0) {
			while (i < n && A[i] == S[i]) { i++; }
			if (i >= n) { break; }
			optimalSwap(A, i, S[i]);
		}
		return A;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int k = scanner.nextInt();
		int[] A = scanArray(scanner, n);
		System.out.println(join(solve(A, k), ' '));
	}

	static int[] scanArray(Scanner scanner, int n) {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = scanner.nextInt();
		}
		return A;
	}

	static String join(int[] A, char delimiter) {
		StringBuilder sb = new StringBuilder();
		for (Object o : A) {
			sb.append(o);
			sb.append(delimiter);
		}
		return sb.toString();
	}

}

