import java.util.*;
import java.io.*;

public class IceCreamParlor {

	static String solve(int m, int[] c) {
		for (int i = 0; i < c.length - 1; i++) {
			for (int j = i + 1; j < c.length; j++) {
				if (c[i] + c[j] == m) {
					return String.format("%d %d", i + 1, j + 1);
				}
			}
		}
		assert(false);
		return "";
	}

	static void scan(Scanner scanner) {
		int t = Integer.valueOf(scanner.nextLine());
		for (int i = 0; i < t; i++) {
			int m = Integer.valueOf(scanner.nextLine());
			int n = Integer.valueOf(scanner.nextLine());
			int[] c = scanArray(scanner, n);
			System.out.println(solve(m, c));
		}
	}

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		if (args.length == 1 && "COHADAR".equals(args[0])) {
			scanner = new Scanner(new File("IceCreamParlor.in"));
		}
		scan(scanner);
	}

	static int[] scanArray(Scanner scanner, int n) {
		String line = scanner.nextLine();
		Scanner arrayScanner = new Scanner(line);
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = arrayScanner.nextInt();
		}
		return a;
	}

	static void debug(Object...os) {
		System.err.println(Arrays.deepToString(os));
	}

}

