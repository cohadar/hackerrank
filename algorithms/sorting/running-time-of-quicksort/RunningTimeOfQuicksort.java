import java.util.*;
import java.io.*;

public class RunningTimeOfQuicksort {

	int qsort_count;
	int isort_count;

	void insertionsort(Integer[] A) {
		for (int i = 1; i < A.length; i++) {
			int v = A[i];
			int j = i;
			while (j > 0 && A[j - 1] > v) {
				A[j] = A[j - 1];
				isort_count++;
				j--;
			}
			A[j] = v;
		}
	}

	void swap(Integer[] A, int l, int r) {
		int temp = A[l];
		A[l] = A[r];
		A[r] = temp;
		qsort_count++;
	}

	int partition(Integer[] A, int l, int r) {
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

	void quicksort(Integer[] A, int l, int r) {
		if (l < r) {
			int p = partition(A, l, r);
			quicksort(A, l, p - 1);
			quicksort(A, p + 1, r);
		}
	}

	int solve(Integer[] ar) {
		Integer[] br = Arrays.copyOf(ar, ar.length);
		quicksort(ar, 0, ar.length - 1);
		insertionsort(br);
		return isort_count - qsort_count;
	}

	static void load(Scanner scanner) {
		int N = scanner.nextInt();
		List<Integer> ar = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			ar.add(scanner.nextInt());
		}
		System.out.println(new RunningTimeOfQuicksort().solve(ar.toArray(new Integer[0])));
	}

	public static void main(String[] args) throws Exception {
		Scanner scanner;
		if (args.length == 1 && "COHADAR".equals(args[0])) {
			scanner = new Scanner(new File("running-time-of-quicksort.in"));
		} else {
			scanner = new Scanner(System.in);
		}
		load(scanner);
	}
}
