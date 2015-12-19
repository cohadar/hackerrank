import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class BinaryStringGame {

	

	static ArrayList<Integer> toArray(String s) {
		debug(s);
		ArrayList<Integer> ar = new ArrayList<>();
		int ones = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '0') {
				if (ones > 0) {
					ar.add(ones);
					ones = 0;
				}
			} else {
				ones++;
			}
		}
		if (ones > 0) {
			ar.add(ones);
			ones = 0;
		}
		return ar;
	}

	static boolean solve(int k, ArrayList<Integer> A) {
		boolean[] W = calcWins(k);
		debug(A);
		int wins = 0;
		for (int a : A) {
			if (W[a]) {
				wins++;
			}
		}
		return wins % 2 == 1;
	}

	static boolean[] calcWins(int k) {
		boolean W[] = new boolean[(int)1e6 + 1];
		W[k] = true;
		for (int i = k + 1; i <= (int)1e6; i++) {
			int z = i - k;
			for (int j = 0; j < z / 2 + 1; j++) {
				int b = z - j;
				if (W[j] == W[b]) {
					W[i] = true;
					break;
				}
			}
		}
		return W;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = Integer.valueOf(scanner.nextLine());
		while (n-->0) {
			int k = Integer.valueOf(scanner.nextLine());
			String s = scanner.nextLine().trim();
			System.out.println((solve(k, toArray(s)) ? "Alice" : "Bob"));
		}
	}

	static boolean DEBUG = true;
	
	static void debug(Object...os) {
		if (!DEBUG) { return; }
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}

