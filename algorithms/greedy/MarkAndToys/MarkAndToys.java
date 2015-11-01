import java.util.*;
import java.io.*;

public class MarkAndToys {

	static int maxToys(int[] A, int k) {
		Arrays.sort(A);
		int sum = 0;
		for (int i = 0; i < A.length; i++) {
			if (sum + A[i] > k) {
				return i;
			} 
			sum += A[i];
		}
		return A.length;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int k = scanner.nextInt();
		int[] A = new int[n];
		for (int i = 0; i < A.length; i++) {
			A[i] = scanner.nextInt();
		}
		System.out.println(maxToys(A, k));
	}

}

