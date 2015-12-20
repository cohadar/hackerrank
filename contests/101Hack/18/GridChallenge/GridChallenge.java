import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class GridChallenge {

	static boolean solve(char[][] G) {
		for (int i = 0; i < G.length; i++) {
			Arrays.sort(G[i]);
		}
		for (int x = 0; x < G.length; x++) {
			for (int y = 1; y < G.length; y++) {
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
			for (int i = 0; i < n; i++) {
				G[i] = scanner.nextLine().toCharArray();
				assert G[i].length == n;
			}
			System.out.println((solve(G)) ? "YES" : "NO");
		}
	}

}

