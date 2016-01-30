import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class BuildAString {

	final int n;
	final int a;
	final int b;
	final String s;
	final char[] S;
	final int[] C;
	
	BuildAString(int n, int a, int b, String s) {
		this.n = n;
		this.a = a;
		this.b = b;
		this.s = s;
		debug(n, a, b, s);
		this.S = s.toCharArray();
		this.C = new int[1 + n];
	}

	boolean exists(int p, int k) {
		if (k == n) {
			return false;
		}
		int k2 = k % 2;
		int l = k - p + 1;
		String tl = s.substring(0, l);
		String tr = s.substring(l, k + 1);
		return tl.contains(tr);
	}

	int bs(int l, int r, int k) {
		for (int p = l; p <= r; p++) {
			if (!exists(p, k)) {
				return p - 1;
			}
		}
		return r;
	}

	int solve() {
		for (int k = 1; k <= n; k++) {
			C[k] = a + C[k - 1];
			int rp = (k + 1) / 2;
			rp = bs(1, rp, k);
			for (int p = 1; p <= rp; p++) {
				C[k] = Math.min(C[k], b + C[k - p]);
			}
		}
		return C[n];
	}

	static BuildAString load(Scanner scanner) {
		String[] _nab = scanner.nextLine().split(" ");
		int n = Integer.valueOf(_nab[0]);
		int a = Integer.valueOf(_nab[1]);
		int b = Integer.valueOf(_nab[2]);
		assert 1 <= n && n <= 3e4 : "out of range, n: " + n;
		assert 1 <= a && a <= 1e4 : "out of range, a: " + a;
		assert 1 <= b && b <= 1e4 : "out of range, b: " + b;
		String s = scanner.nextLine();
		assert n == s.length();
		return new BuildAString(n, a, b, s);
	}	

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = Integer.valueOf(scanner.nextLine());
		assert 1 <= t && t <= 3 : "out of range, t: " + t;
		while (t-->0) {
			BuildAString o = BuildAString.load(scanner);
			System.out.println(o.solve());
		}
	}

	static boolean DEBUG = false;
	
	static void debug(Object...os) {
		if (!DEBUG) { return; }
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}
