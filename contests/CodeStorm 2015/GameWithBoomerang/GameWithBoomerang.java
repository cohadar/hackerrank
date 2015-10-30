import java.util.*;
import java.io.*;

public class GameWithBoomerang {

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

	long findBase(long n) {
		for (int i = 0; i < BS.length; i++) {
			if (n < BS[i]) {
				return BS[i - 1];
			}
		}
		return BS[BS.length - 1];
	}

	long closedFormula(long n) {
		if (n % 2 == 1) {
			return 1 + closedFormula(n - 1);
		}
		long base = findBase(n);
		if (base == n) return n;
		long k = (n - base) / 2;
		long split = base / 2 + 1;
		if (k < split) {
			return 2 * k - 1;
		} else {
			return 3 * k - 1 - split;
		}
	}
	
	static void printBaseSeries() {
		long pow4n = 4;
		long an = 4;
		while (an > 0) {			
			an = (pow4n - 1) / 3 * 4;
			pow4n *= 4;
			System.out.println(an);
		}
	}

	static void scan(Scanner scanner) {
		GameWithBoomerang o = new GameWithBoomerang();
		int t = scanner.nextInt();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < t; i++) {
			long n = scanner.nextLong();
			sb.append(o.closedFormula(n));
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

