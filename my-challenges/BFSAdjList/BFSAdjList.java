import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class BFSAdjList {

	static class Edge {
		final int a;
		final int b;
		final int w;
		Edge(int a, int b, int w) {
			this.a = a;
			this.b = b;
			this.w = w;
		}
		public String toString() {
			return String.format("(a=%d, b=%d, w=%d)", a, b, w);
		}	
	}

	static void solve(List<List<Edge>> G) {
		debug(G);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		List<List<Edge>> G = scanGraph(scanner, n, m);
		solve(G);
	}

	static List<List<Edge>> scanGraph(Scanner scanner, int n, int m) {
		List<List<Edge>> G = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			G.add(new ArrayList<Edge>());
		}
		for (int i = 0; i < m; i++) {
			int a = scanner.nextInt();
			int b = scanner.nextInt();
			int w = scanner.nextInt();
			Edge e = new Edge(a, b, w);
			G.get(a).add(e);
			G.get(b).add(e);
		}		
		return G;
	}

	static boolean DEBUG = true;
	
	static void debug(Object...os) {
		if (!DEBUG) { return; }
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}

