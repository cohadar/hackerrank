import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class CutTheTree {

	static class Edge {
		final int a;
		final int b;
		Edge(int a, int b) {
			this.a = a;
			this.b = b;
		}
		public String toString() {
			return String.format("(a=%d, b=%d)", a + 1, b + 1);
		}	
	}

	final int n;
	final int[] V;
	final List<List<Edge>> G;
	final int sum;
	final boolean[] M; 
	int diff;

	CutTheTree(int[] V, List<List<Edge>> G) {
		this.n = V.length;
		this.V = V;
		this.G = G;
		int s = 0;
		for (int i = 0; i < n; i++) {
			s += V[i];
		}	
		this.sum = s;	
		M = new boolean[n];
		this.diff = Integer.MAX_VALUE;
	}

	int findSum(int p) {
		int ret = V[p];
		M[p] = true;
		for (Edge e : G.get(p)) {
			int c = -1;
			if (!M[e.a]) {
				c = e.a;
			} else if (!M[e.b]) {
				c = e.b;
			} else {
				continue;
			}
			int childSum = findSum(c);
			ret += childSum;
			diff = Math.min(diff, Math.abs(sum - 2 * childSum));
		}
		return ret;
	}

	int minDiff() {
		findSum(0);
		return diff;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int[] V = scanArray(scanner, n);
		List<List<Edge>> G = scanGraph(scanner, n, n - 1);
		CutTheTree o = new CutTheTree(V, G);
		System.out.println(o.minDiff());
	}

	static int[] scanArray(Scanner scanner, int n) {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = scanner.nextInt();
		}
		return A;
	}

	static List<List<Edge>> scanGraph(Scanner scanner, int n, int m) {
		List<List<Edge>> G = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			G.add(new ArrayList<Edge>());
		}
		for (int i = 0; i < m; i++) {
			int a = scanner.nextInt() - 1;
			int b = scanner.nextInt() - 1;
			Edge e = new Edge(a, b);
			G.get(a).add(e);
			G.get(b).add(e);
		}		
		return G;
	}

}

