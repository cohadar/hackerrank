import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class ScalarProducts {

	static class Generator {
		private int a0;
		private int a1;
		private final int m;
		public Generator(int a0, int a1, int m) {
			this.a0 = a0;
			this.a1 = a1;
			this.m = m;
		}
		public int next() {
			int a2 = (a1 + a0) % m;
			a0 = a1;
			a1 = a2;
			return a2;
		}
		public Generator clone() {
			return new Generator(this.a0, this.a1, this.m);
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int c = scanner.nextInt();
		int m = scanner.nextInt();
		int n = scanner.nextInt();
		assert 1 <= c && c <= 1e9 : "out of range, c: " + c;
		assert 1 <= m && m <= 1e9 : "out of range, m: " + m;
		assert 1 <= n && n <= 3e5 : "out of range, n: " + n;
		BitSet B = new BitSet(m);
		Generator ig = new Generator(0, c, m);
		for (int i = 0; i < n; i++) {
			int x1 = ig.next();
			int y1 = ig.next();
			Generator jg = ig.clone();
			for (int j = i + 1; j < n; j++) {
				int x2 = jg.next();
				int y2 = jg.next();	
				B.set((x1 * x2 + y1 * y2) % m);
			}
		}
		System.out.println(B.cardinality() % m);
	}

}

