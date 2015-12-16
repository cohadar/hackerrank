import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class Flowers {

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

	static int solve(int k, int[] C) {
		Arrays.sort(C);
		reverse(C);
		int price = 0;
		for (int i = 0; i < C.length; i++) {
			int x = i / k;
			price += (x + 1) * C[i];
		}
		return price;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int k = scanner.nextInt();
		assert 1 <= n && n <= 100 : "out of range, n: " + n;
		assert 1 <= k && k <= 100 : "out of range, k: " + k;
		assert k <= n;
		int[] C = scanArray(scanner, n);
		System.out.println(solve(k, C));
	}

	static int[] scanArray(Scanner scanner, int n) {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = scanner.nextInt();
			assert 1 <= A[i] && A[i] <= 1e6 : "out of range, A[i]: " + A[i];
		}
		return A;
	}

}

