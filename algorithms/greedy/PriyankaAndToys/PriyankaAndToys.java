import java.util.*;
import java.io.*;

public class PriyankaAndToys {

	static int minUnits(int[] A) {
		Arrays.sort(A);
		int k = A.length - 1;
		int units = 0;
		while (k >= 0) {
			int cuwe = A[k];
			units++;
			while (k >= 0 && A[k] >= cuwe - 4) {
				k--;
			}
		}
		return units;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int[] A = scanArray(scanner, n);
		System.out.println(minUnits(A));
	}

	static int[] scanArray(Scanner scanner, int n) {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = scanner.nextInt();
		}
		return A;
	}

}

