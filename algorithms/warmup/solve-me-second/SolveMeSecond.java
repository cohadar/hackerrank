import java.util.*;
import java.io.*;

public class SolveMeSecond {

	int solve(int a, int b) {
		return a + b;
	}

	void load(Scanner scanner) {
		int T = scanner.nextInt();
		for (int i = 0; i < T; i++) {
			int a = scanner.nextInt();
			int b = scanner.nextInt();
			System.out.println(solve(a, b));
		}
	}

	public static void main(String[] args) throws Exception {
		Scanner scanner;
		if (args.length == 1 && "COHADAR".equals(args[0])) {
			scanner = new Scanner(new File("solve-me-second.in"));
		} else {
			scanner = new Scanner(System.in);
		}
		new SolveMeSecond().load(scanner);
	}
}
