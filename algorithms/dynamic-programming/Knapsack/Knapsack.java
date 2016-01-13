import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class Knapsack {

	final int[] A;
	final int n;
	final int k;
	final int[][] D;
	
	Knapsack(int[] A, int k) {
		this.A = A;
		this.n = A.length;
		this.k = k;
		this.D = new int[1 + n][1 + k];
	}

	int solve() {
		for (int in = 1; in <= n; in++) {
			for (int s = 0; s <= k; s++) {
				D[in][s] = D[in - 1][s];
				if (A[in - 1] <= s) {
					int temp = D[in][s - A[in - 1]] + A[in - 1];
					if (D[in][s] < temp) {
						D[in][s] = temp;
					}
				}
			}
		}
		return D[n][k];
	}

	static Knapsack load(Scanner scanner) {
		int n = scanner.nextInt();
		int k = scanner.nextInt();
		assert 1 <= n && n <= 2000 : "out of range, n: " + n;
		assert 1 <= k && k <= 2000 : "out of range, k: " + k;
		int[] A = scanArray(scanner, n);
		return new Knapsack(A, k);
	}	

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = Integer.valueOf(scanner.nextLine());
		assert 1 <= t && t <= 10 : "out of range, t: " + t;
		while (t-->0) {
			Knapsack o = Knapsack.load(scanner);
			System.out.println(o.solve());
		}
	}

	static int[] scanArray(Scanner scanner, int n) {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = scanner.nextInt();
		}
		return A;
	}

}
