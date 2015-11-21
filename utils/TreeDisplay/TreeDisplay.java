import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class TreeDisplay {

	private final List<List<Integer>> G;
	
	public TreeDisplay(List<List<Integer>> G) {
		this.G = G;
	}

	void print(int start) {
		/***/
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		assert n == m + 1;
		List<List<Integer>> G = scanGraph(scanner, n, m);
		TreeDisplay o = new TreeDisplay(G);
		o.print(0);
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

