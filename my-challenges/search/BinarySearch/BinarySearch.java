import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class BinarySearch {

	// TODO: make test resistant to BS on Dutch flag array.
	// TODO: make tests that fail on unsafe middle calculation.

	static int binarySearch(int[] A, int x) {
		int l = 0;
		int r = A.length - 1;
		while (l <= r) {
			int m = (l + r) >>> 1;
			if (A[m] == x) {
				return m;
			} else if (A[m] < x) {
				l = m + 1;
			} else {
				r = m - 1;
			}
		}
		return -(l + 1);
	}

	static void solve(int[] K, int[] N) {
		StringBuilder sb = new StringBuilder();
		for (int k : K) {
			int a = binarySearch(N, k);
			int b = Arrays.binarySearch(N, k);
			assert a == b;
			sb.append(a);
			sb.append('\n');
		}
		System.out.print(sb);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int k = scanner.nextInt();
		int n = scanner.nextInt();
		int[] K = scanArray(scanner, k);
		int[] N = scanArray(scanner, n);
		solve(K, N);
	}

	static int[] scanArray(Scanner scanner, int n) {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = scanner.nextInt();
		}
		return A;
	}

}

