import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class Matrix {

	static class Edge implements Comparable<Edge> {
		final int b;
		final int z;
		boolean destroyed = false;
		Edge mirror;
		Edge(int b, int z) {
			this.b = b;
			this.z = z;
		}
		public String toString() {
			return String.format("(b=%d, z=%d)", b, z);
		}	
		public int compareTo(Edge that) {
			return Integer.compare(this.z, that.z);
		}
	}

	private final List<List<Edge>> G;
	private final int[] V;
	
	public Matrix(List<List<Edge>> G, int[] V) {
		debug(G);
		debug(V);
		this.G = G;
		this.V = V;
		markMultis();
	}

	void markMultis() {
		for (int a = 0; a < V.length; a++) {
			if (V[a] < 0) {
				for (Edge e : G.get(a)) {
					if (V[e.b] >= 0) {
						V[e.b]++;
					}
				}
			}
		}		
	}

	long reduceMultis() {
		long sum = 0;
		PriorityQueue<Edge> Q = new PriorityQueue<>();
		for (int a = 0; a < V.length; a++) {
			if (V[a] > 1) {
				for (Edge e : G.get(a)) {
					if (V[e.b] < 0) {
						Q.add(e);
					}
				}
				while (Q.size() > 1) {
					Edge e = Q.remove();
					if (!e.destroyed) {
						e.destroyed = true;
						e.mirror.destroyed = true;
						sum += e.z;
						debug("reduceMultis", a, e.b, e.z);
					}
					V[a]--;
				}
				Q.clear();
			} 
		}
		return sum;
	}

	Edge destroyMin(int a, int parent) {
		for (Edge e : G.get(a)) {
			if (e.destroyed == false && e.b != parent) {
				if (V[e.b] < 0) {
					return e;
				}
				Edge f = destroyMin(e.b, a); 
				if (f != null) {
					return (e.z < f.z) ? e : f;
				}
			}
		}
		return null;
	}

	long reduceSingles() {
		long sum = 0;
		for (int a = 0; a < V.length; a++) {
			if (V[a] < 0) {
				Edge min = destroyMin(a, -7);
				if (min != null) {
					min.destroyed = true;
					min.mirror.destroyed = true;					
					sum += min.z;
					debug("reduceSingles", a, min.b, min.z);
				}
			}
		}
		return sum;
	}

	long solve() {
		debug(V);
		long time = reduceMultis();
		debug(V);
		time += reduceSingles();
		return time;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int k = scanner.nextInt();
		assert 2 <= n && n <= 1e5 : "out of range, n: " + n;
		assert 2 <= k && k <= n : "out of range, k: " + k;
		List<List<Edge>> G = scanGraph(scanner, n, n - 1);
		int[] V = new int[n];
		for (int i = 0; i < k; i++) {
			V[scanner.nextInt()] = -1;
		}
		Matrix o = new Matrix(G, V);
		System.out.println(o.solve());
	}

	static List<List<Edge>> scanGraph(Scanner scanner, int n, int m) {
		List<List<Edge>> G = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			G.add(new ArrayList<Edge>());
		}
		for (int i = 0; i < m; i++) {
			int a = scanner.nextInt();
			int b = scanner.nextInt();
			int z = scanner.nextInt();
			assert 1 <= z && z <= 1e6 : "out of range, z: " + z;
			Edge eab = new Edge(b, z);
			Edge eba = new Edge(a, z);
			eab.mirror = eba;
			eba.mirror = eab;
			G.get(a).add(eab);
			G.get(b).add(eba);
		}		
		return G;
	}

	static boolean DEBUG = false;
	
	static void debug(Object...os) {
		if (!DEBUG) { return; }
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}
}

