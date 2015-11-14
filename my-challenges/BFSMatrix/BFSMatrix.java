import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  * @link 
  */
public class BFSMatrix {

	int[][] G;

	BFSMatrix(int[][] G) {
		this.G = G;
	}

	void preProcessVertex(int a) {

	}

	void postProcessVertex(int a) {
		
	}

	void processEdge(int a, int b) {
		System.out.printf("%c -> %c\n", a + 'A', b + 'A');
	}	

	void solve() {
		Queue<Integer> Q = new ArrayDeque<>();
		boolean[] B = new boolean[G.length];
		B[0] = true;
		Q.add(0);
		while (!Q.isEmpty()) {
			int a = Q.remove();
			preProcessVertex(a);
			for (int b = 0; b < G.length; b++) {
				if (G[a][b] == 1 && !B[b]) {
					B[b] = true;
					Q.add(b);
					processEdge(a, b);
				}
			}
			postProcessVertex(a);
		}
	}

	static BFSMatrix load(Scanner scanner) {
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		int[][] G = new int[n][n];
		for (int i = 0; i < m; i++) {
			int a = scanner.nextInt();
			int b = scanner.nextInt();
			G[a][b] = 1;
			G[b][a] = 1;
		}
		return new BFSMatrix(G);
	}	

	static BFSMatrix full(int n) {
		int[][] G = new int[n][n];
		for (int y = 0; y < n; y++) {
			for (int x = 0; x < n; x++) {
				G[y][x] = 1;
			}
		}
		return new BFSMatrix(G);
	}

	public static void main(String[] args) {
		for (int i = 1; i < 20; i++) {
			BFSMatrix o = BFSMatrix.full(i);
			System.out.println("########## n=" + i);
			o.solve();
		}
	}

	static boolean DEBUG = true;
	
	static void debug(Object...os) {
		if (!DEBUG) { return; }
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

	static void debug(int[][] M) {
		if (!DEBUG) { return; }
		for (int y = 0; y < M.length; y++) {
			for (int x = 0; x < M[0].length; x++) {
				System.err.print(M[y][x]);
			}
			System.err.println();
		}
		System.err.println();
	}

}
