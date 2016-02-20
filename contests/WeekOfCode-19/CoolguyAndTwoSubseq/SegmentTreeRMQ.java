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

	public static int left(int h, int p) {
		while (h > 0) {
			p = p * 2 + 1;
			h--;
		}
		return p;
	}

	public static int right(int h, int p) {
		while (h > 0) {
			p = p * 2 + 2;
			h--;
		}
		return p;
	}	

	public void calcFreq() {
		final int th = (int) (Math.ceil(Math.log(n) / Math.log(2)));
		final int shift = (1 << th) - 1;
		for (int i = 0; i < n; i++) {
			final int c = i + shift;
			int h = 1;
			int prev = c;
			int p = (c - 1) / 2;
			F[i] = 1;
			while (prev != 0) {
				if (T[c] != T[p]) { break; }
				int a = 1 << (h - 1);
				int b = 1;
				if (prev % 2 == 0) {
					b = c - right(h, p) + 1;
				} else {
					b = c - left(h, p) + 1;
				}
				F[i] += a * b;
				prev = p;
				p = (p - 1) / 2;
				h++;
			}
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

	static int min(int[] A, int l, int r) {
		int m = Integer.MAX_VALUE;
		for (int i = l; i <= r; i++) {
			m = Math.min(m, A[i]);
		}
		return m;
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
		int[] A = new int[] { 860437367, 382264818, 97862808, 511775535, 573080082, 697891811, 593731288, 813762475 };
		SegmentTreeRMQ o = new SegmentTreeRMQ(A);
		debug("o.T", o.T);
		debug("o.A", o.A);
		debug("o.F", o.F);

		for (int l = 0; l < n; l++) {
			for (int r = l; r < n; r++) {
				assert A[o.rmq(l, r)] == min(A, l, r);
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
