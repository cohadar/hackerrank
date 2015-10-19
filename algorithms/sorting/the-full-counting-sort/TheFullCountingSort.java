import java.util.*;
import java.io.*;

public class TheFullCountingSort {

	void solve(int[] A, String[] S) {
		StringBuilder[] C = new StringBuilder[100];
		for (int i = 0; i < C.length; i++) {
			C[i] = new StringBuilder();
		}
		for (int i = 0; i < A.length; i++) {
			C[A[i]].append(S[i]).append(" ");
		}
		for (int i = 0; i < C.length; i++) {
			System.out.print(C[i]);
		}
	}

	static void load(Scanner scanner) {
		int n = Integer.valueOf(scanner.nextLine());
		int[] A = new int[n];
		String[] S = new String[n];
		for (int i = 0; i < n; i++) {
			String temp[] = scanner.nextLine().split(" ");
			A[i] = Integer.valueOf(temp[0]);
			S[i] = (i < n/2) ? "-" : temp[1];
		}
		new TheFullCountingSort().solve(A, S);
	}

	static void maketest(int n) {
		System.out.println(n);
		Random random = new Random();
		for (int i = 0; i < n; i++) {
			int a = random.nextInt(100);
			String s = "" + ('a' + random.nextInt(26));
			System.out.printf("%d %s\n", a, s);
		}
	}

	/*
		java -cp . TheFullCountingSort < the-full-counting-sort.in
	*/
	public static void main(String[] args) throws Exception {
		// maketest(1000000);
		Scanner scanner;
		if (args.length == 1 && "COHADAR".equals(args[0])) {
			scanner = new Scanner(new File("the-full-counting-sort.in"));
		} else {
			scanner = new Scanner(System.in);
		}
		load(scanner);
	}
}
