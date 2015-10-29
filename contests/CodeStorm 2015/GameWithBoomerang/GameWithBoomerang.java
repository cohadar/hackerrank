import java.util.*;
import java.io.*;

public class GameWithBoomerang {

	static long solve(int n) {
		assert n > 1;
		int[] a = new int[n];
		for (int i = 0; i < n; i++) a[i] = i + 1;
		int c = 0;
		int target = 0;
		while (n > 1) {
			if (n % 2 == 0) {
				target = (c + n / 2) % n;
				if (target > c) {
					c++;
				}				
			} else {
				target = c;
			}
			for (int i = target + 1; i < n; i++) {
				a[i - 1] = a[i];
			}
			n--;
			c %= n;
		}
		return a[0];
	}

	static void scan(Scanner scanner) {
		int t = scanner.nextInt();
		for (int i = 0; i < t; i++) {
			long n = scanner.nextLong();
			System.out.println(solve((int)n)); // <<-------<< INT!!!
		}
	}

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		if (args.length == 1 && "COHADAR".equals(args[0])) {
			scanner = new Scanner(new File("GameWithBoomerang.in"));
		}
		scan(scanner);
	}

	static void debug(Object...os) {
		System.err.println(Arrays.deepToString(os));
	}

}

