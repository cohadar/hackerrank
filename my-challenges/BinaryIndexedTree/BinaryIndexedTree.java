import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class BinaryIndexedTree {

	static int left(int i) {
		return i - (i & -i);  // i & (i - 1)
	}

	static int right(int i) {
		return i + (i & -i);
	}

	static void pointUpdateA(int[] A, int index, int delta) {
		A[index] += delta;
	}

	static void rangeUpdateA(int[] A, int low, int high, int delta) {
		for (int i = low; i <= high; i++) {
			A[i] += delta;	
		}
	}

	static int rangeGetA(int[] A, int index) {
		int sum = 0;
		for (int i = 0; i <= index; i++) {
			sum += A[i];
		}
		return sum;
	}	

	static void pointUpdateB1(int[] B, int index, int delta) {
		assert index > 0;
		while (index < B.length) {
			B[index] += delta;
			index = right(index);	
		}
	}	

	// returns element A or cumul A depending on update use
	static int query(int[] B, int index) {
		int sum = 0;
		while (index > 0) {
			sum += B[index];
			index = left(index);
		}
		return sum;
	}		

	static void rangeUpdateB2(int[] B, int low, int high, int delta) {
		pointUpdateB1(B, low, delta);
		pointUpdateB1(B, high + 1, -delta);
		// for (int i = low; i <= high; i++) {
		// 	pointUpdateB1(B, i, delta);
		// }
	}		

	static int pointGetB2(int[] B, int index) {
		return query(B, index) - query(B, index - 1);
	}	

	static int[] makeBIT(int[] A) {
		int[] B = new int[1 + A.length];
		for (int i = 0, cumul = 0; i < A.length; i++) {
			cumul += A[i];
			if ((i + 1) % 2 == 0) {
				B[i + 1] = cumul - query(B, left(i + 1));	
			} else {
				B[i + 1] = A[i];
			}
			int a = rangeGetA(A, i);
			int b = query(B, i + 1);
			assert rangeGetA(A, i) == query(B, i + 1) : String.format("%d: %d <> %d", i, a, b);
		}		
		return B;
	}

	public static void main(String[] args) {
		Random random = new Random();
		int n = 10000;
		int[] A = randomArray(random, n);
		int[] B1 = makeBIT(A);
		int[] B2 = makeBIT(A);
		for (int i = 0; i < 1000; i++) {
			int j = random.nextInt(n);
			int v = random.nextInt(i + 1);
			pointUpdateA(A, j, v);
			pointUpdateB1(B1, j + 1, v);
			// rangeUpdateA(A, 0, j, v);
			// rangeUpdateB2(B2, 1, j + 1, v);
			int k = random.nextInt(n);
			int a = rangeGetA(A, k);
			int b = query(B1, k + 1);
			// int a = A[k];
			// int b = query(B2, k + 1);
			assert a == b : String.format("%d: %d <> %d", i, a, b);
		}
	}

	static int[] randomArray(Random random, int n) {
		int[] A = new int[n];
		for (int i = 0; i < A.length; i++) {
			A[i] = random.nextInt(1000);
		}
		return A;
	}

	static boolean DEBUG = true;
	
	static void debug(Object...os) {
		if (!DEBUG) { return; }
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}

