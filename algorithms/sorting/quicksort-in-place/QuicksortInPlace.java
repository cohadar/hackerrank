import java.util.*;
import java.io.*;

public class QuicksortInPlace {

	void printlnA(Integer[] ar) {
		for (int i = 0; i < ar.length; i++) {
			System.out.print(ar[i] + " ");
		}
		System.out.println();
	}

	void swap(Integer[] ar, int a, int b) {
		int temp = ar[a];
		ar[a] = ar[b];
		ar[b] = temp;
	}

	int partition(Integer[] ar, int l, int r) {
		int piv = ar[r];
		int a = l;
		for (int b = l; b < r; b++) {
			if (ar[b] < piv) {
				swap(ar, a, b);
				a++;
			}
		}
		swap(ar, a, r);
		return a;
	}

	void qsort(Integer[] ar, int l, int r) {
		if (l < r) {
			int p = partition(ar, l , r);
			printlnA(ar);
			qsort(ar, l, p - 1);
			qsort(ar, p + 1, r);
		}
	}

	void solve(Integer[] ar) {
		qsort(ar, 0, ar.length - 1);
	}

	static void load(Scanner scanner) {
		int n = scanner.nextInt();
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			list.add(scanner.nextInt());
		}
		new QuicksortInPlace().solve(list.toArray(new Integer[]{}));
	}

	public static void main(String[] args) throws Exception {
		Scanner scanner;
		if (args.length == 1 && "COHADAR".equals(args[0])) {
			scanner = new Scanner(new File("quicksort-in-place.in"));
		} else {
			scanner = new Scanner(System.in);
		}
		load(scanner);
	}
}
