import java.util.*;
import java.io.*;

public class TheTimeInWords {

	static String[] numbers = {
		"zero",
		"one",
		"two",
		"three",
		"four",
		"five",
		"six",
		"seven",
		"eight",
		"nine",
		"ten",
		"eleven",
		"twelve",
		"thirteen",
		"fourteen",
		"fifteen",
		"sixteen",
		"seventeen",
		"eighteen",
		"nineteen",
		"twenty",
	};

	static String number(int n) {
		assert 0 <= n && n < 30;
		if (n <= 20) {
			return numbers[n];
		} else {
			return "twenty " + numbers[n - 20];
		}
	}

	static String solve(int hh, int mm) {
		if (mm == 0) {
			return numbers[hh] + " o' clock";
		}
		if (mm == 1) {
			return "one minute past " + number(hh);
		}
		if (mm == 59) {
			return "one minute to " + number(hh + 1);
		}
		if (mm == 15) {
			return "quarter past " + number(hh);
		}
		if (mm == 30) {
			return "half past " + number(hh);
		}
		if (mm == 45) {
			return "quarter to " + number(hh + 1);
		}
		if (mm < 30) {
			return number(mm) + " minutes past " + number(hh);
		} else {
			return number(60 - mm) + " minutes to " + number(hh + 1);
		}
	}

	static void load(Scanner scanner) {
		int hh = Integer.valueOf(scanner.nextLine());
		assert 1 <= hh && hh < 12;
		int mm = Integer.valueOf(scanner.nextLine());
		assert 0 <= mm && mm < 60;
		System.out.println(solve(hh, mm));
	}

	static void test() {
		for (int i = 0; i <= 15; i++) {
			debug(solve(5, i));
		}
		debug("=======");
		for (int i = 16; i <= 30; i++) {
			debug(solve(5, i));
		}
		debug("=======");
		for (int i = 31; i <= 45; i++) {
			debug(solve(5, i));
		}
		debug("=======");
		for (int i = 46; i <= 59; i++) {
			debug(solve(5, i));
		}
		debug("=======");
	}

	public static void main(String[] args) throws Exception {
		test();
		Scanner scanner;
		if (args.length == 1 && "COHADAR".equals(args[0])) {
			scanner = new Scanner(new File("TheTimeInWords.in"));
		} else {
			scanner = new Scanner(System.in);
		}
		load(scanner);
	}

	static void debug(Object...os) {
		System.err.println(Arrays.deepToString(os));
	}

}

