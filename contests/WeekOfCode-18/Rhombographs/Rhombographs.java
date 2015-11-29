import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class Rhombographs {

	static final int INF = (int)1e9 + 7;

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

	static boolean generate_old(int n, int r) {
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

	static boolean generate_all(int n, int r) {
		assert r < n;
		int[][] G = new int[n][n];
		for (int y = 0; y < n; y++) {
			firstCombination(G[y], y, r);
		}
		if (isUndirected2(G) && isRhomboGraph(G)) {
			int m = n * r / 2;
			int z = n * n - 2 * m;
			debug(n, r, m, z);
			debug(G);
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
			if (isUndirected2(G) && isRhomboGraph(G)) {
				int m = n * r / 2;
				int z = n * n - 2 * m;
				debug(n, r, m, z);
				debug(G);
			}
		}
		return false;
	}	

	static boolean isRhomboGraph2(int[][] G) { 
		return true;
	}

	static boolean isUndirected2(int[][] G) { 
		return true;
	}	

	static void fillZeros(int[][] G, int a, int b, int x) {
		int n = G.length;
		for (int i = 0; i < n; i++) {
			Arrays.fill(G[i], 1);
		}
		int ix = 0;
		while (a-->0) {
			for (int i = 0; i < x; i++) {
				for (int j = 0; j < x; j++) {
					G[ix + i][ix + j] = 0;
				}
			}
			ix += x;
		}
		while (b-->0) {
			int x1 = x - 1;
			for (int i = 0; i < 2 * x1; i++) {
				G[ix + i][ix + i] = 0;
			}
			for (int i = 0; i < x1; i++) {
				for (int j = 0; j < x1; j++) {
					G[ix + i][ix + x1 + j] = 0;		
					G[ix + x1 + i][ix + j] = 0;
				}
			}
			ix += (2 * x1);
		}
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

	static boolean isRhombo(int[][] G, int a, int b) {
		int n = G.length;
		int count = 0;
		for (int c = 0; c < n - 1; c++) {
			for (int d = c + 1; d < n; d++) {
				if (c != a && d != a && c != b && d != b) {
					if (G[a][c] == 1 && G[c][b] == 1 && G[a][d] == 1 && G[d][b] == 1) {
						count++;
						debug(a, b, c, d, count);
					}
				}
			}
		}
		return count == 1;
	}

	static boolean isRhomboGraph(int[][] G) {
		int n = G.length;
		for (int a = 0; a < n - 1; a++) {
			for (int b = a + 1; b < n; b++) {
				if (G[a][b] == 0) {
					if (isRhombo(G, a, b) == false) {
						return false;
					}
				}	
			}
		}
		return true;
	}	

	static int[][] dist(int[][] G) {
		int n = G.length;
		int[][] D = new int[n][n];
		for (int a = 0; a < n; a++) {
			Arrays.fill(D[a], INF);
			D[a][a] = 0;
		}
		for (int a = 0; a < n; a++) {
			for (int b = 0; b < n; b++) {
				if (G[a][b] == 1) {
					D[a][b] = 1;
				}
			}
		}
		for (int c = 0; c < n; c++) {
			for (int a = 0; a < n; a++) {
				for (int b = 0; b < n; b++) {
					D[a][b] = Math.min(D[a][b], D[c][a] + D[c][b]);
				}
			}
		}
		return D;
	}

	public static int[][] generateN(int n) {
		int[][] G = new int[8][8];
		for (int i = 0; i < n; i++) {
			Arrays.fill(G[i], 1);
			G[i][i] = 0;
		}
		return G;
	}

	public static int[][] generate_perfect(int n) {
		int[][] G = new int[n][n];
		for (int a = 0; a < n; a++) {
			for (int b = 0; b < n; b++) {
				if ((a + b) % 2 == 0) {
					G[a][b] = 1;
				}
			}
		}
		for (int a = 0; a < n / 4; a++) {
			G[4*a+0][4*a+0] = 0;
			G[4*a+0][4*a+1] = 0;
			G[4*a+1][4*a+0] = 0;
			G[4*a+1][4*a+1] = 0;
			G[4*a+2][4*a+2] = 0;
			G[4*a+2][4*a+3] = 0;
			G[4*a+3][4*a+2] = 0;
			G[4*a+3][4*a+3] = 0;
			G[4*a+0][4*a+2] = 1;
			G[4*a+0][4*a+3] = 1;
			G[4*a+1][4*a+2] = 1;
			G[4*a+1][4*a+3] = 1;
			G[4*a+2][4*a+0] = 1;
			G[4*a+3][4*a+0] = 1;
			G[4*a+2][4*a+1] = 1;
			G[4*a+3][4*a+1] = 1;
		}
		return G;
	}

	public static int[][] generate_6_3() {
		int[][] G = new int[][]{
			{0, 0, 1, 1, 1, 0},
			{0, 0, 1, 1, 0, 1},
			{1, 1, 0, 0, 0, 1},
			{1, 1, 0, 0, 1, 0},
			{1, 0, 0, 1, 0, 1},
			{0, 1, 1, 0, 1, 0},
		};	
		return G;
	}

	public static boolean cond(int n, int r) {
		return 5 * Math.sqrt(n) <= Math.floor(7.0 * r / 3) + 6;
	}

	public static boolean hipo(int n, int r) {
		return true;
	}

	public static String perf(int n, int r) {
		if (r < n / 2) {
			return "<";
		}
		if (r > n / 2) {
			return ">";
		}
		return "PERF";
	}	

	public static void findRhombo(int r) {
		for (int n = 4; n <= 4000; n += 1) {
			if (!cond(n, r)) {
				break;
			}
			if (n * (n - r - 1) % 4 != 0) {
				continue;
			}
			int a = n * (n - r - 1) / 4;
			if (!hipo(n, r)) {
				continue;
			}
			if (a > 0 && r <= n / 2) {
				debug(n, r, a, perf(n, r));
			}
		}		
	}

	public static void findRhombos() {
		for (int r = 2; r < 121; r++) {
			findRhombo(r);	
		}		
	}

	public static void main(String[] args) {
		// findRhombos();
		int[][] G = generate_6_3();
		debug(G);
		assert isRhomboGraph(G);
		debug(dist(G));
		// for (int i = 4; i < 100; i+= 4) {
		// 	assert isRhomboGraph(generate_perfect(i));
		// 	System.out.println(i);
		// }
		// int[][] G = generate_perfect(12);
		// isRhomboGraph(G);

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

