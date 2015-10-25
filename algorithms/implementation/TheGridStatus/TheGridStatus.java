import java.util.*;
import java.io.*;

public class TheGridStatus {

	static void load(Scanner scanner) {
		int t = scanner.nextInt();
		for (int i = 0; i < t; i++) {

		}
	}

	public static void main(String[] args) throws Exception {
		Scanner scanner;
		if (args.length == 1 && "COHADAR".equals(args[0])) {
			scanner = new Scanner(new File("TheGridStatus.in"));
		} else {
			scanner = new Scanner(System.in);
		}
		load(scanner);
	}
}