import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  * @link 
  */
public class PlayWithWords {

	static int same(char[] S, int a, int b) {
		if (a == b) {
			return 1;
		}
		return (S[a] == S[b]) ? 2 : 0;
	}

	static int get(int[][] D, int a, int b) {
		if (a > b) {
			return 0;
		}
		int n = D.length;
		if (a >= n || b < 0) {
			return 0;
		}
		return D[a][b];
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String s = scanner.nextLine();
		char[] S = s.toCharArray();
		int n = S.length;
		int[][] D = new int[n][n];
		for (int i = 0; i < n; i++) {
			D[i][i] = 1;
		}
		for (int a = n - 1; a >= 0; a--) {
			for (int b = a; b < n; b++) {
				D[a][b] = same(S, a, b) + get(D, a + 1, b - 1);
				D[a][b] = Math.max(D[a][b], get(D, a + 1, b));
				D[a][b] = Math.max(D[a][b], get(D, a, b - 1));
			}
		}
		int max = 0;
		for (int i = 0; i < n - 1; i++) {
			int temp = D[0][i] * D[i + 1][n - 1];
			max = Math.max(max, temp);
		}
		System.out.println(max);
	}

	static boolean DEBUG = true;
	
	static void debug(Object...os) {
		if (!DEBUG) { return; }
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}
