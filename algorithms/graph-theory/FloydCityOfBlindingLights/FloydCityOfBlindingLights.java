import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class FloydCityOfBlindingLights {

	static final int INF = (int)1e9 + 7;

	static int[][] solve(int[][] G) {
		int n = G.length;
		int[][] D = new int[n][n];
		for (int a = 0; a < n; a++) {
			for (int b = 0; b < n; b++) {
				if (a == b) {
					D[a][b] = 0;	
				} else {
					D[a][b] = (G[a][b] > 0) ? G[a][b] : INF;
				}
			}
		}
		for (int c = 0; c < n; c++) {
			for (int a = 0; a < n; a++) {
				for (int b = 0; b < n; b++) {
					int acb = D[a][c] + D[c][b];
					D[a][b] = Math.min(D[a][b], D[a][c] + D[c][b]);
				}
			}
		}
		return D;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		assert 2 <= n && n <= 400 : "out of range, n: " + n;
		int m = scanner.nextInt();
		assert 1 <= m && m <= n * (n - 1) / 2 : "out of range, m: " + m;
		int[][] G = scanGraph(scanner, n, m);
		int[][] D = solve(G);
		int q = scanner.nextInt();
		assert 1 <= q && q <= 1e5 : "out of range, q: " + q;
		for (int i = 0; i < q; i++) {
			int a = scanner.nextInt() - 1;
			int b = scanner.nextInt() - 1;
			System.out.println((D[a][b] < INF) ? D[a][b] : -1);
		}
	}

	static int[][] scanGraph(Scanner scanner, int n, int m) {
		int[][] G = new int[n][n];
		for (int i = 0; i < m; i++) {
			int a = scanner.nextInt() - 1;
			int b = scanner.nextInt() - 1;
			int r = scanner.nextInt();
			assert 1 <= r && r <= 350 : "out of range, r: " + r;
			G[a][b] = r;
		}	
		return G;
	}
	
}

