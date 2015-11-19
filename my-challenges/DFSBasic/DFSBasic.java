import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class DFSBasic {

	private final List<List<Integer>> gr;
	private final boolean[] vi;
	
	public DFSBasic(List<List<Integer>> gr) {
		this.gr = gr;
		this.vi = new boolean[gr.size()];
	}

	void pre(int a) {
		System.out.printf("PRE: %c\n", 'A' + a);
	}

	void post(int a) {
		System.out.printf("POST: %c\n", 'A' + a);
	}

	void edge(int a, int b) {
		System.out.printf("E: %c -> %c\n", 'A' + a, 'A' + b);
	}

	void tree(int a, int b) {
		System.out.printf("T: %c -> %c\n", 'A' + a, 'A' + b);
	}	
	
	void dfs(int a) {
		vi[a] = true;
		System.out.println("################");
		pre(a);
		for (int b : gr.get(a)) {
			if (!vi[b]) {
				tree(a, b);
				dfs(b);
			} else {
				edge(a, b);
			}
		}
		post(a);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		List<List<Integer>> gr = scanGraph(scanner, n, m);
		DFSBasic o = new DFSBasic(gr);
		o.dfs(0);
	}

	static List<List<Integer>> scanGraph(Scanner scanner, int n, int m) {
		List<List<Integer>> gr = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			gr.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < m; i++) {
			int a = scanner.nextInt();
			int b = scanner.nextInt();
			assert a != b;
			gr.get(a).add(b);
			gr.get(b).add(a);
		}		
		return gr;
	}

}

