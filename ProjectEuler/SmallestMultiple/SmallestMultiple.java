import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class SmallestMultiple {

	static int[] precompute() {
		int[] A = new int[41];
		for (int i = 0; i < A.length; i++) {
			A[i] = i;
			for (int j = i - 1; j >= 2; j--) {
				if (A[i] % A[j] == 0) {
					A[i] /= A[j];
				}
			}
		}
		int p = 1;
		for (int i = 1; i < A.length; i++) {
			p *= A[i];
			A[i] = p;
		}
		return A;
	}

	public static void main(String[] args) {
		int[] A = precompute();
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		while (t-->0) {
			int n = scanner.nextInt();
			System.out.println(A[n]);
		}
	}

}

