import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class StockMaximize {

	static final int INF = Integer.MAX_VALUE / 2;

	final int n;
	final int[] A;
	final long[][] P;
	
	StockMaximize(int[] A) {
		this.n = A.length;
		this.A = A;
		this.P = new long[2][n + 1];
		for (int s = 0; s <= n; s++) {
			P[0][s] = A[0] * s;
		}
	}

	long buyOrNone(int i, int s) {
		int k = i % 2;
		long ret = P[1 - k][s];
		if (s < n) {
			long temp = P[1 - k][s + 1] - A[i];
			if (temp > ret) {
				ret = temp;
			}
		}
		return ret;
	}

	long sell(long ret, int i, int s) {
		int k = i % 2;
		for (int a = 0; a <= Math.min(1, s); a++) {
			long temp = P[1 - k][s - a] + a * A[i];
			if (temp > ret) {
				ret = temp;
			}			
		}
		return ret;
	}

	long solve() {
		for (int i = 1; i < n; i++) {
			int k = i % 2;
			for (int s = 0; s <= n; s++) {
				P[k][s] = buyOrNone(i, s);
				P[k][s] = sell(P[k][s], i, s);
			}
		}
		return P[(n - 1) % 2][0];
	}

	static StockMaximize load(Scanner scanner) {
		int n = scanner.nextInt();
		assert 1 <= n && n <= 5e4 : "out of range, n: " + n;
		int[] A = scanArray(scanner, n);
		reverse(A);
		return new StockMaximize(A);
	}	

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = Integer.valueOf(scanner.nextLine());
		assert 1 <= t && t <= 10 : "out of range, t: " + t;
		while (t-->0) {
			StockMaximize o = StockMaximize.load(scanner);
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

	static void swap(int[] A, int i, int j) {
		int t = A[i];
		A[i] = A[j];
		A[j] = t;
	}

	static void reverse(int[] A) {
		for (int i = 0; i < A.length / 2; i++) {
			swap(A, i, A.length - i - 1);
		}
	}

}
