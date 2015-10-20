import java.util.*;
import java.io.*;

public class FindTheMedian {

	void swap(int[] A, int l, int r) {
		int temp = A[l];
		A[l] = A[r];
		A[r] = temp;
	}

	int partition(int[] A, int l, int r) {
		int piv = A[r];
		int a = l;
		for (int b = l; b <= r; b++) {
			if (A[b] < piv) {
				swap(A, a, b);
				a++;
			}
		}
		swap(A, a, r);
		return a;
	}

	int solve(int[] A) {
		int l = 0;
		int r = A.length - 1;
		for (;;) {
			int p = partition(A, l, r);
			if (p == A.length / 2) {
				return A[p];
			}
			if (p < A.length / 2) {
				l = p + 1;
			} else {
				r = p - 1;
			}
			// System.out.printf("%d %d\n", l, r);
		}
	}

	static void load(Scanner scanner) {
		int n = scanner.nextInt();
		assert n % 2 == 1;
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] =  scanner.nextInt();
		}
		System.out.println(new FindTheMedian().solve(A));
	}

	public static void main(String[] args) throws Exception {
		Scanner scanner;
		if (args.length == 1 && "COHADAR".equals(args[0])) {
			scanner = new Scanner(new File("find-the-median.in"));
		} else {
			scanner = new Scanner(System.in);
		}
		load(scanner);
	}
}
