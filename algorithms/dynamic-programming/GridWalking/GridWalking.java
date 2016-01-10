import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class GridWalking {

	final int n;
	final int m;
	final int[] X;
	final int[] D;
	
	GridWalking(int n, int m, int[] X, int[] D) {
		this.n = n;
		this.m = m;
		this.X = X;
		this.D = D;
	}

	long solve() {
		return -1;
	}

	static GridWalking load(Scanner scanner) {
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		assert 1 <= n && n <= 10 : "out of range, n: " + n;
		assert 1 <= m && m <= 300 : "out of range, m: " + m;
		int[] X = scanArray(scanner, n, -1);
		int[] D = scanArray(scanner, n, 0);
		return new GridWalking(n, m, X, D);
	}	

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = Integer.valueOf(scanner.nextLine());
		assert 1 <= t && t <= 10 : "out of range, t: " + t;
		while (t-->0) {
			GridWalking o = GridWalking.load(scanner);
			System.out.println(o.solve());
		}
	}

	static int[] scanArray(Scanner scanner, int n, int delta) {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = scanner.nextInt() + delta;
		}
		return A;
	}

}
