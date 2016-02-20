import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class SegmentTreeRMQ
{
	int n;
	int[] A;
	int[] T; 

	public SegmentTreeRMQ(int A[]) {
		this.n = A.length;
		this.A = A;
		int h = (int) (Math.ceil(Math.log(n) / Math.log(2)));
		int max_size = 2 * (int) Math.pow(2, h) - 1;
		this.T = new int[max_size];
		this.fill(0, n - 1, 0);
	}

	int fill(int l, int r, int i) {
		if (l == r) {
			T[i] = A[l];
			return A[l];
		}
		int m = (l + r) >>> 1;
		int vl = fill(l, m, i * 2 + 1);
		int vr = fill(m + 1, r, i * 2 + 2);
		T[i] = Math.min(vl, vr);
		return T[i];
	}	
 
 	int rmq(int al, int ar, int l, int r, int index)
	{
		if (l <= al && r >= ar) {
			return T[index];
		}
		if (ar < l || al > r) {
			return Integer.MAX_VALUE;
		}
		int mid = (al + ar) >>> 1;
		int vl = rmq(al, mid, l, r, 2 * index + 1);
		int vr = rmq(mid + 1, ar, l, r, 2 * index + 2);
		return Math.min(vl, vr);
	}
 
	int rmq(int n, int l, int r)
	{
		assert l >= 0 : "l >= 0";
		assert r < n : "r < n";
		assert l <= r : "l <= r";
		return rmq(0, n - 1, l, r, 0);
	}

	public static void main(String args[]) 
	{
		int A[] = { 3, 2, 7, 5, 4, 2, 3, 8 };
		int n = A.length;
		SegmentTreeRMQ o = new SegmentTreeRMQ(A);
		debug(o.A);
		debug(o.T);
	}

	static boolean DEBUG = true;
	
	static void debug(Object...os) {
		if (!DEBUG) { return; }
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}
}
