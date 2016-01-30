import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class AlienFlowers {

	static final int PRIME = (int)1e9 + 7;

	final int na;
	final int nb;
	final int nc;
	final int nd;
	final long[][][][] R;
	final long[][][][] B;

	final long NULL = -1;
	final long BAD = -2;
	
	AlienFlowers(int na, int nb, int nc, int nd) {
		this.na = na;
		this.nb = nb;
		this.nc = nc;
		this.nd = nd;
		this.R = new long[1 + na][1 + nb][1 + nc][1 + nd];
		this.B = new long[1 + na][1 + nb][1 + nc][1 + nd];
		for (int a = 0; a < na; a++) {
			for (int b = 0; b < nb; b++) {
				for (int c = 0; c < nc; c++) {
					for (int d = 0; d < nd; d++) {
						R[a][b][c][d] = NULL;
						B[a][b][c][d] = NULL;
					}
				}
			}
		}
		R[0][0][0][0] = 1;
		B[0][0][0][0] = 1;
	}
	
	long rec(int a, int b, int c, int d) {
		if (a < 0 || b < 0 || c < 0 || d < 0) {
			return BAD;
		}
		if (R[a][b][c][d] > 0) {
			return R[a][b][c][d];
		}
		return R[a][b][c][d] = orSum(rec(a - 1, b, c, d), bec(a, b - 1, c, d));
	}

	long bec(int a, int b, int c, int d) {
		if (a < 0 || b < 0 || c < 0 || d < 0) {
			return BAD;
		}		
		if (B[a][b][c][d] > 0) {
			return B[a][b][c][d];
		}
		return B[a][b][c][d] = orSum(bec(a, b, c - 1, d), rec(a, b, c, d - 1));
	}

	long orSum(long j, long k) {
		if (j == BAD) {
			return (k == BAD) ? BAD : k;
		} else {
			return (k == BAD) ? j : (j + k) % PRIME;
		}
	}

	long njak(long x) {
		return	(x < 0) ? 0 : x;
	}

	long solve() {
		long cr = rec(na, nb, nc, nd);
		long cb = bec(na, nb, nc, nd);
		debug(R);
		debug(B);
		return (njak(cr) + njak(cb)) % PRIME;
	}

	static AlienFlowers load(Scanner scanner) {
		int na = scanner.nextInt();
		int nb = scanner.nextInt();
		int nc = scanner.nextInt();
		int nd = scanner.nextInt();
		return new AlienFlowers(na, nb, nc, nd);
	}	

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		AlienFlowers o = AlienFlowers.load(scanner);
		System.out.println(o.solve());
	}

	static boolean DEBUG = false;
	
	static void debug(Object...os) {
		if (!DEBUG) { return; }
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}
