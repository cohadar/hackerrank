import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class SubstringDiff {

	static final int INF = (int)1e9 + 7;

	static int solve(int s, char[] P, char[] Q) {
		assert P.length == Q.length;
		assert 0 <= s && s <= P.length : "out of range, s: " + s;
		int n = P.length;
		int[][] M = new int[n][n];
		int l = 0;
		for (int k = 0; k < n; k++) {
			boolean updated = false;
			for (int i = 0; i < n - k; i++) {
				for (int j = 0; j < n - k; j++) {
					M[i][j] += (P[i + k] == Q[j + k]) ? 0 : 1;
					if (M[i][j] <= s) {
						l = k;
						updated = true;
					} else {
						M[i][j] = INF;
					}
				}
			}
			if (!updated) {
				return l;
			}
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
			System.out.println(solve(s, P, Q) + 1);
		}
	}

	static boolean DEBUG = true;
	
	static void debug(Object...os) {
		if (!DEBUG) { return; }
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}

