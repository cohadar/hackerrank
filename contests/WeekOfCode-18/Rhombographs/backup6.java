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

	static boolean isConnected(int[][] G) {
		int n = G.length;
		boolean[] V = new boolean[n];
		Deque<Integer> Q = new ArrayDeque<>();
		V[0] = true;
		Q.add(0);
		int vc = 1;
		while (!Q.isEmpty()) {
			int a = Q.remove();
			for (int b = 0; b < n; b++) {
				if (!V[b] && G[a][b] == 1) {
					V[b] = true;
					Q.add(b);
					vc++;
				}
			}
		}
		return vc == n;
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
					}
				}
			}
		}
		debug("count", count);
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

	static int[][] generate_old(int n, int r) {
		assert r < n;
		int[][] G = new int[n][n];
		for (int y = 0; y < n; y++) {
			firstCombination(G[y], y, r);
		}
		if (isUndirected(G) && isConnected(G) && isRhomboGraph(G)) {
			return G;
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
			if (isUndirected(G) && isConnected(G) && isRhomboGraph(G)) {
				return G;
			}
		}
		return null;
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

	static int[][] generate_crazy(int n, int r) {
		int[][] G = new int[n][n];
		int m = n * r / 2;
		int z = n * n - 2 * m;
		int x = z / n;

		if ((n * r) % 2 != 0) {
			return null;
		}
		if (z % n != 0) {
			return null;
		}

		// z = axx + b2x(x-1)
		for (int b = 0; b < n; b++) {
			int axx = z - b * 2 * x * (x - 1);
			if (axx < 0) {
				break;
			}
			if (axx % (x * x) == 0) {
				int a = axx / (x * x);
				fillZeros(G, a, b, x);
				// debug("good", n, r, z);
				// debug(G);
				return G;
			}
		}
		// debug("bad", n, r, z);
		return null;
	}	

	public static boolean diff(int[][] G, int[][] H) {
		int n = G.length;
		for (int a = 0; a < n; a++) {
			for (int b = 0; b < n; b++) {
				if (G[a][b] != H[a][b]) {
					return false;
				}
			}
		}
		return true;
	}

	public static void test_crazy() {
		for (int n = 2; n < 10; n++) {
			for (int r = n - 1; r > 1; r--) {
				int[][] G = generate_crazy(n, r);
				if (G != null && isConnected(G) && isRhomboGraph(G)) {
					debug(G);
				} 
			}
		}		
	}

	public static void test() {
		for (int n = 2; n < 10; n++) {
			for (int r = n - 1; r > 1; r--) {
				int[][] G = generate_old(n, r);
				int[][] H = generate_idiot(n, r);
				if (H != null) {
					debug(H);
					assert isUndirected(H) : "idiot fail 1";
					assert isConnected(H) : "idiot fail 2";
					assert isRhomboGraph(H) : "idiot fail 3";
				}
				if (H == null && G == null) {
					continue;
				}
				if (G == null && H != null) {
					debug(H);
					assert false : "G null";
				}
				if (H == null && G != null) {
					debug(G);
					assert false : "H null";
				}
				if (diff(G, H)) {
					debug(G);
					debug(H);
					assert false : "diff";
				}
				if (G != null) {
					if (isConnected(G) == false) {
						assert false : "unc";
						//debug("unconnected", n, r, z);	
						//debug(G);
					} else if (isRhomboGraph(G) == false) {
						debug(G);
						assert false : "rho";
						//debug("not rhombograph", n, r, z);	
					} else {
						assert isUndirected(G) : "not undirected"; 
						if (n > 4) {
							assert n == r + 1;
						}
						debug("good", n, r);	
						debug(G);
					}
				}
			}
		}
	}

	public static int[][] generate_idiot(int n, int r) {
		int[][] G = new int[n][n];
		if (r == 2 && n == 4) {
			for (int i = 0; i < n - 1; i++) {
				G[i][i+1] = 1;
				G[i+1][i] = 1;
			}
			G[n - 1][0] = 1;
			G[0][n - 1] = 1;
			return G;
		}
		if (n == r + 1) {
			for (int i = 0; i < n; i++) {
				Arrays.fill(G[i], 1);
				G[i][i] = 0;
			}			
			return G;
		}		
		return null;
	}

	public static void main(String[] args) {
		test_crazy();
		Scanner scanner = new Scanner(System.in);
		int in = scanner.nextInt();
		int im = scanner.nextInt();
		int r = scanner.nextInt();
		for (int n = im; n >= in; n--) {
			int[][] G = generate_idiot(n, r);
			if (G != null) {
				print(G, n);
				return;
			}
		}
		System.out.println("-1 -1");
	}

	public static void print(int[][] G, int n) {
		StringBuilder sb = new StringBuilder();
		int m = 0;
		for (int a = 0; a < n - 1; a++) {
			for (int b = a + 1; b < n; b++) {
				if (G[a][b] == 1) {
					sb.append(a + 1);
					sb.append(' ');
					sb.append(b + 1);
					sb.append('\n');
					m++;
				}
			}
		}
		System.out.printf("%d %d\n", n, m);
		System.out.print(sb);		
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

