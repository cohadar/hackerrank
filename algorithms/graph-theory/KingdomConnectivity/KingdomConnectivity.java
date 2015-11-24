import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class KingdomConnectivity {

	static final int MOD = (int)1e9;

	private final List<List<Integer>> G;
	private final boolean[] V; // visited
	private final TreeSet<Integer> C; // cycle nodes
	
	public KingdomConnectivity(List<List<Integer>> G) {
		this.G = G;
		debug(G);
		this.V = new boolean[G.size()];
		this.C = new TreeSet<Integer>();
	}

	static class CycleException extends RuntimeException {};  

	
	int target;
	int sum;

	void dfs(int a) {
		V[a] = true;
		for (int b : G.get(a)) {
			if (V[b]) {
				C.add(b);
			} else if (b == target) {
				sum = (sum + 1) % MOD;
				if (!C.isEmpty()) {
					throw new CycleException();
				}
			} else {
				dfs(b);
			}
		}
		V[a] = false;
		C.remove(a);
	}

	int solve(int start, int end) {
		debug(start, end);
		target = end;
		try {
			dfs(start);
			return sum;
		} catch (CycleException e) {
			return -1;
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		assert 2 <= n && n <= 1e4 : "out of range, n: " + n;
		assert 1 <= m && m <= 1e5 : "out of range, m: " + m;
		List<List<Integer>> G = scanGraph(scanner, n, m);
		KingdomConnectivity o = new KingdomConnectivity(G);
		int solution = o.solve(0, n - 1);
		System.out.println((solution < 0) ? "INFINITE PATHS" : solution);
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

	static boolean DEBUG = false;
	
	static void debug(Object...os) {
		if (!DEBUG) { return; }
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}

