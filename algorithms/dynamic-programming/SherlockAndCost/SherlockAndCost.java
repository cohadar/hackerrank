import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class SherlockAndCost {

	static final int MAX_B = 100;

	private final int[] B;
	private final int[][] D;
	
	public SherlockAndCost(int[] B) {
		this.B = B;
		this.D = new int[B.length][MAX_B + 1];
	}

	int solve(int k, int a) {
		int m = Integer.MIN_VALUE;
		for (int a1 = 1; a1 <= B[k - 1]; a1++) {
			int v = Math.abs(a - a1) + D[k - 1][a1];
			m = Math.max(m, v);
		}
		return m;
	}

	int solve() {
		for (int k = 1; k < B.length; k++) {
			for (int a = 1; a <= B[k]; a++) {
				D[k][a] = solve(k, a);		
			}
		}
		int m = Integer.MIN_VALUE;
		for (int a = 1; a <= B[B.length - 1]; a++) {
			m = Math.max(m, D[B.length - 1][a]);
		}
		return m;
	}

	static SherlockAndCost load(Scanner scanner) {
		int n = scanner.nextInt();
		assert 1 <= n && n <= 1e5 : "out of range, n: " + n;
		int[] B = scanArray(scanner, n);
		return new SherlockAndCost(B);
	}	

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = Integer.valueOf(scanner.nextLine());
		assert 1 <= t && t <= 20 : "out of range, t: " + t;
		while (t-->0) {
			SherlockAndCost o = SherlockAndCost.load(scanner);
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
