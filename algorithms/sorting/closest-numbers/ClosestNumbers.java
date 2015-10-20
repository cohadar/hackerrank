import java.util.*;
import java.io.*;

public class ClosestNumbers {

	void solve(int[] A) {
		if (A.length < 2) {
			return;
		}
		Arrays.sort(A);
		int mindiff = A[1] - A[0];
		for (int i = 2; i < A.length; i++) {
			int diff = A[i] - A[i - 1];
			if (diff < mindiff) {
				mindiff = diff;
			}
		}
		for (int i = 1; i < A.length; i++) {
			int diff = A[i] - A[i - 1];
			if (diff == mindiff) {
				System.out.printf("%d %d ", A[i - 1], A[i]);
			}
		}
	}

	static void load(Scanner scanner) {
		int n = scanner.nextInt();
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] =  scanner.nextInt();
		}
		new ClosestNumbers().solve(A);
	}

	public static void main(String[] args) throws Exception {
		Scanner scanner;
		if (args.length == 1 && "COHADAR".equals(args[0])) {
			scanner = new Scanner(new File("closest-numbers.in"));
		} else {
			scanner = new Scanner(System.in);
		}
		load(scanner);
	}
}
