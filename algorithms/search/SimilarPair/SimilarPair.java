import java.util.*;
import java.io.*;

/* Mighty Cohadar */
public class SimilarPair {

	static long solve(Scanner scanner, int n, int t) {
		if (n == 1) {
			return 0;
		}
		int[] P = new int[n + 1]; // parent index array, inverted tree 
		int[] C = new int[n + 1]; // child count array
		for (int i = 0; i < n - 1; i++) {
			int parent = scanner.nextInt();
			int child = scanner.nextInt();
			P[child] = parent;
			C[parent]++;
		}
		long count = 0;
		ArrayDeque<Integer> L = new ArrayDeque<>(); // leaf nodes
		for (int i = 1; i < C.length; i++) {
			if (C[i] == 0) {
				L.add(i);
			}
		}
		while (!L.isEmpty()) {
			int child = L.remove();
			int ancestor = P[child];
			C[ancestor]--;
			if (C[ancestor] == 0) {
				L.add(ancestor);
			}
			while (ancestor != 0) {
				if (Math.abs(ancestor - child) <= t) {
					count++;
				}
				ancestor = P[ancestor];
			}
		}
		return count;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		assert 1 <= n && n <= 1e5 : "out of range, n: " + n;
		int t = scanner.nextInt();
		assert 0 <= t && t <= n : "out of range, t: " + t;
		System.out.println(solve(scanner, n, t));
	}

}

