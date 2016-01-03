import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class NewYearParty {

	static long solve(int[] T) {
		debug(T);
		long last = T[0];
		long backlog = 0;
		for (int i = 1; i < T.length; i++) {
			long t = T[i];
			if (backlog > 0) {
				last += backlog;
				backlog = 0;
			}
			if (t <= last) { 
				backlog = 1;
			} else {
				last = t;
			}
		}
		return last + backlog;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int[] T = scanArray(scanner, n);
		System.out.println(solve(T));
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

