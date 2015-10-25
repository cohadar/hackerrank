import java.util.*;
import java.io.*;

public class Multiply {

	// I don't know enough mat for this
	static void load(Scanner scanner) {
		long n = scanner.nextInt();
		long m7 = 1000000007;
		int q = scanner.nextInt();
		for (int i = 0; i < q; i++) {
			int x = scanner.nextInt();
			n *= x;
			n %= m7;
			long sum = 0;
			for (long j = 1; j <= n; j++) {
				if (n % j == 0) {
					// System.out.printf("%d ", j);
					sum += j;
					sum += n / j;
				}
			}
			System.out.println(sum / 2);
		}

	}

	public static void main(String[] args) throws Exception {
		Scanner scanner;
		if (args.length == 1 && "COHADAR".equals(args[0])) {
			scanner = new Scanner(new File("multiply.in"));
		} else {
			scanner = new Scanner(System.in);
		}
		load(scanner);
	}
}
