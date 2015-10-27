import java.util.*;
import java.io.*;

public class FunnyString {

	static boolean funny(char[] s) {
		int n = s.length;
		for (int i = 1; i < n; i++) {
			int ds = Math.abs(s[i] - s[i - 1]);
			int dr = Math.abs(s[n - 1 - i] - s[n - i]);
			if (ds != dr) {
				return false;
			}
		}
		return true;
	}

	static void load(Scanner scanner) {
		int t = Integer.valueOf(scanner.nextLine());
		for (int i = 0; i < t; i++) {
			String s = scanner.nextLine();
			System.out.println(funny(s.toCharArray()) ? "Funny" : "Not Funny");
		}
	}

	public static void main(String[] args) throws Exception {
		Scanner scanner;
		if (args.length == 1 && "COHADAR".equals(args[0])) {
			scanner = new Scanner(new File("FunnyString.in"));
		} else {
			scanner = new Scanner(System.in);
		}
		load(scanner);
	}

	static void debug(Object...os) {
		System.err.println(Arrays.deepToString(os));
	}

}

