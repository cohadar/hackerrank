import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class Rhombographs {

	public static int[][] generate_dense(int n, int r) {
		int[][] G = new int[n][n];
		for (int i = 0; i < n; i++) {
			Arrays.fill(G[i], 1);
			G[i][i] = 0;
		}			
		return G;
	}	

	public static int[][] generate_spec(int n, int r) {
		int[][] G = new int[n][n];
		for (int i = 0; i < n - 1; i++) {
			G[i][i+1] = 1;
			G[i+1][i] = 1;
		}
		G[n - 1][0] = 1;
		G[0][n - 1] = 1;
		return G;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int in = scanner.nextInt();
		int im = scanner.nextInt();
		int r = scanner.nextInt();
		for (int n = im; n >= in; n--) {
			if (n == r + 1) {
				int[][] G = generate_dense(n, r);
				print(G, n);
				return;
			} else if (r == 2 && n == 4) {
				int[][] G = generate_spec(n, r);
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

}

