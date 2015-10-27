import java.util.*;
import java.io.*;

public class Gemstones {

	static int gems(String[] rocks) {
		boolean[] isgem = new boolean[256];
		Arrays.fill(isgem, true);
		for (int i = 0; i < rocks.length; i++) {
			boolean[] present = new boolean[256];
			for (int j = 0; j < rocks[i].length(); j++) { present[rocks[i].charAt(j)] = true; }
			for (int j = 0; j < 256; j++) { isgem[j] &= present[j]; }
		}
		int gems = 0;
		for (int i = 0; i < 256; i++) { if (isgem[i]) { gems++; } }
		return gems;
	}

	static void load(Scanner scanner) {
		int n = Integer.valueOf(scanner.nextLine());
		String[] rocks = new String[n];
		for (int i = 0; i < n; i++) {
			rocks[i] = scanner.nextLine();
		}
		System.out.println(gems(rocks));
	}

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		if (args.length == 1 && "COHADAR".equals(args[0])) {
			scanner = new Scanner(new File("Gemstones.in"));
		}
		load(scanner);
	}

	static void debug(Object...os) {
		System.err.println(Arrays.deepToString(os));
	}

}

