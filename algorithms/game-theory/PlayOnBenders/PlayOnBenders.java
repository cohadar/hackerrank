import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class PlayOnBenders {

	final int n;
	final List<List<Integer>> G;
	final Boolean[] W;
	
	PlayOnBenders(int n, List<List<Integer>> G) {
		this.n = n;
		this.G = G;
		this.W = new Boolean[n];
	}

	boolean rec(int parent) {
		boolean w = false;
		for (int child : G.get(parent)) {
			if (W[child] == null) {
				W[child] = rec(child);
			}
			if (W[child] == false) {
				w = true;
			}
		}
		return w;
	}

	void solve() {
		for (int i = 0; i < n; i++) {
			if (W[i] == null) {
				W[i] = rec(i);
			}
		}
	}

	boolean solve(int[] A) {
		int winCount = 0;
		for (int a : A) {
			if (W[a]) {
				winCount++;
			}
		}
		return winCount % 2 != 0;
	}

	static PlayOnBenders load(Scanner scanner) {
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		assert 1 <= n && n <= 1e5 : "out of range, n: " + n;
		assert 1 <= m && m <= 1e6 : "out of range, m: " + m;
		List<List<Integer>> G = scanGraph(scanner, n, m);
		return new PlayOnBenders(n, G);
	}	

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		PlayOnBenders o = PlayOnBenders.load(scanner);
		o.solve();
		debug(o.G);
		debug(o.W);
		int t = scanner.nextInt();
		assert 1 <= t && t <= 1e5 : "out of range, t: " + t;
		StringBuilder sb = new StringBuilder();
		while (t-->0) {
			int k = scanner.nextInt();
			assert 1 <= k && k <= 100 : "out of range, k: " + k;
			int[] A = scanArray(scanner, k, -1);
			debug(A);
			sb.append((o.solve(A)) ? "Bumi\n" : "Iroh\n");
		}
		System.out.println(sb);
	}

	static List<List<Integer>> scanGraph(Scanner scanner, int n, int m) {
		List<List<Integer>> G = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			G.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < m; i++) {
			int a = scanner.nextInt() - 1;
			int b = scanner.nextInt() - 1;
			G.get(a).add(b);
		}		
		return G;
	}

	static int[] scanArray(Scanner scanner, int n, int delta) {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = scanner.nextInt() + delta;
		}
		return A;
	}

	static boolean DEBUG = true;
	
	static void debug(Object...os) {
		if (!DEBUG) { return; }
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}
