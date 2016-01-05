import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class Matrix {

	static class Edge implements Comparable<Edge> {
		final int a;
		final int b;
		int w;
		Edge(int a, int b, int w) {
			this.a = a;
			this.b = b;
			this.w = w;
		}
		public int compareTo(Edge that) {
			return Integer.compare(this.w, that.w);
		}
		public String toString() {
			return String.format("(a=%d, b=%d, w=%d)", a, b, w);
		}	
	}

	final List<List<Edge>> G;
	final List<Edge> E;
	final boolean[] K;

	Matrix(List<List<Edge>> G, List<Edge> E, boolean[] K) {
		this.G = G;
		this.E = E;
		this.K = K;
	}

	int countMachines(int start) {
		int count = 0;
		List<Edge> E2 = new ArrayList<Edge>();
		Deque<Integer> D = new ArrayDeque<>();
		boolean[] V = new boolean[G.size()];
		V[start] = true;
		D.add(start);
		if (K[start]) {
			count++;
		}
		while (!D.isEmpty()) {
			int a = D.remove();
			for (Edge e : G.get(a)) {
				if (e.w == 0) { continue; };
				E2.add(e);
				int b = (e.a == a) ? e.b : e.a;
				if (!V[b]) {
					V[b] = true;
					D.add(b);
					if (K[b]) {
						count++;
						if (count > 1) {
							return count;
						}
					}
				}
			}
		}
		if (count == 1) {
			for (Edge e : E2) {
				e.w = 0;
			}
		}		
		return count;
	}

	long solve() {
		long cost = 0;
		Collections.sort(E);
		debug(G);
		debug(E);
		debug(K);
		for (Edge e : E) {
			if (e.w != 0) {
				int temp = e.w;
				e.w = 0;
				int ma = countMachines(e.a);
				int mb = countMachines(e.b);
				if (ma > 0 && mb > 0) {
					cost += temp;
				}
			}
		}
		return cost;
	}

	static Matrix load(Scanner scanner) {
		int n = scanner.nextInt();
		assert 2 <= n && n <= 1e5 : "out of range, n: " + n;
		int k = scanner.nextInt();
		assert 2 <= k && k <= n : "out of range, k: " + k;
		List<Edge> E = new ArrayList<>();
		List<List<Edge>> G = scanGraph(scanner, n, n - 1, E);
		boolean[] K = new boolean[n];
		for (int i = 0; i < k; i++) {
			K[scanner.nextInt()] = true;
		}
		return new Matrix(G, E, K);
	}	

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Matrix o = Matrix.load(scanner);
		System.out.println(o.solve());
	}

	static List<List<Edge>> scanGraph(Scanner scanner, int n, int m, List<Edge> E) {
		List<List<Edge>> G = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			G.add(new ArrayList<Edge>());
		}
		for (int i = 0; i < m; i++) {
			int a = scanner.nextInt();
			int b = scanner.nextInt();
			int w = scanner.nextInt();
			Edge e = new Edge(a, b, w);
			E.add(e);
			G.get(a).add(e);
			G.get(b).add(e);
		}		
		return G;
	}

	static boolean DEBUG = false;
		
	static void debug(Object...os) {
		if (!DEBUG) { return; }
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}	

}
