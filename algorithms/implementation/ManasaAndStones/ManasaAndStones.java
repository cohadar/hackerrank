import java.util.*;
import java.io.*;

public class ManasaAndStones {

	static void solve(int n, int a, int b) {
		debug(n, a, b);
		int min = (n - 1) * a;
		int max = (n - 1) * b;
		int delta = b - a;
		StringBuilder res = new StringBuilder(n * 10);
		for (int i = min; i < max; i += delta) {
			res.append(i);
			res.append(' ');
		}
		res.append(max);
		System.out.println(res);
	}

	static void load(Scanner scanner) {
		int t = Integer.valueOf(scanner.nextLine());
		for (int i = 0; i < t; i++) {
			int n = Integer.valueOf(scanner.nextLine());
			int a = Integer.valueOf(scanner.nextLine());
			int b = Integer.valueOf(scanner.nextLine());
			if (a >= b) {
				int c = a;
				a = b;
				b = c;
			}
			solve(n, a, b);
		}
	}

	public static void main(String[] args) throws Exception {
		Scanner scanner;
		if (args.length == 1 && "COHADAR".equals(args[0])) {
			scanner = new Scanner(new File("ManasaAndStones.in"));
		} else {
			scanner = new Scanner(System.in);
		}
		load(scanner);
	}

	static void debug(Object...os) {
		// System.err.println(Arrays.deepToString(os));
	}
}
