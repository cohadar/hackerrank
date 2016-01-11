import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class TheLongestCommonSubsequence {

	final int an;
	final int bn;
	final int[] A;
	final int[] B;
	final int[][] L; // length matrix
	
	TheLongestCommonSubsequence(int[] A, int[] B) {
		this.an = A.length;
		this.bn = B.length;
		this.A = A;
		this.B = B;
		this.L = new int[1 + an][1 + bn];
	}

	void dp() {
		for (int a = 1; a <= an; a++) {
			for (int b = 1; b <= bn; b++) {
				L[a][b] = L[a - 1][b - 1] + ((A[a - 1] == B[b - 1]) ? 1 : 0);
				if (L[a][b] < L[a][b - 1]) {
					L[a][b] = L[a][b - 1];
				}
				if (L[a][b] < L[a - 1][b]) {
					L[a][b] = L[a - 1][b];
				}
			}
		}		
	}

	int[] backpedal() {
		int[] C = new int[L[an][bn]];
		int ic = C.length - 1;
		int a = an;
		int b = bn;
		while (ic >= 0) {
			if (A[a - 1] == B[b - 1]) {
				C[ic] = A[a - 1];
				ic--;
				a--;
				b--;				
			} else if (L[a - 1][b] < L[a][b - 1]) {
				b--;
			} else {
				a--;
			}
		}
		return C;		
	}

	int[] solve() {
		dp();
		return backpedal();
	}

	static TheLongestCommonSubsequence load(Scanner scanner) {
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		assert 1 <= n && n <= 100 : "out of range, n: " + n;
		assert 1 <= m && m <= 100 : "out of range, m: " + m;
		int[] A = scanArray(scanner, n);
		int[] B = scanArray(scanner, m);
		return new TheLongestCommonSubsequence(A, B);
	}	

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		TheLongestCommonSubsequence o = TheLongestCommonSubsequence.load(scanner);
		int[] C = o.solve();
		System.out.println(join(C, ' '));
	}

	static int[] scanArray(Scanner scanner, int n) {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = scanner.nextInt();
		}
		return A;
	}

	static String join(int[] A, char delimiter) {
		StringBuilder sb = new StringBuilder();
		for (int o : A) {
			sb.append(o);
			sb.append(delimiter);
		}
		return sb.toString();
	}

}
