import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class EventTree {

	private final List<List<Integer>> G;
	private final int m;
	private final boolean[] visited; 
	private final Deque<Pair> Q;
	private int cuts;
	
	static class Pair {
		final int a;
		int ib;
		int n_children;
		Pair(int a, int ib) {
			this.a = a;
			this.ib = ib;
			this.n_children = 0;
		}
		public String toString() {
			return String.format("(a=%d, ib=%d, n_children=%d)", a, ib, n_children);
		}	
	}

	public EventTree(List<List<Integer>> G, int m) {
		this.G = G;
		this.m = m;
		visited = new boolean[G.size()];
		Q = new ArrayDeque<>();
	}

	void dfs() {
		Pair p = Q.element();
		while (p.ib < G.get(p.a).size()) {
			int b = G.get(p.a).get(p.ib);
			if (!visited[b]) {
				visited[b] = true;
				p = new Pair(b, 0);
				Q.push(p);
			} else {
				p.ib++;
			}
		}
		p = Q.pop();
		if (!Q.isEmpty()) {
			if ((1 + p.n_children) % 2 == 0) {
				cuts++;
			} else {
				Q.element().n_children += (1 + p.n_children);
			}
		}
	}
	
	int solve(int start) {
		visited[start] = true;
		Q.push(new Pair(start, 0));
		while (!Q.isEmpty()) {
			dfs();	
		}
		return cuts;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		assert n == m + 1;
		List<List<Integer>> G = scanGraph(scanner, n, m);
		EventTree o = new EventTree(G, m);
		System.out.println(o.solve(0));
	}

	static List<List<Integer>> scanGraph(Scanner scanner, int n, int m) {
		List<List<Integer>> G = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			G.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < m; i++) {
			int a = scanner.nextInt() - 1;
			int b = scanner.nextInt() - 1;
			G.get(a).add(b);
			G.get(b).add(a);
			assert a != b;
		}		
		return G;
	}

}

