import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class SupermanCelebratesDivali {

	final int n;
	final int h;
	final int[][] B;
	final int d;
	final int[][] S;
	final int[][] M;
	
	SupermanCelebratesDivali(int[][] B, int d) {
		this.n = B.length;
		this.h = B[0].length - 1; // bulding-people matrix
		this.B = B;
		this.d = d;
		this.S = new int[n][1 + h]; // saved people matrix
		this.M = new int[n][1 + h]; // max that can be saved on level h
		for (int i = 0; i < n; i++) {
			Arrays.fill(M[i], -1);
		}
	}

	void calcMaxOnAllFloors(int b, int f) {
		int m = Integer.MIN_VALUE;
		int im = -1;
		for (int b2 = 0; b2 < n; b2++) {
			if (S[b2][f] > m) {
				m = S[b2][f];
				im = b2;
			}
		}
		for (int b2 = 0; b2 < n; b2++) {
			M[b][f] = m;
		}
		m = Integer.MIN_VALUE;
		for (int b2 = 0; b2 < n; b2++) {
			if (b2 == im) { continue; };
			if (S[b2][f] > m) {
				m = S[b2][f];
			}
		}			
		M[im][f] = m;			
	}

	int calcMax(int b, int f) {
		int max = 0;
		if (f > 0) {
			if (M[b][f] == -1) {
				calcMaxOnAllFloors(b, f);
			}
			if (M[b][f] > max) {
				max = M[b][f];
			}
		}
		return max;
	}

	int solve() {
		for (int f = 1; f <= h; f++) {
			for (int b = 0; b < n; b++) {
				S[b][f] = B[b][f];
				S[b][f] += Math.max(S[b][f - 1], calcMax(b, f - d));
			}
		}
		int max = 0;
		for (int b = 0; b < n; b++) {
			if (S[b][h] > max) {
				max = S[b][h];
			}
		}
		return max;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int h = scanner.nextInt();
		int d = scanner.nextInt();
		assert 1 <= n && n <= 1900 : "out of range, n: " + n;
		assert 1 <= h && h <= 1900 : "out of range, h: " + h;
		assert 1 <= d && d <= 450 : "out of range, d: " + d;
		int[][] B = new int[n][1 + h];
		for (int i = 0; i < n; i++) {
			int u = scanner.nextInt();
			assert 0 <= u && u <= 1900 : "out of range, u: " + u;
			for (int j = 0; j < u; j++) {
				int f = scanner.nextInt();
				B[i][f]++;
			}
		}
		SupermanCelebratesDivali o = new SupermanCelebratesDivali(B, d);
		System.out.println(o.solve());
	}

}

