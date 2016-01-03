import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class NewYearPresent {

	static Map<Integer, Integer> counts(int[] A) {
		Map<Integer, Integer> C = new HashMap<>();
		for (int i = 0; i < A.length; i++) {
			Integer count = C.get(A[i]);
			if (count == null) {
				count = 0;
			}
			C.put(A[i], count + 1);
		}
		return C;
	}

	static Map<Integer, Integer> doubles(int[] A) {
		Map<Integer, Integer> D = counts(A);
		for (Iterator<Map.Entry<Integer, Integer>> i = D.entrySet().iterator(); i.hasNext();) {
			Map.Entry<Integer, Integer> entry = i.next();
			if (entry.getValue() < 2) { 
				i.remove();
			}
		}
		return D;
	}

	static long binom2(long n) {
		return n * (n - 1) / 2;
	}

	static long binom3(long n) {
		return n * (n - 1) * (n - 2) / (3 * 2);
	}	

	static long binom4(long n) {
		return n * (n - 1) * (n - 2) * (n - 3) / (4 * 3 * 2);
	}	

	static long solve(int[] A, int r, int a) {
		long count 
		return count;
	}

	static long solve(int[] A) {
		long count = 0;
		Arrays.sort(A);
		for (int ia = A.length - 1; ia >= 5; ia--) {
			if (A[ia] == A[ia - 1]) {
				count += solve(A, ia - 2, A[ia]);
			}
		}
		return count;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		assert 6 <= n && n <= 3000 : "out of range, n: " + n;
		int[] A = scanArray(scanner, n);
		System.out.println(solve(A));
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

