import java.util.*;
import java.io.*;

public class SherlockAndPairs {

	void solve(int[] A) {
		Arrays.sort(A);
		int prev = A[0];
		long count = 1;
		long cumul = 0;
		for (int i = 1; i < A.length; i++) {
			if (prev == A[i]) {
				count++;
			} else {
				if (count > 0) {
					cumul += count * (count - 1);
				}
				count = 1;
			}
			prev = A[i];
		}
		if (count > 1) {
			cumul += count * (count - 1);
		}
		System.out.println(cumul);
	}

	static void load(Scanner scanner) {
		int T = Integer.valueOf(scanner.nextLine());
		for (int t = 0; t < T; t++) {
			int n = Integer.valueOf(scanner.nextLine());
			int[] A = new int[n];
			Scanner ls = new Scanner(scanner.nextLine());
			for (int i = 0; i < A.length; i++) {
				A[i] = ls.nextInt();
			}
			new SherlockAndPairs().solve(A);
		}
	}

	public static void main(String[] args) throws Exception {
		Scanner scanner;
		if (args.length == 1 && "COHADAR".equals(args[0])) {
			scanner = new Scanner(new File("sherlock-and-pairs.in"));
		} else {
			scanner = new Scanner(System.in);
		}
		load(scanner);
	}
}
