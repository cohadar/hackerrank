import java.util.*;
import java.io.*;

public class LibraryFine {

	int solve(Calendar actual, Calendar expected) {
		if (actual.before(expected)) {
			return 0;
		}
		if (actual.get(Calendar.YEAR) == expected.get(Calendar.YEAR)) {
			if (actual.get(Calendar.MONTH) == expected.get(Calendar.MONTH)) {
				return (actual.get(Calendar.DAY_OF_MONTH) - expected.get(Calendar.DAY_OF_MONTH)) * 15;
			} else {
				return (actual.get(Calendar.MONTH) - expected.get(Calendar.MONTH)) * 500;
			}
		}
		return 10000;
	}

	Calendar loadCalendar(Scanner scanner) {
		int D = scanner.nextInt();
		int M = scanner.nextInt();
		int Y = scanner.nextInt();
		assert(1 <= D && D <= 31);
		assert(1 <= M && M <= 12);
		assert(1 <= Y && Y <= 3000);
		return new GregorianCalendar(Y, M - 1, D);
	}

	void load(Scanner scanner) {
		Calendar actual = loadCalendar(scanner);
		Calendar expected = loadCalendar(scanner);
		System.out.println(solve(actual, expected));
	}

	public static void main(String[] args) throws Exception {
		Scanner scanner;
		if (args.length == 1 && "COHADAR".equals(args[0])) {
			scanner = new Scanner(new File("library-fine.in"));
		} else {
			scanner = new Scanner(System.in);
		}
		new LibraryFine().load(scanner);
	}
}
