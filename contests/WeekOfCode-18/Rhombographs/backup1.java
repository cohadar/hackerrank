import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class Rhombographs {

	static void firstCombination(int[] A, int y, int r) {
		int count = r;
		int x = 0;
		while (count > 0) {
			if (x == y) {
				A[x++] = -1;
			} else{
				A[x++] = 1;
				count--;
			}
		}
		while (x < A.length) {
			if (x == y) { 
				A[x++] = -1;
			} else {
				A[x++] = 0;	
			}
		}
	}

	static boolean nextCombination(int[] A, int y, int r) {
		int erased = 0;
		int x = A.length - 1;
		while (x >= 0 && A[x] != 0) {
			if (x != y) {
				A[x] = 0;
				erased++;
			}
			x--;
		}
		int z = x;
		x--;
		while (x >= 0) {
			if (x != y) {
				if (A[x] == 1) {
					A[x] = 0;
					A[z] = 1;
					x = z + 1;
					while (erased > 0) {
						if (x != y) {
							A[x] = 1;
							erased--;
						}
						x++;
					}
					return true;
				}
			}
			x--;
		}
		return false;
	}

	static boolean generate(int n, int r) {
		assert r < n;
		int[][] G = new int[n][n];
		for (int y = 0; y < n; y++) {
			firstCombination(G[y], y, r);
		}
		if (isUndirected(G) && isRhomboGraph(G)) {
			int m = n * r / 2;
			int z = n * n - 2 * m;
			debug(n, r, m, z);
			debug(G);
			return true;
		}
		int c = 0;
		while (true) {
			c = 0;
			for (int y = 0; y < n; y++) {
				if (nextCombination(G[y], y, r)) {
					break;
				} else {
					firstCombination(G[y], y, r);
					c++;
				}
			}
			if (c == n) {
				break;
			}
			if (isUndirected(G) && isRhomboGraph(G)) {
				int m = n * r / 2;
				int z = n * n - 2 * m;
				debug(n, r, m, z);
				debug(G);
				return true;
			}
		}
		return false;
	}

	static boolean isUndirected(int[][] G) {
		int n = G.length;
		for (int a = 0; a < n; a++) {
			for (int b = 0; b < n; b++) {
				if (G[a][b] != G[b][a]) {
					return false;
				}
			}
		}
		return true;
	}

	static boolean isRhomboGraph(int[][] G) {
		int n = G.length;
		for (int a = 0; a < n - 1; a++) {
			ok:
			for (int b = a + 1; b < n; b++) {
				if (G[a][b] == 0) {
					for (int c = 0; c < n - 1; c++) {
						for (int d = c + 1; d < n; d++) {
							if (c != a && d != a && c != b && d != b) {
								if (G[a][c] == 1 && G[c][b] == 1 && G[a][d] == 1 && G[d][b] == 1) {
									continue ok;
								}
							}
						}
					}
					return false;
				}	
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		for (int n = 2; n < 10; n++) {
			for (int r = n - 1; r > 0; r--) {
				if ((n * r) % 2 != 0) {
					continue;
				}
				int m = n * r / 2;
				int z = n * n - 2 * m;
				if (z % n != 0) {
					continue;
				}
				if (generate(n, r) == false) {
					assert z / n > r;
					debug(String.format("formula is bad for: n=%d, r=%d, m=%d, z=%d", n, r, m, z));
				} else {
					assert z / n <= r;
				}
			}
		}
	}

	static boolean DEBUG = true;
	
	static void debug(Object...os) {
		if (!DEBUG) { return; }
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

	static void debug(int[][] M) {
		if (!DEBUG) { return; }
		for (int y = 0; y < M.length; y++) {
			for (int x = 0; x < M[0].length; x++) {
				System.err.printf("%-2d", (M[y][x] == -1) ? 0 : M[y][x]);
			}
			System.err.println();
		}
		System.err.println();
	}

}

