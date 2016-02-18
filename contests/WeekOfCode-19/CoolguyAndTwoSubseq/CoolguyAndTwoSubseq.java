import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class CoolguyAndTwoSubseq {

	static final int PRIME = (int)1e9 + 7;

	public static int f(int[] A, int l, int r) {
		int min = A[l];
		for (int i = l; i <= r; i++) {
			min = Math.min(min, A[i]);
		}
		return min;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		assert 1 <= n && n <= 2e5 : "out of range, n: " + n;
		int[] A = scanArray(scanner, n);
		
		int ans = 0;
		for (int a = 0; a < n; a++) {
			for (int b = a; b < n; b++) {
				for (int c = b+1; c < n; c++) {
					for (int d = c; d < n; d++) {
						ans += Math.min(f(A, a, b), f(A, c, d));
						ans %= PRIME;
					}
				}
			}
		}

		System.out.println(ans);
	}

	static int[] scanArray(Scanner scanner, int n) {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = scanner.nextInt();
			assert 1 <= A[i] && A[i] <= 1e9 : "out of range, A[i]: " + A[i];
		}
		return A;
	}

}

