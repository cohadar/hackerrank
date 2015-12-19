import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class CavityMap {

	static boolean isCavity(char[][] M, int y, int x) {
		if (M[y-1][x] >= M[y][x]) return false;
		if (M[y+1][x] >= M[y][x]) return false;
		if (M[y][x-1] >= M[y][x]) return false;
		if (M[y][x+1] >= M[y][x]) return false;
		return true;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = Integer.valueOf(scanner.nextLine());
		char[][] M = new char[n][n];
		boolean[][] C = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			M[i] = scanner.nextLine().toCharArray();
			assert M[i].length == n;
		}
		for (int y = 1; y <= n - 2; y++) {
			for (int x = 1; x <= n - 2; x++) {
				C[y][x] = isCavity(M, y, x);
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int y = 0; y < n; y++) {
			for (int x = 0; x < n; x++) {
				if (C[y][x]) {
					sb.append('X');
				} else {
					sb.append(M[y][x]);
				}
			}
			sb.append("\n");
		}		
		System.out.println(sb);
	}

}

