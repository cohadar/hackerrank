import java.util.*;
import java.io.*;

public class TheFullCountingSort {

	void solve(Integer[] A, String[] S) {
		Integer[] C = new Integer[100];
		Arrays.fill(C, 0);
		for (int i = 0; i < A.length; i++) {
			C[A[i]]++;
		}
		int cumul = 0;
		for (int i = 0; i < C.length; i++) {
			cumul += C[i];
			C[i] = cumul - C[i];
		}
		String[] sorted = new String[S.length];
		for (int i = 0; i < S.length; i++) {
			sorted[C[A[i]]++] = S[i];
		}
		// for (int i = 0; i < sorted.length; i++) {
		// 	System.out.print(sorted[i] + " ");
		// }
		//System.out.println(String.join(" ", sorted));
	}

	static void load(Scanner scanner) {
		int n = Integer.valueOf(scanner.nextLine());
		Integer[] A = new Integer[n];
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
