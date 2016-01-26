import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class StonePiles {

	final int n;
	final int[] A; // starting piles
	
	StonePiles(int n, int[] A) {
		this.n = n;
		this.A = A;
	}
	
	boolean solve(Boolean[] W) {
		int numWin = 0;
		for (int i = 0; i < A.length; i++) {
			if (W[A[i]]) {
				numWin++;
			}
		}
		return (numWin % 2 != 0);
	}

	static StonePiles load(Scanner scanner) {
		int n = scanner.nextInt();
		assert 1 <= n && n <= 50 : "out of range, n: " + n;
		int[] A = scanArray(scanner, n);
		return new StonePiles(n, A);
	}	

	static Boolean[] calcWinningNumbers(int n) {
		Boolean[] W = new Boolean[1 + n];
		Arrays.fill(W, true);
		W[0] = false;
		W[1] = false;
		W[2] = false;
		W[4] = false;
		W[8] = false;
		return W;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		Boolean[] W = calcWinningNumbers(50);
		assert 1 <= t && t <= 50 : "out of range, t: " + t;
		while (t-->0) {
			StonePiles o = StonePiles.load(scanner);
			System.out.println((o.solve(W)) ? "ALICE" : "BOB");
		}
	}

	static int[] scanArray(Scanner scanner, int n) {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = scanner.nextInt();
			assert 1 <= A[i] && A[i] <= 50 : "out of range, A[i]: " + A[i];
		}
		return A;
	}

}
