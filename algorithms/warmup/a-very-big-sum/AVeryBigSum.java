import java.util.*;
import java.io.*;

public class AVeryBigSum {

	void load(Scanner scanner) {
		int N = scanner.nextInt();
		long sum = 0;
		for (int i = 0; i < N; i++) {
			sum += scanner.nextInt();
		}
		System.out.println(sum);
	}

	public static void main(String[] args) throws Exception {
		Scanner scanner;
		if (args.length == 1 && "COHADAR".equals(args[0])) {
			scanner = new Scanner(new File("a-very-big-sum.in"));
		} else {
			scanner = new Scanner(System.in);
		}
		new AVeryBigSum().load(scanner);
	}
}
