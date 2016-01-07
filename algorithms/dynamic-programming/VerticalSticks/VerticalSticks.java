import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class VerticalSticks {

	private final int n;
	private final int[] A;
	private final int[] LT;
	private final int[] GE;
	
	public VerticalSticks(int[] A) {
		this.n = A.length;
		this.A = A;
		this.LT = new int[n];
		this.GE = new int[n];
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A.length; j++) {
				if (i == j) { continue; };
				if (A[i] <= A[j]) {
					GE[i]++;
				} else {
					LT[i]++;
				}
			}
		}
	}

	static double fact(int n) {
		assert n >= 0;
		if (n == 0) {
			return 1;
		}
		return n * fact(n - 1);
	}

	double solve(int aa, int lt, int ge) {
		double res = 0.0;
		for (int m = 0; m <= lt; m++) {
			res += (m + 1) * fact(lt) / fact(lt - m) * fact(n - m - 1);
			res += (m + 1) * fact(lt) / fact(lt - m) * ge * fact(n - m - 1);
		}
		debug(aa, lt, ge, res);
		return res;
	}

	double solve() {
		double res = 0.0;
		for (int a = 0; a < A.length; a++) {
			double resA = solve(A[a], LT[a], GE[a]);
			res += resA;
		}
		return res / fact(n);
	}

	static VerticalSticks load(Scanner scanner) {
		int n = scanner.nextInt();
		assert 1 <= n && n <= 50 : "out of range, n: " + n;
		int[] A = scanArray(scanner, n);
		return new VerticalSticks(A);
	}	

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = Integer.valueOf(scanner.nextLine());
		assert 1 <= t && t <= 100 : "out of range, t: " + t;
		while (t-->0) {
			VerticalSticks o = VerticalSticks.load(scanner);
			System.out.printf("%.2f\n", o.solve());
		}
	}

	static int[] scanArray(Scanner scanner, int n) {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = scanner.nextInt();
		}
		return A;
	}	

	static boolean DEBUG = false;
	
	static void debug(Object...os) {
		if (!DEBUG) { return; }
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}
