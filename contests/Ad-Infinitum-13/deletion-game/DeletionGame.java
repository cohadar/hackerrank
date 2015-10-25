import java.util.*;
import java.io.*;

public class DeletionGame {

	boolean solve(long n, long k) {
		if (n == 2) {
			if (k == 3 || k == 1 || k == -1) {
				return true;
			}
			return false;
		}
		long s = n * (n + 1) / 2;
		if (k % 2 != s % 2) {
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

	static void generate(int n) {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = i + 1;
		}
		int p = (int)Math.pow(2, n);
		for (int i = 1; i <= p; i++) {
			int sum = 0;
			for (int j = 0; j < n; j++) {
				int index = 1 << j;
				if ((i & index) == 0) {
					sum += A[j];
				} else {
					sum -= A[j];
				}
			}
			int s = n * (n + 1) / 2;
			System.out.printf("%03d\n", s + sum);
		}
	}

	public static void main(String[] args) throws Exception {
		Scanner scanner;
		if (args.length == 1 && "COHADAR".equals(args[0])) {
			scanner = new Scanner(new File("deletion-game.in"));
		} else {
			scanner = new Scanner(System.in);
		}
		//load(scanner);
		generate(13);
	}
}
