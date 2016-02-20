import java.util.*;
import java.io.*;

/**
  * @author Mighty Cohadar 
  */
public class FindThePermutation {

	final int n;
	final long k;
	
	public FindThePermutation(int n, long k) {
		this.n = n;
		this.k = k;
	}

	public String solve() {
		int[] P = firstPermutation(n);
		List<Integer> L = new ArrayList<>();
		int maxd = distance(P);
		int i = 1;
		L.add(i);
		while (nextPermutation(P)) {
			i++;
			int d = distance(P);
			if (maxd < d) {
				maxd = d;
				L.clear();
				L.add(i);

			} else if (maxd == d) {
				L.add(i);
			}
		}
		if (L.size() < k) {
			return "-1";
		}
		int x = L.get((int)k-1);
		P = firstPermutation(n);
		for (int j = 1; j != x; j++) {
			nextPermutation(P);	
		}
		return join(P, " ");
	}

	static String join(int[] A, String delimiter) {
		StringBuilder sb = new StringBuilder();
		for (int a : A) {
			sb.append(a);
			sb.append(delimiter);
		}
		if (sb.length() >= delimiter.length()) {
			sb.setLength(sb.length() - delimiter.length());
		}			
		return sb.toString();
	}

	public static int distance(int[] P) {
		if (P.length == 1) {
			return 0;
		}
		int d = Integer.MAX_VALUE;
		for (int i = 1; i < P.length; i++) {
			d = Math.min(d, Math.abs(P[i] - P[i-1]));
		}
		return d;
	}

	public int[] firstPermutation(int n) {
		int[] P = new int[n];
		for (int i = 1; i <= n; i++) {
			P[i-1] = i;
		}
		return P;
	}

	public boolean nextPermutation(int[] array) {
		// Find longest non-increasing suffix
		int i = array.length - 1;
		while (i > 0 && array[i - 1] >= array[i])
			i--;
		// Now i is the head index of the suffix
		
		// Are we at the last permutation already?
		if (i <= 0)
			return false;
		
		// Let array[i - 1] be the pivot
		// Find rightmost element that exceeds the pivot
		int j = array.length - 1;
		while (array[j] <= array[i - 1])
			j--;
		// Now the value array[j] will become the new pivot
		// Assertion: j >= i
		
		// Swap the pivot with j
		int temp = array[i - 1];
		array[i - 1] = array[j];
		array[j] = temp;
		
		// Reverse the suffix
		j = array.length - 1;
		while (i < j) {
			temp = array[i];
			array[i] = array[j];
			array[j] = temp;
			i++;
			j--;
		}
		
		// Successfully computed the next permutation
		return true;
	}		

	public static FindThePermutation load(Scanner scanner) {
		int n = scanner.nextInt();
		assert 1 <= n && n <= 1e6 : "out of range, n: " + n;
		long k = scanner.nextInt();
		assert 1 <= k && k <= 1e18 : "out of range, k: " + k;
		return new FindThePermutation(n, k);
	}	

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		assert 1 <= t && t <= 10 : "out of range, t: " + t;
		while (t-->0) {
			FindThePermutation o = FindThePermutation.load(scanner);
			System.out.println(o.solve());
		}
	}

	static boolean DEBUG = true;
	
	static void debug(Object...os) {
		if (!DEBUG) { return; }
		System.err.printf("%.65536s\n", Arrays.deepToString(os));
	}

}
