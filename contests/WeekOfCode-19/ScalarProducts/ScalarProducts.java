import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class ScalarProducts {

	final int m;
	final int n;
	int a1, a0;
	int b1, b0;
	
	public ScalarProducts(int c, int m, int n) {
		this.m = m;
		this.n = n;
		this.a0 = 0;
		this.a1 = c;
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
	
	public int solve() {
		TreeSet<Integer> T = new TreeSet<>();
		for (int i = 0; i < n; i++) {
			int x1 = nextA();
			int y1 = nextA();
			a2b();
			for (int j = i + 1; j < n; j++) {
				int x2 = nextB();
				int y2 = nextB();	
				T.add((x1 * x2 + y1 * y2) % m);
			}
		}
		return T.size() % m;
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
	}

}

