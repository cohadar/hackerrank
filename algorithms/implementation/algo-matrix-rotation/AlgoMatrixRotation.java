import java.util.*;
import java.io.*;

public class AlgoMatrixRotation {

	int layer(int y, int x, int nY, int nX) {
		int mx = Math.min(x, Math.abs(nX - 1 - x));
		int my = Math.min(y, Math.abs(nY - 1 - y));
		return Math.min(mx, my);
	}

	int index(int y, int x, int nY, int nX, int l) {
		if (x == l) {
			return y - l;
		}
		if (y == nY - 1 - l) {
			return x - l + nY - 1 - l - l;
		}
		if (x == nX - 1 - l) {
			return nY - 1 + nX - 1 + nY - 1 - y - 5 * l;
		}
		if (y == l) {
			return nY - 1 + nY - 1 + nX - 1 + nX - 1 - x - 7 * l;
		}
		return -1;
	}

	void solve(int[][] M, int r) {
		int nY = M.length;
		int nX = M[0].length;
		nY = 21;
		nX = 14;
		int[][] L = new int[nY][nX];
		int[][] I = new int[nY][nX];
		System.out.printf("nY=%d, nX=%d\n", nY, nX);
		for (int y = 0; y < nY; y++) {
			for (int x = 0; x < nX; x++) {
				L[y][x] = layer(y, x, nY, nX);
				I[y][x] = index(y, x, nY, nX, L[y][x]);
				if (I[y][x] < 0) {
					System.out.printf("(%d,   )", L[y][x]);
				} else {
					System.out.printf("(%d, %02d)", L[y][x], I[y][x]);
				}
			}
			System.out.println();
		}
	}

	static void load(Scanner scanner) {
		int nY = scanner.nextInt();
		int nX = scanner.nextInt();
		int r = scanner.nextInt();
		int[][] M = new int[nY][nX];
		for (int y = 0; y < nY; y++) {
			for (int x = 0; x < nX; x++) {
				M[y][x] = scanner.nextInt();
			}
		}
		new AlgoMatrixRotation().solve(M, r);
	}

	public static void main(String[] args) throws Exception {
		Scanner scanner;
		if (args.length == 1 && "COHADAR".equals(args[0])) {
			scanner = new Scanner(new File("algo-matrix-rotation.in"));
		} else {
			scanner = new Scanner(System.in);
		}
		load(scanner);
	}
}
