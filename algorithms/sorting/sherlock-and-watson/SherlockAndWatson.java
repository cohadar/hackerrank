import java.util.*;
import java.io.*;

public class SherlockAndWatson {

	void solve(int k, int[] A, int[] Q) {
		int shift = -k % A.length + A.length;
		for (int i = 0; i < Q.length; i++) {
			int q = Q[i];
			System.out.println(A[(q + shift) % A.length]);
		}
	}

	static void load(Scanner scanner) {
		String[] params = scanner.nextLine().split(" ");
		int n = Integer.valueOf(params[0]);
		int k = Integer.valueOf(params[1]);
		int q = Integer.valueOf(params[2]);
		int[] A = new int[n];
		for (int i = 0; i < A.length; i++) {
			A[i] =  scanner.nextInt();
		}
		int[] Q = new int[q];
		for (int i = 0; i < Q.length; i++) {
			Q[i] =  scanner.nextInt();
		}
		new SherlockAndWatson().solve(k, A, Q);
	}

	public static void main(String[] args) throws Exception {
		Scanner scanner;
		if (args.length == 1 && "COHADAR".equals(args[0])) {
			scanner = new Scanner(new File("sherlock-and-watson.in"));
		} else {
			scanner = new Scanner(System.in);
		}
		load(scanner);
	}
}
