import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class EventTree {

	private final List<List<Integer>> G;
	private final int m;
	private final boolean[] visited; 
	private final int[] start_time;
	private final int[] end_time;
	private int time;
	
	public EventTree(List<List<Integer>> G, int m) {
		this.G = G;
		this.m = m;
		visited = new boolean[G.size()];
		start_time = new int[G.size()];
		end_time = new int[G.size()];
	}

	void dfs(int a) {
		start_time[a] = time++;
		for (int b : G.get(a)) {
			if (!visited[b]) {
				visited[b] = true;
				dfs(b);
			}
		}
		end_time[a] = time++;
	}
	
	int solve(int start) {
		visited[start] = true;
		dfs(start);
		return 0;
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

