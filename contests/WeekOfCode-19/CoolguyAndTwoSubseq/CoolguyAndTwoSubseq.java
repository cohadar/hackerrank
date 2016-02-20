import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class CoolguyAndTwoSubseq {

	static final int PRIME = (int)1e9 + 7;

	final int n;
	final int[] A;
	
	public CoolguyAndTwoSubseq(int n, int[] A) {
		this.n = n;
		this.A = A;
	}

	public int brute() {
		int ans = 0;
		for (int a = 0; a < n; a++) {
			int l = A[a];
			for (int b = a; b < n; b++) {
				l = Math.min(l, A[b]);
				for (int c = b+1; c < n; c++) {
					int r = A[c];
					for (int d = c; d < n; d++) {
						r = Math.min(r, A[d]);
						ans += Math.min(l, r);
						ans %= PRIME;
					}
				}
			}
		}
		return ans;
	}

	public long solve(SegmentTreeRMQ L, SegmentTreeRMQ R) {
		long ans = 0;
		for (int l = 0; l < L.size(); l++) {
			for (int d = 0; d < R.size(); d++) {
				int r = R.rmq(0, d);
				if (L.A[l] <= R.A[r]) {
					ans += (long)L.F[l] * (long)L.A[l];
				} else {
					ans += (long)R.F[r] * (long)R.A[r];
				}
				ans %= PRIME;
			}	
		}
		return ans;
	}

	public long solve() {
		long ans = 0;
		for (int b = 0; b < n-1; b++) {
			int[] L = Arrays.copyOfRange(A, 0, b + 1);
			int[] R = Arrays.copyOfRange(A, b + 1, n);
			ans += solve(new SegmentTreeRMQ(L), new SegmentTreeRMQ(R));
			ans %= PRIME;
		}
		return ans;
	}

	public static CoolguyAndTwoSubseq load(Scanner scanner) {
		int n = scanner.nextInt();
		assert 1 <= n && n <= 2e5 : "out of range, n: " + n;
		int[] A = scanArray(scanner, n);
		return new CoolguyAndTwoSubseq(n, A);
	} 

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		CoolguyAndTwoSubseq o = CoolguyAndTwoSubseq.load(scanner);
		System.out.println(o.solve());
	}

	static int[] scanArray(Scanner scanner, int n) {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = scanner.nextInt();
		}
		return A;
	}

	static boolean DEBUG = true;
	
	static void debug(Object...os) {
		if (!DEBUG) { return; }
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}
