import java.util.*;
import java.io.*;

public class GameWithCoins {

	String solve(int a, int b) {
		if (a == 0 || b == 0) {
			return "First";
		}
		if ((a % 2 - b % 2) == 0) {
			return "Second";
		} else {
			return "First";
		}
	}

	static void load(Scanner scanner) {
		int t = scanner.nextInt();
		for (int i = 0; i < t; i++) {
			int a = scanner.nextInt();
			int b = scanner.nextInt();
			System.out.println(new GameWithCoins().solve(a, b));
		}
	}

	public static void main(String[] args) throws Exception {
		Scanner scanner;
		if (args.length == 1 && "COHADAR".equals(args[0])) {
			scanner = new Scanner(new File("game-with-coins.in"));
		} else {
			scanner = new Scanner(System.in);
		}
		load(scanner);
	}
}
