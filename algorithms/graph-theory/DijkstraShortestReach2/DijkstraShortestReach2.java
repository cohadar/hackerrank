import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class DijkstraShortestReach2 {

	static final int INFINITY = 1000000007;

	static class Edge {
		final int b;
		final int w;
		Edge(int b, int w) {
			this.b = b;
			this.w = w;
		}
		public String toString() {
			return String.format("(b=%d, w=%d)", b + 1, w);
		}	
	}	

	static class Vertex implements Comparable<Vertex>{
		final int a;
		int w;
		Vertex(int a, int w) {
			this.a = a;
			this.w = w;
		}
		public int compareTo(Vertex that) {
			return Integer.compare(this.w, that.w);
		}
		public String toString() {
			return String.format("(a=%d, w=%d)", a + 1, w);
		}			
	}

	static Vertex removeMin(List<Vertex> Q) {
		Vertex min = Q.get(0);
		for (Vertex v : Q) {
			if (v.w < min.w) {
				min = v;
			}
		}
		Q.remove(min);
		return min;
	}

	static void decreaseKey(List<Vertex> Q, int a, int w) {
		for (Vertex v : Q) {
			if (v.a == a) {
				v.w = w;
				return;
			}
		}
		Q.add(new Vertex(a, w));
	}

	static int[] solve(int n, List<List<Edge>> AL, int s) {
		debug(n, AL, s);
		boolean[] done = new boolean[n];
		int[] D = new int[n];
		Arrays.fill(D, INFINITY);
		D[s] = 0;
		// TODO: pairing heap
		List<Vertex> Q = new ArrayList<Vertex>();
		Q.add(new Vertex(s, 0));
		while (!Q.isEmpty()) {
			Vertex v = removeMin(Q);
			debug(v);
			done[v.a] = true;
			List<Edge> al = AL.get(v.a);
			for (Edge e : al) {
				if (done[e.b]) continue;
				D[e.b] = Math.min(D[e.b], D[v.a] + e.w);
			}
			for (Edge e : al) {
				if (done[e.b]) continue;
				decreaseKey(Q, e.b, D[e.b]);
			}
		}
		return D;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		assert 1 <= t && t <= 10;
		while (t-->0) {
			int n = scanner.nextInt();
			int m = scanner.nextInt();
			assert 2 <= n && n <= 3000;
			assert 1 <= m && m <= n * (n - 1) / 2;
			// adjacency matrix
			int[][] M = new int[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					M[i][j] = INFINITY;
				}
			}			
			for (int i = 0; i < m; i++) {
				int x = scanner.nextInt() - 1;
				int y = scanner.nextInt() - 1;
				int w = scanner.nextInt();
				assert 0 <= x && x < n;
				assert 0 <= y && y < n;
				assert x != y;
				// handle duplicates
				M[x][y] = Math.min(M[x][y], w);
				M[y][x] = Math.min(M[y][x], w);
			}
			int s = scanner.nextInt() - 1;
			assert 0 <= s && s < n;
			// convert matrix to adjacency lists
			List<List<Edge>> AL = new ArrayList<>();
			for (int a = 0; a < n; a++) {
				List<Edge> l = new ArrayList<Edge>();
				for (int b = 0; b < n; b++) {
					if (M[a][b] != INFINITY) {
						l.add(new Edge(b, M[a][b]));
					}
				}
				AL.add(l);
			}
			int[] distances = solve(n, AL, s);
			System.out.println(join(distances, ' ', s));
		}
	}

	static String join(int[] A, char delimiter, int s) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < A.length; i++) {
			if (i != s) {
				sb.append((A[i] == INFINITY) ? -1 : A[i]);
				sb.append(delimiter);
			}
		}
		return sb.toString();
	}

	static boolean DEBUG = false;
	
	static void debug(Object...os) {
		if (!DEBUG) { return; }
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}

