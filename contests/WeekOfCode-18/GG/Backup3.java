import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class GG {

	static class Ret {
		final int last;
		final int count;
		Ret(int last, int count) {
			this.last = last;
			this.count = count;
		}
		public String toString() {
			return String.format("(l=%d, c=%d)", last, count);
		}
	}

	private final boolean[] G;
	private final int n;
	private final int m;
	
	public GG(boolean[] G, int n, int m) {
		this.G = G;
		this.n = n;
		this.m = m;
	}

	int N2get(int last, int n, int i) {
		if (last < i) {
			return last;
		} else {
			return last + 1;
		}
	}

	// N is always sorted
	int[] count(int g, int[] prev) {
		if (G[g - 1]) {
			int[] cnt = new int[g + 1];
			for (int i = 0; i < g + 1; i++) {
				boolean change = false;
				for (int ip = 0; ip < prev.length; ip++) {
					if (prev[ip] > 0 && N2get(ip, g + 1, i) > i) {
						cnt[i] = (cnt[i] + prev[ip]) % m;
						change = true;
					}
				}
				if (!change) {
					break;
				}
			}
			return cnt;
		} else {
			int[] cnt = new int[g + 1];
			for (int i = g; i >= 0; i--) {
				boolean change = false;
				for (int ip = 0; ip < prev.length; ip++) {
					if (prev[ip] > 0 && N2get(ip, g + 1, i) < i) {
						cnt[i] = (cnt[i] + prev[ip]) % m;
						change = true;
					}
				}
				if (!change) {
					break;
				}
			}
			return cnt;
		}
	}	
	
	public int count2() {
		int[] prev = new int[2];
		if (G[0]) {
			prev[0] = 1;
		} else {
			prev[1] = 1;
		}
		for (int g = 2; g <= n - 1; g++) {
			prev = count(g, prev);
		}
		int cnt = 0;
		for (int r : prev) {
			if (r > 0) {
				cnt = (cnt + r) % m;
				debug(r);
			}
		}
		return cnt;		
	}	

	// 396655125 big3000
	// 389680576 big1201
	// 363301351 big101
	// 2491930 LLGLLLGGLGLG
	// 302358  LLGLLLGGLGL
	// 39095   LLGLLLGGLG
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String[] _nm = scanner.nextLine().split(" ");
		int n = Integer.valueOf(_nm[0]);
		int m = Integer.valueOf(_nm[1]);
		assert 2 <= n && n <= 3000 : "out of range, n: " + n;
		assert 1 <= m && m <= 1e9 : "out of range, m: " + m;
		char[] S = scanner.nextLine().toCharArray();
		assert S.length == n - 1;
		boolean[] G = new boolean[n - 1];
		for (int i = 0; i < S.length; i++) {
			G[i] = (S[i] == 'G');
		}
		GG o = new GG(G, n, m);
		System.out.println(o.count2());
	}

	static boolean DEBUG = false;
	
	static void debug(Object...os) {
		if (!DEBUG) { return; }
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

	static void debug2(ArrayList<Ret> R) {
		if (!DEBUG) { return; }
		StringBuilder sb = new StringBuilder();
		for (Ret r : R) {
			sb.append(r);
		}
		System.err.printf("\t\tret %.65536s\n", sb);
	}	
}

