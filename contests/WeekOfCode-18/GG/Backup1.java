import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class GG {

	static class Ret {
		final int last;
		final long count;
		Ret(int last, long count) {
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
	private final Map<String, ArrayList<Ret>> map;
	
	public GG(boolean[] G, int n, int m) {
		this.G = G;
		this.n = n;
		this.m = m;
		this.map = new HashMap<>();
	}

	// N is always sorted
	ArrayList<Ret> count(int g, ArrayList<Integer> N) {
		String key = null;
		if (g < 5) {
			StringBuilder sb = new StringBuilder();
			for (int x : N) {
				sb.append(x);
				sb.append('.');
			}
			key = sb.toString();
			ArrayList<Ret> ret = map.get(key);
			if (ret != null) {
				return ret;
			}
		} 
		ArrayList<Ret> ret = new ArrayList<>();
		if (g == 1) {
			if (G[0]) {
				ret.add(new Ret(N.get(0), 1));
			} else {
				ret.add(new Ret(N.get(1), 1));
			}
			if (key != null) {
				map.put(key, ret);
			}
			return ret;
		}
		if (G[g - 1]) {
			long[] cnt = new long[N.size()];
			for (int i = 0; i < N.size(); i++) {
				ArrayList<Integer> N2 = (ArrayList<Integer>)N.clone();
				N2.remove(i);	
				ArrayList<Ret> r1 = count(g - 1, N2);
				boolean change = false;
				for (Ret r : r1) {
					if (r.last > N.get(i)) {
						cnt[i] = (cnt[i] + r.count) % m;
						change = true;
					}
				}
				if (!change) {
					break;
				}
			}
			for (int i = 0; i < cnt.length; i++) {
				if (cnt[i] > 0) {
					ret.add(new Ret(N.get(i), cnt[i]));
				}
			}
			if (key != null) {
				map.put(key, ret);
			}
			return ret;
		} else {
			long[] cnt = new long[N.size()];
			for (int i = N.size() - 1; i >= 0; i--) {
				ArrayList<Integer> N2 = (ArrayList<Integer>)N.clone();
				N2.remove(i);	
				ArrayList<Ret> r1 = count(g - 1, N2);
				boolean change = false;
				for (Ret r : r1) {
					if (r.last < N.get(i)) {
						cnt[i] = (cnt[i] + r.count) % m;
						change = true;
					}
				}
				if (!change) {
					break;
				}
			}
			for (int i = 0; i < cnt.length; i++) {
				if (cnt[i] > 0) {
					ret.add(new Ret(N.get(i), cnt[i]));
				}
			}		
			if (key != null) {
				map.put(key, ret);
			}			
			return ret;
		}
	}	
	
	public long count() {
		ArrayList<Integer> N = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			N.add(i);
		}
		ArrayList<Ret> ret = count(G.length, N);
		long cnt = 0;
		for (Ret r : ret) {
			cnt = (cnt + r.count) % m;
			debug(r);
		}
		return cnt;
	}

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
		System.out.println(o.count());
	}

	static boolean DEBUG = true;
	
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

