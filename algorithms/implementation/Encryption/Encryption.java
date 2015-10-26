import java.util.*;
import java.io.*;

public class Encryption {

	static String encrypt(int rows, int columns, String text) {
		debug(rows, columns, text);
		char[][] m = new char[rows][columns];
		int i = 0;
		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < columns; x++) {
				if (i < text.length()) {
					m[y][x] = text.charAt(i++);
				} else {
					break;
				}
			}
		}
		StringBuilder ret = new StringBuilder();
		i = 0;
		for (int x = 0; x < columns; x++) {
			for (int y = 0; y < rows; y++) {
				if (i < text.length() && m[y][x] != 0) {
					i++;
					ret.append(m[y][x]);
				} else {
					break;
				}
			}
			ret.append(' ');
		}
		return ret.toString();
	}

	static String solve(int min, int max, String text) {
		if (min * min >= text.length()) {
			return encrypt(min, min, text);
		}
		if (min * max >= text.length()) {
			return encrypt(min, max, text);
		}
		return encrypt(max, max, text);
	}

	static void load(Scanner scanner) {
		String text = scanner.nextLine();
		int min = (int)Math.floor(Math.sqrt(text.length()));
		int max = (int)Math.ceil(Math.sqrt(text.length()));
		System.out.println(solve(min, max, text));
	}

	public static void main(String[] args) throws Exception {
		Scanner scanner;
		if (args.length == 1 && "COHADAR".equals(args[0])) {
			scanner = new Scanner(new File("Encryption.in"));
		} else {
			scanner = new Scanner(System.in);
		}
		load(scanner);
	}

	static void debug(Object...os) {
		// System.err.println(Arrays.deepToString(os));
	}

}

