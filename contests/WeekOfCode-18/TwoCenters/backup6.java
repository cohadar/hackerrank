import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class TwoCenters {

	static final int INF = Integer.MAX_VALUE / 2;

	private final List<List<Integer>> G;
	private final int n;
	private final int[][] D;
	
	public TwoCenters(List<List<Integer>> G) {
		this.G = G;
		this.n = G.size();
		this.D = new int[n][n];
		for (int a = 0; a < n; a++) {
			Arrays.fill(D[a], INF);
		}
	}

	void bfs(int sa, int[] DA, int maxd) {
		DA[sa] = 0;
		Deque<Integer> Q = new ArrayDeque<>();
		Q.add(sa);
		while (!Q.isEmpty()) {
			int a = Q.remove();
			for (int b : G.get(a)) {
				if (DA[b] == INF) {
					DA[b] = DA[a] + 1;
					if (DA[b] < maxd) {
						Q.add(b);
					}
				}
			}
		}
	}

	int solve() {
		// use BFS for calculating distance matrix
		int maxd = n / 4 + 1;
		for (int a = 0; a < n; a++) {
			bfs(a, D[a], maxd);
		}

		// pick two roots (a, b) and calculate min distance to all others (c)
		int min = INF;
		for (int a = 0; a < n - 1; a++) {
			for (int b = a + 1; b < n; b++) {
				int abmax = 0;
				for (int c = 0; c < n; c++) {
					abmax = Math.max(abmax, Math.min(D[a][c], D[b][c]));
					if (abmax >= min) {
						break;
					}
				}
				if (abmax < min) {
					min = abmax;	
				}
			}
		}
		return min;
	}

	// 3, 1250, 60
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		assert 2 <= n && n <= 5000 : "out of range, n: " + n;
		List<List<Integer>> G = scanGraph(scanner, n, n - 1);
		TwoCenters o = new TwoCenters(G);
		System.out.println(o.solve());
	}

	static List<List<Integer>> scanGraph(Scanner scanner, int n, int m) {
		List<List<Integer>> G = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			G.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < m; i++) {
			int a = scanner.nextInt() - 1;
			int b = scanner.nextInt() - 1;
			assert a != b : "node self-loop: " + (a + 1);
			assert 0 <= a && a <= n - 1 : "out of range, a: " + (a + 1);
			assert 0 <= b && b <= n - 1 : "out of range, b: " + (b + 1);
			G.get(a).add(b);
			G.get(b).add(a);
		}		
		return G;
	}

}