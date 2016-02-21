import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class CoolguyAndTwoSubseq {

	static class Interval {
		int start;
		final int imin;
		int count;
		Interval(int start, int imin, int count) {
			this.start = start;
			this.imin = imin;
			this.count = count;
		}
		public String toString() {
			return String.format("(s%d, m%d, %d)", start, imin, count);
		}	
	}

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

	public static long njak2(long x) {
		return x * (x + 1) / 2;
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

	public long brute(int b) {
		long ans = 0;
		for (int a = 0; a <= b; a++) {
			for (int c = b + 1; c < A.length; c++) {
				for (int d = c; d < A.length; d++) {
					int il = imin(A, a, b);
					int ir = imin(A, c, d);
					ans += Math.min(A[il], A[ir]);
					debug("+", Math.min(A[il], A[ir]));
					ans %= PRIME;
				}
			}
		}
		debug("brute(b)", ans);
		return ans;
	}

	public long solve(int b, Deque<Interval> L, Deque<Interval> R) {
		long ans = 0;
		for (Interval l : L) {
			for (Interval r : R) {
				long temp = (long)l.count * (long)r.count;
				temp *= Math.min(A[l.imin], A[r.imin]);
				ans += temp;
				ans %= PRIME;
			}
		}
		return ans;
	}

	public Deque<Interval> rightIntervals() {
		Deque<Interval> R = new ArrayDeque<>();
		Deque<Interval> H = new ArrayDeque<>();
		R.add(new Interval(n-1, n-1, 1));
		for (int c = n - 2; c > 0; c--) {
			H.clear();
			for (Interval i : R) {
				if (i.start == c + 1) {
					H.add(i);
				} // break?
			}
			Interval r = new Interval(c, c, 1);
			for (Interval i : H) {
				if (A[r.imin] <= A[i.imin]) {
					r.count += i.count;
				} else {
					R.add(new Interval(c, i.imin, i.count));
				}
			}
			R.add(r);
		}
		return R;
	}

	public Deque<Interval> removeRightIntervals(Deque<Interval> R, int b) {
		Deque<Interval> H = new ArrayDeque<>();
		while (!R.isEmpty()) {
			Interval i = R.pollFirst();
			if (i.start != b) {
				H.add(i);
			};
		}
		return H;
	}

	public Deque<Interval> incLeftIntervals(Deque<Interval> L, int b) {
		Deque<Interval> H = new ArrayDeque<>();
		Interval l = new Interval(b, b, 1);
		for (Interval i : L) {
			i.start = b;
			if (A[l.imin] < A[i.imin]) {
				l.count += i.count;
			} else {
				H.add(i);
			}
		}
		H.add(l);
		return H;
	}

	public long solve() {
		Deque<Interval> L = new ArrayDeque<>();
		Deque<Interval> R = rightIntervals();
		long ans = 0;
		for (int b = 0; b < n-1; b++) {
			L = incLeftIntervals(L, b);
			long temp = solve(b, L, R);
			R = removeRightIntervals(R, b + 1);
			// debug("b", b, "temp", temp);
			ans += temp;
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

	static boolean DEBUG = false;
	
	static void debug(Object...os) {
		if (!DEBUG) { return; }
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}
