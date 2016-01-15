import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class TheLongestIncSubseq {

	static int findHigher(int[] A, int[] H, int l, int r, int v) {
		for (int i = r; i >= l; i--) {
			if (A[H[i]] < v) {
				return i + 1;
			}
		}
		return 1;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		assert 1 <= n && n <= 1e6 : "out of range, n: " + n;
		int[] A = scanArray(scanner, n);
		int[] P = new int[n]; // previous indices
		int[] H = new int[n]; // head indices
		P[0] = -1;
		H[0] = 0;
		int nh = 1; 
		for (int i = 1; i < A.length; i++) {
			if (A[i] > A[H[nh - 1]]) {
				// A[i] is biggest
				P[i] = H[nh - 1];
				H[nh++] = i;
			} else if (A[i] <= A[H[0]]) {
				// A[i] is smallest
				P[i] = -1;
				H[0] = i;
			} else {
				// A[i] is in the mid
				int higher = findHigher(A, H, 0, nh - 1, A[i]);
				P[i] = H[higher - 1];
				H[higher] = i;
			}
		}
		int count = 0;
		for (int i = H[nh - 1]; i >= 0; i = P[i]) {
			count++;
		}
		System.out.println(count);
	}

	static int[] scanArray(Scanner scanner, int n) {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = scanner.nextInt();
		}
		return A;
	}

}

