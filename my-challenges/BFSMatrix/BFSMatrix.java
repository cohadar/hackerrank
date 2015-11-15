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
		System.out.printf("pre: %c\n", 'A' + a);
	}

	void postProcessVertex(int a) {
		System.out.printf("post: %c\n", 'A' + a);
	}

	void processEdge(int a, int b) {
		System.out.printf("%c -> %c\n", a + 'A', b + 'A');
	}	

	void solve(int start) {
		Queue<Integer> Q = new ArrayDeque<>();
		boolean[] B = new boolean[G.length];
		B[start] = true;
		Q.add(start);
		while (!Q.isEmpty()) {
			System.out.println("##########");
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

	public static void main(String[] args) {
		BFSMatrix o = BFSMatrix.load(new Scanner(System.in));
		o.solve(0);
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
