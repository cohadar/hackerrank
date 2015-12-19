import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class CutTheSticks {

	static final int INF = Integer.MAX_VALUE / 2;

	static int min(int[] A) {
		int min = INF;
		for (int i = 0; i < A.length; i++) {
			if (A[i] != 0) {
				if (A[i] < min) {
					min = A[i];
				}
			}
		}
		return min;
	}

	static int count(int[] A) {
		int c = 0;
		for (int i = 0; i < A.length; i++) {
			if (A[i] != 0) {
				c++;
			}
		}
		return c;
	}	

	static void cut(int[] A, int min) {
		for (int i = 0; i < A.length; i++) {
			if (A[i] != 0) {
				A[i] -= min;
			}
		}
	}		

	static void solve(int[] A) {
		while (true) {
			int min = min(A);
			if (min == INF) {
				break;
			}
			System.out.println(count(A));
			cut(A, min);
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int[] A = scanArray(scanner, n);
		solve(A);
	}

	static int[] scanArray(Scanner scanner, int n) {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = scanner.nextInt();
		}
		return A;
	}

}

