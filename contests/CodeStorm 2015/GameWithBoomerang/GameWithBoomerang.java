import java.util.*;
import java.io.*;

public class GameWithBoomerang {

	static final int MEGA = 1024 * 1024;
	static final int MEMOIZATION = 100 * MEGA;

	int[] PN = new int[MEMOIZATION];
	int lastKnown = 3;

	public GameWithBoomerang() {
		PN[2] = 1;
		PN[3] = 2;
	}

	int aleph(int odd, int index) {
		int ret = index + 1;
		if (index > odd / 2) {
			ret++;
		}
		if (index == odd) {
			ret = 1;
		}
		return ret;
	}

	long solve(int n) {
		for (int i = lastKnown + 1; i <= n; i++) {
			if (i % 2 == 0) {
				PN[i] = aleph(i - 1, PN[i - 1]);
			} else {
				PN[i] = PN[i - 1] + 1;
			}			
		}
		lastKnown = n;
		return PN[n];
	}

	static void scan(Scanner scanner) {
		GameWithBoomerang o = new GameWithBoomerang();
		int t = scanner.nextInt();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < t; i++) {
			long n = scanner.nextLong();
			sb.append(o.solve((int)n));
			sb.append('\n');
		}
		System.out.println(sb);
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

