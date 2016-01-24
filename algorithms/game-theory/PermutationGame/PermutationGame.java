import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class PermutationGame {

	static boolean isIncreasing(String S) {
		for (int i = 1; i < S.length(); i++) {
			if (S.charAt(i - 1) > S.charAt(i)) {
				return false;
			}
		}
		return true;
	}

	static Map<String, Boolean> M = new HashMap<>();
	
	static boolean solve(String S) {
		Boolean c = M.get(S);
		if (c != null) {
			return c;
		}
		boolean ret = false;
		if (!isIncreasing(S)) {
			for (int i = 0; i < S.length(); i++) {
				if (solve(S.replace("" + S.charAt(i), "")) == false) {
					ret = true;
					break;
				}
			}
		}
		M.put(S, ret);
		return ret;
	}

	static String load(Scanner scanner) {
		int n = scanner.nextInt();
		assert 2 <= n && n <= 15 : "out of range, n: " + n;
		int[] A = scanArray(scanner, n);
		String S = "";
		for (int i = 0; i < A.length; i++) {
			S += (char)('A' + A[i]);
		}
		return S;
	}	

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		assert 1 <= t && t <= 100 : "out of range, t: " + t;
		while (t-->0) {
			System.out.println((solve(load(scanner))) ? "Alice" : "Bob");
		}
	}

	static int[] scanArray(Scanner scanner, int n) {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = scanner.nextInt();
		}
		return A;
	}

}
