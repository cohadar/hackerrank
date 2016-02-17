import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class ScalarProducts {

	final int c;
	final int m;
	final int n;
	int a1, a0;
	int b1, b0;
	
	public ScalarProducts(int c, int m, int n) {
		this.m = m;
		this.n = n;
		this.a0 = 0;
		this.a1 = c;
		this.c = c;
	}

	public int nextA() {
		int a2 = (a1 + a0) % m;
		a0 = a1;
		a1 = a2;
		return a2;
	}

	public int nextB() {
		int b2 = (b1 + b0) % m;
		b0 = b1;
		b1 = b2;
		return b2;
	}

	public void a2b() {
		b1 = a1;
		b0 = a0;
	}

	public void test() {
		for (int i = 0; i < n; i++) {
			int x1 = nextA();
			int y1 = nextA();
			debug(x1, y1);
		}		
	}
	
	public int solve() {
		BitSet B = new BitSet();
		int[] X = new int[n];
		int[] Y = new int[n];
		int nn = n;
		for (int i = 0; i < n; i++) {
			X[i] = nextA();
			Y[i] = nextA();
			if (Y[i] == c && X[i] == 0) {
				nn = i;
				break;
			}
		}			
		debug(nn);
		for (int i = 0; i < nn; i++) {
			for (int j = i + 1; j < nn; j++) {
				int residue = (int)(((long)X[i] * X[j] + (long)Y[i] * Y[j]) % m);
				B.set(residue);
			}
		}
		return B.cardinality() % m;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int c = scanner.nextInt();
		int m = scanner.nextInt();
		int n = scanner.nextInt();
		assert 1 <= c && c <= 1e9 : "out of range, c: " + c;
		assert 1 <= m && m <= 1e9 : "out of range, m: " + m;
		assert 1 <= n && n <= 3e5 : "out of range, n: " + n;
		ScalarProducts o = new ScalarProducts(c, m, n);
		System.out.println(o.solve());
		// o.test();
	}

	static boolean DEBUG = true;
	
	static void debug(Object...os) {
		if (!DEBUG) { return; }
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}

