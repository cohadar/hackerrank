import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class BreadthFirstSearchShortestReach {

	final int[][] G;
	final int start;

	BreadthFirstSearchShortestReach(int[][] G, int start) {
		this.G = G;
		this.start = start;
	}

	int[] solve() {
		int n = G.length;
		int[] ret = new int[n];
		boolean[] p = new boolean[n];
		Arrays.fill(ret, -1);
		Queue<Integer> q = new ArrayDeque<Integer>();
	   	q.add(start);
		ret[start] = 0; 
		p[start] = true;
		while (!q.isEmpty()) {
			int a = q.remove();
			for (int b = 0; b < n; b++) {
				if (G[a][b] > 0) {
					if (ret[b] >= 0) {
						ret[b] = Math.min(ret[b], ret[a] + G[a][b]);
					} else {
						ret[b] = ret[a] + G[a][b];
					}
					if (p[b] == false) {
						p[b] = true;
						q.add(b);
					}
				}
			}	
		}
		return ret;
	}

	static BreadthFirstSearchShortestReach load(Scanner scanner) {
		int nodes = scanner.nextInt();
		int[][] G = new int [nodes][nodes];
		int edges = scanner.nextInt();
		for (int i = 0; i < edges; i++) {
			int a = scanner.nextInt() - 1;
			int b = scanner.nextInt() - 1;
			G[a][b] = 6;
			G[b][a] = 6;
		}
		int start = scanner.nextInt() - 1;	
		return new BreadthFirstSearchShortestReach(G, start);	
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		while (t-->0) {
			BreadthFirstSearchShortestReach o = BreadthFirstSearchShortestReach.load(scanner);
			int[] result = o.solve();
			for (int i = 0; i < result.length; i++) {
				if (i != o.start) {
					System.out.printf("%d ", result[i]);
				}
			}
			System.out.println();
		}	
	}

}

