import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class FloydCityOfBlindingLights {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		assert 2 <= n && n <= 400 : "out of range, n: " + n;
		int m = scanner.nextInt();
		assert 1 <= m && m <= n * (n - 1) / 2 : "out of range, m: " + m;
		int[][] G = scanGraph(scanner, n, m);
		int q = scanner.nextInt();
		assert 1 <= q && q <= 10e5 : "out of range, q: " + q;
		for (int i = 0; i < q; i++) {
			int a = scanner.nextInt();
			int b = scanner.nextInt();
			System.out.println(-1);
		}
	}

	static int[][] scanGraph(Scanner scanner, int n, int m) {
		int[][] G = new int[n][n];
		for (int i = 0; i < m; i++) {
			int a = scanner.nextInt() - 1;
			int b = scanner.nextInt() - 1;
			int r = scanner.nextInt();
			assert 1 <= r && r <= 350 : "out of range, r: " + r;
			G[a][b] = r;
		}	
		return G;
	}
	
}

