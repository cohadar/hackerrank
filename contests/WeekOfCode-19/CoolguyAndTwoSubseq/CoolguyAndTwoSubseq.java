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

	public static int imin(int[] A, int l, int r) {
		int im = l;
		for (int i = l; i <= r; i++) {
			if (A[im] > A[i]) {
				im = i;
			}
		}
		return im;
	}

	public static int[] freq(int[] L) {
		int[] LF = new int[L.length];
		for (int i = 0; i < L.length; i++) {
			int l = i;
			while (l > 0 && L[l-1] > L[i]) { l--; }
			int r = i;
			while (r < L.length-1 && L[r+1] >= L[i]) { r++; }
			LF[i] = (i - l + 1) * (r - i + 1);
		}
		return LF;
	}

	public static int[] rightFreq(int[] R) {
		int[] RF = new int[R.length];
		int c = 0;
		int im = c;
		for (int d = 0; d < R.length; d++) {
			if (R[im] > R[d]) {
				im = d;
			}
			RF[im]++;
		}
		return RF;
	}

	public long solve(int[] L, int[] R) {
		long ans = 0;
		int[] LF = freq(L);
		int[] RF = rightFreq(R);
		for (int l = 0; l < L.length; l++) {
			for (int r = 0; r < R.length; r++) {
				if (L[l] <= R[r]) {
					ans += (long)L[l] * LF[l] * 1;
				} else {
					ans += (long)R[r] * RF[r] * LF[l];
				}
				ans %= PRIME;
			}
		}
		debug(L, R, "ans=", ans);
		debug(LF, RF);
		return ans;
	}

	public long solve() {
		long ans = 0;
		for (int b = 0; b < n-1; b++) {
			int[] L = Arrays.copyOfRange(A, 0, b + 1);
			int[] R = Arrays.copyOfRange(A, b + 1, n);
			// ans += solve(new SegmentTreeRMQ(L), new SegmentTreeRMQ(R));
			ans += solve(L, R);
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
