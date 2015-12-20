import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class SaveAnurag {

	static final int SIZE = (int)1e7;

	static int[] solve() {
		int[] D = new int[SIZE + 1];
		Arrays.fill(D, 1);
		int sq = (int)Math.sqrt(SIZE) + 1;
		for (int i = 2; i < SIZE; i++) {
			for (int j = i; j <= SIZE; j += i) {
				D[j]++;
			}
		}
		return D;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		StringBuilder sb = new StringBuilder();
		int[] D = solve();
		debug(D);
		for (int i = 0; i < n; i++) {
			int x = scanner.nextInt();
			sb.append((D[x] >= 4) ? "YES\n" : "NO\n");
		}
		System.out.println(sb);
	}

	static boolean DEBUG = false;
	
	static void debug(Object...os) {
		if (!DEBUG) { return; }
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}

