import java.util.*;
import java.io.*;

public class DeletionGame {

	// I got only half points on this challenge because I forgot modulo is a signed operation.
	// I was behaving like an idiot, did not make a single test for a negative K.
	boolean solve(long n, long k) {
		if (n == 2) {
			if (k == 3 || k == 1 || k == -1) {
				return true;
			}
			return false;
		}
		long s = n * (n + 1) / 2;
		if (Math.abs(k % 2) != s % 2) {
			return false;
		}
		if (-s + 2 <= k && k <= s) {
			return true;
		}
		return false;
	}

	static void load(Scanner scanner) {
		int t = scanner.nextInt();
		for (int i = 0; i < t; i++) {
			long n = scanner.nextLong();
			long k = scanner.nextLong();
			System.out.println((new DeletionGame().solve(n, k)) ? "YES" : "NO");
		}
	}

	public static void main(String[] args) throws Exception {
		Scanner scanner;
		if (args.length == 1 && "COHADAR".equals(args[0])) {
			scanner = new Scanner(new File("deletion-game.in"));
		} else {
			scanner = new Scanner(System.in);
		}
		load(scanner);
	}
}
