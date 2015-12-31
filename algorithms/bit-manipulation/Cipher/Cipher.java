import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class Cipher {



	static char xor(char a, char b) {
		return (char)(((a - '0') ^ (b - '0')) + '0');
	}

	static String solve(int n, int k, char[] S) {
		char[] B = new char[n]; 
		char cumul = '0';
		for (int i = 0; i < n; i++) {
			B[i] = xor(S[i], cumul);
			cumul = xor(cumul, B[i]);
			if (i - k + 1 >= 0) {
				cumul = xor(cumul, B[i - k + 1]);
			}
		}
		return new String(B);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String[] _nk = scanner.nextLine().split(" ");
		int n = Integer.valueOf(_nk[0]);
		int k = Integer.valueOf(_nk[1]);
		char[] S = scanner.nextLine().toCharArray();
		assert 1 <= n && n <= 1e6 : "out of range, n: " + n;
		assert 1 <= k && k <= 1e6 : "out of range, k: " + k;
		assert S.length == n + k - 1 : "wrong length";
		debug(n, k, S);
		System.out.println(solve(n, k, S));
	}

	static boolean DEBUG = true;
	
	static void debug(Object...os) {
		if (!DEBUG) { return; }
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}

