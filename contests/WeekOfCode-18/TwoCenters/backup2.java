import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class TwoCenters {

	static final int INF = Integer.MAX_VALUE / 2;

	private final List<List<Integer>> G;
	private final int n;
	private int min = INF;
	
	public TwoCenters(List<List<Integer>> G) {
		this.G = G;
		this.n = G.size();
		debug(G);
		DA = new int[n];
		DB = new int[n];		
	}

	private int[] DA;
	private int[] DB;

	void double_bfs(int ca, int cb) {
		Arrays.fill(DA, -1);
		Arrays.fill(DB, -1);
		Deque<Integer> A = new ArrayDeque<>();
		Deque<Integer> B = new ArrayDeque<>();
		DA[ca] = 0;
		DB[cb] = 0;
		A.add(ca);
		B.add(cb);
		int maxda = 0;
		int maxdb = 0;
		while (A.isEmpty() == false || B.isEmpty() == false) {
			if (A.isEmpty() == false) {
				int a = A.remove();
				for (int b : G.get(a)) {
					if (DA[b] < 0 && DB[b] < 0) { // TODO: check with || here
						DA[b] = DA[a] + 1;
						maxda = Math.max(maxda, DA[b]);
						A.add(b);
					}
				}
			}
			if (B.isEmpty() == false) {
				int b = B.remove();
				for (int a : G.get(b)) {
					if (DB[a] < 0 && DA[a] < 0) { // TODO: check with || here
						DB[a] = DB[b] + 1;
						maxdb = Math.max(maxdb, DB[a]);
						B.add(a);
					}
				}
			}
		}
		min = Math.min(min, Math.max(maxda, maxdb));
	}

	int solve() {
		for (int a = 0; a < n - 1; a++) {
			for (int b = a + 1; b < n; b++) {
				double_bfs(a, b);
			}
		}
		return min;
	}

	// 3, 17, 12
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

	static boolean DEBUG = false;
	
	static void debug(Object...os) {
		if (!DEBUG) { return; }
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

	static void debug(int[][] M) {
		if (!DEBUG) { return; }
		for (int y = 0; y < M.length; y++) {
			for (int x = 0; x < M[0].length; x++) {
				System.err.print(M[y][x]);
				System.err.print(' ');
			}
			System.err.println();
		}
		System.err.println();
	}

}

