import java.util.*;
import java.io.*;

/**
  * Generate random tree 
  * @author Mighty Cohadar 
  */
public class Tree {

	static class PairWithRandom implements Comparable<PairWithRandom>{
		final int x;
		final double r;
		PairWithRandom(int x) {
			this.x = x;
			this.r = Math.random();
		}
		public String toString() {
			return String.format("(x=%d, r=%f)", x, r);
		}	
		public int compareTo(PairWithRandom that) {
			return Double.compare(this.r, that.r);
		}
	}

	static int[] generate(int n, int max_spann) {
		Random random = new Random();
		Queue<PairWithRandom> V = new PriorityQueue<>();
		Queue<PairWithRandom> L = new PriorityQueue<>();
		for (int i = 0; i < n; i++) {
			V.add(new PairWithRandom(i));
		}
		int[] P = new int[n];
		Arrays.fill(P, -1);
		L.add(V.remove()); // root
		while (!V.isEmpty()) {
			int remaining = V.size();
			int c = 1 + random.nextInt(Math.min(remaining, max_spann));
			int a = L.remove().x;
			List<Integer> new_leaves = new ArrayList<>();
			for (int i = 0; i < c; i++) {
				int b = V.remove().x;
				P[b] = a;
				new_leaves.add(b);
			}
			for (int l : new_leaves) {
				L.add(new PairWithRandom(l));
			}
		}
		return P;
	}

	static void print_graph(int[] P, boolean one_based) {
		for (int i = 0; i < P.length; i++) {
			if (P[i] >= 0) {
				int a = (one_based) ? P[i] + 1 : P[i];
				int b = (one_based) ? i + 1 : i;
				if (Math.random() < 0.5) {
					System.out.printf("%d %d\n", a, b);
				} else {
					System.out.printf("%d %d\n", b, a);
				}
			}
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		assert n > 0;
		int max_spann = scanner.nextInt();
		assert max_spann >= 1;
		boolean one_based = scanner.nextBoolean();
		int[] P = generate(n, max_spann);
		System.out.printf("%d %d\n", n, n - 1);
		print_graph(P, one_based);
	}

}
