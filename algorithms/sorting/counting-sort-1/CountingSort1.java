import java.util.*;
import java.io.*;

public class CountingSort1 {

	Integer[] solve(Integer[] A) {
		Integer C[] = new Integer[100];
		Arrays.fill(C, 0);
		for (int i = 0; i < A.length; i++) {
			C[A[i]]++;
		}
		return C;
	}

	static void load(Scanner scanner) {
		int n = scanner.nextInt();
		Integer[] A = new Integer[n];
		for (int i = 0; i < n; i++) {
			A[i] = scanner.nextInt();
		}
		Integer[] C = new CountingSort1().solve(A);
		for (int c : C) {
			System.out.printf("%d ", c);
		}
	}

	public static void main(String[] args) throws Exception {
		Scanner scanner;
		if (args.length == 1 && "COHADAR".equals(args[0])) {
			scanner = new Scanner(new File("counting-sort-1.in"));
		} else {
			scanner = new Scanner(System.in);
		}
		load(scanner);
	}
}
