import java.util.*;
import java.io.*;

public class SaveQuantumland {

	static int solve(int[] b) {
		int sum = 0;
		for (int i = 1; i < b.length; i++) {
			if (b[i - 1] == 0 && b[i] == 0) {
				b[i] = 1;
				sum++;
			}
		}
		return sum;
	}

	static void scan(Scanner scanner) {
		int t = Integer.valueOf(scanner.nextLine());
		for (int i = 0; i < t; i++) {
			int n = scanner.nextInt();
			int b[] = new int[n];
			for (int j = 0; j < n; j++) {
				b[j] = scanner.nextInt();
			}
			System.out.println(solve(b));
		}
	}

	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		if (args.length == 1 && "COHADAR".equals(args[0])) {
			scanner = new Scanner(new File("SaveQuantumland.in"));
		}
		scan(scanner);
	}

	static void debug(Object...os) {
		System.err.println(Arrays.deepToString(os));
	}

}

