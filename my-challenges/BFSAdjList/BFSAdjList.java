import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class BFSAdjList {

	static void pre(int a) {
		System.out.printf("PRE: %c\n", 'A' + a);
	}

	static void post(int a) {
		System.out.printf("POST: %c\n", 'A' + a);
	}

	static void edge(int a, int b) {
		System.out.printf("E: %c -> %c\n", 'A' + a, 'A' + b);
	}

	static void tree(int a, int b) {
		System.out.printf("T: %c -> %c\n", 'A' + a, 'A' + b);
	}	

	static void solve(List<List<Integer>> G, int start) {
		Queue<Integer> qu = new ArrayDeque<Integer>();
		boolean[] vi = new boolean[G.size()];
		qu.add(start);
		vi[start] = true;
		while (!qu.isEmpty()) {
			System.out.println("###############");
			int a = qu.remove();
			pre(a);
			for (int b : G.get(a)) {
				if (!vi[b]) {
					vi[b] = true;
					tree(a, b);
					qu.add(b);
				} else {
					edge(a, b);
				}
			}
			post(a);
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		List<List<Integer>> G = scanGraph(scanner, n, m);
		solve(G, 0);
	}

	static List<List<Integer>> scanGraph(Scanner scanner, int n, int m) {
		List<List<Integer>> G = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			G.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < m; i++) {
			int a = scanner.nextInt();
			int b = scanner.nextInt();
			G.get(a).add(b);
			// G.get(b).add(a);
		}
		return G;
	}

	static boolean DEBUG = true;

	static void debug(Object...os) {
		if (!DEBUG) { return; }
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}

