import java.util.*;
import java.io.*;

public class AlternatingCharacters {

	static int solve(char[] s) {
		int count = 1;
		char current = s[0];
		for (int i = 1; i < s.length; i++) {
			if (current != s[i]) {
				current = s[i];
				count++;
			}
		}
		return s.length - count;
	}

	static void load(Scanner scanner) {
		int t = Integer.valueOf(scanner.nextLine());
		for (int i = 0; i < t; i++) {
			String s = scanner.nextLine();
			System.out.println(solve(s.toCharArray()));
		}
	}

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		if (args.length == 1 && "COHADAR".equals(args[0])) {
			scanner = new Scanner(new File("AlternatingCharacters.in"));
		}
		load(scanner);
	}

	static void debug(Object...os) {
		System.err.println(Arrays.deepToString(os));
	}

}

