import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class SamAndSubstrings {

	static final int PRIME = (int)1e9 + 7;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		char[] C = scanner.nextLine().toCharArray();
		int n = C.length;
		long[] N = new long[n];
		for (int i = 0; i < n; i++) {
			N[i] = C[i] - '0';
		}
		long sum_first = 0;
		long sum_last = N[0];
		for (int a = 1; a < n; a++) {
			sum_first += sum_last;
			sum_first %= PRIME;
			sum_last = (sum_last * 10) + (N[a] * (a + 1));
			sum_last %= PRIME;
			debug(sum_first, sum_last);
		}
		sum_first += sum_last;
		sum_first %= PRIME;
		System.out.println(sum_first);
	}

	static boolean DEBUG = false;
	
	static void debug(Object...os) {
		if (!DEBUG) { return; }
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}

