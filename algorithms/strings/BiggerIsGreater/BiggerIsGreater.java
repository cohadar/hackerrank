import java.util.*;
import java.io.*;

public class BiggerIsGreater {

	static void swap(char[] a, int i, int j) {
		char t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	// going from end of string find index of first element smaller that its successor.
	static int firstHump(char[] s) {
		for (int i = s.length - 1; i >= 1; i--) {
			if (s[i - 1] < s[i]) {
				return i - 1;
			}
		}
		return -1;
	}

	// find smallest element bigger than v, from index start
	static int im(char[] s, int start, int v) {
		int im = start;
		for (int i = start + 1; i < s.length; i++) {
			if (v < s[i] && s[i] < s[im]) {
				im = i;
			}
		}
		return im;
	}

	static String solve(char[] s) {
		boolean hasAnswer = false;
		int h = firstHump(s);
		if (h >= 0) {
			int im = im(s, h + 1, s[h]);
			swap(s, h, im);
			Arrays.sort(s, h + 1, s.length);
			return String.valueOf(s);
		} else {
			return "no answer";
		}
	}

	static void load(Scanner scanner) {
		StringBuilder sb = new StringBuilder();
		int t = Integer.valueOf(scanner.nextLine());
		for (int i = 0; i < t; i++) {
			String s = scanner.nextLine();
			sb.append(solve(s.toCharArray()));
			sb.append('\n');
		}
		System.out.println(sb);
	}

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		if (args.length == 1 && "COHADAR".equals(args[0])) {
			scanner = new Scanner(new File("BiggerIsGreater.in"));
		}
		load(scanner);
	}

	static void debug(Object...os) {
		System.err.println(Arrays.deepToString(os));
	}

}

