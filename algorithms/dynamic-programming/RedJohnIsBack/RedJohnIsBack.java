import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class RedJohnIsBack {

	static int[] M = new int[41];
	static boolean[] C;

	static int primesLTEQ(int m) {
		int count = 0;
		for (int i = 2; i <= m; i++) {
			if (!C[i]) {
				count++;
			}
		}
		return count;
	}

	static void brickCombos() {
		for (int i = 0; i < M.length; i++) {
			if (i < 4) {
				M[i] = 1;
			} else {
				M[i] = M[i - 1] + M[i - 4];
			}
		}
	}

	static void erase(int k) {
		for (int i = k*k; i < C.length; i += k) {
			C[i] = true;
		}
	}

	static void eratosthenes() {
		int q = (int)Math.sqrt(C.length) + 1;
		for (int i = 2; i < q; i++) {
			erase(i);
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		assert 1 <= t && t <= 20 : "out of range, t: " + t;
		brickCombos();
		C = new boolean[M[40] + 1];
		eratosthenes();
		while (t-->0) {
			int n = scanner.nextInt();
			assert 1 <= n && n <= 40 : "out of range, n: " + n;
			System.out.println(primesLTEQ(M[n]));
		}
	}

}

