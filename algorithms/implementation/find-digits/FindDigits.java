import java.util.*;
import java.io.*;

public class FindDigits {

	void solve(long n) {
		int count = 0;
		String s = new Long(n).toString();
		for (int i = 0; i < s.length(); i++) {
			int digit = s.charAt(i) - '0';
			if (digit != 0 && n % digit == 0) {
				count++;
			}
		}
		System.out.println(count);
	}

	static void load(Scanner scanner) {
		int t = scanner.nextInt();
		for (int i = 0; i < t; i++) {
			long n = scanner.nextLong();
			new FindDigits().solve(n);
		}
	}

	public static void main(String[] args) throws Exception {
		Scanner scanner;
		if (args.length == 1 && "COHADAR".equals(args[0])) {
			scanner = new Scanner(new File("find-digits.in"));
		} else {
			scanner = new Scanner(System.in);
		}
		load(scanner);
	}
}
