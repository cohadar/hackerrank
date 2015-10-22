import java.util.*;
import java.io.*;

public class SherlockAndTheBeast {

	String decent(int fives, int threes) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < fives; i++) {
			sb.append('5');
		}
		for (int i = 0; i < threes; i++) {
			sb.append('3');
		}
		return sb.toString();
	}

	String solve(int n) {
		int threes = 0;
		while (true) {
			int k = n - threes;
			if (k < 0) {
				break;
			}
			if (k % 3 == 0) {
				return decent(k, threes);
			}
			threes += 5;
		}
		return "-1";
	}

	static int[] loadArray(Scanner scanner, int n) {
		int[] A = new int[n];
		for (int i = 0; i < A.length; i++) {
			A[i] = scanner.nextInt();
		}
		return A;
	}

	static void load(Scanner scanner) {
		int t = scanner.nextInt();
		for (int i = 0; i < t; i++) {
			int n = scanner.nextInt();
			String solution = new SherlockAndTheBeast().solve(n);
			if (!"-1".equals(solution)) {
				assert solution.length() == n;
			}
			System.out.println(solution);
		}
	}

	public static void main(String[] args) throws Exception {
		Scanner scanner;
		if (args.length == 1 && "COHADAR".equals(args[0])) {
			scanner = new Scanner(new File("sherlock-and-the-beast.in"));
		} else {
			scanner = new Scanner(System.in);
		}
		load(scanner);
	}
}
