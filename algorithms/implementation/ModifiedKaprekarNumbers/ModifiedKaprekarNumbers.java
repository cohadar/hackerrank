import java.util.*;
import java.io.*;

public class ModifiedKaprekarNumbers {

	static void solve(long p, long q) {
		StringBuilder sb = new StringBuilder();
		for (long k = p; k <= q; k++) {
			String sk = String.valueOf(k);
			String sq = "0" + String.valueOf(k * k);
			long d = Long.valueOf(sq.substring(sq.length() - sk.length(), sq.length()));
			long l = Long.valueOf(sq.substring(0, sq.length() - sk.length()));
			if (l + d == k) {
				sb.append(k);
				sb.append(' ');
			}
		}
		if (sb.length() == 0) {
			System.out.println("INVALID RANGE");
		} else {
			System.out.println(sb);
		}
	}

	static void load(Scanner scanner) {
		int p = Integer.valueOf(scanner.nextLine());
		int q = Integer.valueOf(scanner.nextLine());
		solve(p, q);
	}

	public static void main(String[] args) throws Exception {
		Scanner scanner;
		if (args.length == 1 && "COHADAR".equals(args[0])) {
			scanner = new Scanner(new File("ModifiedKaprekarNumbers.in"));
		} else {
			scanner = new Scanner(System.in);
		}
		load(scanner);
	}

	static void debug(Object...os) {
		System.err.println(Arrays.deepToString(os));
	}

}

