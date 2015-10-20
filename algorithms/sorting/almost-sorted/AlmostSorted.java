import java.util.*;
import java.io.*;

public class AlmostSorted {

	int indexL, indexR;

	void swap(int[] A, int l, int r) {
		int temp = A[l];
		A[l] = A[r];
		A[r] = temp;
	}

	boolean isSorted(int[] A) {
		for (int i = 1; i < A.length; i++) {
			if (A[i - 1] > A[i]) {
				return false;
			}
		}
		return true;
	}

	boolean canSwap(int[] A) {
		boolean used = false;
		for (int i = 1; i < A.length; i++) {
			if (A[i - 1] > A[i]) {
				indexL = i;
			}
		}
		for (int i = A.length - 1; i > 0; i--) {
			if (A[i - 1] > A[i]) {
				indexR = i - 1;
			}
		}
		if (indexL > indexR) {
			int temp = indexL;
			indexL = indexR;
			indexR = temp;
		}
		swap(A, indexL, indexR);
		return isSorted(A);
	}

	boolean canReverse(int[] A) {
		indexL = -1;
		for (int i = 1; i < A.length; i++) {
			if (A[i - 1] > A[i]) {
				indexL = i - 1;
				break;
			}
		}
		assert indexL != -1;
		indexR = indexL + 1;
		while (indexR < A.length && A[indexR - 1] >= A[indexR]) {
			indexR++;
		}
		indexR--;
		swap(A, indexL, indexR);
		if (indexL > 0) {
			if (A[indexL - 1] > A[indexL]) {
				return false;
			}
		}
		for (int i = indexR + 1; i < A.length; i++) {
			if (A[i - 1] > A[i]) {
				return false;
			}
		}
		return true;
	}

	void solve(int[] A) {
		if (isSorted(A)) {
			System.out.println("yes");
			return;
		}
		if (canSwap(Arrays.copyOf(A, A.length))) {
			System.out.println("yes");
			System.out.printf("swap %d %d", indexL + 1, indexR + 1);
			return;
		}
		if (canReverse(A)) {
			System.out.println("yes");
			System.out.printf("reverse %d %d", indexL + 1, indexR + 1);
			return;
		}
		System.out.println("no");
	}

	static void load(Scanner scanner) {
		int n = scanner.nextInt();
		int[] A = new int[n];
		for (int i = 0; i < A.length; i++) {
			A[i] =  scanner.nextInt();
		}
		new AlmostSorted().solve(A);
	}

	public static void main(String[] args) throws Exception {
		Scanner scanner;
		if (args.length == 1 && "COHADAR".equals(args[0])) {
			scanner = new Scanner(new File("almost-sorted.in"));
		} else {
			scanner = new Scanner(System.in);
		}
		load(scanner);
	}
}
