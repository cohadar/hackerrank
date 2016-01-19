import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class ASuperHero {

	final int n;
	final int m;
	final int[][] P;
	final int[][] B;
	
	ASuperHero(int[][] P, int[][] B) {
		this.n = P.length;
		this.m = P[0].length;
		this.P = P;
		this.B = B;
	}

	int solve() {
		int[][] D = new int[n][m];
		for (int j = 0; j < m; j++) {
			D[0][j] = P[0][j];
		}
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < m; j++) {
				D[i][j] = Integer.MAX_VALUE;
				for (int k = 0; k < m; k++) {
					int price = Math.max(P[i][j] - B[i - 1][k], 0);
					D[i][j] = Math.min(D[i][j], D[i - 1][k] + price);
				}
			}
		}
		int min = Integer.MAX_VALUE;
		for (int j = 0; j < m; j++) {
			min = Math.min(min, D[n - 1][j]);
		}
		return min;
	}
	
	static ASuperHero load(Scanner scanner) {
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		assert 1 <= n && n <= 100 : "out of range, n: " + n;
		assert 1 <= m && m <= 5e5 : "out of range, m: " + m;
		int[][] P = new int[n][m];
		int[][] B = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				P[i][j] = scanner.nextInt();
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				B[i][j] = scanner.nextInt();
			}
		}
		return new ASuperHero(P, B);
	}	

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = Integer.valueOf(scanner.nextLine());
		assert 1 <= t && t <= 100 : "out of range, t: " + t;
		while (t-->0) {
			ASuperHero o = ASuperHero.load(scanner);
			System.out.println(o.solve());
		}
	}

}
