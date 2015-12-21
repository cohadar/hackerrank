import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class MrKmarsh {

	static boolean[][][] wallX(char[][] C, int m, int n) {
		boolean[][][] WX = new boolean[m][n][n];
		for (int y = 0; y < m; y++) {
			for (int px = 0; px < n - 1; px++) {
				WX[y][px][px] = (C[y][px] == '.');
				for (int qx = px + 1; qx < n; qx++) {
					WX[y][px][qx] = (WX[y][px][qx - 1] && (C[y][qx] == '.')); 
					if (!WX[y][px][qx]) {
						break;
					}
				}
			}
		}
		return WX;
	}

	static boolean[][][] wallY(char[][] C, int m, int n) {
		boolean[][][] WY = new boolean[n][m][m];
		for (int x = 0; x < n; x++) {
			for (int py = 0; py < m - 1; py++) {
				WY[x][py][py] = (C[py][x] == '.');
				for (int qy = py + 1; qy < m; qy++) {
					WY[x][py][qy] = (WY[x][py][qy - 1] && (C[qy][x] == '.')); 
					if (!WY[x][py][qy]) {
						break;
					}
				}
			}
		}
		return WY;
	}	

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String[] _mn = scanner.nextLine().split(" ");
		int m = Integer.valueOf(_mn[0]);
		int n = Integer.valueOf(_mn[1]);
		char[][] C = scanCharMatrix(scanner, m, n);
		boolean[][][] WX = wallX(C, m, n);
		boolean[][][] WY = wallY(C, m, n);
		int max = 0;

		for (int px = 0; px < n - 1; px++) {
			for (int py = 0; py < m - 1; py++) {
				for (int qy = py + 1; qy < m; qy++) {
					if (!WY[px][py][qy]) {
						break;
					}
					for (int qx = px + 1; qx < n; qx++) {
						if (!WX[py][px][qx]) {
							break;
						}
						if (!WX[qy][px][qx]) {
							break;
						}
						if (WY[qx][py][qy]) {
							int area = 2 * (qy - py) + 2 * (qx - px);
							max = Math.max(max, area);
						}
					}
				}
			}
		}

		if (max == 0) {
			System.out.println("impossible");
		} else {
			System.out.println(max);
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

}

