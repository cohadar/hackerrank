import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class Rhombographs {

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

	public static void test() {
		for (int n = 2; n < 20; n++) {
			for (int r = n - 1; r > 0; r--) {
				if ((n * r) % 2 != 0) {
					continue;
				}
				int m = n * r / 2;
				int z = n * n - 2 * m;
				if (z % n != 0) {
					continue;
				}
				if (r < z / n) {
					continue;
				}
				int[][] G = generate(n, r);
			}
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int in = scanner.nextInt();
		int im = scanner.nextInt();
		int r = scanner.nextInt();
		if (in == 1 && r == 0) {
			System.out.println("1 0");
			return;
		}
		for (int n = im; n >= in; n--) {
			if ((n * r) % 2 != 0) {
				continue;
			}
			int m = n * r / 2;
			int z = n * n - 2 * m;
			if (z == 0 || z % n != 0 || r < z / n) {
				continue;
			}
			int[][] G = generate(n, r);
			if (G != null) {
				StringBuilder sb = new StringBuilder();
				sb.append(n);
				sb.append(' ');
				sb.append(m);
				sb.append('\n');
				// System.out.printf("%d %d\n", n, m);
				for (int a = 0; a < n - 1; a++) {
					for (int b = a + 1; b < n; b++) {
						if (G[a][b] == 1) {
							sb.append(a + 1);
							sb.append(' ');
							sb.append(b + 1);
							sb.append('\n');
							// System.out.printf("%d %d\n", a + 1, b + 1);
						}
					}
				}
				System.out.print(sb);
				return;
			}
		}
		System.out.println("-1 -1");
	}

	static boolean DEBUG = false;
	
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

