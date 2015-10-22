import java.util.*;
import java.io.*;

public class Candies {

	void solve(int[] R) {
		int n = R.length;
		int[] V = new int[n];
		V[n - 1] = 1;
		for (int k = n - 2; k >= 0; k--) {
			if (R[k] <= R[k + 1]) {
				V[k] = 1;
			} else {
				V[k] = 1 + V[k + 1];
			}
		}
		// System.err.println(Arrays.toString(V));
		long total = V[0];
		for (int k = 1; k < n; k++) {
			if (R[k - 1] < R[k]) {
				V[k] = Math.max(V[k], 1 + V[k - 1]);
			}
			total += V[k];
		}
		// System.err.println(Arrays.toString(R));
		// System.err.println(Arrays.toString(V));
		System.out.println(total);
	}

	static void load(Scanner scanner) {
		int n = scanner.nextInt();
		int[] A = new int[n];
		for (int i = 0; i < A.length; i++) {
			A[i] =  scanner.nextInt();
		}
		new Candies().solve(A);
	}

	public static void main(String[] args) throws Exception {
		Scanner scanner;
		if (args.length == 1 && "COHADAR".equals(args[0])) {
			scanner = new Scanner(new File("candies.in"));
		} else {
			scanner = new Scanner(System.in);
		}
		load(scanner);
	}
}
