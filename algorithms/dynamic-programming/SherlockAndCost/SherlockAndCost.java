import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class SherlockAndCost {

	static final int MIN = 0;
	static final int MAX = 1;

	private final int[] B;
	private final int[][] D;
	private final int n;
	
	public SherlockAndCost(int[] B) {
		this.B = B;
		this.n = B.length;
		this.D = new int[n][2];
	}

	int value(int k, int a, int ib) {
		int b = (ib == 0) ? 1 : B[k - 1];
		return Math.abs(a - b) + D[k - 1][ib];
	}

	int value(int k, int ia) {
		int a = (ia == 0) ? 1 : B[k];
		return Math.max(value(k, a, MIN), value(k, a, MAX));
	}

	int solve() {
		for (int k = 1; k < n; k++) {
			D[k][MIN] = value(k, MIN);
			D[k][MAX] = value(k, MAX);
		}
		return Math.max(D[n - 1][MIN], D[n - 1][MAX]);
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
