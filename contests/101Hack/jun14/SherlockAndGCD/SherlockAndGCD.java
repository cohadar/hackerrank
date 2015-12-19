import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class SherlockAndGCD {

	static int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a % b);
	}

	static boolean solve(int[] A) {
		Set<Integer> S = new HashSet<>();
		for (int i = 0; i < A.length; i++) {
			S.add(A[i]);
		}
		int a = A[0];
		for (int s : S) {
			a = gcd(a, s);
		}
		return a == 1;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		while (t-->0) {
			int n = scanner.nextInt();
			int[] A = scanArray(scanner, n);
			System.out.println((solve(A)) ? "YES" : "NO");
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

