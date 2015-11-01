import java.util.*;
import java.io.*;

public class GridChallenge {

	static boolean canRearange(char[][] G) {
		int n = G.length;
		for (int y = 0; y < n; y++) {
			Arrays.sort(G[y]);
		}
		for (int x = 0; x < n; x++) {
			for (int y = 1; y < n; y++) {
				if (G[y - 1][x] > G[y][x]) {
					return false;
				}
			}
		}
		return true;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = Integer.valueOf(scanner.nextLine());
		while (t-->0) {
			int n = Integer.valueOf(scanner.nextLine());
			char[][] G = new char[n][n];
			for (int y = 0; y < n; y++) {
				G[y] = scanner.nextLine().toCharArray();
				assert G[y].length == n;
			}
			System.out.println((canRearange(G)) ? "YES" : "NO");
		}
	}

}

