import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class CoolguyAndTwoSubseq {

	static final int PRIME = (int)1e9 + 7;

	final int n;
	final int[] A;
	
	public CoolguyAndTwoSubseq(int n, int[] A) {
		this.n = n;
		this.A = A;
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

	public Map<Integer, Integer> left(int b) {
		Map<Integer, Integer> L = new TreeMap<>();
		return L;
	}

	public Map<Integer, Integer> right(int b) {
		Map<Integer, Integer> R = new TreeMap<>();
		return R;
	}

	public int mul(Map<Integer, Integer> L, Map<Integer, Integer> R) {
		return -1;
	}

	public int solve() {
		int ans = 0;
		for (int b = 0; b < n-2; b++) {
			Map<Integer, Integer> L = left(b);
			Map<Integer, Integer> R = right(b);
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
				A[i] = 1 + random.nextInt((int)1e9);
			}
			CoolguyAndTwoSubseq o = new CoolguyAndTwoSubseq(n, A);
			assert o.solve() == o.solve2() : "o.solve()=" + o.solve() + ", o.solve2()=" + o.solve2();
		}
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

