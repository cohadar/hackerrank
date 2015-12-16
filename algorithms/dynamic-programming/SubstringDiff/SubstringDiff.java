import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class SubstringDiff {

	static final int INF = (int)1e9 + 7;

	static int[] njak(int i, int j, char[] P, char[] Q) {
		int n = P.length;
		int m = Math.max(i, j);
		int[] R = new int[n - m];
		for (int k = 0; k < R.length; k++) {
			R[k] = (P[i + k] == Q[j + k]) ? 0 : 1;
		}
		return R;
	}

	static int zrak(int l, int[] A, int s) {
		if (l >= A.length) {
			return l;
		}
		int k = 0;
		int c = 0;
		for (int i = 0; i < A.length; i++) {
			c += A[i];
			if (c <= s) {
				k++;
			} else {
				c -= A[i - k];
			}
		}
		return Math.max(l, k);
	}

	static int solve(int s, char[] P, char[] Q) {
		assert P.length == Q.length;
		assert 0 <= s && s <= P.length : "out of range, s: " + s;
		int n = P.length;
		int l = 0;
		int[] A = njak(0, 0, P, Q);
		l = zrak(l, A, s);
		for (int i = 1; i < n; i++) {
			A = njak(0, i, P, Q);
			l = zrak(l, A, s);
			A = njak(i, 0, P, Q);
			l = zrak(l, A, s);
		}
		return l;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = Integer.valueOf(scanner.nextLine());
		assert 1 <= t && t <= 10 : "out of range, t: " + t;
		while (t-->0) {
			String[] temp = scanner.nextLine().split(" ");
			int s = Integer.parseInt(temp[0]);
			char[] P = temp[1].toCharArray();
			char[] Q = temp[2].toCharArray();
			System.out.println(solve(s, P, Q));
		}
	}

	static boolean DEBUG = true;
	
	static void debug(Object...os) {
		if (!DEBUG) { return; }
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}

