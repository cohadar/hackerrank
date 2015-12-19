import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class SherlockAndArray {

	static int sum(int[] A) {
		int s = 0;
		for (int i = 0; i < A.length; i++) {
			s += A[i];
		}
		return s;
	}

	static boolean has(int[] A) {
		int s = sum(A);
		int l = 0;
		int r = s - A[0];
		if (l == r) {
			return true;
		}
		for (int i = 1; i < A.length; i++) {
			l += A[i - 1];
			r -= A[i];
			if (l == r) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		while (t-->0) {
			int n = scanner.nextInt();
			int[] A = scanArray(scanner, n);
			System.out.println((has(A)) ? "YES" : "NO");
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

