import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class Billboards {

	static class Rec implements Comparable<Rec> {
		final int value;
		final int index;
		Rec(int value, int index) {
			this.value = value;
			this.index = index;
		}
		public int compareTo(Rec that) {
			if (this.value == that.value) {
				return -Integer.compare(this.index, that.index);
			} else {
				return Integer.compare(this.value, that.value);
			}
		}
		public String toString() {
			return String.format("(value=%d, index=%d)", value, index);
		}	
	}

	final int[] A;
	final int k;
	final int n;
	final long[] P;
	final long[] C;
	final Rec[] R;
	
	Billboards(int[] A, int k) {
		this.A = A;
		this.n = A.length;
		this.k = k;
		this.P = new long[n];
		this.C = new long[n];
		this.R = new Rec[n];
		initP();
		initC();
		initR();
	}

	void initR() {
		for (int i = 0; i < A.length; i++) {
			R[i] = new Rec(A[i], i);
		}
	}

	void initP() {
		Arrays.fill(P, -1);
		long sum = 0;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < k; i++) {
			if (n - 1 - i < 0) {
				break;
			}
			sum += A[n - 1 - i];
			P[n - 1 - i] = sum;
			min = Math.min(min, A[n - 1 - i]);
		}
		if (n - 1 - k >= 0) {
			min = Math.min(min, A[n - 1 - k]);	
			sum += A[n - 1 - k];
			P[n - 1 - k] = sum - min;
		}
	}

	void initC() {
		long cumul = 0;
		for (int i = 0; i < A.length; i++) {
			cumul += A[i];
			C[i] = cumul;
		}
	}

	long sum(int l, int r) {
		if (r < l) {
			return 0;
		}
		if (l == 0) {
			return C[r];
		} else {
			return C[r] - C[l - 1];
		}
	}

	long rec(int i) {
		if (i >= n) {
			return 0;
		}
		if (P[i] >= 0) {
			return P[i];
		}
		PriorityQueue<Rec> Q = new PriorityQueue<>();
		for (int j = 0; j <= k; j++) {
			Q.add(R[i + j]);
		}
		int imin = i - 1;
		while (!Q.isEmpty()) {
			Rec r = Q.poll();
			if (imin < r.index) {
				imin = r.index;
				P[i] = Math.max(P[i], sum(i, r.index - 1) + rec(r.index + 1));
			} 
			if (imin == i + k) {
				break;
			}
		}
		return P[i];
	}

	long solve() {
		return rec(0);
	}
	
	static Billboards load(Scanner scanner) {
		int n = scanner.nextInt();
		int k = scanner.nextInt();
		assert 1 <= n && n <= 1e5 : "out of range, n: " + n;
		assert 1 <= k && k <= n : "out of range, k: " + k;
		int[] A = scanArray(scanner, n);
		return new Billboards(A, k);
	}	

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Billboards o = Billboards.load(scanner);
		System.out.println(o.solve());
		debug(o.P);
	}

	static int[] scanArray(Scanner scanner, int n) {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = scanner.nextInt();
			assert 0 <= A[i] && A[i] <= 2e9 : "out of range, A[i]: " + A[i];
		}
		return A;
	}

	static boolean DEBUG = false;
	
	static void debug(Object...os) {
		if (!DEBUG) { return; }
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}

