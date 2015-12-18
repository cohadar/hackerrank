import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class CoinOnTheTable {

	static int[] LURDY = { 0, -1, 0, +1 };
	static int[] LURDX = { -1, 0, +1, 0 };

	static int dirToIndex(char direction) {
		return "LURD".indexOf(direction);
	}

	static char indexToDir(int index) {
		return "LURD".charAt(index);
	}

	static int taxiDistance(int y, int x) {
		return y + x - 2;
	}

	static int solve(char[][] C, int k) {
		debug(C);
		int ny = C.length - 2;
		int nx = C[0].length - 2;
		int starY = -1;
		int starX = -1;
		// find star
		for (int y = 1; y <= ny; y++) {
			for (int x = 1; x <= nx; x++) {
				if (C[y][x] == '*') {
					starY = y;
					starX = x;
					break;
				}
			}
		}
		if (taxiDistance(starY, starX) > k) {
			return -1;
		}
		return 0;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String[] _nmk = scanner.nextLine().split(" ");
		int n = Integer.valueOf(_nmk[0]);
		int m = Integer.valueOf(_nmk[1]);
		int k = Integer.valueOf(_nmk[2]);
		char[][] C = scanCharMatrix(scanner, n, m);
		System.out.println(solve(C, k));
	}

	static char[][] scanCharMatrix(Scanner scanner, int ny, int nx) {
		char[][] C = new char[ny + 2][nx + 2];
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < nx + 2; i++) {
			sb.append('|');
		}
		char[] wall = sb.toString().toCharArray();
		C[0] = wall;
		for (int y = 1; y <= ny; y++) {
			C[y] = ('|' + scanner.nextLine().trim() + '|').toCharArray();
			assert C[y].length == nx + 2;
		}
		C[ny + 1] = wall;
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

