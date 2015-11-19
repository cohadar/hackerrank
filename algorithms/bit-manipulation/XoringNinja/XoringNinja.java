import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class XoringNinja {

	static final int PRIME = 1000000007;

	static int solve(int[] A) {
		return 3;
	}

	static void test() {
		for (int i = 0; i < 8; i++) {
			System.out.printf("%d %d %d\n", i, (3 ^ i) + (2 ^ i), (3 + 2) ^ i);
		}
	}

	public static void main(String[] args) {
		test();
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		while (t-->0) {
			int n = scanner.nextInt();
			int[] A = scanArray(scanner, n);
			//System.out.println(solve(A));
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

