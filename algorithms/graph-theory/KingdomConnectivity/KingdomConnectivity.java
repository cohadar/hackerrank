import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class KingdomConnectivity {

	static final int INF = Integer.MAX_VALUE;
	static final int PRIME = (int)1e9; // problem setter was stupid

	final int n;
	final int[][] G;
	final boolean[] C; // cycle form 0?
	
	KingdomConnectivity(int[][] G) {
		this.n = G.length;
		this.G = G;
		this.C = new boolean[n];
	}
	
	long solve() {
		ArrayDeque<Integer> Q = new ArrayDeque<>();
		Q.add(0);
		boolean[] V = new boolean[n];
		TreeSet<Integer> T = new TreeSet<>();
		T.add(0);
		V[0] = true;
		while (!Q.isEmpty()) {
			int a = Q.remove();
			for (int b = 0; b < n; b++) {
				if (G[a][b] == 0 || a == b) { continue; };
				if (V[b]) {
					for (int x : T) {
						C[x] = true;
					}
				} else {
					V[b] = true;
					P[b]++;
				}
			}
		}
		return G[0][n - 1];
	}

	static KingdomConnectivity load(Scanner scanner) {
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		assert 2 <= n && n <= 1e4 : "out of range, n: " + n;
		assert 1 <= m && m <= 1e5 : "out of range, m: " + m;
		int[][] G = scanGraph(scanner, n, m);	 
		return new KingdomConnectivity(G);
	}	

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		KingdomConnectivity o = KingdomConnectivity.load(scanner);
		System.out.println(o.solve());
	}

	static int[][] scanGraph(Scanner scanner, int n, int m) {
		int[][] G = new int[n][n];
		for (int i = 0; i < m; i++) {
			int a = scanner.nextInt() - 1;
			int b = scanner.nextInt() - 1;
			G[a][b]++;
		}	
		return G;
	}

}
