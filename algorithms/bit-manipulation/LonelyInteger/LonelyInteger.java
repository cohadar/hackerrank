import java.util.*;
import java.io.*;

public class LonelyInteger {

	static int once(int[] A) {
		int x = 0;
		for (int i = 0; i < A.length; i++) {
			x ^= A[i];
		}
		return x;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int[] A = scanArray(scanner, n);
		System.out.println(once(A));
	}

	static int[] scanArray(Scanner scanner, int n) {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = scanner.nextInt();
		}
		return A;
	}

}

