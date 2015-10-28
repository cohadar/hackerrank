import java.util.*;
import java.io.*;

public class Anagram {

	// number of changes: length - number of matches
	static int changes(char[] first, char[] second) {
		assert first.length == second.length;
		int n = first.length;
		int matches = 0;
		int a = 0;
		int b = 0;
		Arrays.sort(first);
		Arrays.sort(second);
		while (true) {
			if (first[a] == second[b]) {
				a++;
				b++;
				matches++;
				if (a == n) break;
				if (b == n) break;
			} else if (first[a] < second[b]) {
				a++;
				if (a == n) break;
			} else {
				b++;
				if (b == n) break;
			}
		}
		return n - matches;
	}

	// first split the string into halves
	static int changes(String s) {
		int n = s.length();
		if (n % 2 == 1) {
			return -1;
		}
		char[] left = s.substring(0, n / 2).toCharArray();
		char[] right = s.substring(n / 2, n).toCharArray();
		return changes(left, right);
	}

	static void load(Scanner scanner) {
		int t = Integer.valueOf(scanner.nextLine());
		for (int i = 0; i < t; i++) {
			String s = scanner.nextLine();
			System.out.println(changes(s));
		}
	}

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		if (args.length == 1 && "COHADAR".equals(args[0])) {
			scanner = new Scanner(new File("Anagram.in"));
		}
		load(scanner);
	}

	static void debug(Object...os) {
		System.err.println(Arrays.deepToString(os));
	}

}

