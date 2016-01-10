import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class StockMaximize {

	final int n;
	final int[] P;
	
	StockMaximize(int[] P) {
		this.n = P.length;
		this.P = P;
	}

	int max(int[] A, int start) {
		int im = start;
		for (int i = start + 1; i < A.length; i++) {
			if (A[i] > A[im]) {
				im = i;
			}
		}
		return im;
	}

	long profit(int start) {
		if (start >= n) {
			return 0;
		}
		int im = max(P, start);
		long cost = 0;
		int shares = 0;
		for (int i = start; i < im; i++) {
			cost += P[i];
			shares++;
		}
		return -cost + (long)P[im] * (long)shares + profit(im + 1);
	}

	long solve() {
		return profit(0);
	}

	static StockMaximize load(Scanner scanner) {
		int n = scanner.nextInt();
		assert 1 <= n && n <= 5e4 : "out of range, n: " + n;
		int[] P = scanArray(scanner, n);
		return new StockMaximize(P);
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
}
