import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class TheLongestIncSubseq {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		assert 1 <= n && n <= 1e6 : "out of range, n: " + n;
		int[] A = scanArray(scanner, n);
		int[] L = new int[1 + n];
		Arrays.fill(L, 1);
		for (int i = 0; i < n; i++) {
			for (int j = i - 1; j >= 0; j--) {
				if (A[i] >= A[j]) {
					if (L[i] < L[j] + 1) {
						L[i] = L[j] + 1;
					}
				}
			}
		}
		System.out.println(max(L));
	}

	static int max(int[] A) {
		int m = A[0];
		for (int i = 1; i < A.length; i++) {
			if (m < A[i]) {
				m = A[i];
			}
		}
		return m;
	}

	static int[] scanArray(Scanner scanner, int n) {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = scanner.nextInt();
		}
		return A;
	}

}

