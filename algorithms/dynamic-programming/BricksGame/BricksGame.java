import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class BricksGame {

	static class Ret {
		final long sum;
		final int index;
		Ret(long sum, int index) {
			this.sum = sum;
			this.index = index;
		}
		public String toString() {
			return String.format("(sum=%d, index=%d)", sum, index);
		}	
	}

	final int n;
	final int[] A;
	final Ret[][] D;
	
	public BricksGame(int[] A) {
		this.n = A.length;
		this.A = A;
		this.D = new Ret[2][n + 1];
	}

	long sumA(int l, int r) {
		long sum = 0;
		for (int i = l; i <= r; i++) {
			sum += A[i];
		}
		return sum;
	}

	Ret value(Ret prev, int b, int k, int l) {
		long sum = 0;
		sum += sumA(k - l - 1, k - 1);
		Ret second = rec(1 - b, k - l - 1);
		int j = second.index;
		if ((prev == null) || (sum + rec(b, j).sum > prev.sum)) {
			return new Ret(sum + rec(b, j).sum, k - l - 1);
		}
		return prev;
	}

	Ret rec(int b, int k) {
		if (D[b][k] != null) {
			return D[b][k];
		}
		if (k <= 3) {
			D[b][k] = new Ret(sumA(0, k - 1), 0);
			return D[b][k];
		}
		for (int l = 0; l < 3; l++) {
			D[b][k] = value(D[b][k], b, k, l);
		}
		return D[b][k];
	}

	long solve() {
		return rec(0, n).sum;
	}

	static void swap(int[] A, int i, int j) {
		int t = A[i];
		A[i] = A[j];
		A[j] = t;
	}

	static void reverse(int[] A) {
		for (int i = 0; i < A.length / 2; i++) {
			swap(A, i, A.length - i - 1);
		}
	}

	static BricksGame load(Scanner scanner) {
		int n = scanner.nextInt();
		assert 1 <= n && n <= 1e5 : "out of range, n: " + n;
		int[] A = scanArray(scanner, n);
		reverse(A);
		return new BricksGame(A);
	}	

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = Integer.valueOf(scanner.nextLine());
		assert 1 <= t && t <= 5 : "out of range, t: " + t;
		while (t-->0) {
			BricksGame o = BricksGame.load(scanner);
			System.out.println(o.solve());
		}
	}

	static int[] scanArray(Scanner scanner, int n) {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = scanner.nextInt();
		}
		return A;
	}

}
