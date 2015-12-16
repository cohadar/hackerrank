import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class Pairs {

	static int solve(int k, int[] A) {
		Arrays.sort(A);
		int count = 0;
		int n = A.length;
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				int d = Math.abs(A[i] - A[j]);
				if (d > k) {
					break;
				}
				if (d == k) {
					count++;
				}
			}
		}
		return count;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int k = scanner.nextInt();
		int[] A = scanArray(scanner, n);
		System.out.println(solve(k, A));
	}

	static int[] scanArray(Scanner scanner, int n) {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = scanner.nextInt();
		}
		return A;
	}

}

