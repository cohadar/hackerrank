import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class TreeDisplay {

	private final List<List<Integer>> G;
	private final Deque<Boolean> LC = new ArrayDeque<>();
	private final boolean[] V;
	
	public TreeDisplay(List<List<Integer>> G) {
		this.G = G;
		this.V = new boolean[G.size()];
	}

	void print_line(int a) {
		Iterator<Boolean> i = LC.descendingIterator();
		int s = LC.size();
		int c = 0;
		while (i.hasNext()) {
			c++;
			if (c == s) {
				System.out.print((i.next()) ? "`-- " : "|-- ");	
			} else {
				System.out.print((i.next()) ? "    " : "|   ");
			}
		}
		System.out.printf("(%d)\n", a + 1);
	}

	void print(int a) {
		V[a] = true;
		print_line(a);
		int s = G.get(a).size();
		int c = 0;
		for (int b : G.get(a)) {
			c++;
			LC.push(c == s);
			if (!V[b]) {
				print(b);
			}
			LC.pop();
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		assert n == m + 1;
		List<List<Integer>> G = scanGraph(scanner, n, m);
		sortChildren(G);
		TreeDisplay o = new TreeDisplay(G);
		o.print(0);
	}

	static void sortChildren(List<List<Integer>> G) {
		for (int i = 0; i < G.size(); i++) {
			Collections.sort(G.get(i));
		}
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
		}		
		return G;
	}

}

