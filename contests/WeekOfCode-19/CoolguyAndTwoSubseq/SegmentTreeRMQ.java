import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class SegmentTreeRMQ
{
	int n;
	int[] A;
	int[] T; // contains indexes of min elements
	int[] F; // contains frequencies of min elements

	public SegmentTreeRMQ(int A[]) {
		this.n = A.length;
		this.A = A;
		this.F = new int[n];
		int h = (int) (Math.ceil(Math.log(n) / Math.log(2)));
		int max_size = 2 * (int) Math.pow(2, h) - 1;
		this.T = new int[max_size];
		this.fill(0, n - 1, 0);
		this.calcFreq();
	}

	int fill(int l, int r, int i) {
		if (l == r) {
			T[i] = l;
			return l;
		}
		int m = (l + r) >>> 1;
		int il = fill(l, m, i * 2 + 1);
		int ir = fill(m + 1, r, i * 2 + 2);
		T[i] = (A[il] <= A[ir]) ? il : ir;
		return T[i];
	}	

	public static int left(int p, int shift) {
		while (p < shift) {
			p = p * 2 + 1;
		}
		return p;
	}

	public static int right(int p, int shift) {
		while (p < shift) {
			p = p * 2 + 2;
		}
		return p;
	}	

	public void calcFreq(int shift, int i) {
		int l = i;
		int r = i;
		while (l > 0 && A[l-1] > A[i]) { l--; };
		while (r < n-1 && A[r+1] >= A[i]) { r++; };
		F[i] = (i - l + 1) * (r - i + 1);
	}

	public void calcFreq() {
		final int th = (int) (Math.ceil(Math.log(n) / Math.log(2)));
		final int shift = (1 << th) - 1;
		for (int i = 0; i < n; i++) {
			calcFreq(shift, i);
		}
	}
 
 	int rmq(int al, int ar, int l, int r, int index)
	{
		if (l <= al && r >= ar) {
			return T[index];
		}
		if (ar < l || al > r) {
			return -1;
		}
		int mid = (al + ar) >>> 1;
		int il = rmq(al, mid, l, r, 2 * index + 1);
		int ir = rmq(mid + 1, ar, l, r, 2 * index + 2);
		int vl = (il < 0) ? Integer.MAX_VALUE : A[il];
		int vr = (ir < 0) ? Integer.MAX_VALUE : A[ir];
		return (vl <= vr) ? il : ir;
	}
 
	int rmq(int l, int r)
	{
		assert l >= 0 : "l >= 0";
		assert r < n : "r < n";
		assert l <= r : "l <= r";
		return rmq(0, n - 1, l, r, 0);
	}

	int size() {
		return n;
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

	public static void main(String args[]) 
	{
		Random random = new Random();
		// int n = 1 + random.nextInt(10);
		int n = 8;
		// int A[] = new int[n];
		// for (int i = 0; i < n; i++) {
		// 	A[i] = 1 + random.nextInt(1000_000_000);
		// }		
		int[] A = new int[] { 86, 38, 97, 51, 57, 69, 59, 81 };
		SegmentTreeRMQ o = new SegmentTreeRMQ(A);
		debug("o.T", o.T);
		debug("o.A", o.A);
		debug("o.F", o.F);

		for (int l = 0; l < n; l++) {
			for (int r = l; r < n; r++) {
				assert A[o.rmq(l, r)] == A[imin(A, l, r)];
			}
		}

		int[] F = new int[n];
		for (int l = 0; l < n; l++) {
			for (int r = l; r < n; r++) {
				F[o.rmq(l, r)]++;
			}
		}

		debug("  F", F);
		for (int i = 0; i < n; i++) {
			assert o.F[i] == F[i] : "o.F[i] == F[i]";
		}
	}

	static boolean DEBUG = true;
	
	static void debug(Object...os) {
		if (!DEBUG) { return; }
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}
}
