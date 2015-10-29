import java.util.*;
import java.io.*;

public class EmmasNotebook {

	static long solve(int t) {
		long sum = 0;
		long curr = 1;
		for (int i = 1; i <= t; i++) {
			curr += 1 - (i & 1); // inc on even
			sum += curr;
		}
		return sum;
	}

	static void scan(Scanner scanner) {
		int t = Integer.valueOf(scanner.nextLine());
		System.out.println(solve(t));
	}

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		if (args.length == 1 && "COHADAR".equals(args[0])) {
			scanner = new Scanner(new File("EmmasNotebook.in"));
		}
		scan(scanner);
	}

	static void debug(Object...os) {
		System.err.println(Arrays.deepToString(os));
	}

}

