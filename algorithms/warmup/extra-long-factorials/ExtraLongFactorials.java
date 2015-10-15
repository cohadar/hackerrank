import java.util.*;
import java.io.*;
import java.math.*;

public class ExtraLongFactorials {

	BigInteger solve(int n) {
		BigInteger p = new BigInteger("1");
		for (int i = 2; i <= n; i++) {
			p = p.multiply(new BigInteger(String.valueOf(i)));
		}
		return p;
	}

	void load(Scanner scanner) {
		int N = scanner.nextInt();
		System.out.println(solve(N));
	}

	public static void main(String[] args) throws Exception {
		Scanner scanner;
		if (args.length == 1 && "COHADAR".equals(args[0])) {
			scanner = new Scanner(new File("extra-long-factorials.in"));
		} else {
			scanner = new Scanner(System.in);
		}
		new ExtraLongFactorials().load(scanner);
	}
}
