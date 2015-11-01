import java.util.*;
import java.io.*;

public class TwoArrays {

	static boolean canDo(int[] A, int[] B, int k) {
		int n = A.length;
		assert n == B.length;
		Arrays.sort(A);
		Arrays.sort(B);
		for (int i = 0; i < n; i++) {
			if (A[i] + B[n - i - 1] < k) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		while (t-->0) {
			int n = scanner.nextInt();
			int k = scanner.nextInt();
			int[] A = new int[n];
			int[] B = new int[n];
			for (int i = 0; i < A.length; i++) {
				A[i] = scanner.nextInt();				
			}
			for (int i = 0; i < B.length; i++) {
				B[i] = scanner.nextInt();				
			}
			System.out.println((canDo(A, B, k)) ? "YES" : "NO");
		}
		
	}

}

