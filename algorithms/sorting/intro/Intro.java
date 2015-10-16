import java.util.*;
import java.io.*;

public class Intro {

	int ar[] = new int[1023];

	int solve(int n, int v) {
		int l = 0;
		int r = n - 1;
		while (l <= r) {
			int m = (l + r) >>> 1;
			if (ar[m] < v) {
				l = m + 1;
			} else if (v < ar[m]) {
				r = m - 1;
			} else {
				return m;
			}
		}
		return -(l + 1);
	}

	void load(Scanner scanner) {
		int v = scanner.nextInt();
		int n = scanner.nextInt();
		for (int i = 0; i < n; i++) {
			ar[i] = scanner.nextInt();
		}
		System.out.println(solve(n, v));
	}

	public static void main(String[] args) throws Exception {
		Scanner scanner;
		if (args.length == 1 && "COHADAR".equals(args[0])) {
			scanner = new Scanner(new File("intro.in"));
		} else {
			scanner = new Scanner(System.in);
		}
		new Intro().load(scanner);
	}
}
