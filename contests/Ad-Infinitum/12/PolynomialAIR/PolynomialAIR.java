import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class PolynomialAIR {

	static int sumRoots(int[] A) {
		return -A[A.length - 2];
	}

	static int sign(int n) {
		return (n % 2 == 0) ? 1 : -1;
	}

	static int prodRoots(int[] A) {
		return A[0] * sign(A.length - 1);
	}

	static void normalize(int[] A) {
		for (int i = 0; i < A.length; i++) {
			A[i] /= A[A.length - 1];	
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int[] A = scanArray(scanner, n + 1);
		normalize(A);
		System.out.printf("%d %d\n", sumRoots(A), prodRoots(A));		
	}

	static int[] scanArray(Scanner scanner, int n) {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = scanner.nextInt();
		}
		return A;
	}

}

