import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class GG {

	private final boolean[] G;
	private final int n;
	private final int m;
	private final int[][] P = new int[3000][3000];
	
	public GG(boolean[] G, int n, int m) {
		this.G = G;
		this.n = n;
		this.m = m;
	}

	void count(int g) {
		int[] prev = P[g - 1];
		int[] next = P[g];
		if (G[g - 1]) {
			int cumul = 0;
			for (int i = g; i >= 0; i--) {
				cumul = (cumul + prev[i]) % m;
				next[i] = (next[i] + cumul) % m;
			}
		} else {
			int cumul = 0;
			for (int i = 1; i <= g; i++) {
				cumul = (cumul + prev[i - 1]) % m;
				next[i] = (next[i] + cumul) % m;
			}
		}
	}	
	
	public int count2() {
		if (G[0]) {
			P[1][0] = 1;
		} else {
			P[1][1] = 1;
		}
		for (int g = 2; g <= n - 1; g++) {
			count(g);
		}
		int cnt = 0;
		for (int r : P[n - 1]) {
			if (r > 0) {
				cnt = (cnt + r) % m;
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

}

