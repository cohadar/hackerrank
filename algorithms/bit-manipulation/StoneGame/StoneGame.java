import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class StoneGame {

	static final int PRIME = (int)1e9 + 7;

	private final int[] A;

	long options(int nI) {
		long p2 = 1;
		for (int i = nI - 1; i > 0; i--) {
			p2 *= 2;
			p2 %= PRIME; 
		}
		return p2;
	}

	public StoneGame(int[] A) {
		this.A = A;
		debug(A.length, A);
	}

	int cutLegs(int[] A) {
		int nI = 0;
		for (int i = 0; i < A.length; i++) {
			if ((A[i] & 1) != 0) {
				nI++;
			}
			A[1] >>>= 1;
		}
		return nI;
	}

	long solve() {
		long count = 0;
		for (int i = 0; i < 32; i++) {
			int nI = cutLegs(A);
			count *= options(nI);
			count %= PRIME;
		}
		return count;
	}

	static StoneGame load(Scanner scanner) {
		int n = scanner.nextInt();
		assert 3 <= n && n <= 100 : "out of range, n: " + n;
		int[] A = scanArray(scanner, n);
		return new StoneGame(A);
	}	

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		StoneGame o = StoneGame.load(scanner);
		System.out.println(o.solve());
	}

	static int[] scanArray(Scanner scanner, int n) {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = scanner.nextInt();
		}
		return A;
	}

	static boolean DEBUG = true;
	
	static void debug(Object...os) {
		if (!DEBUG) { return; }
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}
