import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class CoolguyAndTwoSubseq {

	static final int PRIME = (int)1e9 + 7;

	final int n;
	final int[] A;
	final int[] RMIN;
	final int[] LMIN;
	
	public CoolguyAndTwoSubseq(int n, int[] A) {
		this.n = n;
		this.A = A;
		this.RMIN = new int[n];
		this.LMIN = new int[n];
		int r = A[n-1];
		for (int i = n - 1; i >= 0; i--) {
			r = Math.min(r, A[i]);
			RMIN[i] = r;
		}
		int l = A[0];
		for (int i = 0; i < n; i++) {
			l = Math.min(l, A[i]);
			LMIN[i] = l;
		}
	}
	
	public static void inc(TreeMap<Integer, Integer> F, int key) {
		Integer value = F.get(key);
		if (value == null) {
			value = 0;
		}
		F.put(key, value + 1);
	}

	public int ans(int[] F) {
		long ans = 0;
		for (int i = 0; i < n; i++) {
			ans += (long)A[i] * (long)F[i];
			ans %= PRIME;
		}
		return (int)ans;
	}

	public int solve() {
		int[] F = new int[n];
		for (int a = 0; a < n; a++) {
			int l = A[a];
			int il = a;
			for (int b = a; b < n; b++) {
				if (l > A[b]) {
					l = A[b];
					il = b;
				}
				for (int c = b+1; c < n; c++) {
					int r = A[c];
					int ir = c;
					for (int d = c; d < n; d++) {
						if (r > A[d]) {
							r = A[d];
							ir = d;
						}
						if (l > r) {
							F[ir]++;
						} else {
							F[il]++;
						}
					}
				}
			}
		}
		debug(A);
		debug(F);
		return ans(F);		
	}

	public static CoolguyAndTwoSubseq load(Scanner scanner) {
		int n = scanner.nextInt();
		assert 1 <= n && n <= 2e5 : "out of range, n: " + n;
		int[] A = scanArray(scanner, n);
		return new CoolguyAndTwoSubseq(n, A);
	}

	public static void main(String[] args) {
		// test();
		Scanner scanner = new Scanner(System.in);
		CoolguyAndTwoSubseq o = CoolguyAndTwoSubseq.load(scanner);
		TreeMap<Integer, Integer> FL = new TreeMap<>();
		for (int i = 0; i < o.n; i++) {
			inc(FL, o.LMIN[i]);			
		}
		debug(FL);

		TreeMap<Integer, Integer> FR = new TreeMap<>();
		for (int i = 0; i < o.n; i++) {
			inc(FR, o.RMIN[i]);			
		}
		debug(FR);

		System.out.println(o.solve());
	}

	// makes sure new version works as brute for small n
	// public static void test() {
	// 	Random random = new Random();
	// 	for (int n = 1; n < 10; n++) {
	// 		int[] A = new int[n];
	// 		for (int i = 0; i < A.length; i++) {
	// 			A[i] = 1 + random.nextInt((int)1000);
	// 		}
	// 		debug("####", 'n', n, 'A', A);
	// 		CoolguyAndTwoSubseq o = new CoolguyAndTwoSubseq(n, A);
	// 		assert o.solve() == o.solve2() : "o.solve()=" + o.solve() + ", o.solve2()=" + o.solve2() + ", n=" + o.n;
	// 	}
	// 	debug("###########");
	// }

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

