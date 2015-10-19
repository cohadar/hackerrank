import java.util.*;
import java.io.*;

public class CountingSort3 {

	static class Pair {
		final int a;
		final String s;

		Pair(int a, String s) {
			this.a = a;
			this.s = s;
		}
	}

	void solve(Pair[] pairs) {
		Integer[] C = new Integer[100];
		Arrays.fill(C, 0);
		for (Pair p : pairs) {
			C[p.a]++;
		}
		int cumul = 0;
		for (int i = 0; i < C.length; i++) {
			cumul += C[i];
			System.out.printf("%d ", cumul);
		}
	}

	static void load(Scanner scanner) {
		int n = Integer.valueOf(scanner.nextLine());
		Pair[] pairs = new Pair[n];
		for (int i = 0; i < n; i++) {
			String temp[] = scanner.nextLine().split(" ");
			pairs[i] = new Pair(Integer.valueOf(temp[0]), temp[1]);
		}
		new CountingSort3().solve(pairs);
	}

	public static void main(String[] args) throws Exception {
		Scanner scanner;
		if (args.length == 1 && "COHADAR".equals(args[0])) {
			scanner = new Scanner(new File("counting-sort-3.in"));
		} else {
			scanner = new Scanner(System.in);
		}
		load(scanner);
	}
}
