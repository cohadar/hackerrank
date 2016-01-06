import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class ChocolateInBox {

	static int solve(int[] A) {
		int count = 0;
		int xorAll = 0;
		for (int i = 0; i < A.length; i++) {
			xorAll ^= A[i];
		}
		for (int i = 0; i < A.length; i++) {
			if ((xorAll ^ A[i]) < A[i]) {
				count++;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		assert 1 <= n && n <= 1e6 : "out of range, n: " + n;
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

