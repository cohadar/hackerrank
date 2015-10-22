import java.util.*;
import java.io.*;

public class AngryProfessor {

	void solve(int[] A, int k) {
		int ontime = 0;
		for (int i = 0; i < A.length; i++) {
			if (A[i] <= 0) {
				ontime++;
			}
		}
		boolean canceled = ontime < k;
		System.out.println((canceled) ? "YES" : "NO");
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
			int k = scanner.nextInt();
			int[] A = loadArray(scanner, n);
			new AngryProfessor().solve(A, k);
		}
	}

	public static void main(String[] args) throws Exception {
		Scanner scanner;
		if (args.length == 1 && "COHADAR".equals(args[0])) {
			scanner = new Scanner(new File("angry-professor.in"));
		} else {
			scanner = new Scanner(System.in);
		}
		load(scanner);
	}
}
