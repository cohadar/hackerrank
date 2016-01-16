import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class Billboards {

	static class Rec implements Comparable<Rec> {
		final int value;
		final int index;
		Rec(int value, int index) {
			this.value = value;
			this.index = index;
		}
		public int compareTo(Rec that) {
			if (this.value == that.value) {
				return -Integer.compare(this.index, that.index);
			} else {
				return Integer.compare(this.value, that.value);
			}
		}
		public String toString() {
			return String.format("(value=%d, index=%d)", value, index);
		}	
	}

	final int[] A;
	final int k;
	final int n;
	final long[] F;

	Billboards(int[] A, int k) {
		this.A = A;
		this.n = A.length;
		this.k = k;
		this.F = new long[1 + n];		
	}

	long solve2() {
		long[] F = new long[1 + n];
		for (int i = k; i < A.length; i++) {
			long min = A[i - k] + F[i - k];
			for (int j = i - k + 1; j <= i; j++) {
				min = Math.min(min, A[j] + F[j]);
			}
			F[i + 1] = min;
		}
		return sum(A) - F[n];
	}

	long solve3() {
		long[] F = new long[1 + n];
		F[k + 1] = min(A, k + 1);
		PriorityQueue<Long> Q = new PriorityQueue<>();
		for (int i = 0; i <= k; i++) {
			Q.add(A[i] + F[i]);
		}
		for (int i = k; i < A.length - 1; i++) {
			F[i + 1] = Q.peek();
			Q.remove(A[i - k] + F[i - k]);
			Q.add(A[i + 1] + F[i + 1]);
		}
		F[n] = Q.peek();
		return sum(A) - F[n];
	}

    long g(int i) {
        return F[i] + A[i];
    }	

    void monoAppend(Deque<Integer> Q, int index) {
		while (!Q.isEmpty() && g(index) <= g(Q.peekLast())) {
			Q.pollLast();
		}
		Q.addLast(index);
    }

    Integer monoGet(Deque<Integer> Q, int border) {
    	while (!Q.isEmpty() && Q.peekFirst() < border) {
    		Q.pollFirst();
    	}
    	return Q.peekFirst();
    }

	long solve() {
		Deque<Integer> Q = new ArrayDeque<>();
		for (int i = 0; i < k; i++) {
			monoAppend(Q, i);
		}
		for (int i = k; i < n; i++) {
			monoAppend(Q, i);
			F[i + 1] = g(monoGet(Q, i - k));
		}
        return sum(A) - F[n];		
	}

	static Billboards load(Scanner scanner) {
		int n = scanner.nextInt();
		int k = scanner.nextInt();
		assert 1 <= n && n <= 1e5 : "out of range, n: " + n;
		assert 1 <= k && k <= n : "out of range, k: " + k;
		int[] A = scanArray(scanner, n);
		return new Billboards(A, k);
	}	

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Billboards o = Billboards.load(scanner);
		System.out.println(o.solve());
	}

	static int[] scanArray(Scanner scanner, int n) {
		int[] A = new int[n];
		for (int i = 0; i < n; i++) {
			A[i] = scanner.nextInt();
			assert 0 <= A[i] && A[i] <= 2e9 : "out of range, A[i]: " + A[i];
		}
		return A;
	}

	static int min(int[] A, int len) {
		int m = Integer.MAX_VALUE;
		for (int i = 0; i < len; i++) {
			m = Math.min(m, A[i]);
		}
		return m;
	}	

	static long sum(int[] A) {
		long s = 0;
		for (int i = 0; i < A.length; i++) {
			s += A[i];
		}
		return s;
	}	

	static boolean DEBUG = true;
	
	static void debug(Object...os) {
		if (!DEBUG) { return; }
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}
