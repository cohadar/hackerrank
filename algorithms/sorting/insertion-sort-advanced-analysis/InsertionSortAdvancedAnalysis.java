import java.util.*;
import java.io.*;

public class InsertionSortAdvancedAnalysis {

	long total;

	void merge(int[] A, int l, int m, int r) {
		int[] C = Arrays.copyOfRange(A, l, r + 1);
		int a = l;
		int b = m + 1;
		int k = l;
		while (a <= m && b <= r) {
			if (C[a - l] <= C[b - l]) {
				A[k++] = C[a++ - l];
			} else {
				assert b >= k;
				total += (b - k);
				A[k++] = C[b++ - l];
			}
		}
		while (a <= m) {
			// no total here, these are floaters
			A[k++] = C[a++ - l];
		}
	}

	void mergeSort(int[] A, int l, int r) {
		if (l < r) {
			int m = (l + r) >>> 1;
			mergeSort(A, l, m);
			mergeSort(A, m + 1, r);
			merge(A, l, m, r);
		}
	}

	static void load(Scanner scanner) {
		int t = scanner.nextInt();
		for (int c = 0; c < t; c++) {
			int n = scanner.nextInt();
			int[] A = new int[n];
			for (int i = 0; i < A.length; i++) {
				A[i] = scanner.nextInt();
			}
			InsertionSortAdvancedAnalysis instance = new InsertionSortAdvancedAnalysis();
			instance.mergeSort(A, 0, A.length - 1);
			System.err.println(Arrays.toString(A));
			System.out.println(instance.total);
		}
	}

	static void makeInput(int t, int n, int maxVal) {
		Random random = new Random();
		System.out.println(t);
		for (int it = 0; it < t; it++) {
			System.out.println(n);
			int[] A = new int[n];
			for (int i = 0; i < A.length; i++) {
				A[i] = 1 + random.nextInt(maxVal);
			}
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < A.length; i++) {
				builder.append(String.valueOf(A[i]));
				builder.append(" ");
			}
			System.out.println(builder);
		}
	}

	public static void main(String[] args) throws Exception {
		// makeInput(15, 100000, 10000000);
		Scanner scanner;
		if (args.length == 1 && "COHADAR".equals(args[0])) {
			scanner = new Scanner(new File("insertion-sort-advanced-analysis.in"));
		} else {
			scanner = new Scanner(System.in);
		}
		load(scanner);
	}
}
