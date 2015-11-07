import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class KruskalMSTReallySpecialSubtree {

	static class Edge implements Comparable<Edge>{
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
		public int compareTo(Edge that) {
			int ret = Integer.compare(this.w, that.w);
			if (ret == 0) {
				ret = Integer.compare(this.a + this.b, that.a + that.b);
			}
			return ret;
		}
	}

	static int solve(int n, Edge[] E, int s) {
		Arrays.sort(E);
		int weight = 0;
		UnionFind uf = new UnionFind(n);
		for (Edge e : E) {
			if (!uf.connected(e.a, e.b)) {
				uf.union(e.a, e.b);
				weight += e.w;
			}
		}
		return weight;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		Edge[] E = new Edge[m];
		for (int i = 0; i < m; i++) {
			int a = scanner.nextInt() - 1;
			int b = scanner.nextInt() - 1;
			int w = scanner.nextInt();
			E[i] = new Edge(a, b, w);
		}
		int s = scanner.nextInt() - 1;
		System.out.println(solve(n, E, s));
	}

}

/* https://en.wikipedia.org/wiki/Disjoint-set_data_structure */
class UnionFind {
	final int size;
	int[] parent;
	byte[] rank;
	int count;
	public UnionFind(int n) {
		if (n < 0) throw new IllegalArgumentException("size cannot be negative: " + n);
		this.size = n;
		this.count = n;
		this.parent = new int[n];
		this.rank = new byte[n];
		for (int i = 0; i < n; i++) {
			this.parent[i] = i;
			this.rank[i] = 0;
		}
	}
	public int find(int index) {
		if (index < 0 || index >= size) throw new IndexOutOfBoundsException("index: " + index);
		if (parent[index] != index) {
			parent[index] = find(parent[index]);
		}
		return parent[index];
	}
	public void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		if (pa == pb) return;
		if (rank[pa] > rank[pb]) {
			parent[pb] = pa;
		} else if (rank[pa] < rank[pb]) {
			parent[pa] = pb;
		} else {
			parent[pa] = pb;
			rank[pb]++;
		}
		count--;
	}
	public boolean connected(int a, int b) {
		return find(a) == find(b);
	}
	public int count() {
		return count;
	}
	public int size() {
		return size;
	}		
}

