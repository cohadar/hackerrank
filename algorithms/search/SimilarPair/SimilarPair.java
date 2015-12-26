import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class SimilarPair {
	int root;
	List<List<Integer>> G = new ArrayList<>();
	boolean[] V;
	int[] A;
	int t;
	int depth = 0;
	long count = 0;
	FenwickTree fw;
	int n;

	public SimilarPair(Scanner scanner, int n, int t) {
		for (int i = 0; i < n; i++) {
			G.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < n - 1; i++) {
			int a = scanner.nextInt() - 1;
			int b = scanner.nextInt() - 1;
			G.get(a).add(b);
			G.get(b).add(a);
			if (i == 0) {
				root = a;
			}
		}	
		this.t = t;	
		this.n = n;
		this.V = new boolean[n];
		this.A = new int[n];
		fw = new FenwickTree(n);
	}

	void dfs(int a) {
		try {
			fw.addValue(a + 1, 1);
			for (int b : G.get(a)) {
				if (!V[b]) {
					V[b] = true;
					count += fw.getRangeSum(Math.max(1, b + 1 - t), Math.min(n, b + 1 + t));
					dfs(b);
				}
			}
		} finally {
			depth--;
			fw.addValue(a + 1, -1);
		}
	}

	public long solve() {
		V[root] = true;
		dfs(root);
		return count;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		assert 1 <= n && n <= 1e5 : "out of range, n: " + n;
		int t = scanner.nextInt();
		assert 0 <= t && t <= n : "out of range, t: " + t;
		SimilarPair o = new SimilarPair(scanner, n, t);
		System.out.println(o.solve());
	}

}


/**
* All indexes in FenwickTree are 1-based.
*/
class FenwickTree {
	private final int[] add;
	private final int[] mul;
	private final int[] min;
	private final int[] max;
	private final int n;

	public FenwickTree(int n) {
		this.add = new int[1 + n];
		this.mul = new int[1 + n];
		this.min = new int[1 + n];
		this.max = new int[1 + n];
		Arrays.fill(this.min, Integer.MAX_VALUE);
		Arrays.fill(this.max, Integer.MIN_VALUE);
		this.n = n;
	}

	public FenwickTree(int[] A) {
		this(A.length);
		for (int i = 0; i < A.length; i++) {
			this.addValue(i + 1, A[i]);
		}
	}	

	private void internalUpdate(final int index, int factor, int delta) {
		assert index > 0 && index <= n;
		int oldMin = getMin(index);
		int oldMax = getMax(index);
		for (int i = index; i <= n; i += (i & -i)) {
			mul[i] += factor;
			add[i] += delta;
		}
		int newValue = getValue(index);
		if (newValue < oldMin) {
			for (int i = index; i <= n; i += (i & -i)) {
				min[i] = newValue;
			}
		}
		if (newValue > oldMax) {
			for (int i = index; i <= n; i += (i & -i)) {
				max[i] = newValue;
			}
		}
	}

	// @return sum(A[1]..A[index])
	public int getSum(final int index) {
		assert index >= 0 && index <= n;
		int sum = 0;
		int fact = 0;
		for (int i = index; i > 0; i -= (i & -i)) {
			sum += add[i];
			fact += mul[i];
		}
		return fact * index + sum;		
	}

	// @return min(A[1]..A[index])
	public int getMin(final int index) {
		assert index > 0 && index <= n;
		int m = min[index];
		for (int i = index - (index & -index); i > 0; i -= (i & -i)) {
			m = Math.min(m, min[i]);
		}
		return m;
	}	

	// @return max(A[1]..A[index])
	public int getMax(final int index) {
		assert index > 0 && index <= n;
		int m = max[index];
		for (int i = index - (index & -index); i > 0; i -= (i & -i)) {
			m = Math.max(m, max[i]);
		}
		return m;
	}		

	// @return sum(A[low]..A[high])
	public int getRangeSum(int low, int high) {
		assert low <= high;
		return getSum(high) - getSum(low - 1);
	}

	// @return A[index]
	public int getValue(int index) {
		assert index > 0 && index <= n;
		return getSum(index) - getSum(index - 1);
	}	

	// A[index] += value
	public void addValue(int index, int value) {
		internalUpdate(index, 0, value);
	}	

	// for (int i = low; i <= high; i++) A[i] += value
	public void addValueToRange(int low, int high, int value) {
		internalUpdate(low, value, -value * (low - 1));
		internalUpdate(high, -value, value * high); 
	}

	// @return min index, where getSum(index) > sum
	int findHigherSumIndex(int sum) {
		int low = 1;
		int high = n + 1;
		while (low < high) {
			int mid = (low + high) >>> 1;
			int midSum = getSum(mid);
			if (midSum <= sum) {
				low = mid + 1;
			} else {
				high = mid;
			}
		}
		return high;
	}	

	// @return A[]
	public int[] toArray() {
		int[] A = new int[n];
		int cumul = 0;
		for (int i = 0; i < A.length; i++) {
			A[i] = this.getSum(i + 1) - cumul;
			cumul += A[i];
		}
		return A;
	}

}	
