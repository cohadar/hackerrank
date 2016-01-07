import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class LegoBlocks {

	static final int PRIME = (int)1e9 + 7;

	private final int n;
	private final int m;
	
	public LegoBlocks(int n, int m) {
		this.n = n;
		this.m = m;
	}

	long[] solveOneRow(int m) {
		long[] R = new long[Math.max(5, m + 1)];
		R[1] = 1;
		R[2] = 2;
		R[3] = 4;
		R[4] = 8;
		for (int i = 5; i <= m; i++) {
			R[i] = R[i - 1] + R[i - 2] + R[i - 3] + R[i - 4]; 
			R[i] %= PRIME;
		}
		return R;
	}

	long[] jagged(int m, long[] R) {
		long[] J = new long[m + 1];
		for (int im = 1; im <= m; im++) {
			for (int j = 0; j < 4 && im - j >= 0; j++) {
				J[im] += R[im - j];
				J[im] %= PRIME;
			}
		}
		return J;		
	}

	long[] jaggedN(long[] J, int n) {
		long[] N = new long[J.length];
		Arrays.fill(N, 1);
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < J.length; j++) {
				N[j] *= J[j];
				N[j] %= PRIME;
			}
		}
		return N;
	}

	long[] solid(long[] N) {
		long[] S = new long[N.length];
		for (int i = 1; i < N.length; i++) {
			S[m] = N[m - 1] - S[m - 1];
		}
		return S;
	}

	long solve() {
		long[] R = solveOneRow(m);
		long[] J = jagged(m, R);
		long[] N = jaggedN(J, n);
		long[] S = solid(N);
		debug(S);
		return S[m];
	}

	static LegoBlocks load(Scanner scanner) {
		int n = scanner.nextInt();
		int m = scanner.nextInt();
		assert 1 <= n && n <= 1000 : "out of range, n: " + n;
		assert 1 <= m && m <= 1000 : "out of range, m: " + m;
		return new LegoBlocks(n, m);
	}	

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = Integer.valueOf(scanner.nextLine());
		assert 1 <= t && t <= 100 : "out of range, t: " + t;
		while (t-->0) {
			LegoBlocks o = LegoBlocks.load(scanner);
			System.out.println(o.solve());
		}
	}

	static boolean DEBUG = true;
	
	static void debug(Object...os) {
		if (!DEBUG) { return; }
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}
