import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class Matrix {

	static class Edge implements Comparable<Edge> {
		final int a;
		final int b;
		final int z;
		boolean ma;
		boolean mb;
		boolean destroyed = false;
		Edge(int a, int b, int z) {
			this.a = a;
			this.b = b;
			this.z = z;
		}
		public String toString() {
			return String.format("(a=%d, b=%d, z=%d)", a, b, z);
		}	
		public int compareTo(Edge that) {
			return Integer.compare(this.z, that.z);
		}
	}

	private final List<List<Edge>> G;
	private final int[] K;
	
	public Matrix(List<List<Edge>> G, int[] K) {
		Arrays.sort(K);
		debug(G);
		debug(K);
		this.G = G;
		this.K = K;
	}

	boolean leadsToMachines(boolean mb, int b) {
		if (mb) {
			return true;
		}
		for (Edge f : G.get(b)) {
			if (!f.destroyed) {
				f.destroyed = true;
				if (leadsToMachines(f.ma, f.a) || leadsToMachines(f.mb, f.b)) {
					f.destroyed = false;
					return true;
				}
				f.destroyed = false;
			}
		}
		return false;
	}

	void locateMachines() {
		for (List<Edge> le : G) {
			for (Edge e : le) {
				e.ma = Arrays.binarySearch(K, e.a) >= 0;
				e.mb = Arrays.binarySearch(K, e.b) >= 0;
			}
		}
	}

	long solve() {
		locateMachines();
		PriorityQueue<Edge> Q = new PriorityQueue<>();
		for (int k : K) {
			for (Edge e : G.get(k)) {
				Q.add(e);
			}
		}
		long time = 0;
		while (!Q.isEmpty()) {
			Edge e = Q.remove();
			if (!e.destroyed) {
				e.destroyed = true;
				if (leadsToMachines(e.ma, e.a) && leadsToMachines(e.mb, e.b)) {
					time += e.z;
					// debug(e);
				} else {
					e.destroyed = false;
				}
			}
		}
		return time;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int k = scanner.nextInt();
		assert 2 <= n && n <= 1e5 : "out of range, n: " + n;
		assert 2 <= k && k <= n : "out of range, k: " + k;
		List<List<Edge>> G = scanGraph(scanner, n, n - 1);
		int[] K = scanArray(scanner, k);
		Matrix o = new Matrix(G, K);
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
			Edge e = new Edge(a, b, z);
			G.get(a).add(e);
			G.get(b).add(e);
		}		
		return G;
	}

	static int[] scanArray(Scanner scanner, int n) {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = scanner.nextInt();
		}
		return A;
	}

	static boolean DEBUG = false;
	
	static void debug(Object...os) {
		if (!DEBUG) { return; }
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}
}

