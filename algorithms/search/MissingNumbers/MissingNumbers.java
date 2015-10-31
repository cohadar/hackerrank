import java.util.*;
import java.io.*;

public class MissingNumbers {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int[] A = new int[n];
		for (int i = 0; i < A.length; i++) {
			A[i] = scanner.nextInt();
		}
		int m = scanner.nextInt();
		int[] B = new int[n];
		for (int i = 0; i < B.length; i++) {
			B[i] = scanner.nextInt();
		}
		debug(A);
		debug(B);
	}

	static void debug(Object...os) {
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}

