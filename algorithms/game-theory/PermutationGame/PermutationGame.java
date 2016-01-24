import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class PermutationGame {

	final int n;
	final int[] A;
	
	PermutationGame(int[] A) {
		this.n = A.length;
		this.A = A;
	}

	boolean isIncreasing(int[] B) {
		for (int i = 1; i < B.length; i++) {
			if (B[i - 1] > B[i]) {
				return false;
			}
		}
		return true;
	}

	static int[] remove(int[] B, int k) {
		int[] R = new int[B.length - 1];
		for (int i = 0, j = 0; i < B.length; i++) {
			if (i != k) {
				R[j++] = B[i];
			}
		}
		return R;
	}
	
	boolean solve(int[] B) {
		if (isIncreasing(B)) {
			return false;
		}
		for (int i = 0; i < B.length; i++) {
			if (solve(remove(B, i)) == false) {
				return true;
			}
		}
		return false;
	}

	boolean solve() {
		return solve(A);
	}

	static PermutationGame load(Scanner scanner) {
		int n = scanner.nextInt();
		assert 2 <= n && n <= 15 : "out of range, n: " + n;
		int[] A = scanArray(scanner, n);
		return new PermutationGame(A);
	}	

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		assert 1 <= t && t <= 100 : "out of range, t: " + t;
		while (t-->0) {
			PermutationGame o = PermutationGame.load(scanner);
			System.out.println((o.solve()) ? "Alice" : "Bob");
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
