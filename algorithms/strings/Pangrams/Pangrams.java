import java.util.*;
import java.io.*;

public class Pangrams {

	static String alphabet = "abcdefghijklmnopqrstuvwxyz";

	static boolean pangram(char[] s) {
		char[] t = alphabet.toCharArray();
		int n = t.length;
		for (int i = 0; i < s.length; i++) {
			int c = Character.toLowerCase(s[i]);
			if ('a' <= c && c <= 'z') {
				if (t[c - 'a'] > 0) {
					t[c - 'a'] = 0;
					n--;
					if (n == 0) {
						return true;
					}
				}
			}
		}
		return false;
	}

	static void load(Scanner scanner) {
		String s = scanner.nextLine();
		System.out.println((pangram(s.toCharArray())) ? "pangram" : "not pangram");
	}

	public static void main(String[] args) throws Exception {
		Scanner scanner;
		if (args.length == 1 && "COHADAR".equals(args[0])) {
			scanner = new Scanner(new File("Pangrams.in"));
		} else {
			scanner = new Scanner(System.in);
		}
		load(scanner);
	}

	static void debug(Object...os) {
		System.err.println(Arrays.deepToString(os));
	}

}

