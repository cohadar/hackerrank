import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class AngryProfessor {

	static boolean cancelled(int[] A, int k) {
		int count = 0;
		for (int a : A) {
		 	if (a <= 0) {
		 		count++;
		 	}
		 } 
		return count < k;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		while (t-->0) {
			int n = scanner.nextInt();
			int k = scanner.nextInt();
			int[] A = scanArray(scanner, n);
			System.out.println((cancelled(A, k)) ? "YES" : "NO");
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

