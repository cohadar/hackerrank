import java.util.*;
import java.io.*;

public class MultiplesOf3And5 {

	static long sum(long n) {
		return n * (n + 1) / 2;
	}

	static long solve(int n) {
		return 3 * sum(n / 3) + 5 * sum(n / 5) - 15 * sum(n / 15);
	}

	static void load(Scanner scanner) {
		StringBuilder sb = new StringBuilder();
		int t = Integer.valueOf(scanner.nextLine());
		for (int i = 0; i < t; i++) {
			int n = Integer.valueOf(scanner.nextLine());
			sb.append(solve(n - 1));
			sb.append('\n');
		}
		System.out.println(sb);
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int t = Integer.valueOf(scanner.nextLine());
		for (int i = 0; i < t; i++) {
			int n = Integer.valueOf(scanner.nextLine());
			sb.append(solve(n - 1));
			sb.append('\n');
		}
		System.out.println(sb);
	}

	static void debug(Object...os) {
		System.err.println(Arrays.deepToString(os));
	}

}

