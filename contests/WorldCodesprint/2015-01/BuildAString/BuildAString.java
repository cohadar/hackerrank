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
		String l = s.substring(0, k - p + 1);
		String r = s.substring(k - p + 1, k + 1);
		return l.contains(r);
	}

	int solve() {
		for (int k = 1; k <= n; k++) {
			C[k] = a + C[k - 1];
			int rp = (k + 1) / 2;
			for (int p = 1; p <= rp; p++) {
				if (exists(p, k)) {
					C[k] = Math.min(C[k], b + C[k - p]);
				} else {
					break;
				}
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
