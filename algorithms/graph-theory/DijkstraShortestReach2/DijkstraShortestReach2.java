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
			return String.format("(b=%d, w=%d)", b, w);
		}	
	}	

	static class Vertex implements Comparable<Vertex>{
		final int a;
		final int w;
		Vertex(int a, int w) {
			this.a = a;
			this.w = w;
		}
		public int compareTo(Vertex that) {
			return Integer.compare(this.w, that.w);
		}
		public String toString() {
			return String.format("(a=%d, w=%d)", a, w);
		}			
	}

	static int[] solve(int n, List<List<Edge>> AL, int s) {
		boolean[] B = new boolean[n];
		int[] D = new int[n];
		Arrays.fill(D, INFINITY);
		D[s] = 0;
		Queue<Vertex> Q = new PriorityQueue<Vertex>();
		Q.add(new Vertex(s, 0));
		while (!Q.isEmpty()) {
			Vertex v = Q.remove();
			System.out.println(v);
			List<Edge> al = AL.get(v.a);
			for (Edge e : al) {
				if (B[e.b]) continue;
				D[e.b] = Math.min(D[e.b], D[v.a] + e.w);
				Q.add(new Vertex(e.b, D[e.b]));
			}
			B[v.a] = true;
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
			List<List<Edge>> AL = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				AL.add(new ArrayList<Edge>());
			}
			for (int i = 0; i < m; i++) {
				int x = scanner.nextInt() - 1;
				int y = scanner.nextInt() - 1;
				int w = scanner.nextInt();
				assert 0 <= x && x < n;
				assert 0 <= y && y < n;
				assert x != y;
				// handle duplicates
				AL.get(x).add(new Edge(y, w));
				AL.get(y).add(new Edge(x, w));
			}
			int s = scanner.nextInt() - 1;
			assert 0 <= s && s < n;
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

}

