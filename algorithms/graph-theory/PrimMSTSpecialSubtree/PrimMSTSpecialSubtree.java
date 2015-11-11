import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class PrimMSTSpecialSubtree {

	static class Edge implements Comparable<Edge> {
		final int x;
		final int y;
		final int r;
		Edge(int x, int y, int r) {
			this.x = x;
			this.y = y;
			this.r = r;
		}
		public int compareTo(Edge that) {
			return Integer.compare(this.r, that.r);
		}
		public String toString() {
			return String.format("(x=%d, y=%d, r=%d)", x, y, r);
		}	
	}

	static long mstWeight(List<List<Edge>> G, int s) {
		long ret = 0;
		boolean[] visited = new boolean[G.size()];
		visited[s] = true;
		Queue<Edge> Q = new PriorityQueue<>(); 
		for (Edge e : G.get(s)) {
			Q.add(e);
		}
		while (!Q.isEmpty()) {
			Edge e = Q.remove();
			int c = -1;
			if (!visited[e.x]) {
				c = e.x;
			} else if (!visited[e.y]) {
				c = e.y;
			} else {
				continue;
			}
			visited[c] = true;
			for (Edge f : G.get(c)) {
				if (!visited[f.x] || !visited[f.y]) {
					Q.add(f);
				}
			}
			ret += e.r;
		}
		return ret;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		assert 2 <= n && n <= 3000;
		assert 1 <= m && m <= n * (n - 1) / 2;
		List<List<Edge>> G = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			G.add(new ArrayList<Edge>());
		}
		for (int i = 0; i < m; i++) {
			int x = scanner.nextInt() - 1;
			int y = scanner.nextInt() - 1;
			int r = scanner.nextInt();
			Edge e = new Edge(x, y ,r);
			G.get(x).add(e);
			G.get(y).add(e);
		}
		int s = scanner.nextInt() - 1;
		System.out.println(mstWeight(G, s));
	}

}

