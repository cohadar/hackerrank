import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class DijkstraShortestReach2 {

	static final int PRIME = 1000000007;

	static class Vertex implements Comparable<Vertex>{
		final int a;
		final int w;
		Vertex(int a, int w) {
			this.a = a;
			this.w = w;
		}
		public int compareTo(Vertex that) {
			return Integer.compare(this.w, that.w);
		}
	}

	static int[] solve(int n, int[][] M, int s) {
		boolean[] B = new boolean[n];
		int[] D = new int[n];
		Arrays.fill(D, PRIME);
		D[s] = 0;
		Queue<Vertex> Q = new PriorityQueue<Vertex>();
		Q.add(new Vertex(s, 0));
		while (!Q.isEmpty()) {
			Vertex v = Q.remove();
			for (int b = 0; b < n; b++) {
				if (B[b] || M[v.a][b] == PRIME || v.a == b) continue;
				D[b] = Math.min(D[b], D[v.a] + M[v.a][b]);
				Q.add(new Vertex(b, D[b]));
			}
			B[v.a] = true;
		}
		return D;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		assert 1 <= t && t <= 10;
		while (t-->0) {
			int n = scanner.nextInt();
			int m = scanner.nextInt();
			assert 2 <= n && n <= 3000;
			assert 1 <= m && m <= n * (n - 1) / 2;
			int[][] M = new int[n][n];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					M[i][j] = PRIME;
				}
			}
			for (int i = 0; i < m; i++) {
				int x = scanner.nextInt() - 1;
				int y = scanner.nextInt() - 1;
				int w = scanner.nextInt();
				assert 0 <= x && x < n;
				assert 0 <= y && y < n;
				assert x != y;
				M[x][y] = Math.min(M[x][y], w);
				M[y][x] = Math.min(M[y][x], w);
			}
			int s = scanner.nextInt() - 1;
			assert 0 <= s && s < n;
			int[] distances = solve(n, M, s);
			System.out.println(join(distances, ' ', s));
		}
	}

	static String join(int[] A, char delimiter, int s) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < A.length; i++) {
			if (i != s) {
				sb.append((A[i] == PRIME) ? -1 : A[i]);
				sb.append(delimiter);
			}
		}
		return sb.toString();
	}

}

