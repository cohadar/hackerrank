import java.util.*;
import java.io.*;

public class GameWithBoomerang {

	static final int MEGA = 1024 * 1024;
	static final int MEMOIZATION = 100 * MEGA;

	// BS(n) = (4^n - 1) / 3 * 4
	long[] BS = new long[] {
		0L,
		4L,
		20L, 
		84L, 
		340L, 
		1364L, 
		5460L, 
		21844L, 
		87380L, 
		349524L, 
		1398100L, 
		5592404L, 
		22369620L, 
		89478484L, 
		357913940L, 
		1431655764L, 
		5726623060L, 
		22906492244L, 
		91625968980L, 
		366503875924L, 
		1466015503700L, 
		5864062014804L, 
		23456248059220L, 
		93824992236884L, 
		375299968947540L, 
		1501199875790164L, 
		6004799503160660L, 
		24019198012642644L, 
		96076792050570580L, 
		384307168202282324L, 
		1537228672809129300L, 
		6148914691236517204L,
	};

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

	
	static void baseSeries() {

		long pow4n = 4;
		long an = 4;
		while (an > 0) {
			pow4n *= 4;
			an = (pow4n - 1) / 3 * 4;
			System.out.println(an);
		}
		

	}

	static void scan(Scanner scanner) {
		GameWithBoomerang o = new GameWithBoomerang();
		// int t = scanner.nextInt();
		// StringBuilder sb = new StringBuilder();
		// for (int i = 0; i < t; i++) {
		// 	long n = scanner.nextLong();
		// 	sb.append(o.solve((int)n));
		// 	sb.append('\n');
		// }
		// System.out.println(sb);

		int lastBase = 0;
		int k = 0;
		for (int i = 2; i < 1000; i+=2) {
			int r = (int)o.solve(i);
			if (i == r) {
				lastBase = i;
				k = 0;
			}
			int split = lastBase / 2 + 1;
			if (k <= split) {
				debug(i, r, 2 * k - 1, k);
			} else {
				debug(i, r, 2 * split - 1 + 3 * (k - split), k);
			}
			
			k++;
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

