import java.util.*;
import java.io.*;

public class TaumAndBday {

	static long solve(long b, long w, long x, long y, long z) {
		debug(b, w, x, y ,z);
		long bPrice = b * Math.min(x, y + z);
		long wPrice = w * Math.min(y, x + z);
		return bPrice + wPrice;
	}

	static void load(Scanner scanner) {
		int t = Integer.valueOf(scanner.nextLine());
		for (int i = 0; i < t; i++) {
			String[] _bw = scanner.nextLine().split(" ");
			int b = Integer.valueOf(_bw[0]);
			int w = Integer.valueOf(_bw[1]);
			String[] _xyz = scanner.nextLine().split(" ");
			int x = Integer.valueOf(_xyz[0]);
			int y = Integer.valueOf(_xyz[1]);
			int z = Integer.valueOf(_xyz[2]);
			System.out.println(solve(b, w, x, y, z));
		}
	}

	public static void main(String[] args) throws Exception {
		Scanner scanner;
		if (args.length == 1 && "COHADAR".equals(args[0])) {
			scanner = new Scanner(new File("TaumAndBday.in"));
		} else {
			scanner = new Scanner(System.in);
		}
		load(scanner);
	}

	static void debug(Object...os) {
		// System.err.println(Arrays.deepToString(os));
	}

}

