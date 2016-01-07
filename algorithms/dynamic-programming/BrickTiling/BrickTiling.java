import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class BrickTiling {

	static class Pair {
		final int x;
		final int y;
		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
		public String toString() {
			return String.format("(x=%d, y=%d)", x, y);
		}	
	}

	static class Brick {
		private final Pair[] cells;
		
		public Brick(Pair[] cells) {
			this.cells = cells;
		}

		boolean canTile(char[][] C, int y, int x) {
			for (Pair p : cells) {
				if (!inGrid(y + p.y, x + p.x, C.length, C[0].length)) {
					return false;
				}
				if (C[y + p.y][x + p.x] != '.') {
					return false;
				};
			}
			return true;
		}		

		char[][] tile(char[][] G, int y, int x) {
			char[][] C = copyGrid(G);
			for (Pair p : cells) {
				C[y + p.y][x + p.x] = 'X';
			}
			return C;
		}
	}

	final int n;
	final int m;
	final char[][] G;
	final Brick[] B;
	
	public BrickTiling(int n, int m, char[][] G) {
		this.n = n;
		this.m = m;
		this.G = G;
		this.B = new Brick[8];
		B[0] = new Brick(new Pair[] {
			new Pair( 0, 0),
			new Pair( 1, 0),
			new Pair( 2, 0), 
			new Pair( 2, 1),
		});
		B[1] = new Brick(new Pair[] {
			new Pair( 0, 1),
			new Pair( 1, 1),
			new Pair( 2, 1),    
			new Pair( 2, 0),
		});
		B[2] = new Brick(new Pair[] {
			new Pair( 0, 0),
			new Pair( 0, 1),
			new Pair( 1, 1),    
			new Pair( 2, 1),
		});
		B[3] = new Brick(new Pair[] {
			new Pair( 0, 1),
			new Pair( 0, 0),
			new Pair( 1, 0),    
			new Pair( 2, 0),
		});
		B[4] = new Brick(new Pair[] {
			new Pair( 0, 0),
			new Pair( 1, 0),
			new Pair( 1, 1),    
			new Pair( 1, 2),
		});
		B[5] = new Brick(new Pair[] {
			new Pair( 0, 2),
			new Pair( 0, 1),
			new Pair( 0, 0),    
			new Pair( 1, 0),
		});		
		B[6] = new Brick(new Pair[] {
			new Pair( 0, 2),
			new Pair( 1, 2),
			new Pair( 1, 1),    
			new Pair( 1, 0),
		});		
		B[7] = new Brick(new Pair[] {
			new Pair( 0, 0),
			new Pair( 0, 1),
			new Pair( 0, 2),    
			new Pair( 1, 2),
		});		
	}

	static boolean inGrid(int y, int x, int m, int n) {
		if (y < 0 || y >= m) {
			return false;
		}
		if (x < 0 || x >= n) {
			return false;
		}
		return true;
	}

	static char[][] copyGrid(char[][] G) {
		char[][] C = new char[G.length][G[0].length];
		for (int i = 0; i < G.length; i++) {
			C[i] = Arrays.copyOf(G[i], G[i].length);
		}
		return C;
	}

	int countEmpty(char[][] G) {
		int empty = 0;
		for (int y = 0; y < n; y++) {
			for (int x = 0; x < m; x++) {
				if (G[y][x] == '.') {
					empty++;
				}
			}
		}
		return empty;
	}

	long count = 0;

	void backtrack(char[][] C, int empty) {
		if (empty == 0) {
			count++;
			return;
		}
		for (int y = 0; y < n; y++) {
			for (int x = 0; x < m; x++) {
				for (int b = 0; b < B.length; b++) {
					if (B[b].canTile(C, y, x)) {
						backtrack(B[b].tile(copyGrid(C), y, x), empty - 4);
					}
				}
			}
		}
	}

	long solve() {
		int empty = countEmpty(copyGrid(G));
		if (empty % 4 != 0) {
			return 0;
		}
		backtrack(G, empty);
		return count;
	}

	static BrickTiling load(Scanner scanner) {
		String[] _nm = scanner.nextLine().split(" ");
		int n = Integer.valueOf(_nm[0]);
		int m = Integer.valueOf(_nm[1]);
		assert 1 <= n && n <= 20 : "out of range, n: " + n;
		assert 1 <= m && m <= 8 : "out of range, m: " + m;
		char[][] G = scanCharMatrix(scanner, n, m);
		return new BrickTiling(n, m, G);
	}	

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = Integer.valueOf(scanner.nextLine());
		assert 1 <= t && t <= 50 : "out of range, t: " + t;
		while (t-->0) {
			BrickTiling o = BrickTiling.load(scanner);
			System.out.println(o.solve());
		}
	}

	static char[][] scanCharMatrix(Scanner scanner, int ny, int nx) {
		char[][] C = new char[ny][nx];
		for (int y = 0; y < ny; y++) {
			C[y] = scanner.nextLine().trim().toCharArray();
			assert C[y].length == nx;
		}
		return C;
	}

	static boolean DEBUG = true;
	
	static void debug(Object...os) {
		if (!DEBUG) { return; }
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

	static void debug(char[][] M) {
		if (!DEBUG) { return; }
		for (int y = 0; y < M.length; y++) {
			for (int x = 0; x < M[0].length; x++) {
				System.err.print(M[y][x]);
			}
			System.err.println();
		}
		System.err.println();
	}

}
