import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class CoolguyAndTwoSubseq {

	static final int PRIME = (int)1e9 + 7;

	final int n;
	final int[] A;
	final int[] RMIN;
	
	public CoolguyAndTwoSubseq(int n, int[] A) {
		this.n = n;
		this.A = A;
		this.RMIN = new int[n];
		int r = A[n-1];
		for (int i = n - 1; i >= 0; i--) {
			r = Math.min(r, A[i]);
			RMIN[i] = r;
		}
	}
	
	public int solve2() {
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

	public static void inc(Map<Integer, Integer> X, int key) {
		Integer val = X.get(key);
		if (val == null) {
			val = 0;
		}
		X.put(key, val + 1);
	}

	public TreeMap<Integer, Integer> right(int b) {
		TreeMap<Integer, Integer> R = new TreeMap<>();
		for (int c = b+1; c < n; c++) {
			int r = A[c];
			for (int d = c; d < n; d++) {
				r = Math.min(r, A[d]);
				inc(R, r);
			}
		}
		return R;
	}

	public int mul(int key, int f1, int f2) {
		return (int) (((long)key * (long)f1 * (long)f2) % PRIME);
	}

	public int mul(Map.Entry<Integer, Integer> el, Map.Entry<Integer, Integer> er) {
		if (el.getKey() < er.getKey()) {
			return mul(el.getKey(), el.getValue(), er.getValue());
		} else {
			return mul(er.getKey(), el.getValue(), er.getValue());
		}
	}

	public int mul(Map<Integer, Integer> L, Map<Integer, Integer> R) {
		int ans = 0;
		for (Map.Entry<Integer, Integer> el : L.entrySet()) {
			for (Map.Entry<Integer, Integer> er : R.entrySet()) {
				ans += mul(el, er);
				ans %= PRIME;
			}
		}
		return ans;
	}

	public void updateLeft(TreeMap<Integer, Integer> L, int l) {
		int nl = 1;
		while (L.isEmpty() == false && L.lastKey() >= l) {
			Map.Entry<Integer, Integer> e = L.pollLastEntry();
			nl += e.getValue();
		}
		L.put(l, nl);
	}

	public void updateRight(TreeMap<Integer, Integer> R, int r) {
		
	}

	// TODO: TreeMap<Integer, Integer>  or TreeMap<Integer, Long> !!! ???

	public int solve() {
		int ans = 0;
		TreeMap<Integer, Integer> L = new TreeMap<>();
		TreeMap<Integer, Integer> R = right(-1);
		for (int b = 0; b < n-1; b++) {
			updateLeft(L, A[b]);
			R = right(b);
			// updateRight(R, A[b]);
			// debug('L', L);
			debug('R', R);
			ans += mul(L, R);
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
		test();
		Scanner scanner = new Scanner(System.in);
		CoolguyAndTwoSubseq o = CoolguyAndTwoSubseq.load(scanner);
		System.out.println(o.solve());
	}

	// makes sure new version works as brute for small n
	public static void test() {
		Random random = new Random();
		for (int n = 1; n < 10; n++) {
			int[] A = new int[n];
			for (int i = 0; i < A.length; i++) {
				A[i] = 1 + random.nextInt((int)1000);
			}
			debug("####", 'n', n, 'A', A);
			CoolguyAndTwoSubseq o = new CoolguyAndTwoSubseq(n, A);
			assert o.solve() == o.solve2() : "o.solve()=" + o.solve() + ", o.solve2()=" + o.solve2() + ", n=" + o.n;
		}
		debug("###########");
	}

	static int[] scanArray(Scanner scanner, int n) {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = scanner.nextInt();
			assert 1 <= A[i] && A[i] <= 1e9 : "out of range, A[i]: " + A[i];
		}
		return A;
	}

	static boolean DEBUG = true;
	
	static void debug(Object...os) {
		if (!DEBUG) { return; }
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}

