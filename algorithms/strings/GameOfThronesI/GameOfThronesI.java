import java.util.*;
import java.io.*;

public class GameOfThronesI {

	static boolean isAnagram(char[] s) {
		char[] x = new char[256];
		for (int i = 0; i < s.length; i++) {
			x[s[i]] ^= -1;
		}
		int odd = 0;
		for (int i = 0; i < 256; i++) {
			if (x[i] != 0) {
				odd++;
			}
		}
		return odd <= 1;
	}

	static void load(Scanner scanner) {
		String s = scanner.nextLine();
		System.out.println((isAnagram(s.toCharArray())) ? "YES" : "NO");
	}

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		if (args.length == 1 && "COHADAR".equals(args[0])) {
			scanner = new Scanner(new File("GameOfThronesI.in"));
		}
		load(scanner);
	}

	static void debug(Object...os) {
		System.err.println(Arrays.deepToString(os));
	}

}
