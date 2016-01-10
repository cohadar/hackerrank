import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class FarVertices {

	static final int INF = Integer.MAX_VALUE / 2;

	final int n;
	final int k;
	final int[][] G;
	
	FarVertices(int[][] G, int k) {
		this.n = G.length;
		this.k = k;
		this.G = G;
	}

	void floydWarshall() {
		for (int b = 0; b < n; b++) {
			for (int a = 0; a < n; a++) {
				for (int c = 0; c < n; c++) {
					G[a][c] = Math.min(G[a][c], G[a][b] + G[b][c]);
				}
			}
		}
	}


	int distC2(int a) {
		int count = 0;
		for (int i = 0; i < n; i++) {
			if (G[a][i] > k) {
				count++;
			}
		}
		return count;
	}

	int findBiggest() {
		int im = -1;
		int max = 0;
		for (int i = 0; i < n; i++) {
			int d = distC2(i);
			if (max < d) {
				max = d;
				im = i;
			}
		}
		return im;
	}

	void removeNode(int a) {
		Arrays.fill(G[a], 0);
		for (int i = 0; i < n; i++) {
			G[i][a] = 0;
		}		
	}
	
	int solve() {
		floydWarshall();
		for (int count = 0; true; count++) {
			int a = findBiggest();
			if (a == -1) { return count; }
			removeNode(a);
		}
	}

	static FarVertices load(Scanner scanner) {
		int n = scanner.nextInt();
		int k = scanner.nextInt();
		assert 0 <= n && n <= 100 : "out of range, n: " + n;
		assert 0 <= k && k < n : "out of range, k: " + k;
		int[][] G = scanGraph(scanner, n, n - 1);
		return new FarVertices(G, k);
	}	

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		FarVertices o = FarVertices.load(scanner);
		System.out.println(o.solve());
	}

	static int[][] scanGraph(Scanner scanner, int n, int m) {
		int[][] G = new int[n][n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(G[i], INF);
		}
		for (int i = 0; i < m; i++) {
			int a = scanner.nextInt() - 1;
			int b = scanner.nextInt() - 1;
			G[a][b] = 1;
			G[b][a] = 1;
		}	
		for (int i = 0; i < n; i++) {
			G[i][i] = 0;
		}
		return G;
	}

}
